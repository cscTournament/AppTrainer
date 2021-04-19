package by.gourianova.apptrainer.action;

import javax.servlet.http.HttpServletRequest;


public class ActionFactory {

    public static Action getAction(HttpServletRequest request){
        ActionType entry = ActionType.valueOf(request.getParameter("action").toUpperCase());
        return entry.getAction();
    }
}
