<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>information/welcome.jsp</title>
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
				<li><a href="career.in">Career</a></li>
				<li><a href="news.in?sId=${sessionScope.sId }">News</a></li>
				<li><a href="#">Public Policy</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->
		<article>
			<h1>Welcome</h1>
			<br>
			<br>
			<br>
			<p>BsBlog에 오신걸 환영합니다!</p>
			<p>하고 싶은 말이 있으시면 GUESTBOOK에 적어주세요</p>
			<p>확인하는대로 답글 달아드리겠습니다!</p>
			<p>기타 문의사항이 있으시면 하단 메일로 보내주세요!</p>
		</article>


		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>


