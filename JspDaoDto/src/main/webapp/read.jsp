<%@page import="com.db.Dao"%>
<%@page import="com.db.Dto"%>
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
// 	클라이언트가 list.jsp에서 제목 링크를 요청(request)하면 그 list.jsp에 있는 내용(parameter)이 서버로 날아감
	String readNum = request.getParameter("num");
	Dao dao = new Dao();
	//이 Dto는 Dao에 있는 걸 말함...?
	Dto post = dao.read(readNum);
%>
<!-- 	화면에 출력하기 -->
	<%=post.num %>
	<%=post.title %>
	<%=post.content %>
	<%=post.id %>
	<%=post.hits %>
	<%=post.dt %>

<a href="list.jsp"><input type="button" value="목록"></a>
<a href="del.jsp?num=<%=post.num %>"><input type="button" value="삭제"></a>
<a href="edit.jsp?num=<%=post.num %>"><input type="button" value="수정"></a>
	
</body>
</html>