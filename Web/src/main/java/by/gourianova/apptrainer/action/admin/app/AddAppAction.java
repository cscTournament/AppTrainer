package by.gourianova.apptrainer.action.admin.app;

import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.AppService;
import by.gourianova.apptrainer.util.PageConstant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AddAppAction implements Action {
    private final static String TYPE_ID = "typeId";
    private final static String HTTPADDRESS_ID = "httpAddressId";
    private final static String TITLE = "title";
    private final static String MESSAGE = "message";
    private AppService appService = new AppService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        App app = new App();
        try {
            app.setTypeId(Integer.parseInt(request.getParameter(TYPE_ID)));
            app.setHttpAddressesId(Integer.parseInt(request.getParameter(HTTPADDRESS_ID)));
            app.setTitle(request.getParameter(TITLE));
            appService.createApp(app);
            router.setPagePath(PageConstant.ADMIN_PAGE);
          //  router.setPagePath(PageConstant.FIRST_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
