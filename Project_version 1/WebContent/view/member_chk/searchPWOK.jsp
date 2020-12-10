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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/JS/chk.js"></script>
<title>비밀번호 찾기</title>
</head>
<body>
<h3>비밀번호 찾기</h3>
	<c:choose>
		<c:when test="${flag}">
			<form action="changePW.do" method="post" onsubmit="return confirmChk(${confirm})">
				인증번호 : <input type="text" name="confirmNum" id="confirmNum" /><br />
				<input type="hidden" name="id" value="${id}" />
				<input type="hidden" name="mode" value="0" />
				<input type="submit" value="입력" />
			</form>
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