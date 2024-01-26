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
	// 만약 함수가 작동 않는다면 프로젝트 클린, 톰캣 클린하고 인터넷 개시 삭제 해보기
	// pom.xml json 파싱 필수
	function reply_write() {
		alert("되니");
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
						$("#reply_content").val("댓글은 1000자 이상 작성할 수 없습니다.");
					}, 
					error: function (result) {
						alert("댓글 작성이 실패되었습니다. 다시 시도해주세요.");
						console.log("에러 : " + result);
					}
					
				})
			}
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
		</article>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


