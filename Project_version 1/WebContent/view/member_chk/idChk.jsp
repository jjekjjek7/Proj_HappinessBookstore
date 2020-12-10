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
<title>아이디 중복체크</title>
</head>
<body>
<h3> 아이디 중복체크 </h3>
	<form method="post" action="idChkOK.do" onsubmit="return blankCheck(this)">
		아이디 : <input type="text" name="id" maxlength="10" autofocus>
		<input type="submit" value="중복확인">
	</form>

	<script>
	function blankCheck(f){
		var id=f.id.value;
		id=id.trim();
		if(id.length<2){
			alert("아이디는 2자 이상 입력해주십시오.");
			return false;
		}
		return true;
	}
	</script>
</body>
</html>