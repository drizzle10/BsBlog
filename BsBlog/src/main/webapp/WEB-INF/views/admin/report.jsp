<%@page import="com.project.BsBlog.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin/report.jsp</title>
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
				<li><a href="member.ad?member_id=${sessionScope.sId }">Member</a></li>
				<li><a href="report.ad?member_id=${sessionScope.sId }">Report</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->
		<article>
			<h1>Report</h1>
			<div>
				<a href="report.ad">전체</a> | <a href="report_hold.ad">처리대기</a> | <a href="report_complete.ad">처리완료</a>		
			</div>
			<table id="notice">
				<tr>
					<th class="twrite">신고상태</th>
					<th class="ttitle">신고자</th>
					<th class="ttitle">신고일</th>
				</tr>
				<c:forEach var="report" items="${reportList }">
					<tr>
						<td>${report.report_status }</td>
						<td class="left" onclick="location.href='report_detail.ad?report_idx=${report.report_idx }&pageNum=${pageInfo.pageNum}&sId=${sessionScope.sId }'">${report.report_id }</td>
						<td>${report.report_date }</td>
					</tr>
				</c:forEach>
			</table>
			<div id="table_search">
				<form action="report.ad" method="get">
					<select name="searchType">
						<option value="id">아이디</option>
						<option value="content">신고내용</option>
					</select>
						<input type="text" name="keyword" class="input_box">
						<input type="submit" value="검색" class="btn">
				</form>
			</div>

			<%PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo"); %>
			<div class="clear"></div>
			<div id="page_control">
				<%if(pageInfo.getPageNum() > pageInfo.getStartPage()) {%><a href="report.ad?pageNum=${pageInfo.pageNum - 1}&searchType=${searchType}&keyword=${keyword}"><%}%>Prev</a>
				<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
                   <c:choose>
                      <c:when test="${i eq pageInfo.pageNum }"><a href="#">${i }</a></c:when>
                      <c:otherwise><a class="pageLink" href="report.ad?pageNum=${i }&searchType=${searchType}&keyword=${keyword}">${i }</a></c:otherwise>
                   </c:choose>
                </c:forEach>
				<%if(pageInfo.getPageNum() < pageInfo.getMaxPage()) {%><a href="report.ad?pageNum=${pageInfo.pageNum + 1}&searchType=${searchType}&keyword=${keyword}"><%}%>Next</a>
			</div>
		</article>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>