<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<br>
<font color="#ff4500" >

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${message}
</font>
<br/>
&nbsp;&nbsp;
<br/>


<nav class="navbar navbar-default navbar-fixed-bottom" style="background: #20B2aa">
    <div class="container-fluid">
        <form class="navbar-form navbar-right" action="/Controller" method="POST">
            <input type="hidden" name="command" value="gotoadminpage">
            <option>  <a href="/Controller?command=gotodeleteuserpage" class="btn btn-default"><fmt:message key="delete.user"/></a></option>


            <!-- <select title="locale" name="locale" class="form-control input-sm"
                    onchange="if (this.selectedIndex) this.form.submit ()">


                <option value="default">English</option>
                <option value="ru">Русский</option>
            </select>-->

        </form>
    </div>
    <div class="container-fluid">
        <form class="navbar-form navbar-right" action="/Controller" method="POST">
            <input type="hidden" name="command" value="gotodeleteuserpage">
            <option>    <a href="/Controller?command=gotoadminpage" class="btn btn-default"><fmt:message key="continue"/></a>&nbsp;&nbsp;&nbsp;</option>

        </form>
    </div>

</nav>
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>