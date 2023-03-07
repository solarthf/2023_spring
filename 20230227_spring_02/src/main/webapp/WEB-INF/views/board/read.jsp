<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
<h1>${board.boardNum } 게시글</h1>
<div>
<p>${board.boardTitle }</p>
</div>
<div>
${board.boardContent }
</div>

<form id="frmReply">
<fieldset>
<legend>답글작성</legend>
	<div>제목<input type="text" name="boardTitle"></div>
	<div>내용<input type="text" name="boardContent"></div>
	<input type="hidden" name="boardNum" value="${board.boardNum }">
	<button type="button" class="btn reply">답글작성</button>
</fieldset>
</form>

<!-- Ajax -->
<script type="text/javascript">
	$(".btn.reply").on("click", replyClickHandler);
	function replyClickHandler() {
		console.log(this);	    // this => DOM 형태
		console.log($(this));	// $(this) => jQuery 형태
		console.log($("#frmReply").serialize()); // QueryString 형태(boardTitle=값&boardContent=값&boardNum=값)
		$.ajax({ 
			url: "<%=request.getContextPath()%>/board/insertReplyAjax"
			, type: "post" // ↓ data => form안의 들어가는 내용
//			, data: {boardTitle: $("#a").val(), boardContent:$("#b").val(), boardNum:'${board.boardNum}'}
			, data: $("#frmReply").serialize()
			, success: function (result) { // result => controller의 return값이 들어옴
				console.log(result);
//				$("#frmReply")[0].reset(); // 작성된 글 초기화
				frmReply.reset();
				if(result == "ok") {
					alert("댓글이 작성되었습니다.")
				} else {
					alert("댓글이 작성되지 않았습니다. 다시 작성해주세요.")
				}
			}
			, error: function () {
				
			}
		});
	}
</script>
</body>
</html>