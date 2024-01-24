<%@page import="com.project.BsBlog.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>information/news.jsp</title>
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
				<li><a href="welcome.in">Welcome</a></li>
				<li><a href="career.in">Career</a></li>
				<li><a href="news.in">News</a></li>
				<li><a href="#">Public Policy</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->
		<article>
			<h1>News</h1>
			<table id="notice">
				<tr>
					<th class="tno">No.</th>
					<th class="ttitle">Title</th>
					<th class="twrite">Write</th>
					<th class="tdate">Date</th>
					<th class="tread">Read</th>
				</tr>
				<c:forEach var="news" items="${news }">
				<tr>
					<td>${news.news_num }</td>
					<td class="left"><a href="news_detail.in?news_num=${news.news_num }&pageNum=${pageInfo.pageNum}">${news.news_subject }</a></td>
					<td>${news.news_name }</td>
					<td>${news.news_date }</td>
					<td>${news.news_readcount }</td>
				</tr>
				</c:forEach>
			</table>
			<div id="table_search">
			<!-- * location.href 알아보기 -->
				<input type="button" value="글쓰기" class="btn" onclick="location.href='news_write.in'">
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
			<%PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo"); %>
			<div class="clear"></div>
			<div id="page_control">
				<%if(pageInfo.getPageNum() > pageInfo.getStartPage()) {%><a href="news.in?pageNum=${pageInfo.pageNum - 1}&searchType=${searchType}&keyword=${keyword}"><%}%>Prev</a>
				<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
                   <c:choose>
                      <c:when test="${i eq pageInfo.pageNum }"><a href="#">${i }</a></c:when>
                      <c:otherwise><a class="pageLink" href="news.in?pageNum=${i }&searchType=${searchType}&keyword=${keyword}">${i }</a></c:otherwise>
                   </c:choose>
                </c:forEach>
				<%if(pageInfo.getPageNum() < pageInfo.getMaxPage()) {%><a href="news.in?pageNum=${pageInfo.pageNum + 1}&searchType=${searchType}&keyword=${keyword}"><%}%>Next</a>
			</div>
		</article>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>