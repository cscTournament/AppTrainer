<%--@elvariable id="user" type="User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.add.money.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-2">
            <form action="/controller" method="post" class="form-horizontal">
                <input type="hidden" name="action" value="add_money">
                <div class="text-center">
                        <fmt:message key="message.balance"/> ${user.balance}
                </div>
                <div class="form-group">
                    <label for="balanceForm" class="col-sm-2 control-label"><fmt:message key="register.form.balance"/></label>
                    <div class="col-sm-10">
                        <input type="number" step="0.01" id="balanceForm" name="balance" class="form-control" placeholder="<fmt:message key="register.form.balance.placeholder"/>"
                               onKeyup="checkData('balance')" max="1000" required pattern="[0-9]{1,6}">
                        <b id="balance" style="color: red; font-size: 10px"><fmt:message key="validation.balance"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary"><fmt:message key="balance.form.submit"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>

