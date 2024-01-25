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
		<jsp:include page="../inc/top.jsp"></jsp:include>
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
		  	<h1>Join</h1>
		  	<form action="joinPro.me" method="post" id="join" name="fr" onsubmit="return checkForm()">
		  		<div style="border: 1px solid lightgray; width: 670px; height: 670px;">
		  			<div style="display: flex; width: 100%; height: 100%; ">
		  				<div style="flex-direction: column; width: 100%; height: 14%;">
			  				<div style="display: flex; width: 100%; height: 100%; border-bottom: 1px solid lightgray;">
				  				<div style="flex-direction: row; width: 30%; height: 100%; ">
				  					<input type="text" value="아이디" style="width: 92%; height: 89%; border: none; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" readonly="readonly">
				  				</div>
				  				<div style="flex-direction: row; width: 70%; height: 100%;">
				  					<div style="flex-direction: column; width: 100%; height: 33%">
										<input type="button" value="중복체크" id="member_id_dup_btn" onclick="dupId()">
				  					</div>
				  					<div style="flex-direction: column; width: 100%; height: 33%">
				  						<input type="text" name="member_id" class="id" id="member_id" style="width: 98%; height: 89%; border: 1px solid gray; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" placeholder="아이디를 입력해주세요." required="required" onkeyup="checkId(this.value)">
				  					</div>
				  					<div id="checkIdResult"style="flex-direction: column; width: 100%; height: 33%">
				  						<!-- 자바스크립트에 의해 메세지가 표시될 공간 -->
				  					</div>
				  				</div>
			  				</div>
			  				<div style="display: flex; width: 100%; height: 100%; border-bottom: 1px solid lightgray;">
				  				<div style="flex-direction: row; width: 30%; height: 100%; ">
				  					<input type="text" value="비밀번호" style="width: 92%; height: 89%; border: none; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" readonly="readonly">
				  				</div>
				  				<div style="flex-direction: row; width: 70%; height: 100%;">
				  					<div style="flex-direction: column; width: 100%; height: 50%">
										<input type="password" name="member_password" id="member_passward" style="width: 98%; height: 89%; border: 1px solid gray; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" placeholder="비밀번호를 입력해주세요." required="required" onkeyup="checkPasswd(this.value)">				  					
									</div>
				  					<div id="checkPasswdResult" style="flex-direction: column; width: 100%; height: 50%">
				  						<!-- 자바스크립트에 의해 메세지가 표시될 공간 -->
				  					</div>
				  				</div>
			  				</div>
			  				<div style="display: flex; width: 100%; height: 100%; border-bottom: 1px solid lightgray;">
				  				<div style="flex-direction: row; width: 30%; height: 100%; ">
				  					<input type="text" value="이름" style="width: 92%; height: 89%; border: none; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" readonly="readonly">
				  				</div>
				  				<div style="flex-direction: row; width: 70%; height: 100%;">
				  					<div style="flex-direction: column; width: 100%; height: 50%">
										<input type="text" name="member_name" id="member_name" style="width: 98%; height: 89%; border: 1px solid gray; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" placeholder="이름을 입력해주세요." required="required" onkeyup="checkName(this.value)">				  					
									</div>
				  					<div id="checkNameResult" style="flex-direction: column; width: 100%; height: 50%">
				  						<!-- 자바스크립트에 의해 메세지가 표시될 공간 -->
				  					</div>
				  				</div>
			  				</div>
			  				<div style="display: flex; width: 100%; height: 100%; border-bottom: 1px solid lightgray;">
				  				<div style="flex-direction: row; width: 30%; height: 100%; ">
				  					<input type="text" value="주소" style="width: 92%; height: 89%; border: none; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" readonly="readonly">
				  				</div>
				  				<div style="flex-direction: row; width: 70%; height: 100%;">
				  					<div style="flex-direction: column; width: 100%; height: 33%">
				  						<input type="button" value="주소검색" id="member_address_search_btn" onclick="Me_execDaumPostcode()" required="required">
									</div>
				  					<div style="flex-direction: column; width: 100%; height: 33%">
										<input type="text" name="member_postcode" id="member_postcode" style="width: 98%; height: 89%; border: 1px solid gray; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" placeholder="우편번호를 입력해주세요." required="required">				  					
									</div>
				  					<div style="flex-direction: column; width: 100%; height: 33%">
				  						<input type="text" name="member_address" id="member_address" style="width: 98%; height: 89%; border: 1px solid gray; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" placeholder="주소를 선택해주세요." required="required"><br><br> 
				  					</div>
				  				</div>
			  				</div>
			  				<div style="display: flex; width: 100%; height: 100%; border-bottom: 1px solid lightgray;">
				  				<div style="flex-direction: row; width: 30%; height: 100%; ">
				  					<input type="text" value="이메일" style="width: 92%; height: 89%; border: none; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" readonly="readonly">
				  				</div>
				  				<div style="flex-direction: row; width: 70%; height: 100%;">
				  					<div style="flex-direction: column; width: 100%; height: 50%">
										<input type="button" id="member_mail_auth_check_btn" value="인증번호 전송">
									</div>
				  					<div style="flex-direction: column; width: 100%; height: 50%">
										<input type="email" name="member_email" id="member_email" style="width: 98%; height: 89%; border: 1px solid gray; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" placeholder="이메일 주소를 입력해주세요." required="required">
									</div>
				  				</div>
			  				</div>
			  				<div style="display: flex; width: 100%; height: 100%; border-bottom: 1px solid lightgray;">
				  				<div style="flex-direction: row; width: 30%; height: 100%; ">
				  					<input type="text" value="이메일 인증" style="width: 92%; height: 89%; border: none; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" readonly="readonly">
				  				</div>
				  				<div style="flex-direction: row; width: 70%; height: 100%;">
				  					<div style="flex-direction: column; width: 100%; height: 100%">
										<input type="text" id="member_email_auth" style="width: 98%; height: 89%; border: 1px solid gray; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" placeholder="인증번호를 입력해주세요." required="required">
									</div>
				  				</div>
			  				</div>
			  				<div style="display: flex; width: 100%; height: 100%; border-bottom: 1px solid lightgray;">
				  				<div style="flex-direction: row; width: 30%; height: 100%; ">
				  					<input type="text" value="휴대폰 번호" style="width: 92%; height: 89%; border: none; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" readonly="readonly">
				  				</div>
				  				<div style="flex-direction: row; width: 70%; height: 100%;">
				  					<div style="flex-direction: column; width: 100%; height: 100%">
										<input type="text" name="member_phone" id="member_phone" style="width: 92%; height: 89%; border: none; background: none; color: gray; font-size: 2em; text-align: center; vertical-align: middle;" placeholder="휴대폰 번호를 입력해주세요.(숫자만 입력 가능)" required="required" pattern="[0-9]+">
									</div>
				  				</div>
			  				</div>
			  				
		  				</div>
		  			</div>
		  		</div>
		  		<div class="clear"></div>
		  		<div id="buttons">
		  			<input type="submit" value="Submit" class="submit">
		  			<input type="reset" value="Cancel" class="cancel">
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


