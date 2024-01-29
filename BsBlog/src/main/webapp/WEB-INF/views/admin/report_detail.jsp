<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin/report_detail.jsp</title>
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
				<li><a href="member.ad?member_id=${sessionScope.sId }">Member</a></li>
				<li><a href="report.ad?member_id=${sessionScope.sId }">Report</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->

		<article>
			<h1>Report Content</h1>
			<table id="notice">
				<tr>
					<td>신고번호</td>
					<td>${reportDetail.report_idx }</td>
				</tr>
				<tr>
					<td>신고자</td>
					<td>${reportDetail.report_id }</td>
				</tr>
				<tr>
					<td>신고일</td>
					<td>${reportDetail.report_date }</td>
				</tr>
				<tr>
					<td>신고 내용</td>
					<td>${reportDetail.report_content }</td>
				</tr>
				<tr>
					<td>피신고자</td>
					<td>${reportDetail.report_guestbook_id }</td>
				</tr>
				<tr>
					<td>피신고 제목</td>
					<td><a href="guestbook_detail.gu?guestbook_num=${reportDetail.report_guestbook_num }&pageNum=${pageInfo.pageNum}&sId=${sessionScope.sId }'">${reportDetail.report_guestbook_subject }</a></td>
				</tr>
				<tr>
					<td>피신고 내용</td>
					<td>${reportDetail.report_guestbook_content }</td>
				</tr>
			</table>

			<div id="table_search" class="report_status">
				<input type="button" value="처리반려" id="report_companion_btn" class="report_status_btn"> 
				<input type="button" value="처리완료" id="report_complete_btn" class="report_status_btn"> 
			</div>
			<div id="table_search">
				<input type="button" value="신고목록" class="btn" onclick="location.href='report.gu?pageNum=${param.pageNum }&sId=${sessionScope.sId }'"> 
			</div>

			<div class="clear"></div>
		</article>
		
		<script type="text/javascript">
		// * 버튼들을 여러개 만들어놓고 여러 버튼들에 같은 클래스 이름을 줘서 클릭시 value를 가져올 수 있음
		/*
			<input type="button" value="처리완료" id="report_complete_btn" class="report_status_btn"> 
			<input type="button" value="처리대기" id="report_hold_btn" class="report_status_btn"> 
			<input type="button" value="처리보류" id="report_companion_btn" class="report_status_btn"> 
			$('#report_status_btn').on('click', (e) => {
				console.log(e.target.value)
			}
		*/
		$('.report_status_btn').on('click', (e) => {
			  var report_status = e.target.value;
			  $.ajax({
				url: "report_status.ad",
				type: "GET",
				data: {
					report_status : report_status,
					report_idx : ${reportDetail.report_idx},
					pageNum : ${param.pageNum},
					sId : "${sessionScope.sId}"
				},
				success: function () {
					alert("신고 관련 처리가 완료되었습니다.");
				},
				fail: function () {
					alert("신고 관련 처리가 미완료되었습니다. 다시 시도해 주세요.")
				}
			  })
		});
		</script>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


