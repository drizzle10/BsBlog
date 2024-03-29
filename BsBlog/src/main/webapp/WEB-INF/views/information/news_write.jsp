<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>information/news_write.jsp</title>
<link href="<%=request.getContextPath() %>/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/resources/css/subpage.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.js"></script>
<script type="text/javascript">
/* function setThumbnail(event) {
    var reader = new FileReader();

    reader.onload = function(event) {
      var img = document.createElement("img");
      img.setAttribute("src", event.target.result);
      document.querySelector("div#image_container").appendChild(img);
    };

    reader.readAsDataURL(event.target.files[0]);
  } */
</script>
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
				<li><a href="welcome.in">Welcome</a></li>
				<li><a href="career.in">Career</a></li>
				<li><a href="news.in?sId=${sessionScope.sId }">News</a></li>
			</ul>
		</nav>
		<!-- 본문 내용 -->
		<article>
			<h1>News Write</h1>
			<form action="news_writePro.in" method="post" enctype="multipart/form-data">
			<input type="hidden" name="sId" value="${sessionScope.sId }">	
			<input type="hidden" name="pageNum" value="${param.pageNum }">	
				<table id="notice">
					<tr>
						<td>작성자</td>
						<td><input type="text" name="news_id" value="${sessionScope.sId }"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="news_password"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="news_subject"></td>
					</tr>
					<tr>
						<td>파일</td>
						<!-- 
						* 주의! BoardVO 객체의 String 타입 파일명(board_file)과 동일한 name 속성이 아니라
						MultipartFile 객체 타입의 파일명과 동일해야한다! 
						* onchange="setThumbnail(event);"
						-->
						<td><input type="file" name="file"></td>
						<!--  <div id="image_container"></div> -->
					</tr>
					<tr>
						<td colspan="2"><textarea name="news_content" id="editor"></textarea></td>
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


