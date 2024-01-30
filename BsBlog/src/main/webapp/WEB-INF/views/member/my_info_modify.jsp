<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/my_info_modify.jsp</title>
<link href="<%=request.getContextPath() %>/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/resources/css/subpage.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.js"></script>
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 헤더 들어가는곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 본문 메인 이미지 -->
		<div id="sub_img_center"></div>
		<!-- 왼쪽 메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="my_info.me?sId=${sessionScope.sId }">My Info</a></li>
				<li><a href="my_report.me?sId=${sessionScope.sId }">My Report</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->
		<article>
			<h1>My Info Update</h1>
			<form action="my_info_modifyPro.me" id="modify_info" method="post" onsubmit="return modify_info()">
			<input type="hidden" name="sId" value="${sessionScope.sId }">	
				<table id="notice">
					<tr>
						<td colspan="2"><input type="button" id="modify_info_btn" value="정보 수정시 안내사항"></td>
						<td></td>
				<div id="modify_info_modal">
				   <div id="modify_info_modal_content">
				   	  <button type="button" id="modify_info_modal_close_btn">X</button><br>
				   	   <h3>""정보 수정시 안내사항"</h3>
				   	   <p>1. 아이디는 수정할 수 없습니다.</p>
				   	   <p>2-1. 비밀번호는 수정시 영문자(8 ~ 20자), 숫자, 특수문자(!@#$%^&*) 필수입니다.</p>
				   	   <p>2-2. 비밀번호를 수정하지 않으신다면 번거로우시지만 기존 비밀번호를 한번 더 입력해주세요.</p>
				   	   <p>3. 이름 수정시 형식에 맞게 입력해주세요. ex) 홍길동(O) / 홍길동1234(X)</p>
				   	   <p>4-1. 주소, 우편번호 수정시 주소검색 버튼을 클릭하고 해당 주소를 검색 하면 주소와 우편번호가 나옵니다. </p>
				   	   <p>4-2. 해당 주소를 클릭하면 자동으로 주소와 우편번호가 입력됩니다. </p>
				   	   <p>5. 이메일은 수정할 수 없습니다. </p>
				   	   <p>5-1. 휴대폰 번호 수정시 형식에 맞게 입력해주세요.</p>
				   	   <p>5-2. ex)01012345678(O) / 010e1234567(X) / ex)010123456(X)</p>
				   </div>	
				</div>	
					</tr>	
					<tr>
						<td>아이디</td>
						<td><input type="text" id="modify_member_id" name="member_id" value="${member.member_id }" readonly="readonly"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" id="modify_member_password" name="member_password" onkeyup="password_check(this.value)" value="${member.member_password }"></td>
					</tr>
					<tr>
						<td>비밀번호 검사</td>
						<td id="modify_password_check_result"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" id="modify_member_name" name="member_name" onkeyup="name_check(this.value)" value="${member.member_name }"></td>
					</tr>
					<tr>
						<td>이름 검사</td>
						<td id="modify_name_check_result"></td>
					</tr>
					<tr>
						<td>주소 검색</td>
						<td><input type="button" value="주소검색"  onclick="Me_execDaumPostcode()" ></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type="text" id="modify_member_address" name="member_address" value="${member.member_address }"></td>
					</tr>
					<tr>
						<td>우편번호</td>
						<td><input type="text" id="modify_member_postcode" name="member_postcode" value=" ${member.member_postcode }"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="email" id="modify_member_email" name="member_email" value="${member.member_email }" readonly="readonly"></td>
					</tr>
					<tr>
						<td>휴대폰번호</td>
						<td><input type="text" id="modify_member_phone"  name="member_phone" value=" ${member.member_phone }" pattern="[0-9]+"></td>
					</tr>
				</table>

				<div id="table_search">
					<input type="submit" value="정보수정" class="btn">
				</div>
			</form>
			<div class="clear"></div>
		</article>
		
		<script type="text/javascript">
			/* 정보 수정 안내 모달창 */
			const modal = document.querySelector('#modify_info_modal');
			const btnOpenModal = document.querySelector('#modify_info_btn');
		
			btnOpenModal.addEventListener("click", ()=>{
		    	modal.style.display = "flex";
		  	});
		
			/* 정보 수정 안내 모달창 x버튼 누르면 종료*/
			$("#modify_info_modal_close_btn").click(function () {
				$("#modify_info_modal").fadeOut();
			});
			
		</script>
		
		<!-- 주소 api -->
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>  
		  
		<!-- 가입 관련 js -->
		<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/checkModifyInfo.js"></script>


		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


