<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/join.jsp</title>
<link href="<%=request.getContextPath() %>/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/resources/css/subpage.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
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
		  	<h1>Join</h1>
		  	<form action="joinPro.me" method="post" id="join" name="fr" onsubmit="return form_check()">
		  		<fieldset>
		  			<div style="border-bottom: 1px solid lightgray; padding: 20px 0;">
			  			<label>아이디</label>
			  			<input type="button" value="중복확인" class="btn" id="idDup_checkBtn" onclick="idDup_check()"><br>
			  			<input type="text" name="member_id" class="id" id="member_id" placeholder="아이디를 입력해주세요." required="required" onkeyup="id_check(this.value)" style="margin-left: 140px">
			  			<div id="id_check_result" style="margin-left: 140px;"><!-- 자바스크립트에 의해 메세지가 표시될 공간 --></div>
		  			</div>
		  			
		  			<div style="border-bottom: 1px solid lightgray; padding: 20px 0;">
			  			<label>비밀번호</label>
			  			<input type="password" name="member_password" id="member_passward" placeholder="비밀번호를 입력해주세요." required="required" onkeyup="password_check(this.value)"><br> 			
			  			<div id="password_check_result" style="margin-left: 140px;"><!-- 자바스크립트에 의해 메세지가 표시될 공간 --></div> 
					</div>
					
					<div style="border-bottom: 1px solid lightgray; padding: 20px 0;">		  			
			  			<label>이름</label>
			  			<input type="text" name="member_name" id="member_name" placeholder="이름을 입력해주세요." required="required" onkeyup="name_check(this.value)"><br>
			  			<div id="name_check_result" style="margin-left: 140px;"><!-- 자바스크립트에 의해 메세지가 표시될 공간 --></div> 
		  			</div>
		  			
		  			<div style="border-bottom: 1px solid lightgray; padding: 20px 0;">
			  			<label>주소</label>
						<input type="button" value="주소검색" onclick="Me_execDaumPostcode()" required="required"><br>
						<input type="text" name="member_postcode" id="member_postcode" placeholder="우편번호를 선택해주세요" required="required" style="margin-left: 140px;">
			  			<input type="text" name="member_address" id="member_address" placeholder="주소를 선택해주세요." required="required"><br> 
		  			</div>
		  			
		  			<div style="border-bottom: 1px solid lightgray; padding: 20px 0;">
			  			<label>이메일</label>
			  			<input type="button" id="mailDup_checkBtn" class="btn" value="중복확인" onclick="mailDup_check()"><br>
			  			<input type="email" name="member_email" id="member_email" placeholder="이메일 주소를 입력해주세요." required="required" style="margin-left: 140px;"> 
			  			<div id="mail_check_result"><!-- 자바스크립트에 의해 메세지가 표시될 공간 --></div> 
		  			</div>
		  			
			  		<div style="border-bottom: 1px solid lightgray; padding: 20px 0;">
			  			<label>이메일 인증</label>
			  			<input type="button" class="btn" id="mailAuth_checkBtn" value="인증번호 전송"><br>
			  			<input type="text" id="mailAuth" placeholder="인증번호를 입력해주세요." style="margin-left: 140px;"><br>
			  			<div id="mailAuth_check_warn" style="margin-left: 140px;"></div> 
		  			</div>
		  			
		  			<div style="border-bottom: 1px solid lightgray; padding: 20px 0;">
			  			<label>휴대폰 번호</label>
		  				<input type="text" name="member_phone" id="member_phone" placeholder="휴대폰 번호를 입력해주세요.(숫자만 입력 가능)" required="required" pattern="[0-9]+"><br>
		  			</div> 
		  		</fieldset>
		  		<div class="clear"></div>
		  		<div id="buttons">
		  			<input type="submit" value="가입" class="submit">
		  			<input type="reset" value="취소" class="cancel">
		  		</div>
		  	</form>
		  </article>
		
		<!-- 주소 api -->
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>  
		  
		<!-- 가입 관련 js -->
		<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/checkJoin.js"></script>
		  
		<div class="clear"></div>  
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


