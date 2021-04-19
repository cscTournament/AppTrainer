package by.gourianova.apptrainer.action.admin.httpaddress;

import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.entity.HttpAddress;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.HttpAddressService;
import by.gourianova.apptrainer.util.PageConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ShowAllHttpAddressesAction implements Action {
    private final static String HTTPADDRESSES_LIST = "httpAddressesList";
    private final static String MESSAGE = "message";
    private HttpAddressService httpAddressService = new HttpAddressService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        try {
            ArrayList<HttpAddress> httpAddressesList = httpAddressService.findAll();
            request.setAttribute(HTTPADDRESSES_LIST, httpAddressesList);
            router.setPagePath(PageConstant.ALL_HTTPADDRESSES);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}

