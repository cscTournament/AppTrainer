<%--@elvariable id="userOne" type="User"--%>
<%--@elvariable id="rolesList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <script type="text/javascript" src="../js/validator.js"></script>
        <title><fmt:message key="title.add.money.page"/></title>
        <link href="../css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: url(../images/fon.jpg);
        }
    </style>
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<br/>
<br/>
<br/>

<div class="container-fluid">
    <div class="col-md-6 col-md-offset-3">
        <c:if test="${not empty emptyUser}">
            <div class="text-center">
                <h4><fmt:message key="message.empty.user"/></h4>
            </div>
        </c:if>
        <c:if test="${not empty userOne}">
            <table class="table table-condensed table-bordered">
                <tr>
                    <td align="center" style="border-color:  #20B2AA"><fmt:message key="table.user.id"/></td>
                    <td align="center" style="border-color: #20B2AA"><fmt:message key="table.user.first.name"/></td>
                    <td align="center" style="border-color: #20B2AA"><fmt:message key="table.user.last.name"/></td>
                    <td align="center" style="border-color: #20B2AA"><fmt:message key="table.user.login"/></td>
                    <td align="center" style="border-color: #20B2AA"><fmt:message key="table.user.password"/></td>
                    <td align="center" style="border-color: #20B2AA"><fmt:message key="table.user.balance"/></td>
                    <td align="center" style="border-color: #20B2AA"><fmt:message key="table.user.role"/></td>
                </tr>
                <tr>
                    <td align="center" style="border-color: #dae5ff">${userOne.id}</td>
                    <td align="center" style="border-color: #dae5ff">${userOne.firstName}</td>
                    <td align="center" style="border-color: #dae5ff">${userOne.lastName}</td>
                    <td align="center" style="border-color: #dae5ff">${userOne.login}</td>
                    <td align="center" style="border-color: #dae5ff">${userOne.password}</td>
                    <td align="center" style="border-color: #dae5ff">${userOne.balance}</td>
                    <td align="center" style="border-color: #dae5ff">${userOne.roleId}</td>
                </tr>
            </table>
        </c:if>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <%-- TODO: uncomment after writing update password
             <form action="/controller" method="post" class="form-horizontal">
                   <input type="hidden" name="action" value="change_user_data">
                   <div class="hidden">
                       <label for="idForm" class="col-sm-2 control-label"><fmt:message
                               key="table.user.id"/></label>
                       <div class="hidden">
                           <input type="text" id="idForm" name="userId" value="${userOne.id}" class="form-control"
                                  placeholder="<fmt:message key="register.form.id.placeholder"/>">
                       </div>
                   </div>
                   <div class="hidden">
                       <label for="loginForm" class="col-sm-2 control-label"><fmt:message
                               key="register.form.login"/></label>
                       <div class="hidden">
                           <input type="text" id="loginForm" name="login" value="${userOne.login}" class="form-control"
                                  placeholder="<fmt:message key="register.form.login.placeholder"/>"
                                  maxlength="1" required pattern="[a-zA-Z0-9]{3,6}">
                           <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.login"/></b>
                       </div>
                   </div>
                   <div class="form-group">
                       <label for="passwordForm" class="col-sm-2 control-label"><fmt:message
                               key="register.form.password"/></label>
                       <div class="col-sm-10">
                           <input name="password" id="passwordForm" type="password" class="form-control"
                                  placeholder="<fmt:message key="register.form.password.placeholder"/>"
                                  maxlength="6" required pattern="[a-zA-Z0-9._*]{3,6}"/>
                           <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.password"/></b>
                       </div>
                   </div>
                   <div class="form-group">
                       <div class="col-sm-offset-2 col-sm-10">
                           <button type="submit" class="btn btn-primary"><fmt:message
                                   key="login.form.change"/></button>
                       </div>
                   </div>
                   <c:if test="${not empty wrongLoginPassword}">
                       <div class="text-center">
                           <p class="text-danger"><fmt:message key="alert.wrong.login.password"/></p>
                       </div>
                   </c:if>
               </form>
           </div>
       </div>
   </div>

--%>
            <div class="container-fluid">
                <div class="row">

                    <div class="col-md-6 col-md-offset-3">

                        <form action="/controller" method="post" class="form-horizontal">
                            <input type="hidden" name="action" value="add_money">
                            <div class="text-right">
                                <fmt:message
                                        key="message.balance.plus"/>&nbsp; ${userOne.balance}
                            </div>
                            <div class="form-group">
                                <label for="balanceForm" class="col-sm-2 control-label"><fmt:message
                                        key="register.form.balance"/></label>
                                <div class="col-sm-10">
                                    <input type="number" step="0.01" id="balanceForm" name="balance"
                                           class="form-control"
                                           placeholder="<fmt:message key="register.form.balance.placeholder"/>"
                                           onKeyup="checkData('balance')" max="1000" required
                                           pattern="[0-9]{1,6}">
                                    <b id="balance" style="color: red; font-size: 10px"><fmt:message
                                            key="validation.balance"/></b>
                                </div>
                                <div class=".d-none">
                                    <input type="text" id="userId" name="userId" value="${userOne.id}"
                                           class="hidden">
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-7">
                                        <button type="submit" class="btn btn-primary"><fmt:message
                                                key="balance.form.submit"/></button>
                                    </div>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <form action="/controller" method="post" class="form-horizontal">
                            <input type="hidden" name="action" value="change_user">
                            <div class="hidden">
                                <label for="user2" class="col-sm-2 control-label"><fmt:message
                                        key="update.user.form.user"/></label>
                                <div class="hidden">
                                    <input type="text" id="user2" name="userId" value="${userOne.id}"
                                           class="form-control"
                                           placeholder="<fmt:message key="search.form.user.id.placeholder"/>"
                                           maxlength="3" required pattern="[0-9]{1,3}">
                                </div>
                            </div>
                            <div class="col-sm-10">
                                <input type="hidden" name="action" value="change_user">
                                <div class="form-group">
                                    <label for="roleForm"
                                           class="col-sm-2 control-label"><fmt:message
                                            key="nav.role"/></label>
                                    <div class="col-sm-10">
                                        <select class="form-control" id="roleForm" name="roleId">
                                            <c:forEach items="${rolesList}" var="role">
                                                <option value="${role.id}"><fmt:message
                                                        key="update.user.id"/> ${role.id},
                                                    <fmt:message key="update.user.role"/> ${role.role}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-7">
                                        <button type="submit" class="btn btn-primary"><fmt:message
                                                key="nav.user.change"/></button>
                                    </div>
                                </div>

                            </div>
                        </form>
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;   <a href="/controller?action=show_admin_page" class="btn btn-default"><fmt:message key="continue"/></a> &nbsp;   <a href="/controller?action=show_delete_user_page" class="btn btn-default"><fmt:message key="delete.user"/></a>
                    </div>
                </div>

            </div>

                <br/>
                <br/>
            <%@ include file="../include/home.jsp" %>
                <br/>
                <br/>

             <%@ include file="../include/footer_admin.jsp" %>

</body>
</html>

