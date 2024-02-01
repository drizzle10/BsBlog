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
			</ul>
		</nav>
		<!-- 본문 내용 -->
		<article>
			<h1>Carrer</h1>
			<figure class="ceo">
				<img id="carrer_img" src="<%=request.getContextPath() %>/resources/css/images/company/boseul_park.jpg">
				<figcaption>박보슬</figcaption>
			</figure>
			<h3>학력</h3>
			<p>2013.03 ~ 2018.08 영산대학교 경찰행정학과</p>
			<p>2010.03 ~ 2013.02 김해대청고등학교</p>
			<br>
			<h3>경력</h3>
			<p>2023.11 ~ 2024.01 (주)미네르바에듀 SW개발팀(인턴/팀원)</p>
			<p>2021.06 ~ 2022.06 (주)알리아스 총무팀(대리/팀장)</p>
			<p>2019.02 ~ 2020.09 (주)경동기계 자재관리팀(사원/팀원)</p>
			<br>
			<h3>교육 및 훈련</h3>
			<p>2022.06 ~ 2022.12 아이티윌 부산교육센터 e커머스 융합 풀스택 개발자를 위한 자바 프로그래밍 과정</p>
			<p>2018.07 ~ 2018.09 동양캐드전산회계컴퓨터학원 회계사무원양성과정</p>			
			<br>
			<h3>자격증</h3>
			<p>2023.11 정보처리기사</p>
			<p>2018.11 TAT 2급</p>
			<p>2018.09 FAT 1급</p>
			<p>2018.09 FAT 2급</p>
			<p>2012.12 컴퓨터활용능력2급</p>
			<p>2015.02 Microsoft Office Specialist 2007 Master</p>
			<br>
			<h3>수상</h3>
			<p>아이티윌 부산교육센터 주관 e커머스 융합 풀스택 개발자를 위한 자바 프로그래밍 과정 팀프로젝트 경진대회 최우수상</p>
		</article>


		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</div>
</body>
</html>


