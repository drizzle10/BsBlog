<%@page import="com.project.BsBlog.vo.ReplyPageInfo"%>
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
		$('#reReplyBox').hide();
		$('#replyModifyBox').hide();
	}); 

	
	// ---------- 댓글 삭제 ---------
 	/* function reply_delete(reply_idx, reply_id){
		if(('${sessionScope.sId}' != reply_id) || ('${sessionScope.sId}' != 'admin') ){
			alert("삭제 권한이 없습니다.");
		} else {
			var deleteCheck = confirm("댓글을 삭제하시겠습니까?");
			if (deleteCheck) {
				$.ajax({
					url: "reply_deletePro.re",
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
		}
	
	};  */
	
	// 답글 클릭시 토글
	function reReply(r){
		console.log(r);
			$("#reReplyBox" + r).toggle();
	}
	
	// 수정 클릭시 토글
	function reply_modify(r) {
		console.log(r);
		$("#replyModifyBox" + r).toggle();
	}
	
	// 댓글 삭제
	function reply_delete(reply_idx, reply_ne_ref) {
		let result = confirm("댓글을 삭제하시겠습니까?");
		 
		if(result) {
			// redirect위해 get으로?
			// location.href="reply_deletePro.re?reply_idx=" + reply_idx + "&reply_ne_ref=" + reply_ne_ref + "&pageNum=" + ${param.pageNum} + "&sId=" + "${sessionScope.sId}";
			
			$.ajax({
				url: "reply_deletePro.re",
				type: "POST",
				data: {
					reply_idx : reply_idx
				}, 
				success: function (insertCount) {
					if(insertCount > 0){
						alert("댓글이 삭제되었습니다.");
						// 컨트롤러 갔다가 success 되고 나면 redirect가 안됨 ㅜ 그래서 다시 note_detail.in 호출
						location.href="news_detail.in?news_num=" + reply_ne_ref + "&pageNum=" + ${param.pageNum} + "&sId=" + "${sessionScope.sId}";
					} else {
						alert("댓글 삭제가 실패 되었습니다.");
					}
				},
				fail: function (insertCount) {
					if(insertCount < 0){
						alert("댓글 삭제가 정상적으로 이루어지지 않았습니다. 다시 시도해 주세요");
					}
				}
			});  
		}
		
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
			</ul>
		</nav>
		<!-- 본문 내용 -->

		<article>
			<h1>News Content</h1>
			<table id="notice">
				<tr>
					<td>제목</td>
					<td>${newsDetail.news_subject }</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${newsDetail.news_id }</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${newsDetail.news_date }</td>
				</tr>
				<tr>
				<!-- *파일 다운로드 부분은 메모장 참고 -->
				<tr>
				<!-- * 왜 news_realfile , news_file을 서야했는지?-->
					<td>파일</td>
					<td><a href="newsFileDownload?fileName=${newsDetail.news_realfile }&news_num=${newsDetail.news_num}&pageNum=${param.pageNum}&sId=${sessionScope.sId}"><img src="/BsBlog/resources/upload/upload/${newsDetail.news_realfile}"</a></td>
				</tr>
				<tr>
					<td>내용</td>
					<td>${newsDetail.news_content }</td>
				</tr>
			</table>

			<div id="table_search">
				<c:if test="${sessionScope.sId eq 'admin' }">
				<!-- * news-> news_detail로 넘어올때 주소창에 pageNum을 파라미터로 넘겼기 때문에 modify로 넘어갈때 param.pageNum으로 해야함 -->
					<input type="button" value="글수정" class="btn" onclick="location.href='news_modify.in?news_num=${newsDetail.news_num}&pageNum=${param.pageNum }&sId=${sessionScope.sId }'"> 
					<input type="button" value="글삭제" class="btn" onclick="location.href='news_delete.in?news_num=${newsDetail.news_num}&pageNum=${param.pageNum }&sId=${sessionScope.sId }'"> 
				</c:if>
				<input type="button" value="글목록" class="btn" onclick="location.href='news.in?news_num?pageNum=${param.pageNum }&sId=${sessionScope.sId }'">
			</div>
			
			<!-- 댓글 -->
			<div class="clear">
				<div>댓글</div>
				<form action="reply_writePro.re" method="post">
					<input type="hidden" name="reply_id" value="${sessionScope.sId}">
					<input type="hidden" name="reply_ne_ref" value="${newsDetail.news_num }">
					<input type="hidden" name="pageNum" value="${param.pageNum }">
					<textarea maxlength="1000" id="reply_content" name="reply_content" style="resize: none; width: 670px; height: 200px;">댓글은 1000자 이상 작성할 수 없습니다.</textarea>
					<input type="submit" value="작성">
				</form>
			</div>
			<br>
			<hr>
			<br>
			<div class="clear">
				<div id="replyList">
					<c:if test="${empty reply }">
						작성된 댓글이 없습니다.
					</c:if>
					<c:forEach var="reply" items="${reply }">
						<c:set var="r" value="${r+1 }"/>
						<c:if test="${reply.reply_re_lev == 0 }">
							${reply.reply_id } | ${reply.reply_date }<br>
							${reply.reply_content }<br>
							<c:choose>
								<c:when test="${sessionScope.sId ne null && sessionScope.sId eq reply.reply_id }">
									<span id="reply_wri_btn"><a class="reply_write_btn" id="rep_writeBtn" onclick="reReply(${r})" style="cursor: pointer; color: brown;"> 답글 </a></span>
									<span id="reply_mod_btn"><a href="#" id="rep_modBtn" onclick="reply_modify(${r})" style="text-decoration: none; cursor: pointer; color: brown;"> 수정 </a></span>
									<span id="reply_del_btn"><a href="#" id="rep_delBtn" onclick="reply_delete(${reply.reply_idx}, ${reply.reply_ne_ref })" style="text-decoration: none; cursor: pointer; color: brown;"> 삭제 </a></span>
										<div id="reReplyBox${r}" style="display: none;">
											<form action="reReply_writePro.re" method="post" id="reReply_form">
												<input type="hidden" name="reply_ne_ref" value="${ reply.reply_ne_ref}">
												<input type="hidden" name="reply_re_ref" value="${ reply.reply_re_ref}">
												<input type="hidden" name="reply_re_lev" value="${ reply.reply_re_lev}">
												<input type="hidden" name="reply_re_seq" value="${ reply.reply_re_seq}">
												<input type="hidden" name="reply_id" value="${sessionScope.sId}">
												<input type="hidden" name="pageNum" value="${param.pageNum}">
												<textarea id ="reReply_textarea" name="reply_content" style="width: 670px; height: 200px; resize: none;">댓글을 입력하세요.</textarea>
												<input type="submit" id="go_reReply_wri_btn" value="작성">
											</form>
										</div>
										<div id="replyModifyBox${r }" style="display: none">
											<form action="reply_modifyPro.re" method="post" id="reply_mod_form">
												<input type="hidden" name="reply_idx" value="${ reply.reply_idx}">
												<input type="hidden" name="reply_ne_ref" value="${ reply.reply_ne_ref}">
												<input type="hidden" name="reply_re_ref" value="${ reply.reply_re_ref}">
												<input type="hidden" name="reply_re_lev" value="${ reply.reply_re_lev}">
												<input type="hidden" name="reply_re_seq" value="${ reply.reply_re_seq}">
												<input type="hidden" name="reply_id" value="${sessionScope.sId}">
												<input type="hidden" name="pageNum" value="${param.pageNum}">
												<textarea id ="reply_mod_textarea" name="reply_content" style="width: 670px; height: 200px; resize: none;">${reply.reply_content }</textarea>
												<input type="submit" id="go_reply_mod_btn" value="수정">
											</form>	
										</div>
									<hr>
								</c:when>
								<c:when test="${sessionScope.sId ne null && sessionScope.sId ne reply.reply_id }">
									<span id="reply_wri_btn"><a class="reply_write_btn" id="rep_writeBtn" onclick="reReply(${r})" style="cursor: pointer; color: brown;"> 답글 </a></span>
										<div id="reReplyBox${r}" style="display: none;">
											<form action="reReply_writePro.re" method="post" id="reReply_form">
												<input type="hidden" name="reply_ne_ref" value="${ reply.reply_ne_ref}">
												<input type="hidden" name="reply_re_ref" value="${ reply.reply_re_ref}">
												<input type="hidden" name="reply_re_lev" value="${ reply.reply_re_lev}">
												<input type="hidden" name="reply_re_seq" value="${ reply.reply_re_seq}">
												<input type="hidden" name="reply_id" value="${sessionScope.sId}">
												<input type="hidden" name="pageNum" value="${param.pageNum}">
												<textarea id ="reReply_textarea${r }" name="reply_content" style="width: 670px; height: 200px; resize: none;">댓글을 입력하세요.</textarea>
												<input type="submit" id="go_reply_wri_btn" value="작성">
											</form>
										</div>
										<div id="replyModifyBox${r }" style="display: none">
											<form action="reply_modifyPro.re" method="post" id="reply_mod_form">
												<input type="hidden" name="reply_idx" value="${ reply.reply_idx}">
												<input type="hidden" name="reply_ne_ref" value="${ reply.reply_ne_ref}">
												<input type="hidden" name="reply_re_ref" value="${ reply.reply_re_ref}">
												<input type="hidden" name="reply_re_lev" value="${ reply.reply_re_lev}">
												<input type="hidden" name="reply_re_seq" value="${ reply.reply_re_seq}">
												<input type="hidden" name="reply_id" value="${sessionScope.sId}">
												<input type="hidden" name="pageNum" value="${param.pageNum}">
												<textarea id ="reply_mod_textarea" name="reply_content" style="width: 670px; height: 200px; resize: none;">${reply.reply_content }</textarea>
												<input type="submit" id="go_reply_mod_btn" value="수정">
											</form>	
										</div>
									<hr>
								</c:when>
							</c:choose>
						</c:if>
						<c:if test="${reply.reply_re_lev > 0 }">
							<c:forEach var = "i" begin = "0" end = "${reply.reply_re_lev }" step="1">
								&nbsp;&nbsp;
							</c:forEach>
							<img src='<%=request.getContextPath() %>/resources/css/images/re.png'>
							${reply.reply_id } | ${reply.reply_date }<br>
							<c:forEach var = "i" begin = "0" end = "${reply.reply_re_lev }" step="1">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach>
							${reply.reply_content }<br>
							<c:choose>
								<c:when test="${sessionScope.sId ne null && sessionScope.sId eq reply.reply_id }">
									<span id="reply_wri_btn"><a class="reply_write_btn" id="rep_writeBtn" onclick="reReply(${r})" style="cursor: pointer; color: brown;"> 답글 </a></span>
									<span id="reply_mod_btn"><a href="#" id="rep_modBtn" onclick="reply_modify(${r})" style="text-decoration: none; cursor: pointer; color: brown;"> 수정 </a></span>
									<span id="reply_del_btn"><a href="#" id="rep_delBtn" onclick="reply_delete(${reply.reply_idx}, ${reply.reply_ne_ref })" style="text-decoration: none; cursor: pointer; color: brown;"> 삭제 </a></span>
										<div id="reReplyBox${r}" style="display: none;">
											<form action="reReply_writePro.re" method="post" id="reReply_form">
												<input type="hidden" name="reply_ne_ref" value="${ reply.reply_ne_ref}">
												<input type="hidden" name="reply_re_ref" value="${ reply.reply_re_ref}">
												<input type="hidden" name="reply_re_lev" value="${ reply.reply_re_lev}">
												<input type="hidden" name="reply_re_seq" value="${ reply.reply_re_seq}">
												<input type="hidden" name="reply_id" value="${sessionScope.sId}">
												<input type="hidden" name="pageNum" value="${param.pageNum}">
												<textarea id ="reReply_textarea${r }" name="reply_content" style="width: 670px; height: 200px; resize: none;">댓글을 입력하세요.</textarea>
												<input type="submit" id="go_reply_wri_btn" value="작성">
											</form>
										</div>
										<div id="replyModifyBox${r }" style="display: none">
											<form action="reply_modifyPro.re" method="post" id="reply_mod_form">
												<input type="hidden" name="reply_idx" value="${ reply.reply_idx}">
												<input type="hidden" name="reply_ne_ref" value="${ reply.reply_ne_ref}">
												<input type="hidden" name="reply_re_ref" value="${ reply.reply_re_ref}">
												<input type="hidden" name="reply_re_lev" value="${ reply.reply_re_lev}">
												<input type="hidden" name="reply_re_seq" value="${ reply.reply_re_seq}">
												<input type="hidden" name="reply_id" value="${sessionScope.sId}">
												<input type="hidden" name="pageNum" value="${param.pageNum}">
												<textarea id ="reply_mod_textarea" name="reply_content" style="width: 670px; height: 200px; resize: none;">${reply.reply_content }</textarea>
												<input type="submit" id="go_reply_mod_btn" value="수정">
											</form>	
										</div>
									<hr>
								</c:when>
								<c:when test="${sessionScope.sId ne null && sessionScope.sId ne reply.reply_id }">
									<span id="reply_wri_btn"><a class="reply_write_btn" id="rep_writeBtn" onclick="reReply(${r})" style="cursor: pointer; color: brown;"> 답글 </a></span>
										<div id="reReplyBox${r}" style="display: none;">
											<form action="reReply_writePro.re" method="post" id="reReply_form">
												<input type="hidden" name="reply_ne_ref" value="${ reply.reply_ne_ref}">
												<input type="hidden" name="reply_re_ref" value="${ reply.reply_re_ref}">
												<input type="hidden" name="reply_re_lev" value="${ reply.reply_re_lev}">
												<input type="hidden" name="reply_re_seq" value="${ reply.reply_re_seq}">
												<input type="hidden" name="reply_id" value="${sessionScope.sId}">
												<input type="hidden" name="pageNum" value="${param.pageNum}">
												<textarea id ="reReply_textarea${r }" name="reply_content" style="width: 670px; height: 200px; resize: none;">댓글을 입력하세요.</textarea>
												<input type="submit" id="go_reply_wri_btn" value="작성">
											</form>
										</div>
										<div id="replyModifyBox${r }" style="display: none">
											<form action="reply_modifyPro.re" method="post" id="reply_mod_form">
												<input type="hidden" name="reply_idx" value="${ reply.reply_idx}">
												<input type="hidden" name="reply_ne_ref" value="${ reply.reply_ne_ref}">
												<input type="hidden" name="reply_re_ref" value="${ reply.reply_re_ref}">
												<input type="hidden" name="reply_re_lev" value="${ reply.reply_re_lev}">
												<input type="hidden" name="reply_re_seq" value="${ reply.reply_re_seq}">
												<input type="hidden" name="reply_id" value="${sessionScope.sId}">
												<input type="hidden" name="pageNum" value="${param.pageNum}">
												<textarea id ="reply_mod_textarea" name="reply_content" style="width: 670px; height: 200px; resize: none;">${reply.reply_content }</textarea>
												<input type="submit" id="go_reply_mod_btn" value="수정">
											</form>	
										</div>
									<hr>
								</c:when>
							</c:choose>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<%ReplyPageInfo replyPageInfo = (ReplyPageInfo)request.getAttribute("replyPageInfo"); %>
			<div class="clear"></div>
			<div id="page_control">
				<%if(replyPageInfo.getReplyPageNum() > replyPageInfo.getReplyStartPage()) {%><a href="news_detail.in?news_num=${newsDetail.news_num }&pageNum=${param.pageNum }&sId=${sessionScope.sId }&replyPageNum=${replyPageInfo.replyPageNum - 1}"><%}%>Prev</a>
				<c:forEach var="i" begin="${replyPageInfo.replyStartPage }" end="${replyPageInfo.replyEndPage }">
                   <c:choose>
                      <c:when test="${i eq replyPageInfo.replyPageNum }"><a href="#">${i }</a></c:when>
                      <c:otherwise><a class="pageLink" href="news_detail.in?news_num=${newsDetail.news_num }&pageNum=${param.pageNum }&sId=${sessionScope.sId }&replyPageNum=${i }">${i }</a></c:otherwise>
                   </c:choose>
                </c:forEach>
				<%if(replyPageInfo.getReplyPageNum() < replyPageInfo.getReplyMaxPage()) {%><a href="news_detail.in?news_num=${newsDetail.news_num }&pageNum=${param.pageNum }&sId=${sessionScope.sId }&replyPageNum=${replyPageInfo.replyPageNum + 1}"><%}%>Next</a>
			</div>
			
		</article>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


