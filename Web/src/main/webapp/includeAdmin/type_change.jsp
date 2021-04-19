<%--@elvariable id="typesList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.edit.price.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-2">
            <form action="/controller" method="post" class="form-horizontal">
                <input type="hidden" name="action" value="change_type">
                <div class="form-group">
                    <label for="appType" class="col-sm-3 control-label"><fmt:message
                            key="update.type.form.type"/></label>
                    <div class="col-sm-9">
                        <select class="form-control" id="appType" name="typeId">
                            <c:forEach items="${typesList}" var="type">
                                <option value="${type.id}"> ${type.id}, ${type.type}, ${type.price}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="price" class="col-sm-3 control-label"><fmt:message
                            key="update.type.form.price"/></label>
                    <div class="col-sm-9">
                        <input type="number" step="0.01" id="price" name="price" class="form-control"
                               placeholder="<fmt:message key="update.type.form.price.placeholder"/>"
                               max="10" required pattern="[0-9]{1,2}">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.type.price"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" class="btn btn-primary"><fmt:message key="update.type.submit"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../include/footer_admin.jsp" %>
</body>
</html>


