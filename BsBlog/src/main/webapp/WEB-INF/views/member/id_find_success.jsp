<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/id_find_success.jsp</title>
<link href="<%=request.getContextPath() %>/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/resources/css/subpage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 헤더 들어가는곳 -->
		  
		<!-- 본문들어가는 곳 -->
		  <!-- 본문 메인 이미지 -->
		  <div id="sub_img_member"></div>
		  <!-- 왼쪽 메뉴 -->
		  <nav id="sub_menu">
		  	<ul>
		  		<li><a href="join.me">Join</a></li>
		  		<li><a href="login.me">Login</a></li>
		  		<li><a href="id_find.me">Id Find</a></li>
		  		<li><a href="password_find.me">Password Find</a></li>
		  	</ul>
		  </nav>
		  <!-- 본문 내용 -->
		  <article>
		  	<h1>Id Find</h1>
		  		<fieldset>
		  			<label>입력하신 이메일로 아이디를 전송하였습니다.</label><br>
		  			<label>이메일을 확인하여 다시 로그인해주세요.</label><br>
		  		</fieldset>
		
		  		<div class="clear"></div>
		  		<div id="buttons">
		  		<br>
		  			<input type="button" value="홈" class="home_btn" onclick="location.href='/BsBlog'">
		  			<input type="button" value="로그인" class="login_btn" onclick="location.href='login.me'">
		  		</div>
		  </article>
		  
		  
		<div class="clear"></div>  
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


