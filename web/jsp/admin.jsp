<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" %>
<%
    ResourceBundle resource = ResourceBundle.getBundle("local", request.getLocale());
%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h1><%=resource.getString("HiAdmin")%></h1>
<a href="/logout"><%=resource.getString("LogOut")%></a>
</body>
</html>