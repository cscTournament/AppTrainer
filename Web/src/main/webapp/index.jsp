<%@ page import="by.gourianova.apptrainer.dao.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Index</title>
</head>
<body>

<%-- TODO: FIX <c:redirect url="controller?action=show_apps_page"/> --%>
 <c:redirect url="controller?action=show_all_user_apps"/>
</body>
</html>