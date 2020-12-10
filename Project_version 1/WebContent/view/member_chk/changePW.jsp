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
<title>비밀번호 변경</title>
</head>
<body>
<c:choose>
	<c:when test="${flag}">
		<h3>비밀번호 불일치</h3>
			<p>비밀번호가 일치하지 않습니다.</p>
			
			<hr />
			<a href="javascript:history.back()">[다시시도]</a>
			&nbsp; &nbsp;
			<a href="javascript:window.close()">[창닫기]</a>
	</c:when>
	<c:otherwise>
		<h3> 비밀번호 변경 </h3>
			<form method="post" action="changePWOK.do" onsubmit="return pwChk_submit()">
				비밀번호 : <input type="password" name="pw" id="pw" autofocus><br />
				비밀번호 확인 : <input type="password" name="pw2" id="pw2" autofocus onchange="pwChk()"><br />
				<input type="hidden" name="id" value="${id}" />
				<span style='color: red; display: none;' id="pw_chk"> 비밀번호가 일치하지 않습니다.</span><br />
				<input type="submit" value="입력" onsubmit="pwChk_submit()">
			</form>
	</c:otherwise>
</c:choose>
</body>
</html>