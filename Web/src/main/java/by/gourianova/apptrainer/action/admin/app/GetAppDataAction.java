package by.gourianova.apptrainer.action.admin.app;

import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.entity.AppType;
import by.gourianova.apptrainer.entity.HttpAddress;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.AppTypeService;
import by.gourianova.apptrainer.service.HttpAddressService;
import by.gourianova.apptrainer.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;



public class GetAppDataAction implements Action {
    private final static String TYPES_LIST = "typesList";
    private final static String HTTPADDRESSES_LIST = "httpAddressesList";
    private final static String MESSAGE = "message";
    private AppTypeService appTypeService = new AppTypeService();
    private HttpAddressService httpAddressService = new HttpAddressService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<AppType> typesList;
        ArrayList<HttpAddress> httpAddressesList;
        try {
            typesList = appTypeService.findAll();
            httpAddressesList = httpAddressService.findAll();
            request.setAttribute(TYPES_LIST, typesList);
            request.setAttribute(HTTPADDRESSES_LIST, httpAddressesList);
            router.setPagePath(PageConstant.ADD_APP);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
