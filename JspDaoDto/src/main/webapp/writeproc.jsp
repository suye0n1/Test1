<%@page import="com.db.Dto"%>
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
	Dto dto = new Dto(
			request.getParameter("title"),
			request.getParameter("id"),
			request.getParameter("content")
			);
	Dao dao = new Dao();
	dao.write(dto);
	response.sendRedirect("list.jsp");
%>


</body>
</html>