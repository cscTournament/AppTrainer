<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>


<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.register.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="../include/navbar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form action="/controller" method="post" class="form-horizontal">
                <input type="hidden" name="action" value="register">

                <div class="form-group">
                    <label for="first_nameForm" class="col-sm-3 control-label"><fmt:message key="register.form.first.name"/></label>
                    <div class="col-sm-9">
                        <input type="text" id="first_nameForm" name="first_name" class="form-control" placeholder="<fmt:message key="register.form.first.name.placeholder"/>"
                               onKeyup="checkData('first_name')" maxlength="10" required pattern="([A-Z]?[a-z]{1,10})|([А-ЯЁ]?[а-яё]{1,10})">
                        <b id="first_name" style="color: red; font-size: 10px"><fmt:message key="validation.first.name"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="last_nameForm" class="col-sm-3 control-label"><fmt:message key="register.form.last.name"/></label>
                    <div class="col-sm-9">
                        <input type="text" id="last_nameForm" name="last_name" class="form-control" placeholder="<fmt:message key="register.form.last.name.placeholder"/>"
                               onKeyup="checkData('last_name')" maxlength="10" required pattern="([A-Z]?[a-z]{1,10})|([А-ЯЁ]?[а-яё]{1,10})">
                        <b id="last_name" style="color: red; font-size: 10px"><fmt:message key="validation.last.name"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="loginForm" class="col-sm-3 control-label"><fmt:message key="register.form.login"/></label>
                    <div class="col-sm-9">
                        <input type="text" id="loginForm" name="login" class="form-control" placeholder="<fmt:message key="register.form.login.placeholder"/>"
                               onKeyup="checkData('login')" maxlength="6"  required pattern="[a-zA-Z0-9]{3,6}">
                        <b id="login" style="color: red; font-size: 10px"><fmt:message key="validation.login"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwordForm" class="col-sm-3 control-label"><fmt:message key="register.form.password"/></label>
                    <div class="col-sm-9">
                        <input name="password" id="passwordForm" type="password" class="form-control" placeholder="<fmt:message key="register.form.password.placeholder"/>"
                               onKeyUp="checkData('password')" maxlength="6" required pattern="[a-zA-Z0-9._*]{3,6}">
                        <b id="password" style="color: red; font-size: 10px;"><fmt:message key="validation.password"/></b>
                    </div>
                </div>
                   <div class="form-group">
                    <label for="balanceForm" class="col-sm-3 control-label"><fmt:message key="register.form.balance"/></label>
                    <div class="col-sm-9">
                        <input type="number" step="0.01" id="balanceForm" name="balance" class="form-control" placeholder="<fmt:message key="register.form.balance.placeholder"/>"
                               onKeyup="checkData('balance')" max="1000" required pattern="[0-9]{1,6}">
                        <b id="balance" style="color: red; font-size: 10px"><fmt:message key="validation.balance"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" class="btn btn-primary"><fmt:message key="register.form.submit"/></button>
                    </div>
                </div>
                <c:if test="${not empty wrongUser}">
                    <div class="text-center">
                        <p class="text-danger"><fmt:message key="alert.wrong.user"/></p>
                    </div>
                </c:if>
                <c:if test="${not empty wrongRegisterData}">
                    <div class="text-center">
                        <p class="text-danger"><ctg:validate input="${wrongRegisterData}"/>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>
</body>
</html>

