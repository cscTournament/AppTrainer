<%--@elvariable id="appsList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.show.all.user.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <%--   <style>
    body {
                  background: url(../images/fon.jpg);
              }
           </style>--%>
       </head>
       <body>

<%@ include file="../include/navbar.jsp" %>
<%@ include file="../include/home.jsp" %>


<div class="container-fluid">
    <div class="col-md-6 col-md-offset-1">
        <table class="table table-condensed table-bordered">
            <tr>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.app.id"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.app.title"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.type.price"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.httpAddress.location"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.type.photo"/></td>

            </tr>
            <c:forEach items="${appsList}" varStatus="сounter">
                <tr>
                    <td align="center" style="border-color: #dae5ff">${appsList[сounter.count-1].id}</td>
                    <td align="center" style="border-color: #dae5ff">${appsList[сounter.count-1].title}</td>
                    <td align="center" style="border-color: #dae5ff">${appsList[сounter.count-1].pricePerHour}</td>
                    <td align="center" style="border-color: #dae5ff"><a href=${appsList[сounter.count-1].url}>url</a></td>
                    <td align="center" style="vertical-align: middle; border-color: #dae5ff"><img src="data:image/jpeg;base64,${appsList[сounter.count-1].picture}"/>

                </td>
                </tr>
            </c:forEach>
        </table>
     <a href="/controller?action=show_admin_page" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
</div>
<br/>
<br/>
<br/>


<%@ include file="../include/footer.jsp" %>
</body>
</html>



