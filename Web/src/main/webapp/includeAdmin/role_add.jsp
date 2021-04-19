<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.add.role.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form action="/controller" method="post" enctype="multipart/form-data" class="form-horizontal">
                <input type="hidden" name="action" value="add_role">
                <div class="form-group">
                    <label for="role" class="col-sm-2 control-label"><fmt:message key="add.role.form.role"/></label>
                    <div class="col-sm-10">
                        <input type="text" id="role" name="role" class="form-control" placeholder="<fmt:message key="add.role.form.role.placeholder"/>"
                               maxlength="10" required pattern="[A-Z]?[a-z]{1,10}">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.role.name"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary"><fmt:message key="add.role.form.submit"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>


