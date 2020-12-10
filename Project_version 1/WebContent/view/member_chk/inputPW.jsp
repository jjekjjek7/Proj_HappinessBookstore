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
<title>비밀번호 확인</title>
</head>
<body>
<h3> 비밀번호 확인 </h3>
	<form method="post" <c:if test="${!empty param.mode}"> action="/Project/view/member/memberDelete.do"</c:if> <c:if test="${empty param.mode}"> action="changePW.do"</c:if>>
		비밀번호 입력 : <input type="password" name="pw" autofocus><br />
		<input type="submit" value="입력">
	</form>
</body>
</html>