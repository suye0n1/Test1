<%@page import="com.db.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String delNum = request.getParameter("num");
Dao dao = new Dao();
dao.del(delNum);
response.sendRedirect("list.jsp");
%>
</body>
</html>