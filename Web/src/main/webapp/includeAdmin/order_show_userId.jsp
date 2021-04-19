<%--@elvariable id="ordersList" type="java.util.ArrayList"--%>
<%--@elvariable id="emptyOrder" type="java.lang.String"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.user.orders.page"/></title>
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
        <c:if test="${not empty ordersList}">
            <table class="table table-condensed table-bordered">
                <tr>
                    <td align="center" style="border-color: #dae5ff"><fmt:message key="table.number"/></td>
                    <td align="center" style="border-color: #dae5ff"><fmt:message key="table.order.start.rent"/></td>
                    <td align="center" style="border-color: #dae5ff"><fmt:message key="table.order.end.rent"/></td>
                    <td align="center" style="border-color: #dae5ff"><fmt:message key="table.order.value"/></td>
                </tr>
                <c:forEach items="${ordersList}" varStatus="сounter">
                    <tr>
                        <td align="center" style="border-color: #dae5ff">${ordersList[сounter.count-1].id}</td>
                        <td align="center" style="border-color: #dae5ff">${ordersList[сounter.count-1].startRent}</td>
                        <td align="center" style="border-color: #dae5ff">${ordersList[сounter.count-1].endRent}</td>
                        <td align="center" style="border-color: #dae5ff">${ordersList[сounter.count-1].value}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${not empty emptyOrder}">
            <div class="text-center">
                <h3>
                    <fmt:message key="message.empty.user.order"/>
                </h3>
            </div>
        </c:if>
        <a href="/controller?action=show_admin_page" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
</div>
<br/>
<br/>

<%@ include file="../include/footer_admin.jsp" %>
</body>
</html>




