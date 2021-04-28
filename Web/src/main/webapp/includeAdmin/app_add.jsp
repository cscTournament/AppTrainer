<%--@elvariable id="typesList" type="java.util.ArrayList"--%>
<%--@elvariable id="httpAddressesList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
    <title><fmt:message key="title.add.app.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>


            <form action="/controller" method="post" class="form-horizontal">
                <input type="hidden" name="action" value="add_app">
                    <div class="form-group">
                        <label for="title" class="col-sm-3 control-label"><fmt:message key="add.app.form.title"/></label>
                        <div class="col-sm-9">
                            <input type="text"  id="title" name="title" class="form-control" placeholder="<fmt:message key="add.app.form.title.placeholder"/>"
                                   maxlength="100" required pattern="[a-zA-Z0-9._*]{3,100}">
                            <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.app"/></b>
                        </div>
                    </div>
                <div class="form-group">
                    <label for="type2" class="col-sm-3 control-label"><fmt:message key="add.app.form.type"/></label>
                    <div class="col-sm-9">
                        <select class="form-control" id="type2" name="typeId">
                            <c:forEach items="${typesList}" var="type">
                                <option value="${type.id}">${type.id}, ${type.type}, ${type.price}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="httpAddress2" class="col-sm-3 control-label"><fmt:message key="add.app.form.httpAddress"/></label>
                    <div class="col-sm-9">
                        <select class="form-control" id="httpAddress2" name="httpAddressId">
                            <c:forEach items="${httpAddressesList}" var="httpAddress">
                                <option value="${httpAddress.id}">${httpAddress.id}, ${httpAddress.web_shop}, ${httpAddress.location}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" class="btn btn-primary"><fmt:message key="add.app.form.submit"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<a href="/controller?action=show_admin_page" class="btn btn-default"><fmt:message key="continue"/></a>
<br/>
<br/>
<%@ include file="../include/footer.jsp" %>
</body>
</html>


