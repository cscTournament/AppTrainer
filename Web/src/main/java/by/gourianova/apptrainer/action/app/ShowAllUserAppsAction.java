package by.gourianova.apptrainer.action.app;

import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.util.PageConstant;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.AppService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class ShowAllUserAppsAction implements Action {
    private final static String APPS_LIST = "appsList";
    private final static String MESSAGE = "message";
    private AppService appService = new AppService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<App> appsList;
        try {
            appsList = appService.findAll();
            request.setAttribute(APPS_LIST, appsList);
            router.setPagePath(PageConstant.MAIN_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
