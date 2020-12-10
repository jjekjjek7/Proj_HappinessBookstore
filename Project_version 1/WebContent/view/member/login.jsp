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
<script src="${pageContext.servletContext.contextPath}/static/JS/chk.js"></script>
</head>
<body>
<%@ include file="../public/header.jsp" %>
<c:if test="${empty sessionScope.id}">
	<section id="login-section">
        <form action="loginOK.do" method="POST">
            <div id="main-login">
                <div>
                    <label for="id">ID</label> <br />
                    <input type="text" id="id" name="id" value="${cookie.id.value}" /> <br />
                </div>
                <div>
                    <label for="pw">PASSWORD</label> <br />
                    <input type="password" id="pw" name="pw" /> <br />
                </div>
                
                <div>
                    <button type="submit" class="nmBtn">로그인</button>
                </div>
                
                <div id="chk">
                    <input type="checkbox" id="autoidsave" name="autoidsave" value="checked" <c:if test="${not empty cookie.id}"> checked</c:if> >
                    <label for="autoidsave">ID 저장</label>
                    &nbsp;
                	<a href="javascript:search_id();">아이디</a>
                	&nbsp;/&nbsp;
                	<a href="javascript:search_pw();">비밀번호</a>
                </div>
            </div>
        </form>
	</section>
</c:if>
<%@ include file="../public/footer.jsp" %>
</body>
</html>