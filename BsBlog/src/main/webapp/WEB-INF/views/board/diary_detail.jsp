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
				<li><a href="diary.bo">Diary</a></li>
				<li><a href="note.bo">Note</a></li>
				<li><a href="etc.bo">Etc</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->

		<article>
			<h1>Diary Content</h1>
			<table id="notice">
				<tr>
					<td>글쓴이</td>
					<td colspan="3">${diaryDetail.diary_name }</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${diaryDetail.diary_date }</td>
					<td>조회수</td>
					<td>${diaryDetail.diary_readcount }</td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3">${diaryDetail.diary_subject }</td>
				</tr>
				<tr>
				<!-- * 왜 news_realfile , news_file을 서야했는지?-->
					<td>파일</td>
					<td colspan="3"><a href="diaryFileDownload?fileName=${diaryDetail.diary_realfile }&diary_num=${diaryDetail.diary_num}&pageNum=${param.pageNum}">${diaryDetail.diary_file }</a></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3">${diaryDetail.diary_content }</td>
				</tr>
			</table>

			<div id="table_search">
				<!-- * news-> news_detail로 넘어올때 주소창에 pageNum을 파라미터로 넘겼기 때문에 modify로 넘어갈때 param.pageNum으로 해야함 -->
				<input type="button" value="글수정" class="btn" onclick="location.href='diary_modify.bo?diary_num=${diaryDetail.diary_num}&pageNum=${param.pageNum }'"> 
				<input type="button" value="글삭제" class="btn" onclick="location.href='diary_delete.bo?diary_num=${diaryDetail.diary_num}&pageNum=${param.pageNum }'"> 
				<input type="button" value="글목록" class="btn" onclick="location.href='diary.bo'">
			</div>

			<div class="clear"></div>
		</article>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


