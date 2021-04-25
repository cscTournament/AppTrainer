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
              --%>
            <c:if test="${not empty user and (user.roleId >= 6) and(user.balance>0) and(user.roleId != 12)and(user.roleId != 7)and(user.roleId != 17) }">

                <form action="/controller" class="navbar-form navbar-right">
                    <input type="hidden" name="action" value="show_orders">
                    <button type="submit" class="btn btn-default"><fmt:message key="nav.show.orders"/></button>
                </form>
                <div class="container-fluid">
                    <div class="row">

                        <div class="col-md-5 col-md-offset-3">

                            <form action="/controller" method="post" class="form-horizontal">
                                <input type="hidden" name="action" value="rent_app">
                                <div class="text-right">
                                    <fmt:message
                                            key="blank"/>&nbsp; ${appsList[counter.count-1].id}
                                </div>
                                <div class="form-group">
                                    <label for="rentAppForm" class="col-sm-3 control-label"><fmt:message
                                            key="nav.app"/></label>
                                    <div class="col-sm-3">
                                        <input type="number"  id="rentAppForm" name="appId"
                                               class="form-control"
                                               placeholder="<fmt:message key="table.app.id"/>"                                                        >
                                        <b id="appIdForm" style="color: #c7ddef; font-size: 10px"><fmt:message
                                                key="validation.rent"/></b>
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="text" id="appId2" name="appId" value="${appsList[counter.count-1].id}"
                                               class="hidden">
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-5">
                                            <button type="submit" class="btn btn-primary"><fmt:message
                                                    key="rent.form.submit"/></button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
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
