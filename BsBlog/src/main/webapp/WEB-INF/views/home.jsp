<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main/main.jsp</title>
<!-- * request.getContextPath()는 어떤 역할인가? -->
<link href="<%=request.getContextPath() %>/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/resources/css/front.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는 곳 -->
		<jsp:include page="inc/top.jsp"></jsp:include>
		  
		<div class="clear"></div>   
		<!-- 본문들어가는 곳 -->
		<!-- 메인 이미지 -->
		<iframe width="971" height="500" src="https://www.youtube.com/embed/mv20PoNo0GY?si=fGf32RtnhhRlllzP" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
		<q>출처 : 이슬라이브 유튜브</q>
		<!-- 본문 내용 -->
		<article id="front">
		  	<div id="solution">
			  	<div id="hosting">
				  	<h1>NEWS</h1>
				  	<br>
			  		<c:forEach var="news" items="${news }">
			  			<h3><a href="news_detail.in?news_num=${news.news_num }">${news.news_subject }</a></h3>
						<p>${news.news_date }</p>
						<br>
			  		</c:forEach>
			  	</div>
		  		<div id="hosting">
			  		<h1>GUESTBOOK</h1>
				  	<br>
			  		<c:forEach var="guestbook" items="${guestbook }">
			  			<h3><a href="guestbook_detail.gu?guestbook_num=${guestbook.guestbook_num }">${guestbook.guestbook_subject }</a></h3>
						<p>${guestbook.guestbook_date }</p>
						<br>
			  		</c:forEach>
		  		</div>
		  	</div>
		  	<div>
				
				
		  	</div>
		  	<div class="clear"></div>
			<div class="clear"></div>  
		
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>