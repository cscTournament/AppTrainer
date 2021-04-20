<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-default navbar-static-top" style="background: #20B2AA">
    <div class="container-fluid">
        <ul class="nav navbar-nav">

            <%--user--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="/controller?action=show_admin_page"><fmt:message
                        key="nav.user"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/controller?action=show_all_users"><fmt:message key="nav.user.show.all"/></a></li>

                    <li><a href="/controller?action=get_user_data"><fmt:message key="nav.user.change"/></a></li>
                    <li class="divider"></li>

                    <li><a href="#"><fmt:message key="search.form.user"/></a>

                        <form action="/controller" method="post" class="form-horizontal">
                            <input type="hidden" name="action" value="find_user">
                            <div class="form-group">
                                <label for="user" class="col-sm-offset-1 col-sm-2 control-label"><fmt:message
                                        key="search.form.user.id"/></label>
                                <div class="col-sm-8">
                                    <input type="text" id="user" name="userId" class="form-control"
                                           placeholder="<fmt:message key="search.form.user.id.placeholder"/>"
                                           maxlength="3" required pattern="[0-9]{1,3}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button type="submit" class="btn btn-primary"><fmt:message
                                            key="search.form.submit"/></button>
                                </div>
                            </div>
                        </form>
                    </li>
                    <li class="divider"></li>
                    <li><a href="../jsp/admin_page.jsp"><fmt:message key="search.form.user"/></a>

                        <form action="/controller" method="post" class="form-horizontal">
                            <input type="hidden" name="action" value="find_user">

                            <div class="form-group">

                                <div class="col-sm-12">
                                    <select class="form-control" id="userForm2" name="userId">
                                        <c:forEach items="${usersList}" var="user">
                                            <option value="${user.id}"><fmt:message key="update.user.id"/> ${user.id},
                                                <fmt:message key="update.user.role"/> ${user.roleId}, <fmt:message
                                                        key="update.user.first.name"/> ${user.firstName}, <fmt:message
                                                        key="update.user.login"/> ${user.login}, <fmt:message
                                                        key="update.user.balance"/> ${user.balance}</option>
                                        </c:forEach>
                                    </select>

                                    <div class="form-group">
                                        <div class="col-sm-offset-1 col-sm-1">
                                            <br/>
                                            <button type="submit" class="btn btn-primary"><fmt:message
                                                    key="search.form.add.money"/></button>
                                        </div>
                                    </div>

                                </div>
                            </div>

                        </form>
                    </li>
                </ul>
            </li>

            <%--role--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message
                        key="nav.role"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/controller?action=show_all_roles"><fmt:message key="nav.role.show.all"/></a>
                    <li><a href="../includeAdmin/role_add.jsp"><fmt:message key="nav.role.add"/></a></li>
                    <li><a href="../includeAdmin/role_delete.jsp"><fmt:message key="nav.role.delete"/></a></li>
                </ul>
            </li>

            <%--order--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message
                        key="nav.order"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/controller?action=show_all_orders"><fmt:message key="nav.order.show.all"/></a>
                    <li><a href="/controller?action=show_unclosed_orders"><fmt:message
                            key="nav.order.unclosed.orders"/></a>
                    <li class="divider"></li>
                    <li><a href="#"><fmt:message key="search.form.order"/></a>
                        <form action="/controller" method="post" class="form-horizontal">
                            <input type="hidden" name="action" value="show_user_orders">
                            <div class="form-group">
                                <label for="userForm" class="col-sm-offset-1 col-sm-2 control-label"><fmt:message
                                        key="nav.user"/></label>
                                <div class="col-sm-8">
                                    <select class="form-control" id="userForm" name="userId">
                                        <c:forEach items="${usersList}" var="user">
                                            <option value="${user.id}"><fmt:message key="update.user.id"/> ${user.id},
                                                <fmt:message key="update.user.role"/> ${user.roleId}, <fmt:message
                                                        key="update.user.first.name"/> ${user.firstName}, <fmt:message
                                                        key="update.user.login"/> ${user.login}, <fmt:message
                                                        key="update.user.balance"/> ${user.balance}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button type="submit" class="btn btn-primary"><fmt:message
                                            key="search.form.submit"/></button>
                                </div>
                            </div>
                        </form>
                    </li>
                </ul>
            </li>

            <%--type--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message
                        key="nav.type"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/controller?action=show_all_types"><fmt:message key="nav.type.show.all"/></a>
                    </li>
                    <li><a href="../includeAdmin/type_add.jsp"><fmt:message key="nav.type.add"/></a></li>
                    <li><a href="/controller?action=get_type_data"><fmt:message key="nav.type.edit.price"/></a>
                    </li>

                </ul>
            </li>
            <td colspan="3">
                <%--httpAddress--%>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="nav.httpAddress"/>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/controller?action=show_all_http_addresses"><fmt:message
                                key="nav.httpAddresses.show.all"/></a>
                        <li><a href="../includeAdmin/httpAddress_add.jsp"><fmt:message key="nav.httpAddress.add"/></a>
                        </li>
                    </ul>
                </li>
            </td>
            <%--app--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="nav.app"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/controller?action=show_all_apps"><fmt:message
                            key="nav.apps.show.all"/></a></li>
                    <li><a href="/controller?action=get_app_data"><fmt:message key="nav.app.add"/></a>
                    <li class="divider"></li>

                </ul>
            </li>
        </ul>

        <%--exit/admin's page--%>
        <c:if test="${not empty user and user.roleId == 6}">
            <form action="/controller" class="navbar-form navbar-right">
                <fmt:message key="nav.welcome"/><ctg:info user="${user}"/>
                <input type="hidden" name="action" value="logout">
                <button type="submit" class="btn btn-default"><fmt:message key="nav.signout"/></button>
            </form>
            <form action="/controller" method="post" class="navbar-form navbar-right">
                <input type="hidden" name="action" value="show_admin_page">
                <button type="submit" class="btn btn-default"><fmt:message key="nav.admin"/></button>
            </form>
        </c:if>
    </div>
</nav>


