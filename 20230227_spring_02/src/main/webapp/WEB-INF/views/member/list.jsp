<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 회원목록</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
<style type="text/css">
	.f-3 {
		display: flex;
		width: 300px;
		height: 50px;
		/* align-items: center;
		flex-wrap: nowrap; */
	}
	
	.f-3 div {
 		width: 33%; 
		border: 1px black solid;
		
	}
</style>
</head>
<body>
<h1>회원목록</h1>
<div class="f-3">
	<div>아이디</div>
	<div>이름</div>
	<div>이메일</div>
</div>

<c:forEach items="${memberlist }" var="member">
<a href="<%=request.getContextPath()%>/member/info?id=${member.id }">
<div class="f-3">
	<div>${member.id }</div>
	<div>${member.name }</div>
	<div>${member.email }</div>
</div>
</a>
</c:forEach>
</body>
</html>