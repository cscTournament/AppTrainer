package by.gourianova.apptrainer.action.admin.app;

import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.AppService;
import by.gourianova.apptrainer.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ShowAllAppsByPageAction implements Action {

    private final static int PAGE_CAPACITY = 7;
    private final static String PAGE = "page";
    private final static String LEFT_PAGE = "leftPage";
    private final static String RIGHT_PAGE = "rightPage";
    private final static String LEFT_PAGE_CLASS = "leftPageClass";
    private final static String RIGHT_PAGE_CLASS = "rightPageClass";
    private final static String GO_TO_LEFT_PAGE = "controller?action=show_apps_page&page=";
    private final static String GO_TO_RIGHT_PAGE = "controller?action=show_apps_page&page=";
    private final static String DISABLED_BUTTON = " disabled";
    private final static String NOT_ACTION = "";
    private final static String USER = "user";
    private final static String APPS_LIST = "appsList";
    private final static String MESSAGE = "message";
    private int pageNumber = 1;
    private AppService appService = new AppService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        String leftPage;
        String leftPageClass;
        String rightPage;
        String rightPageClass;
        ArrayList<App> appsList;
        if (request.getParameter(PAGE) != null) {
            pageNumber = Integer.parseInt(request.getParameter(PAGE));
        }
        try {
            appsList = appService.findAllByPage(PAGE_CAPACITY, pageNumber);
            int appCount = appService.findAll().size();
            if (pageNumber > 1) {
                leftPage = GO_TO_LEFT_PAGE + (pageNumber - 1);
                leftPageClass = NOT_ACTION;
            } else {
                leftPage = NOT_ACTION;
                leftPageClass = DISABLED_BUTTON;
            }
            if (appCount >= pageNumber * PAGE_CAPACITY) {
                rightPage = GO_TO_RIGHT_PAGE + (pageNumber + 1);
                rightPageClass = NOT_ACTION;
            } else {
                rightPage = NOT_ACTION;
                rightPageClass = DISABLED_BUTTON;
            }
            User user = (User) request.getSession().getAttribute(USER);
            request.setAttribute(USER, user);
            request.setAttribute(APPS_LIST, appsList);
            request.setAttribute(LEFT_PAGE, leftPage);
            request.setAttribute(LEFT_PAGE_CLASS, leftPageClass);
            request.setAttribute(RIGHT_PAGE, rightPage);
            request.setAttribute(RIGHT_PAGE_CLASS, rightPageClass);
            router.setPagePath(PageConstant.MAIN_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
