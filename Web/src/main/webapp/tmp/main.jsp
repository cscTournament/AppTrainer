<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
	<script type="text/javascript" src="../js/validator.js"></script>-
    <title><fmt:message key="title.afterRegistration.page"/></title>
	<link href="../css/bootstrap.min.css" rel="stylesheet">


</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<%@ include file="../include/home.jsp" %>


	<center>
		<br/>
		<h3>

	<a  href="/userPhotoServlet"><fmt:message key="nav.logo"/></a>

			<br/><br/>

	<a  href="Controller?command=logout"><fmt:message key="nav.logout"/></a>
			<br/><br/>

	<a  href="index.xhtml"><fmt:message key="nav.index.page"/></a>
			<br/><br/>
	<a  href="Controller?command=gotoindexpage"> <fmt:message key="nav.first.page"/> </a>
	      <br/><br/>


	<a  href="/index "><fmt:message key="nav.main.servlet"/></a>

			</h3>
		<br/>
		</center>

<%@ include file="../include/footer.jsp" %>
</body>
</html>