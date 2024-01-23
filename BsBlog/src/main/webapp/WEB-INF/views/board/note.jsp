<%@page import="com.project.BsBlog.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/note.jsp</title>
<link href="<%=request.getContextPath() %>/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/resources/css/subpage.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.js"></script>
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>

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
			<h1>Note</h1>
			<table id="notice">
				<tr>
					<th class="tno">No.</th>
					<th class="ttitle">Title</th>
					<th class="twrite">Write</th>
					<th class="tdate">Date</th>
					<th class="tread">Read</th>
				</tr>
				
			</table>
			<div id="table_search">
			<!-- * location.href 알아보기 -->
				<input type="button" value="글쓰기" class="btn" onclick="">
			</div>
			<!-- 검색 기능 구현을 위한 form 태그 -->
			<!-- * get인 이유? -->
			<!-- * 드롭다운 박스 안하고 그냥 해보기 -->
			<div id="table_search">
				<form action="news.in" method="get">
					<select name="searchType">
						<option value="subject">제목</option>
						<option value="content">내용</option>
						<option value="subject_content">제목&내용</option>
						<option value="name">작성자</option>
					</select>
						<input type="text" name="keyword" class="input_box">
						<input type="submit" value="검색" class="btn">
				</form>
			</div>
			<!-- * 맨 위 임포트 이유? -->
			<!-- * 밑에 해석(qna_board_list_backup 참고 -->
			<!-- * 주소 적을때 공백 없어야험!!! -->
			
		</article>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>