<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.List" %>
<%@ page import="by.gourianova.apptrainer.controller.AppsListController" %>
<%@ page import="by.gourianova.apptrainer.advetisment.AppItem" %>
<%@ include file="../include/uselocale.jsp" %>

<html
        xmlns="http://www.w3.org/1999/xhtml"
>

<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.afterAthorithation.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<%@ include file="../include/home.jsp" %>


<%
    String message = (String) request.getParameter("message");
    if (message != null) {
%>
<center>

    <font color="red">

        <p><c:out value="${message}"/></p>

        <%
                out.write(message);
            }
        %>


    </font>



<br/>
<center><h3> your apps:</h3></center>

<br/>
<center>
    <table border="2">
        <c:forEach var="a" items="#{appcontroller.appItems}">
            <tr>
                <td>
                    <font size="18" color="green">
                        <center>
                            <a href="<c:out value="${a.category}" />"><c:out value="${a.name}"/> </a>
                        </center>
                    </font></td>
            </tr>
        </c:forEach>
    </table>
</center>
<%@ include file="../include/footer.jsp" %>
</body>
</html>