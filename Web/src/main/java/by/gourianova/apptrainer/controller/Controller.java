package by.gourianova.apptrainer.controller;

import by.gourianova.apptrainer.action.Action;
import by.gourianova.apptrainer.action.ActionFactory;
import by.gourianova.apptrainer.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "controller", urlPatterns = "/controller")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class Controller extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router;
        HttpSession session = request.getSession();
        Action action = ActionFactory.getAction(request);
        if (action != null) {
            router = action.execute(request, response);
            switch (router.getRoute()) {
                case REDIRECT:
                    response.sendRedirect(router.getPagePath());
                    break;
                case FORWARD:
                    request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                    break;
            }
        } else {
            session.setAttribute("message", "Wrong command.");
            response.sendRedirect(PageConstant.ERROR_PAGE);
        }
    }
}
