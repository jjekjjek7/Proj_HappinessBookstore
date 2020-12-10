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
<title>행복을 담는 행복서점</title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/member.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/JS/chk.js"></script>
</head>
<body>
<%@ include file="../public/header.jsp" %>
	<section id="input-section">
	<form method="post" action="memberDelete.do">
		<div id="main-input">
                <div>
                    <label for="pw">비밀번호 확인</label> <br />
                    <input type="password" id="pw" name="pw" /> <br />
                </div>
                
                <div>
                    <input type="submit" value="입력" class="nmBtn">
                </div>
            </div>
	</form>
		
	</section>
<%@ include file="../public/footer.jsp" %>
</body>
</html>