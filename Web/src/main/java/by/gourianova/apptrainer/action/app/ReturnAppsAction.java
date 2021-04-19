package by.gourianova.apptrainer.action.app;

import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.entity.Order;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.OrderService;
import by.gourianova.apptrainer.service.UserService;
import by.gourianova.apptrainer.util.PageConstant;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReturnAppsAction implements Action {
    private final static String USER = "user";
    private final static String ORDER = "order";
    private final static String MESSAGE = "message";
    private OrderService orderService = new OrderService();
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            User user = (User) request.getSession().getAttribute(USER);
            if (orderService.checkBalance(user)) {
                orderService.closeOrder(user);
                User updateUser = userService.findUserById(user.getId());
                Order order = orderService.findEntityById(user.getId());
                request.getSession().setAttribute(ORDER, order);
                request.getSession().setAttribute(USER, updateUser);
                router.setPagePath(PageConstant.RETURN_APP);
                router.setRoute(Router.RouteType.REDIRECT);
            } else {
                router.setPagePath(PageConstant.ADD_MONEY);
                router.setRoute(Router.RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
