<%@page import="com.db.Page"%>
<%@page import="com.db.Dto"%>
<%@page import="java.util.ArrayList"%>
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
	
	String pageNum = request.getParameter("page");
	   int cPage1 = 1; 
	if(pageNum == null){
// 	      cPage1 = Integer.parseInt(pageNum);
		pageNum = "1";
	}
	//int cPage1 = 1; //현재 페이지

	//페이지가 null이라면 1페이지 출력	
	//if (pageNum == null) {
	//	pageNum = "1";
	//}

	Dao dao = new Dao();
	//페이징 처리:ArrayList<Dto> posts = dao.list()에 현재 페이지 넣어주기(숫자니깐 문자열로) /안 넣으면 null값이 들어감
	ArrayList<Dto> posts = dao.list(pageNum);%>
	<h1>게시판</h1>
	<hr>
	
	<%
	//1.Dao 객체 생성(객체를 생성해야 list 불러오기 가능)
	//2.dao.list()를 posts에 넣기 
	//(ArrayList<Dto> 타입의 posts 객체 생성)

	//배열 객체에서 한개씩 꺼내기
	for (int i = 0; i < posts.size(); i++) {
	%>
	<%=posts.get(i).num%>
	<!-- 	원하는 글을 클릭하면 번호를 비교해서 글 내용을 화면에 출력 (num은 Dto클래스에서 만든 객체)-->
	<a href="read.jsp?num=<%=posts.get(i).num%>"><%=posts.get(i).title%></a>
	<%=posts.get(i).id%>
	<%=posts.get(i).hits%>
	<%=posts.get(i).dt%><hr>
	<%
	}
	%>
	<a href="write.jsp"><input type="button" value="글쓰기"></a>
	<%@include file="page.jsp"%>
<%-- 	<jsp:include page="page.jsp"> --%>
<%-- 		<jsp:param value="<%=cPage1 %>" name="page"/> --%>
<%-- 	</jsp:include> --%>

</body>
</html>