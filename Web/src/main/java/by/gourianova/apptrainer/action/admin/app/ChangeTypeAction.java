package by.gourianova.apptrainer.action.admin.app;
import by.gourianova.apptrainer.controller.Router;
import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.entity.AppType;
import by.gourianova.apptrainer.exception.ServiceException;
import by.gourianova.apptrainer.service.AppTypeService;
import by.gourianova.apptrainer.util.PageConstant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class ChangeTypeAction implements Action {
    private final static String TYPE_ID = "typeId";
    private final static String PRICE = "price";
    private final static String MESSAGE = "message";
    private final static String ADMIN_PAGE="/controller?action=show_admin_page";
    private AppTypeService appTypeService = new AppTypeService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        AppType appType = new AppType();
        try {
            appType.setId(Integer.parseInt(request.getParameter(TYPE_ID)));
            appType.setPrice(new BigDecimal(request.getParameter(PRICE)));
            appTypeService.editPrice(appType);
            router.setPagePath(ADMIN_PAGE);
           // router.setPagePath(PageConstant.FIRST_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
