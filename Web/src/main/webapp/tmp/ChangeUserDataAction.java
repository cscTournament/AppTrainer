package by.gourianova.apptrainer.action.admin.user;

import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.UserService;
import by.gourianova.apptrainer.util.MD5;
import by.gourianova.apptrainer.util.PageConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


public class ChangeUserDataAction implements Action {
//    private final static String LOGIN = "login";
 //   private final static String PASSWORD = "password";
    private final static String MESSAGE = "message";
    private final static String USER_ID = "userId";

    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        User user = new User();
        try {
            user.setId(Integer.parseInt(request.getParameter(USER_ID)));
            user.setLogin(request.getParameter(LOGIN));
            String password=request.getParameter(PASSWORD);
            if (password!=null)user.setPassword(password);

            String login = request.getParameter(LOGIN);
            password = request.getParameter(PASSWORD);

            userService.updateUser(user);
            router.setPagePath(PageConstant.FIRST_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String MESSAGE = "message";
    private final static String USER_ID = "userId";
    //  private UserService userService = new UserServiceImpl();
    User user = new User();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String password;
        log.println(request.getAttribute(USER_ID) + " id");
        int id = 0;
        if (request.getParameter(USER_ID) != null) {
            id = Integer.parseInt(request.getParameter(USER_ID));
        }

        String login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);
        password = MD5.md5Encode(password);
        UserChangeInfo loginInfo = new UserChangeInfo(id, login, password);
        UserService userService = new UserServiceImpl();
        User user = null;
        try {
            if (userService.updateUserData(loginInfo)) {
                log.println("password have changed OK");
                request.setAttribute(MESSAGE, "OK, password have changed ");
            } else {
                log.println("password have NOT changed");
                request.setAttribute(MESSAGE, "Couldn't changed password login&password are not unique");
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ONE_USER);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            e.printStackTrace();
        }
    }
