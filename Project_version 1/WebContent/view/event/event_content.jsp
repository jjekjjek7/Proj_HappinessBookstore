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
<script src="${pageContext.servletContext.contextPath}/static/JS/admin.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../public/header.jsp" %>
	<div id="event-section">
		<c:if test="${sessionScope.id eq 'admin'}">
			<select name="state" id="event_state" onchange="update_state('${dto.no}')">
				<option value="0" <c:if test="${dto.state eq '0'}"> selected</c:if>>진행</option>
				<option value="1" <c:if test="${dto.state eq '1'}"> selected</c:if>>종료</option>
			</select>
		</c:if>
		<img id="contents" src="${pageContext.servletContext.contextPath}/EventFile/${dto.img_contents}" />
		
		<c:if test="${sessionScope.id eq 'admin'}">
			<form action="event_modify.do" method="post" enctype="multipart/form-data" name="fileModify">
				<table>
					<tr>
						<td>커버 이미지</td>
						<td><input type="file" name="img_cover"></td>
					</tr>
					<tr>
						<td>이벤트 이미지</td>
						<td><input type="file" name="img_contents"></td>
					</tr>
				</table>
				<input type="hidden" name=no value="${dto.no}" />
			</form>
		</c:if>
		<div class="list-bt">
			<c:if test="${sessionScope.id eq 'admin'}">
            	<a href="javascript:delete_event('${dto.no}')"><input type="button" value="삭제" class="nmBtn" /></a>
            	<a href="javascript:fileModify.submit()"><input type="button" value="수정" class="nmBtn" /></a>
			</c:if>
			<a href="event.do"><input type="button" class="nmBtn" value="목록"></a>
		</div>
	</div>
<%@ include file="../public/footer.jsp" %>
</body>
</html>