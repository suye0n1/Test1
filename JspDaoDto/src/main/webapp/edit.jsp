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
String readNum = request.getParameter("num");
Dao dao = new Dao();
Dto d = dao.read(readNum);

%>
<form action="editproc.jsp">
<input type="hidden" name="num" value="<%=readNum%>">
<input name="title" value="<%=d.title%>">
<input name="content" value="<%=d.content%>">
<input type="submit" value="수정">

</form>



</body>
</html>