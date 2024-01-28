<%@page import="com.project.BsBlog.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guestbook/guestbook.jsp</title>
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
				<li><a href="guestbook.gu?sId=${sessionScope.sId }">Guestbook</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->
		<article>
			<h1>Guestbook</h1>
			<table id="notice">
				<tr>
					<th class="ttitle">제목</th>
					<th class="twrite">작성자</th>
					<th class="tdate">작성일</th>
					<th class="tread">조회수</th>
				</tr>
				<c:forEach var="guestbook" items="${guestbook }">
					<tr>
						<td class="left" onclick="location.href='guestbook_detail.gu?guestbook_num=${guestbook.guestbook_num }&pageNum=${pageInfo.pageNum}&sId=${sessionScope.sId }'">${guestbook.guestbook_subject }</td>
						<td>${guestbook.guestbook_id }</td>
						<td>${guestbook.guestbook_date }</td>
						<td>${guestbook.guestbook_readcount }</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${not empty sessionScope.sId }">	
				<div id="table_search">
				<!-- * location.href 알아보기 -->
					<input type="button" value="글쓰기" class="btn" onclick="location.href='guestbook_write.gu?pageNum=${pageInfo.pageNum }&sId=${sessionScope.sId}'">
				</div>
			</c:if>
			<!-- 검색 기능 구현을 위한 form 태그 -->
			<!-- * get인 이유? -->
			<!-- * 드롭다운 박스 안하고 그냥 해보기 -->
			<div id="table_search">
				<form action="guestbook.gu" method="get">
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
			
			<%PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo"); %>
			<div class="clear"></div>
			<div id="page_control">
				<%if(pageInfo.getPageNum() > pageInfo.getStartPage()) {%><a href="guestbook.gu?pageNum=${pageInfo.pageNum - 1}&searchType=${searchType}&keyword=${keyword}"><%}%>Prev</a>
				<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
                   <c:choose>
                      <c:when test="${i eq pageInfo.pageNum }"><a href="#">${i }</a></c:when>
                      <c:otherwise><a class="pageLink" href="guestbook.gu?pageNum=${i }&searchType=${searchType}&keyword=${keyword}">${i }</a></c:otherwise>
                   </c:choose>
                </c:forEach>
				<%if(pageInfo.getPageNum() < pageInfo.getMaxPage()) {%><a href="guestbook.gu?pageNum=${pageInfo.pageNum + 1}&searchType=${searchType}&keyword=${keyword}"><%}%>Next</a>
			</div>
			
		</article>
		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>