<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
  <!-- login join -->
  <div id="login"><a href="#">login</a> | <a href="#">join</a></div>
  <div class="clear"></div>
  <!-- 로고들어가는 곳 -->
  
  <!-- * 왜 /BsBlog를 해야 매핑이 되는가? -->
  <div id="logo"><a href="/BsBlog"><img src="<%=request.getContextPath() %>/resources/css/images//logo.gif"></a></div>
  <!-- 메뉴들어가는 곳 -->
  <nav id="top_menu">
  	<ul>
  		<li><a href="/BsBlog">HOME</a></li>
  		<li><a href="welcome.in">INFORMATION</a></li>
  		<li><a href="../board/diary.jsp">BOARD</a></li>
  		<li><a href="../guestbook/notice.jsp">GUEST BOOK</a></li>
  		<li><a href="../contact/mailForm.jsp">CONTACT</a></li>
  	</ul>
  </nav>
</header>