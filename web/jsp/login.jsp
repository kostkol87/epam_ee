<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" %>
<%
    ResourceBundle resource = ResourceBundle.getBundle("local", request.getLocale());
%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>
<form action="/login" method="post">
    <input placeholder="<%=resource.getString("login")%>" name="loginField"><br>
    <input type="password" placeholder="<%=resource.getString("password")%>" name="passField"><br>
    <input type="submit" value="<%=resource.getString("LogIn")%>">
</form>
</body>
</html>