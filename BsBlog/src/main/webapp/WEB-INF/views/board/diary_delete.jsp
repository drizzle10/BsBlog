<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/diary_delete.jsp</title>
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
			<h1>Diary Delete</h1>
			<form action="diary_deletePro.bo" method="post">
			<input type="hidden" name="diary_num" value="${param.diary_num }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="sId" value="${sessionScope.sId }">	
				<table id="notice">
					<tr>
						<td>패스워드</td>
						<td><input type="password" name="diary_password" ></td>
					</tr>
				</table>

				<div id="table_search">
					<input type="submit" value="글삭제" class="btn">
				</div>
			</form>
			<div class="clear"></div>
		</article>


		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


