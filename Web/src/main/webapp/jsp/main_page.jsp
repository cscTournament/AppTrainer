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
            background: url(../images/fon2.jpg);
        }
    </style>
</head>
<body>
<%@ include file="../include/navbar.jsp" %>

<div class="container-fluid">
    <div class="col-sm-10">
    <c:if test="${empty user or (not empty user and user.roleId < 6)}">
        <lable class="col-sm-19 control-label"><b><fmt:message key="welcome.guest"/></b></lable>
    </c:if>
    </div>
</div>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <table class="table table-condensed table-bordered">
            <tr>
                <td align="center" style="border-color: #8381eb"><fmt:message key="table.number"/></td>
                <td align="center" style="border-color: #8381eb"><fmt:message key="table.app.title"/></td>
                <td align="center" style="border-color: #8381eb"><fmt:message key="table.rent.price"/></td>
                <%-- <td align="center" style="border-color: #8381eb"><fmt:message key="table.rent.location"/></td> --%>
                <td align="center" style="border-color: #8381eb"><fmt:message key="table.rent.photo"/></td>
            </tr>
            <c:forEach items="${appsList}" varStatus="сounter">
                <tr>
                        <%--roleId > display a list of apps without the possibility to rent them--%>
                        <%--users role: 16 - vip_user; 6 - user; 17 - user_has_order; 12 - blocked user; 7 - administrator--%>
                    <c:if test="${not empty user  and  (user.roleId == 7)}">
                        <td align="center" style="vertical-align: middle; border-color: #dae5ff">
                            <form action="/controller" method="post">
                                <input type="hidden" name="action" value="show_admin_page">

                                <button type="submit" class="btn btn-link"><fmt:message key="nav.admin"/></button>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${empty user or (not empty user and user.roleId < 6)}">
                        <td align="center"
                            style="vertical-align: middle; border-color: #dae5ff">${appsList[сounter.count-1].id}</td>
                    </c:if>
                        <%--roleId <7 displaying a list of apps with the possibility to rent them--%>
                        <%--users role: 16 - vip_user; 6 - user; 17 - user_has_order; 12 - blocked user; 7 - administrator --%>

                            <c:if test="${not empty user and (user.roleId >= 6) and(user.balance>0) and(user.roleId != 12)and(user.roleId != 7)}">


                            <td align="center" style="vertical-align: middle; border-color: #dae5ff">
                            <form action="/controller" method="post">
                                   <input type="hidden" name="action" value="rent_app">

                                <button type="submit"  class="btn btn-link" name="appId" value="${appsList[counter.count-1].id}" >${appsList[сounter.count-1].id}</button>

                            </form>
                        </td>
                    </c:if>
                    <td align="center"
                        style="vertical-align: middle; border-color: #8381eb">${appsList[сounter.count-1].title}</td>
                    <td align="center"
                        style="vertical-align: middle; border-color: #8381eb">${appsList[сounter.count-1].pricePerHour}</td>
                        <%--  <td align="center" style="border-color: #dae5ff"><a href=${appsList[сounter.count-1].url}>url</a>
                          </td>--%>
                    <td align="center" style="vertical-align: middle; border-color: #8381eb"><img
                            src="data:image/jpeg;base64,${appsList[сounter.count-1].picture}"/>
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
<%-- <jsp:include page="../include/footer.jsp"/>--%>
</body>
</html>



