<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script>
	function logout() {
		let result = confirm("로그아웃 하시겠습니까?");
		
		if(result) {
			location.href = "logout.me";
		}
	}
</script>
<header>
  <!-- login join -->
  <c:choose>
  	<c:when test="${empty sessionScope.sId }">
  		 <div id="login"><a href="login.me">Login</a> | <a href="join.me">Join</a></div>
  	</c:when>
  	<c:when test="${sessionScope.sId eq 'admin' }">
	 	 <div id="login"><a href="member.ad?member_id=${sessionScope.sId }">${sessionScope.sId }님</a> | <a href="javascript:logout()">Logout</a></div> 
  	</c:when>
	 <c:otherwise>
  		 <div id="login"><a href="member_info.me?member_id=${sessionScope.sId }">${sessionScope.sId }님</a> | <a href="javascript:logout()">Logout</a></div>
	 </c:otherwise>
  </c:choose>
  <div class="clear"></div>
  <!-- 로고들어가는 곳 -->
  
  <!-- * 왜 /BsBlog를 해야 매핑이 되는가? -->
  <div id="logo"><a href="/BsBlog"><img src="<%=request.getContextPath() %>/resources/css/images//logo.gif"></a></div>
  <!-- 메뉴들어가는 곳 -->
  <nav id="top_menu">
  	<ul>
  		<li><a href="/BsBlog">HOME</a></li>
  		<li><a href="welcome.in?sId=${sessionScope.sId }">INFORMATION</a></li>
  		<li><a href="diary.bo?sId=${sessionScope.sId }">BOARD</a></li>
  		<li><a href="guestbook.gu?sId=${sessionScope.sId }">GUESTBOOK</a></li>
  		<li><a href="../contact/mailForm.jsp">CONTACT</a></li>
  	</ul>
  </nav>
</header>