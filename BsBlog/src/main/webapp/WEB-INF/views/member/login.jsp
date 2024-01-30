<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/login.jsp</title>
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
		  	</ul>
		  </nav>
		  <!-- 본문 내용 -->
		  <article>
		  	<h1>Login</h1>
		  	<form action="loginPro.me" method="post" id="join">
		  		<fieldset>
		  			<legend>Login Info</legend>
		  			<label>Id</label>
		  			<input type="text" name="member_id"><br>
		  			
		  			<label>Password</label>
		  			<input type="password" name="member_password"><br>
		  		</fieldset>
		
		  		<div class="clear"></div>
		  		<div id="buttons">
		  			<input type="submit" value="로그인" class="submit">
		  			<input type="reset" value="취소" class="cancel">
		  			<br>
		  			<input type="button" value="아이디 찾기" class="submit">
		  			<input type="button" value="비밀번호 찾기" class="cancel">
		  		</div>
		  	</form>
		  </article>
		  
		  
		<div class="clear"></div>  
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


