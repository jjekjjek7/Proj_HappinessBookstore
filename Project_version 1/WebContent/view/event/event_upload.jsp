<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>행복을 담는 행복서점</title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/event.css">
</head>
<body>
<%@ include file="../../view/public/header.jsp" %>
	<section id="eventUpload-section">
		<div id="event-title">
			<div id="sub-title">
				<h3>이벤트 업로드</h3>
			</div>
		</div>
		<form action="event_uploadOK.do" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>이벤트명</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>커버 이미지</td>
					<td><input type="file" name="img_cover"></td>
				</tr>
				<tr>
					<td>이벤트 이미지</td>
					<td><input type="file" name="img_contents"></td>
				</tr>
			</table>
			<div>
				<input type="submit" value="업로드" class="nmBtn" />
				<a href="javascript:history.back();"><input type="button" value="목록" class="nmBtn" /></a>
			</div>
		</form>
	</section>
<%@ include file="../../view/public/footer.jsp" %>
</body>
</html>