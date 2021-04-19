<%--@elvariable id="user" type="User"--%>
<%--@elvariable id="appsList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.main.page"/></title>
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
    <table class="table table-condensed table-bordered">
        <tr>
            <td align="center" style="border-color: #dae5ff"><fmt:message key="table.number"/></td>
            <td align="center" style="border-color: #dae5ff"><fmt:message key="table.app.title"/></td>
            <td align="center" style="border-color: #dae5ff"><fmt:message key="table.rent.price"/></td>
            <td align="center" style="border-color: #dae5ff"><fmt:message key="table.rent.web_shop"/></td>
            <td align="center" style="border-color: #dae5ff"><fmt:message key="table.rent.location"/></td>
            <td align="center" style="border-color: #dae5ff"><fmt:message key="table.rent.photo"/></td>
        </tr>
        <c:forEach items="${appsList}" varStatus="сounter">
            <tr>
                    <%--roleId > display a list of apps without the possibility to rent them--%>
                    <%--users role: 1 - vip_user; 2 - user; 3 - user_has_order; 4 - blocked user; 5 - administrator--%>
                <c:if test="${empty user or (not empty user and user.roleId > 2)}">
                    <td align="center" style="vertical-align: middle; border-color: #dae5ff">${appsList[сounter.count-1].id}</td>
                </c:if>
                    <%--roleId <=2 displaying a list of apps with the possibility to rent them--%>
                    <%--users role: 1 - vip_user; 2 - user; 3 - user_has_order; 4 - blocked user; 5 - administrator--%>
                <c:if test="${not empty user and user.roleId <= 2}">
                    <td align="center" style="vertical-align: middle; border-color: #dae5ff">
                        <form action="/controller" method="post">
                            <input type="hidden" name="action" value="rent_app">
                            <input type="hidden" name="appId" value="${appsList[сounter.count-1].id}">
                            <button type="submit" class="btn btn-link">${appsList[сounter.count-1].id}</button>
                        </form>
                    </td>
                </c:if>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${appsList[сounter.count-1].title}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${appsList[сounter.count-1].pricePerHour}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${appsList[сounter.count-1].web-shop}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${appsList[сounter.count-1].location}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff"><img src="data:image/jpeg;base64,${appsList[сounter.count-1].picture}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="...">
        <ul class="pager">
            <li class="previous${leftPageClass}"><a href="${leftPage}"><fmt:message key="page.previous"/></a></li>
            <li class="next${rightPageClass}"><a href="${rightPage}"><fmt:message key="page.next"/></a></li>
        </ul>
    </nav>
    </div>
</div>
<%@ include file="../include/footer.jsp" %>
<%--<jsp:include page="../include/footer.jsp"/>--%>
</body>
</html>



