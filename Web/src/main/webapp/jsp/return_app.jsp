<%--@elvariable id="order" type="Order"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.return.app.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: url(../images/fon.jpg);
        }
    </style>
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <div class="text-center">
            <h4><fmt:message key="message.ok.return"/></h4>
        </div>
        <table class="table table-condensed table-bordered">
            <tr>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.number"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.order.start.rent"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.order.end.rent"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.order.value"/></td>
            </tr>
            <tr>
                <td align="center" style="border-color: #dae5ff">${order.id}</td>
                <td align="center" style="border-color: #dae5ff">${order.startRent}</td>
                <td align="center" style="border-color: #dae5ff">${order.endRent}</td>
                <td align="center" style="border-color: #dae5ff">${order.value}</td>
            </tr>
        </table>
        <a href="../index.jsp" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
    <%@ include file="../include/footer.jsp" %>
</body>
</html>




