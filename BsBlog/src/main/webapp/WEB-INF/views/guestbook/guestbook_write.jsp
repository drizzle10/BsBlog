<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guestbook/guestbook_write.jsp</title>
<link href="<%=request.getContextPath() %>/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/resources/css/subpage.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.js"></script>

</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>

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
			<h1>Guestbook Write</h1>
			<form action="guestbook_writePro.gu" method="post" enctype="multipart/form-data">
			<input type="hidden" name="sId" value="${sessionScope.sId }">	
				<table id="notice">

					<tr>
						<td>작성자</td>
						<td><input type="text" name="guestbook_id" value="${sessionScope.sId }"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="guestbook_password"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="guestbook_subject"></td>
					</tr>
					<tr>
						<td>파일</td>
						<td><input type="file" name="file"></td>
					</tr>
					<tr>
						<td colspan="2"><textarea name="guestbook_content" id="editor"></textarea></td>
					</tr>
				</table>

				<div id="table_search">
					<input type="submit" value="글쓰기" class="btn">
				</div>
			</form>
			<div class="clear"></div>
		</article>
		
		<!-- ckeditor -->
		<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
		<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
		<script type="text/javascript">
			ClassicEditor.create( document.querySelector( '#editor' ), {
			    language: "ko",
			    toolbar: [ 'heading', '|', 'fontFamily', 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote'],
			    placeholder: '내용을 입력해주세요',
			 } )
		    .then( editor => {
		        console.log( editor );
		    } )
		    .catch( error => {
		        console.error( error );
		    } );
		</script>
	
		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
	</div>
	
	
</body>
</html>


