<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/diary_modify.jsp</title>
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
				<li><a href="diary.bo?sId=${sessionScope.sId }">Diary</a></li>
				<li><a href="note.bo?sId=${sessionScope.sId }">Note</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->
		<article>
			<h1>Diary Update</h1>
			<form action="diary_modifyPro.bo" method="post" enctype="multipart/form-data">
			<input type="hidden" name="diary_num" value="${diaryDetail.diary_num }" />
			<input type="hidden" name="pageNum" value="${param.pageNum }" />
			<input type="hidden" name="sId" value="${sessionScope.sId }">	
			<!-- * 기존 파일명도 함께 전달 이유?-->
			<input type="hidden" name="diary_realfile" value="${diaryDetail.diary_realfile }" />
			<input type="hidden" name="diary_file" value="${diaryDetail.diary_file }" />
				<table id="notice">
					<tr>
						<td>작성자</td>
						<td><input type="text" name="diary_id" value="${diaryDetail.diary_id }"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="diary_password" value="${diaryDetail.diary_password }"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="diary_subject" value="${diaryDetail.diary_subject }"></td>
					</tr>
					<tr>
						<td>파일</td>
						<td><input type="file" name="file">기존 파일 : ${diaryDetail.diary_file }</td>
					</tr>
					<tr>
						<td colspan="2"><textarea id="editor" name="diary_content">${diaryDetail.diary_content }</textarea></td>
					</tr>
				</table>

				<div id="table_search">
					<input type="submit" value="글수정" class="btn">
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
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


