<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보보기</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
<h1>내정보</h1>
<form id="frmInfo" method="get">
	<input name="id" value="${membervo.id }" type="text" readonly="readonly"><br>
	<input value="${membervo.passwd }" type="password" readonly="readonly"><br>
	<input value="${membervo.name}" type="text" readonly="readonly"><br>
	<input value="${membervo.email }" type="text" readonly="readonly"><br>
	<button type="button" onclick="frmSubmit('update');">수 정</button>
	<button type="button" onclick="frmSubmit('delete');">탈 퇴</button>
	
	
	<button type="button">수 정</button>
	<button type="button">탈 퇴</button>
</form>




<script type="text/javascript">
	function frmSubmit(targetEle) {
		console.log(this); // this = window 객체
		frmInfo.action = targetEle;
		frmInfo.submit();
		//$("#frmInfo").attr("action", targetEle);
		//$("#frmInfo").submit();
	}

	$('button').eq(2).click('update', frmSubmit2);
	$('button').eq(3).click('delete', frmSubmit2);
	function frmSubmit2(event) {
		console.log(this);  // this = click이 발생한 시점의 element
		frmInfo.action = event.data; // event.data = click('update'), click('delete')
		frmInfo.submit();
	}

	console.log($('button').eq(2).text());   // jQuery형태
	console.log($('button').get(2).innerText);  // <button type="button">수 정</button>
</script>






</body>
</html>