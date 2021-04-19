package by.gourianova.apptrainer.controller;

import java.util.Objects;

public class Router {
    public enum RouteType {
        FORWARD, REDIRECT
    }
    private String pagePath;
    private RouteType route = RouteType.FORWARD;


    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public RouteType getRoute() {
        return route;
    }

    public void setRoute(RouteType route) {
        if (route == null) {
            this.route = RouteType.FORWARD;
        }
        this.route = route;
    }
}
