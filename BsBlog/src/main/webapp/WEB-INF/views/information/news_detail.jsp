<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>information/news_detail.jsp</title>
<link href="<%=request.getContextPath() %>/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/resources/css/subpage.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.js"></script>
<script type="text/javascript">
	// 로딩 하자마자 댓글 토글 hide
	$(document).ready(function () {
		reply();
	});

	// ---------- 댓글 작성 ----------
	// 만약 함수가 작동 않는다면 프로젝트 클린, 톰캣 클린하고 인터넷 캐시 삭제 해보기
	// pom.xml json 파싱 필수
	function reply_write() {
		if(${sessionScope.sId == null}){
			alert("로그인 후 댓글 작성이 가능합니다.");
			location.href = "login.me";
		} else {
			if($("#reply_content").val() == '' || $("#reply_content").val() == null){
				alert("댓글을 입력해주세요.");
			} else {
				$.ajax({
					url: "reply_writePro.re",
					type: "POST",
					dataType: "json",
					data: {
						reply_content: $("#reply_content").val(),
						reply_id: '${sessionScope.sId}',
						reply_ne_ref: ${newsDetail.news_num}
					},
					success: function (result) {
						alert("댓글 작성이 완료되었습니다.");
						reply();
						$("#reply_content").val("댓글은 1000자 이상 작성할 수 없습니다.");
					}, 
					error: function (result) {
						alert("댓글 작성이 실패되었습니다. 다시 시도해주세요.");
						console.log("에러 : " + result);
					}
					
				})
			}
		}
	};
	
	// ---------- 댓글 삭제 ---------
	function reply_delete(reply_idx){
		var deleteCheck = confirm("댓글을 삭제하시겠습니까?");
		
		if (deleteCheck) {
			$.ajax({
				url: "reply_DeletePro.re",
				type: "GET",
				data: {
						reply_idx : reply_idx
				},
				success: function(msg){
					alert("댓글이 삭제되었습니다.");
					reply();			
				},
			})
		} 
	
	};
	
	// 대댓글 토글
	// 댓글 토글에서 이 함수를 호출하므로 댓글 토글보다 위에 있어야함
	// 토글을 위해 idNum필요?	
	function reReply(idNum){
		 if ($('#reReplyBox' + idNum).css('display') == 'none') {
	          $('#reReplyBox'+ idNum).css('display', 'block');
	     } else {
	            $('#reReplyBox'+ idNum).css('display', 'none');
	     }
	}
	
	// 대댓글
	// serialize 왜 필요?
	function reReply_write(idNum){
		if(${sessionScope.sId == null}){
			
			alert("로그인 후 작성 가능합니다.");
			
			location.href = "login.me";
		} else {
			$.ajax({
				url: "reReply_writePro.re",
				type: "POST",
				datatype: "json",
				data: $("#reReply_form" + idNum).serialize(),
				success: function(){
					reply();
				},
			})
	   }
	};
	

	// 댓글 토글
	function reply(){

		$.ajax({
			url: "reply.re",
			type: "POST",
			data: {
				reply_ne_ref : ${newsDetail.news_num}
			},
			dataType: "json"
		})	
		.done(function(replyList) {
			if(replyList.length < 1) {
				// document.getElementById("replyList").innerHTML = "등록된 댓글이 없습니다.";
			} else {
				let idNum = 0;
			
				for(let reply of replyList) {
					let space = "";
					console.log(reply);
				
					if(reply.reply_re_lev > 0) {
						for(let i = 0; i < reply.reply_re_lev; i++){
							space += "&nbsp;&nbsp;";
						}
							space += "<img src='<%=request.getContextPath() %>/resources/css/images/re.png'>&nbsp;";
					}
					
					let result = space + reply.reply_id + '(' + reply.reply_date +')' + '<br>' + reply.reply_content + '<br>'	
								+  '<span id="reply_wri_btn"><a class="reply_write_btn" id="rep_writeBtn" onclick="reReply('+ idNum +')" style="cursor: pointer" > <br> 답글 </a></span>'
								<!-- 로그인한 사람과 댓글작성자가 같을 경우 삭제버튼, 수정버튼 표시 -->
		                       // if(('${sessionScope.sId}' == reply.reply_id) || ('${sessionScope.sId}' == 'admin') ){
		                    	   	+  '<span id="reply_mod_btn"><a href="#" id="rep_modBtn" onclick="reply_modify('+ reply.reply_idx +');return false;" style="text-decoration: none; cursor: pointer;"> 수정 </a></span>'
			                        +  '<span id="reply_del_btn"><a href="#" id="rep_delBtn" onclick="reply_delete('+ reply.reply_idx +');return false;" style="text-decoration: none; cursor: pointer;"> 삭제 </a></span><hr>'
			                   //  }
		                        + '<div id="reReplyBox'+ idNum++ +'" style="display:none">'
								+ '<form action="#" method="post" id="reReply_form'+ idNum +'">'
								+ '<input type="hidden" name="reply_ne_ref" value="'+ reply.reply_ne_ref +'">'
								+ '<input type="hidden" name="reply_re_ref" value="'+ reply.reply_re_ref +'">'
								+ '<input type="hidden" name="reply_re_lev" value="'+ reply.reply_re_lev +'">'
								+ '<input type="hidden" name="reply_re_seq" value="'+ reply.reply_re_seq +'">'
								+ '<input type="hidden" name="reply_id" value="${sessionScope.sId}">'
								+ '<input type="hidden" name="pageNum" value="${param.pageNum}">'
								+ '<textarea id ="rereBox" name="reply_content" style="width: 670px; height: 200px; resize: none;">댓글을 입력하세요.</textarea>&nbsp;&nbsp;'
								+ '<input type="button" id="goReReply" value="작성" onclick="reReply_write('+ idNum +')"></form></div>';
		                    
								// append로 해야 들어가짐
		                        $("#replyList").append(result);
				}
			}
		})	
		.fail(function () {
			alert("댓글을 불러올 수가 없습니다. 다시 시도해 주세요.");
		});
	}				
				
	
