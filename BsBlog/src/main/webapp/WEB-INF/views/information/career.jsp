<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>information/carrer.jsp</title>
<link href="<%=request.getContextPath() %>/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/resources/css/subpage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>

		<!-- 본문들어가는 곳 -->
		<!-- 본문 메인 이미지 -->
		<div id="sub_img"></div>
		<!-- 왼쪽 메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="welcome.in">Welcome</a></li>
				<li><a href="career.in">Carrer</a></li>
				<li><a href="news.in">News</a></li>
				<li><a href="#">Public Policy</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->
		<article>
			<h1>Carrer</h1>
			<figure class="ceo">
				<img src="<%=request.getContextPath() %>/resources/css/images/company/CEO.jpg">
				<figcaption>Fun Web CEO Michael</figcaption>
			</figure>
			<p>Lorem ipsum ipsum dolor... tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus Lorem ipsum ipsum
				dolor...tellus Lorem ipsum ipsum dolor...tellus</p>
		</article>


		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>


