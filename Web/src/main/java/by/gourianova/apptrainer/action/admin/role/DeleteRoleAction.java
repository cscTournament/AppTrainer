package by.gourianova.apptrainer.action.admin.role;

import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.RoleService;
import by.gourianova.apptrainer.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRoleAction implements Action {
    private final static String ROLE_ID = "roleId";
    private final static String MESSAGE = "message";
    private final static String ADMIN_PAGE = "/controller?action=show_admin_page";
    private RoleService roleService = new RoleService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        int id;
        try {
            id = Integer.parseInt(request.getParameter(ROLE_ID));
            roleService.deleteEntityById(id);
            router.setPagePath(ADMIN_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}