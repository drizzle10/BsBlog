<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/my_info.jsp</title>
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
				<li><a href="my_info.me?sId=${sessionScope.sId }">My Info</a></li>
				<li><a href="my_report.me?sId=${sessionScope.sId }">My Report</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->

		<article>
			<h1>My Info</h1>
			<table id="notice">
				<tr>
					<td>아이디</td>
					<td>${member.member_id }</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" value="${member.member_password }" readonly="readonly" style="border: none"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${member.member_name }</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>${member.member_address }</td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td>${member.member_postcode }</td>
				</tr>
				<tr>
					<td>휴대폰번호</td>
					<td>${member.member_phone }</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${member.member_date }</td>
				</tr>
			</table>

			<div id="table_search">
				<!-- * news-> news_detail로 넘어올때 주소창에 pageNum을 파라미터로 넘겼기 때문에 modify로 넘어갈때 param.pageNum으로 해야함 -->
				<input type="button" value="정보수정" class="btn" onclick="location.href='my_info_modify.me?sId=${sessionScope.sId }'"> 
				<input type="button" value="탈퇴" class="btn" onclick="location.href='my_info_delete?sId=${sessionScope.sId }'"> 
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


