<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" %>
<%
    ResourceBundle resource = ResourceBundle.getBundle("local", request.getLocale());
%>
<html>
<html>
<head>
    <title>Login FAIL!!!</title>
</head>
<body>
<h1><%=resource.getString("WrongPassword")%></h1>
<a href="/"><%=resource.getString("Home")%><<<</a>
</body>
</html>
