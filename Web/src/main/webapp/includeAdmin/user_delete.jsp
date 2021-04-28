<%--@elvariable id="usersList" type="java.util.ArrayList"--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.delete.user.page"/></title>
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
</center>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-2">
            <form action="/controller" method="post" class="form-horizontal">

                <input type="hidden" name="action" value="delete_user">
                <div class="form-group">
                    <label for="user" class="col-sm-3 control-label"><fmt:message
                            key="update.user.form.delete"/></label>

                    <div class="col-sm-8">
                        <input type="text" id="user" name="userId" class="form-control"
                               placeholder="<fmt:message key="search.form.user.id.placeholder"/>"
                               maxlength="3" required pattern="[0-9]{1,3}">
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" class="btn btn-primary"><fmt:message key="delete.user"/></button>
                        </div>
                    </div>
                </div>
        </div>
        </form>
    </div>
</div>

<a href="/Controller?command=gotoadminpage" class="btn btn-default"><fmt:message key="continue"/></a> &nbsp;&nbsp;&nbsp;

<%@ include file="../include/footer.jsp" %>
</body>
</html>


