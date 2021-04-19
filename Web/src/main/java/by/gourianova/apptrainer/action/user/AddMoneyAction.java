package by.gourianova.apptrainer.action.user;

import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.entity.Role;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.RoleService;
import by.gourianova.apptrainer.service.UserService;
import by.gourianova.apptrainer.util.PageConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import static by.gourianova.apptrainer.util.PageConstant.ONE_USER;

//import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class AddMoneyAction implements Action {
    private final static String USER_BALANCE = "balance";
    //  private final static String USER = "user";
    private final static String USER_ID = "userId";
    private final static String MESSAGE = "message";
    private final static String USER = "userOne";
    private final static String ROLES_LIST = "rolesList";
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        //  User user = (User) request.getSession().getAttribute(USER);
        BigDecimal balance = new BigDecimal(request.getParameter(USER_BALANCE));
        int userId = Integer.parseInt(request.getParameter(USER_ID));
        try {
            User user = userService.findUserById(userId);
            user = userService.updateBalance(user, balance);
            request.getSession().setAttribute(USER, user);
      //      ArrayList<Role> rolesList;
       //     rolesList = roleService.findAll();
        //    request.setAttribute(ROLES_LIST, rolesList);
            userService.updateUser(user);
            ArrayList<Role> rolesList;
            rolesList = roleService.findAll();
            request.setAttribute(ROLES_LIST, rolesList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ONE_USER);
            requestDispatcher.forward(request, response);
            router.setPagePath(ONE_USER);
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
