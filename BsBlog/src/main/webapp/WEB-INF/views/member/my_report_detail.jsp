<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/my_report_detail.jsp</title>
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
				<li><a href="my_heart.me?sId=${sessionScope.sId }">My Heart</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->

		<article>
			<h1>My Report Content</h1>
			<table id="notice">
				<tr>
					<td>신고번호</td>
					<td>${reportDetail.report_idx }</td>
				</tr>
				<tr>
					<td>신고자</td>
					<td>${reportDetail.report_id }</td>
				</tr>
				<tr>
					<td>신고일</td>
					<td>${reportDetail.report_date }</td>
				</tr>
				<tr>
					<td>신고 내용</td>
					<td>${reportDetail.report_content }</td>
				</tr>
				<tr>
					<td>피신고자</td>
					<td>${reportDetail.report_guestbook_id }</td>
				</tr>
				<tr>
					<td>피신고 제목</td>
					<td><a href="guestbook_detail.gu?guestbook_num=${reportDetail.report_guestbook_num }&pageNum=${pageInfo.pageNum}&sId=${sessionScope.sId }'">${reportDetail.report_guestbook_subject }</a></td>
				</tr>
				<tr>
					<td>피신고 내용</td>
					<td>${reportDetail.report_guestbook_content }</td>
				</tr>
				<tr>
					<td>신고 상태</td>
					<td>${reportDetail.report_status }</td>
				</tr>
			</table>

			<div id="table_search">
				<input type="button" value="신고목록" class="btn" onclick="location.href='my_report.me?pageNum=${param.pageNum }&sId=${sessionScope.sId }'"> 
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


