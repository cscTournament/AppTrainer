package by.gourianova.apptrainer.action.app;

import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.AppService;
import by.gourianova.apptrainer.service.UserService;
import by.gourianova.apptrainer.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class RentAppAction implements Action {
   // private final static String APPS = "appsList";
    private final static String APP_ID = "appId";
    private final static String APP = "app";
    private final static String USER = "user";
    private final static String MESSAGE = "message";
    private AppService appService = new AppService();
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            User user = (User) request.getSession().getAttribute(USER);

            System.out.println(user.toString());


            if (user != null && user.getBalance().intValue() > 0){
                System.out.println("+ "+request.getParameter(APP_ID)+" appID");

       App  app = appService.rentApp(Integer.parseInt(request.getParameter(APP_ID)), user.getId());
//System.out.println(app.toString()+"app in RentAppAction");
                request.getSession().setAttribute(APP, app);
                User updateUser = userService.findUserById(user.getId());
                request.getSession().setAttribute(USER, updateUser);
                 router.setPagePath(PageConstant.RENT_APP);
                router.setRoute(Router.RouteType.REDIRECT);
            }   else {
                router.setPagePath(PageConstant.ADD_MONEY);
            }
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
