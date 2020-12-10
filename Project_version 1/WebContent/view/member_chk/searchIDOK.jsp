<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이디 찾기</title>
</head>
<body>
<h3>아이디 찾기 결과</h3>
	<c:choose>
		<c:when test="${!empty id}">
			아이디 : <strong>${id}</strong>
		</c:when>
		<c:otherwise>
			<p style='color: red'>정보가 존재하지 않습니다.</p>
		</c:otherwise>
	</c:choose>
	
	<hr />
	<a href="javascript:history.back()">[다시시도]</a>
	&nbsp; &nbsp;
	<a href="javascript:window.close()">[창닫기]</a>
</body>
</html>