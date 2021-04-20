<%--@elvariable id="usersList" type="java.util.ArrayList"--%>
<%--@elvariable id="appssList" type="java.util.ArrayList"--%>
<%--@elvariable id="ordersList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.admin.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="../include/navbar_admin.jsp" %>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <div class="col-md-10 col-md-offset-1">
                <table class="table table-condensed table-bordered">
                    <tr class="active">
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.user.id"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.user.first.name"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.user.last.name"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.user.login"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.user.balance"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.user.role"/></td>
                    </tr>
                    <c:forEach items="${usersList}" varStatus="сounter">
                        <tr class="active">
                            <td align="center" style="border-color: #8381eb">${usersList[сounter.count-1].id}</td>
                            <td align="center"
                                style="border-color: #8381eb">${usersList[сounter.count-1].firstName}</td>
                            <td align="center" style="border-color: #8381eb">${usersList[сounter.count-1].lastName}</td>
                            <td align="center" style="border-color: #8381eb">${usersList[сounter.count-1].login}</td>
                            <td align="center" style="border-color: #8381eb">${usersList[сounter.count-1].balance}</td>
                            <td align="center" style="border-color: #8381eb">${usersList[сounter.count-1].roleId}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <%--
        <div class="col-md-4">
            <div class="col-md-10 col-md-offset-0">
                <table class="table table-condensed table-bordered" style="vertical-align: middle">
                    <tr class="active">
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.number"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message
                                key="table.order.start.rent"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.order.end.rent"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.order.value"/></td>
                    </tr>
                    <c:forEach items="${ordersList}" varStatus="сounter">
                        <tr class="active">
                            <td align="center" style="border-color: #8381eb">${ordersList[сounter.count-1].id}</td>
                            <td align="center"
                                style="border-color: #8381eb">${ordersList[сounter.count-1].startRent}</td>
                            <td align="center" style="border-color: #8381eb">${ordersList[сounter.count-1].endRent}</td>
                            <td align="center" style="border-color: #8381eb">${ordersList[сounter.count-1].value}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>--%>
        <div class="col-md-4">
            <div class="col-md-10 col-md-offset-0">
                <table class="table table-condensed table-bordered">
                    <tr class="active">

                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.app.title"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.rent.price"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.rent.location"/></td>
                        <td align="center" style="border-color: #8381eb"><fmt:message key="table.rent.photo"/></td>
                    </tr>
                    <c:forEach items="${appsList}" varStatus="сounter" end="6">
                        <tr class="active">

                            <td align="center"
                                style="vertical-align: middle; border-color: #8381eb">${appsList[сounter.count-1].title}</td>
                            <td align="center"
                                style="vertical-align: middle; border-color: #8381eb">${appsList[сounter.count-1].pricePerHour}</td>
                            <td align="center" style="border-color: #dae5ff"><a href=${appsList[сounter.count-1].url}>url</a>
                            </td>
                            <td align="center" style="vertical-align: middle; border-color: #8381eb"><img
                                    src="data:image/jpeg;base64,${appsList[сounter.count-1].picture}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="../include/footer_admin.jsp" %>
</body>
</html>

