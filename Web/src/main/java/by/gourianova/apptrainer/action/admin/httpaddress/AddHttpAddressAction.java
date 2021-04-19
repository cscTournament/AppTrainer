package by.gourianova.apptrainer.action.admin.httpaddress;

import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.entity.HttpAddress;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.HttpAddressService;
import by.gourianova.apptrainer.util.PageConstant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddHttpAddressAction implements Action {
    private final static String HTTPADDRESS_WEB_SHOP = "web_shop";
    private final static String HTTPADDRESS_LOCATION = "location";
    private final static String MESSAGE = "message";
    private final static String ADMIN_PAGE="/controller?action=show_admin_page";
    private HttpAddressService httpAddressService = new HttpAddressService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
       HttpAddress httpAddress = new HttpAddress();
        try {

            httpAddress.setWeb_shop(request.getParameter(HTTPADDRESS_WEB_SHOP));
            httpAddress.setLocation(request.getParameter(HTTPADDRESS_LOCATION));
            httpAddressService.createHttpAddress(httpAddress);
            router.setPagePath(ADMIN_PAGE);
       //     router.setPagePath(PageConstant.FIRST_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}