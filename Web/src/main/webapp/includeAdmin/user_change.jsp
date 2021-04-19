<%--@elvariable id="usersList" type="java.util.ArrayList"--%>
<%--@elvariable id="rolesList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.edit.user.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-2">
            <form action="/controller" method="post" class="form-horizontal">
                <input type="hidden" name="action" value="change_user">
                <div class="form-group">
                    <label for="user" class="col-sm-3 control-label"><fmt:message key="update.user.form.user"/></label>
                    <div class="col-sm-9">
                        <select class="form-control" id="user" name="userId">
                            <c:forEach items="${usersList}" var="user">
                                <option value="${user.id}"><fmt:message key="update.user.id"/> ${user.id}, <fmt:message key="update.user.role"/> ${user.roleId}, <fmt:message key="update.user.first.name"/> ${user.firstName}, <fmt:message key="update.user.login"/> ${user.login}, <fmt:message key="update.user.balance"/> ${user.balance}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="roleId" class="col-sm-3 control-label"><fmt:message key="update.user.form.role"/></label>
                    <div class="col-sm-9">
                        <select class="form-control" id="roleID" name="roleId">
                            <c:forEach items="${rolesList}" var="role">
                                <option value="${role.id}">${role.id}, ${role.role}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" class="btn btn-primary"><fmt:message key="update.user.submit"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>


