<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cpath" value="${pageContext.request.contextPath }"/>
<c:set var="uploadpath" value="/resources/uploadfiles/"/>
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
<div>
<img src="${cpath}${uploadpath}${board.boardRenameFilename }">
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
<hr>
<h4>댓글 목록</h4>
<table border="1">
	<thead>
	<tr>
		<td>글번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td>조회수</td>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${replyList }" var="reply">
	<!-- 제목을 누르면 글읽기 화면으로 이동 -->
	<tr>
		<td>${reply.boardNum }</td>
		<td><a href="<%=request.getContextPath()%>/board/read?boardNum=${reply.boardNum }">${reply.boardTitle }</a></td>
		<td>${reply.boardWriter }</td>
		<td>${reply.boardDate }</td>
		<td>${reply.boardReadcount }</td>
	</tr>
	</c:forEach>
	</tbody>
</table>

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
			, dataType: "json" // success에 들어오는 데이터가 json 모양일것이고 이것을 js object로 변형해서 return값으로 보내라
			, success: function (result) { // result => controller의 return값이 들어옴
				console.log(result);
				console.log(result[0]);
				console.log(result[0].boardDate);
				
//				$("#frmReply")[0].reset(); // 작성된 글 input 칸 초기화
				frmReply.reset();
				if(result.length > 0) { 
					alert("댓글이 작성되었습니다.")
				} else {
					alert("댓글이 작성되지 않았습니다. 다시 작성해주세요.")
				}
				// 답글 부분화면 업데이트
				displayReply(result);
			}
			, error: function () {
				
			}
		});
	}
	
	// 답글 부분화면 업데이트 함수
	function displayReply(result) {
		console.log(result);
		console.log(result[0]);
		console.log(result[0].boardDate);
		
		var htmlVal = '';
		for(i = 0; i < result.length; i++){
			var reply = result[i];
			htmlVal += '<tr>';
			htmlVal += '<td>'+reply.boardNum+'</td>';
			htmlVal += '<td><a href="<%=request.getContextPath()%>/board/read?boardNum='+reply.boardNum+'">'+reply.boardTitle+'</a></td>';
			htmlVal += '<td>'+reply.boardWriter+'</td>';
			htmlVal += '<td>'+reply.boardDate+'</td>';
			htmlVal += '<td>'+reply.boardReadcount+'</td>';
			htmlVal += '</tr>';
		}
		$("tbody").html(htmlVal);
	}
</script>
</body>
</html>