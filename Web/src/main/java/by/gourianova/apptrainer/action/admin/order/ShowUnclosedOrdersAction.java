package by.gourianova.apptrainer.action.admin.order;

import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.entity.Order;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.OrderService;
import by.gourianova.apptrainer.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class ShowUnclosedOrdersAction implements Action {
    private final static String ORDERS_LIST = "ordersList";
    private final static String EMPTY_ORDER = "emptyOrder";
    private final static String MESSAGE = "message";
    private OrderService orderService = new OrderService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            ArrayList<Order> ordersList = orderService.findUnclosed();
            if (!ordersList.isEmpty()) {
                request.setAttribute(ORDERS_LIST, ordersList);
            } else {
                request.setAttribute(EMPTY_ORDER, Boolean.TRUE);
            }
            router.setPagePath(PageConstant.UNCLOSED_ORDERS);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