</script>
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 헤더 들어가는곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 본문 메인 이미지 -->
		<div id="sub_img_center"></div>
		<!-- 왼쪽 메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="welcome.in">Welcome</a></li>
				<li><a href="career.in">Career</a></li>
				<li><a href="news.in?sId=${sessionScope.sId }">News</a></li>
				<li><a href="#">Public Policy</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->

		<article>
			<h1>News Content</h1>
			<table id="notice">
				<tr>
					<td>제목</td>
					<td colspan="3">${newsDetail.news_subject }</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td colspan="3">${newsDetail.news_id }</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${newsDetail.news_date }</td>
				</tr>
				<tr>
				<!-- * 왜 news_realfile , news_file을 서야했는지?-->
					<td>파일</td>
					<td colspan="3"><a href="newsFileDownload?fileName=${newsDetail.news_realfile }&news_num=${newsDetail.news_num}&pageNum=${param.pageNum}&sId=${sessionScope.sId}">${newsDetail.news_file }</a></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3">${newsDetail.news_content }</td>
				</tr>
			</table>

			<div id="table_search">
				<c:if test="${sessionScope.sId eq 'admin' }">
				<!-- * news-> news_detail로 넘어올때 주소창에 pageNum을 파라미터로 넘겼기 때문에 modify로 넘어갈때 param.pageNum으로 해야함 -->
					<input type="button" value="글수정" class="btn" onclick="location.href='news_modify.in?news_num=${newsDetail.news_num}&pageNum=${param.pageNum }&sId=${sessionScope.sId }'"> 
					<input type="button" value="글삭제" class="btn" onclick="location.href='news_delete.in?news_num=${newsDetail.news_num}&pageNum=${param.pageNum }&sId=${sessionScope.sId }'"> 
				</c:if>
				<input type="button" value="글목록" class="btn" onclick="location.href='news.in?news_num=${newsDetail.news_num}&pageNum=${param.pageNum }&sId=${sessionScope.sId }'">
			</div>
			
			<!-- 댓글 -->
			<div class="clear">
				<div>댓글</div>
				<textarea maxlength="1000" id="reply_content" style="resize: none; width: 670px; height: 200px;">댓글은 1000자 이상 작성할 수 없습니다.</textarea>
				<div>
					<input type="button" value="작성" onclick="reply_write()">
				</div>
			</div>
			<br>
			<hr>
			<br>
			<div class="clear">
				<div id="replyList"></div>
			</div>
			
		</article>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


