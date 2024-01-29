<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin/member_detail.jsp</title>
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
			<h1>Member Detail</h1>
			<table id="notice">
				<tr>
					<td>회원번호</td>
					<td>${memberDetail.member_idx }</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>${memberDetail.member_id }</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${memberDetail.member_name }</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>${memberDetail.member_address }</td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td>${memberDetail.member_postcode }</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${memberDetail.member_email }</td>
				</tr>
				<tr>
					<td>이메일 인증 여부</td>
					<td>${memberDetail.member_email_auth }</td>
				</tr>
				<tr>
					<td>가입일</td>
					<td>${memberDetail.member_date }</td>
				</tr>
			</table>

			<div id="table_search">
				<input type="button" value="회원목록" class="btn" onclick="location.href='member.ad?pageNum=${param.pageNum }&sId=${sessionScope.sId }'">
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


