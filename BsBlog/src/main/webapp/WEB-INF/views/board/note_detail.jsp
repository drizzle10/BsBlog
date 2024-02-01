<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/note_detail.jsp</title>
<link href="<%=request.getContextPath() %>/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/resources/css/subpage.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.js"></script>

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
				<li><a href="diary.bo?sId=${sessionScope.sId }">Diary</a></li>
				<li><a href="note.bo?sId=${sessionScope.sId }">Note</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->

		<article>
			<h1>Note Content</h1>
			<table id="notice">
				<tr>
					<td>제목</td>
					<td colspan="3">${noteDetail.note_subject }</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td colspan="3">${noteDetail.note_id }</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${noteDetail.note_date }</td>
				</tr>
				<tr>
				<!-- * 왜 news_realfile , news_file을 서야했는지?-->
					<td>파일</td>
					<td colspan="3"><a href="noteFileDownload?fileName=${noteDetail.note_realfile }&note_num=${noteDetail.note_num}&pageNum=${param.pageNum}&sId=${sessionScope.sId}"><img src="/BsBlog/resources/upload/upload/${noteDetail.note_realfile}"></a></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3">${noteDetail.note_content }</td>
				</tr>
			</table>
			<c:if test="${sessionScope.sId ne null }">
				<div id="heart" style="text-align: right; vertical-align: middle;">
					좋아요 <img src="/BsBlog/resources/css/images/heart${heart}.png" width = 20px; height = 20px; onclick="checkHeart()">
				</div>
			</c:if>
			<div id="table_search">
				<c:if test="${sessionScope.sId eq 'admin' }">
					<!-- * news-> news_detail로 넘어올때 주소창에 pageNum을 파라미터로 넘겼기 때문에 modify로 넘어갈때 param.pageNum으로 해야함 -->
					<input type="button" value="글수정" class="btn" onclick="location.href='note_modify.bo?note_num=${noteDetail.note_num}&pageNum=${param.pageNum }&sId=${sessionScope.sId }'"> 
					<input type="button" value="글삭제" class="btn" onclick="location.href='note_delete.bo?note_num=${noteDetail.note_num}&pageNum=${param.pageNum }&sId=${sessionScope.sId }'"> 
				</c:if>	
					<input type="button" value="글목록" class="btn" onclick="location.href='note.bo?pageNum=${param.pageNum }&sId=${sessionScope.sId }'">
			</div>

			<div class="clear"></div>
		</article>

		<script type="text/javascript">
			// 좋아요 추가
			function checkHeart() {
				if(${heart} == 2){ // 빈 하트일때 클릭한다면 꽉찬 하트로 변경
					$.ajax({
						url: "addHeart",
						type: "GET",
						data: {
							note_num : ${noteDetail.note_num}
						},
						success: function (heart) {
							console.log(heart);
							if(heart == 2) {
								alert("좋아요에 실패하였습니다.");
							} else {
								$('#heart').html("<img src='/BsBlog/resources/images/heart" + heart + ".png'");
								location.reload();
							}
						}
					});
				} else if(${heart} == 1) { // 꽉찬 하트일때 클릭한다면 빈 하트로 변경
					// 좋아요 해제
					$.ajax({
						url: "deleteHeart",
						type: "GET",
						data: {
							note_num : ${noteDetail.note_num}
						},
						success: function (heart) {
							console.log(heart);
							if(heart == 1) {
								alert("안좋아요에 실패하였습니다.");
							} else {
								$('#heart').html("<img src='/BsBlog/resources/images/heart" + heart + ".png'");
								location.reload();
							}
						}
					});
				}
			};
		</script>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


