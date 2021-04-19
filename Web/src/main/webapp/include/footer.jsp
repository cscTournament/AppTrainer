<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<br>
<nav class="navbar navbar-default navbar-fixed-bottom" style="background: #20B2AA">
    <div class="container-fluid">
        <form class="navbar-form navbar-right" action="/controller" method="POST">
            <input type="hidden" name="action" value="change_locale">
            <select title="locale" name="locale" class="form-control input-sm"
                    onchange="if (this.selectedIndex) this.form.submit ()">
                <option><fmt:message key="footer.language"/></option>
                <option value="default">English</option>
                <option value="ru">Русский</option>
            </select>
        </form>
    </div>
</nav>
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
