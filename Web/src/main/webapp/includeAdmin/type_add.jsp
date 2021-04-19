<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.add.type.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
                <form action="/controller" method="post" enctype="multipart/form-data" class="form-horizontal">
                    <input type="hidden" name="action" value="add_type">
                <div class="form-group">
                    <label for="type" class="col-sm-2 control-label"><fmt:message key="add.type.form.type"/></label>
                    <div class="col-sm-10">
                        <input type="text" id="type" name="type" class="form-control" placeholder="<fmt:message key="add.type.form.type.placeholder"/>"
                               maxlength="15" required pattern="[А-ЯЁ]?[а-яё]{1,15}">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.type.name"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="price" class="col-sm-2 control-label"><fmt:message key="add.type.form.price"/></label>
                    <div class="col-sm-10">
                        <input type="number" step="0.01" id="price" name="price" class="form-control" placeholder="<fmt:message key="add.type.form.price.placeholder"/>"
                               max="10" required pattern="[0-9]{1,2}">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.type.price"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="file" class="col-sm-2 control-label"><fmt:message key="add.type.form.photo"/></label>
                    <div class="col-sm-10">
                        <input type="file" id="file" name="photo" multiple accept="image/*,image/jpeg">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary"><fmt:message key="add.type.form.submit"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>

