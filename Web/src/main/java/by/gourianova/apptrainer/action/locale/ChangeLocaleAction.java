package by.gourianova.apptrainer.action.locale;

import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.controller.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


public class ChangeLocaleAction implements Action {
    private final static String LOCALE = "locale";
    private final static String REFERRER = "referer";

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String locale = LocaleType.getLocale(request.getParameter(LOCALE));
        session.setAttribute(LOCALE, locale);
        router.setPagePath((String) request.getSession().getAttribute(REFERRER));
        router.setRoute(Router.RouteType.REDIRECT);
        String referer = request.getHeader("referer");
        router.setPagePath(referer);
        router.setRoute(Router.RouteType.REDIRECT);

        return router;
    }
}
