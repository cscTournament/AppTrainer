<%--@elvariable id="wrongLoginPassword" type="java.lang.String"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.login.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form action="/controller" method="post" class="form-horizontal">
                <input type="hidden" name="action" value="login">
                <div class="form-group">
                    <label for="loginForm" class="col-sm-2 control-label"><fmt:message key="register.form.login"/></label>
                    <div class="col-sm-10">
                        <input type="text" id="loginForm" name="login" class="form-control" placeholder="<fmt:message key="register.form.login.placeholder"/>"
                               maxlength="6" required pattern="[a-zA-Z0-9]{3,6}">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.login"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwordForm" class="col-sm-2 control-label"><fmt:message key="register.form.password"/></label>
                    <div class="col-sm-10">
                        <input name="password" id="passwordForm" type="password" class="form-control" placeholder="<fmt:message key="register.form.password.placeholder"/>"
                               maxlength="6" required pattern="[a-zA-Z0-9._*]{3,6}"/>
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.password" /></b>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary"><fmt:message key="login.form.submit"/></button>
                    </div>
                </div>
                <c:if test="${not empty wrongLoginPassword}">
                    <div class="text-center">
                        <p class="text-danger"><fmt:message key="alert.wrong.login.password"/></p>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>

