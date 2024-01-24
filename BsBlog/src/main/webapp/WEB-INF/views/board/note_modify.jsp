<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/note_modify.jsp</title>
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
			</ul>
		</nav>
		<!-- 본문 내용 -->
		<article>
			<h1>Note Update</h1>
			<form action="note_modifyPro.bo" method="post" enctype="multipart/form-data">
			<input type="hidden" name="note_num" value="${noteDetail.note_num }" />
			<input type="hidden" name="pageNum" value="${param.pageNum }" />
			<!-- * 기존 파일명도 함께 전달 이유?-->
			<input type="hidden" name="note_realfile" value="${noteDetail.note_realfile }" />
			<input type="hidden" name="note_file" value="${noteDetail.note_file }" />
				<table id="notice">
					<tr>
						<td>글쓴이</td>
						<td><input type="text" name="note_name" value="${noteDetail.note_name }"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="note_password" value="${noteDetail.note_password }"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="note_subject" value="${noteDetail.note_subject }"></td>
					</tr>
					<tr>
						<td>파일</td>
						<td><input type="file" name="file">기존 파일 : ${noteDetail.note_file }</td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea maxlength="2000" name="note_content">${noteDetail.note_content }</textarea></td>
					</tr>
				</table>

				<div id="table_search">
					<input type="submit" value="글수정" class="btn">
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


