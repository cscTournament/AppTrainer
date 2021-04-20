package by.gourianova.apptrainer.action.admin;

import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.entity.App;
import by.gourianova.apptrainer.entity.Order;
import by.gourianova.apptrainer.entity.HttpAddress;
import by.gourianova.apptrainer.entity.User;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.AppService;
import by.gourianova.apptrainer.service.OrderService;
import by.gourianova.apptrainer.service.HttpAddressService;
import by.gourianova.apptrainer.service.UserService;
import by.gourianova.apptrainer.util.PageConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static by.gourianova.apptrainer.util.PageConstant.ADMIN_PAGE;
import static by.gourianova.apptrainer.util.PageConstant.ONE_USER;

public class ShowAdminPageAction implements Action {
    private final static String USERS_LIST = "usersList";
    private final static String APPS_LIST = "appsList";
    private final static String ORDERS_LIST = "ordersList";
    private final static String HTTPADDRESSES_LIST = "httpAddressesList";
    private final static String MESSAGE = "message";
    private UserService userService = new UserService();
    private AppService appService = new AppService();
    private OrderService orderService = new OrderService();
    private HttpAddressService httpAddressService = new HttpAddressService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<User> usersList;
        ArrayList<App> appsList;
        ArrayList<Order> ordersList;
        ArrayList<HttpAddress> httpAddressesList;
        try {
            usersList = userService.findAll();

            appsList = appService.findAll();
            //TODO: write and test
            //ordersList = orderService.findAll();
            httpAddressesList = httpAddressService.findAll();
            request.setAttribute(USERS_LIST, usersList);
          request.setAttribute(APPS_LIST, appsList);
        // request.setAttribute(ORDERS_LIST, ordersList);
           request.setAttribute(HTTPADDRESSES_LIST, httpAddressesList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PAGE);
           requestDispatcher.forward(request, response);
            router.setPagePath(PageConstant.ADMIN_PAGE);

        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
