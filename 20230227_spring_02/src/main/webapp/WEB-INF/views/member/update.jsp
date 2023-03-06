<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
<h1>회원정보수정</h1>
<form action="update" method="post">
	<input value="${membervo.id }" type="text" name="id" readonly="readonly"><br>
	<input value="${membervo.passwd }" type="password" name="passwd" placeholder="비밀번호"><br>
	<input value="${membervo.name}" type="text" name="name" placeholder="이름"><br>
	<input value="${membervo.email }" type="text" name="email" placeholder="이메일"><br>
	<button type="submit">수정</button>
</form>
</body>
</html>