<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guestbook/guestbook_detail.jsp</title>
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
				<li><a href="guestbook.gu?sId=${sessionScope.sId }">Guestbook</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->

		<article>
			<h1>Guestbook Content</h1>
			<table id="notice">
				<tr>
					<td>작성자</td>
					<td>${guestbookDetail.guestbook_id }</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${guestbookDetail.guestbook_date }</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>${guestbookDetail.guestbook_subject }</td>
				</tr>
				<tr>
				<!-- * 왜 news_realfile , news_file을 서야했는지?-->
					<td>파일</td>
					<td><a href="guestbookFileDownload?fileName=${guestbookDetail.guestbook_realfile }&guestbook_num=${guestbookDetail.guestbook_num}&pageNum=${param.pageNum}&sId=${sessionScope.sId }">${guestbookDetail.guestbook_file }</a></td>
				</tr>
				<tr>
					<td>내용</td>
					<td>${guestbookDetail.guestbook_content }</td>
				</tr>
			</table>

			<div id="table_search">
			<c:if test="${guestbookDetail.guestbook_id eq sessionScope.sId || sessionScope.sId eq 'admin'}">
				<!-- * news-> news_detail로 넘어올때 주소창에 pageNum을 파라미터로 넘겼기 때문에 modify로 넘어갈때 param.pageNum으로 해야함 -->
				<input type="button" value="글수정" class="btn" onclick="location.href='guestbook_modify.gu?guestbook_num=${guestbookDetail.guestbook_num}&pageNum=${param.pageNum }&sId=${sessionScope.sId }'"> 
				<input type="button" value="글삭제" class="btn" onclick="location.href='guestbook_delete.gu?guestbook_num=${guestbookDetail.guestbook_num}&pageNum=${param.pageNum }&sId=${sessionScope.sId }'"> 
			</c:if>
			<c:if test="${sessionScope.sId eq 'admin' }">	
				<input type="button" value="답변" class="btn" onclick="location.href='guestbook_reply_write.gu?guestbook_num=${guestbookDetail.guestbook_num}&pageNum=${param.pageNum }&sId=${sessionScope.sId }'"> 
			</c:if>
				<button class="btn" id="report_btn">신고</button>
				<div id="modal">
				   <div id="modal_content">
				   	  <button type="button" id="modal_close_btn">X</button><br>
				   	   <h1>신고</h1>
				   	  <form action="report.re" method="post" onsubmit="report()">
				   	  	<input type="hidden" value="${param.pageNum }" name="pageNum" id="pageNum">
				   	  	<input type="hidden" value="${guestbookDetail.guestbook_num }" name="report_guestbook_num" id="report_guestbook_num">
				   	  	<input type="hidden" value="${guestbookDetail.guestbook_subject }" name="report_guestbook_subject" id="report_guestbook_subject">
				   	  	<input type="hidden" value="${guestbookDetail.guestbook_content }" name="report_guestbook_content" id="report_guestbook_content">
				   	  	<input type="hidden" value="${guestbookDetail.guestbook_id }" name="report_guestbook_id" id="report_guestbook_id">
				   	  	<input type="hidden" value="${sessionScope.sId }" name="sId" id="sId">
				   	  	<table>
				   	  		<tr>
				   	  			<td>신고자</td>
				   	  			<td><input type="text" value="${sessionScope.sId }" name="report_id" id="report_id" readonly="readonly"></td>
				   	  		</tr>
				   	  		<tr>
				   	  			<td>신고사유</td>
				   	  			<td><textarea name="report_content" id="report_content" cols="30" rows="3" placeholder="신고사유는 1000자 미만으로 입력해주세요."></textarea></td>
				   	  		</tr>
				   	  		<tr>
				   	  			<td colspan="2"><input type="submit" value="신고"></td>
				   	  		</tr>
					   	  </table>
				   	  </form>
				   </div>	
				</div>				
				<input type="button" value="글목록" class="btn" onclick="location.href='guestbook.gu?pageNum=${param.pageNum}&sId=${sessionScope.sId }'">
			</div>

			<div class="clear"></div>
		</article>

		<script type="text/javascript">
			/* 신고 모달창 */
			const modal = document.querySelector('#modal');
			const btnOpenModal = document.querySelector('#report_btn');
		
			btnOpenModal.addEventListener("click", ()=>{
				/* 가입한 회원만 신고 가능*/
				if(${sessionScope.sId == null}){
					alert("로그인 후 사용 가능합니다.");
					location.href = "login.me";
				/* 본인의 글엔 신고 불가능*/	
				} else if(${sessionScope.sId == guestbookDetail.guestbook_id}) {
					alert("본인의 글에는 신고를 할 수 없습니다.");
					history.back();
				} else if(${guestbookDetail.guestbook_id eq 'admin'}){
					alert("관리자의 글에는 신고를 할 수 없습니다.");
					history.back();
				}
		    	modal.style.display = "flex";
		  	});
		
			/* 신고 모달창 x버튼 누르면 종료*/
			$("#modal_close_btn").click(function () {
				$("#modal").fadeOut();
			});
			
			/* 신고 완료 */
			function report() {
				alert("신고가 완료되었습니다.\n관리자의 확인 후 처리됩니다.");
			}
		</script>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


