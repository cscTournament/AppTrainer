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
import java.util.ArrayList;


public class ShowAllTypesAction implements Action {
    private final static String TYPES_LIST = "typesList";
    private final static String MESSAGE = "message";
    private AppTypeService appTypeService = new AppTypeService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<AppType> typesList;
        try {
            typesList = appTypeService.findAll();
            request.setAttribute(TYPES_LIST, typesList);
            router.setPagePath(PageConstant.ALL_TYPES);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
