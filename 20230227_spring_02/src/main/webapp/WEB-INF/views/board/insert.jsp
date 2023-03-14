<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.ckeditor.com/4.20.2/standard/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
<h1>board insert</h1>
<!-- file upload : enctype -->
<form action="insert" method="post" enctype="multipart/form-data">
	<input type="text" name="boardTitle" placeholder="제목"><br>
<!-- 	<input type="text" name="boardContent" placeholder="내용"><br> -->
	<textarea name="boardContent" placeholder="내용"></textarea><br>           
	<!-- file의 경우 name은 vo와 다른 이름으로 해야함!! -->
	<input type="file" name="report" placeholder="첨부파일"><br>
	<button type="text" id="btnCheck">textarea값 확인</button>
	<button type="submit">게시글 등록</button>
</form>

<script>
	CKEDITOR.replace( 'boardContent'
			, {
				filebrowserUploadUrl: 'imageUploda.do'
				// ckfinder 추가하여 이미지 찾기도 가능함
			   }
	);
	$('#btnCheck').click(function() {
		console.log($("[name=boardContent]").val());
		console.log($("[name=boardContent]").html());
		console.log(CKEDITOR.instances.boardContent.getData());
	});
</script>
</body>
</html>