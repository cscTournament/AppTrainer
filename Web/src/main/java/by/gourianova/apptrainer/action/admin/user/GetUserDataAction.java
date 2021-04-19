package by.gourianova.apptrainer.action.admin.user;

import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.entity.Role;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.RoleService;
import by.gourianova.apptrainer.service.UserService;
import by.gourianova.apptrainer.util.PageConstant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class GetUserDataAction implements Action {
    private final static String USERS_LIST = "usersList";
    private final static String ROLES_LIST = "rolesList";
    private final static String MESSAGE = "message";
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<User> usersList;
        ArrayList<Role> rolesList;
        try {
            usersList = userService.findAll();
            rolesList = roleService.findAll();
            request.setAttribute(USERS_LIST, usersList);
            request.setAttribute(ROLES_LIST, rolesList);
            router.setPagePath(PageConstant.UPDATE_USER);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
