<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.add.httpAddress.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <form action="/controller" method="post" enctype="multipart/form-data" class="form-horizontal">

                <input type="hidden" name="action" value="add_http_address">
                <div class="form-group">
                    <label for="web_shop" class="col-sm-4 control-label"><fmt:message key="add.httpAddress.form.web_shop"/></label>
                    <div class="col-sm-8">
                        <input type="text" id="web_shop" name="web_shop" class="form-control" placeholder="<fmt:message key="add.httpAddress.form.web_shop.placeholder"/>"
                               maxlength="60" required pattern="~^(?:(?:https?)://(?:[a-z0-9_-]{1,32}(?::[a-z0-9_-]{1,32})?@)?)?(?:(?:[a-z0-9-]{1,128}\.)+(?:ru|su|com|net|org|mil|edu|arpa|gov|biz|info|aero|inc|name|[a-z]{2})|(?!0)(?:(?!0[^.]|255)[0-9]{1,3}\.){3}(?!0|255)[0-9]{1,3})(?:/[a-z0-9.,_@%&?+=\~/-]*)?(?:#[^ '\&]*)?$~i">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.httpAddress.web_shop"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="location" class="col-sm-4 control-label"><fmt:message key="add.httpAddress.form.location"/></label>
                    <div class="col-sm-8">
                        <input type="text" id="location" name="location" class="form-control" placeholder="<fmt:message key="add.httpAddress.form.location.placeholder"/>"
                               maxlength="115" required pattern="~^(?:(?:https?)://(?:[a-z0-9_-]{1,32}(?::[a-z0-9_-]{1,32})?@)?)?(?:(?:[a-z0-9-]{1,128}\.)+(?:ru|su|com|net|org|mil|edu|arpa|gov|biz|info|aero|inc|name|[a-z]{2})|(?!0)(?:(?!0[^.]|255)[0-9]{1,3}\.){3}(?!0|255)[0-9]{1,3})(?:/[a-z0-9.,_@%&?+=\~/-]*)?(?:#[^ '\&]*)?$~i">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.httpAddress.location"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button type="submit" class="btn btn-primary"><fmt:message key="add.httpAddress.form.submit"/></button>
                    </div>
                </div>
            </form>


        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>
</body>
</html>


