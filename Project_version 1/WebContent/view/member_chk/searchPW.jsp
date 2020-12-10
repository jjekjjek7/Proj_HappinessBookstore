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
<title>비밀번호 찾기</title>
</head>
<body>
<h3> 비밀번호 찾기 </h3>
	<form method="post" action="searchPWOK.do">
		아이디 : <input type="text" name="id" maxlength="10" autofocus><br />
		이메일 : <input type="email" name="email" autofocus><br />
		<input type="submit" value="입력">
	</form>
</body>
</html>