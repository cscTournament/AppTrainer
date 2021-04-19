<%--@elvariable id="user" type="User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-default navbar-static-top" style="background: #20B2AA">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <c:if test="${empty user}">
                <form action="/jsp/user_login.jsp" class="navbar-form navbar-right">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.signin"/></button>
                </form>
                <form action="/jsp/user_register.jsp" class="navbar-form navbar-right">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.register"/></button>
                </form>
            </c:if>
            <c:if test="${not empty user}">
                <form action="/controller" class="navbar-form navbar-right">
                    <fmt:message key="nav.welcome"/><ctg:info user="${user}"/>
                    <input type="hidden" name="action" value="logout">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.signout"/></button>
                </form>
            </c:if>
            <%--users role: 16 - vip_user; 6 - user; 17 - user_has_order; 12 - blocked user; 7- administrator
            controller?action=show_admin_page
            TODO: FIX
              <input type="hidden" name="action" value="show_orders">
            --%>
            <c:if test="${not empty user and user.roleId == 6 }">
                <form action="/controller" class="navbar-form navbar-right">
                    <input type="hidden" name="action" value="show_orders">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.show.orders"/></button>
                </form>
            </c:if>
            <%--roleId == 17 corresponds to the user who has an app in rent--%>
            <%--users role: 16 - vip_user; 6 - user; 17 - user_has_order; 12 - blocked user; 7 - administrator--%>
            <c:if test="${not empty user and user.roleId == 17}">
                <form action="/controller" method="post" class="navbar-form navbar-right">
                    <input type="hidden" name="action" value="return_app">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.return.app"/></button>
                </form>
            </c:if>
            <c:if test="${not empty user and user.roleId == 7}">
                <form action="/controller" method="post" class="navbar-form navbar-right">
                    <input type="hidden" name="action" value="show_admin_page">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.admin"/></button>
                </form>
            </c:if>
        </div>
    </div>
</nav>
