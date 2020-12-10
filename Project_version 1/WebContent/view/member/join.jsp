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
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/JS/Address.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/JS/chk.js"></script>
</head>
<body>
<%@ include file="../public/header.jsp" %>
	<section id="join-section">
		<div id="main-join">
			<div id="table-title"><h1>회원가입</h1></div>
			<form method="post" action="memberResister.do" name="memberForm" onsubmit="return pwChk_submit()">
				<table>
					<tr>
						<td>*</td>
						<td><label for="name">이름</label></td>
						<td><input type="text" class="box" id="name" name="name" required /></td>
					</tr>
					<tr>
						<td>*</td>
						<td><label for="id">아이디</label></td>
						<td> 
							<input type="text" class="box" id="id" name="id" required readonly onfocus="id_Check()"/>
						</td>
					</tr>
					<tr>
						<td>*</td>
						<td><label for="pw">비밀번호</label></td>
						<td><input type="password" class="box" id="pw" name="pw" required /><br />
						<span> 영문,  숫자, 특수문자 2가지 이상을 혼합하여 8~20자리 이내</span></td>
					</tr>
					<tr>
						<td>*</td>
						<td><label for="pw2">비밀번호 확인</label></td>
						<td><input type="password" class="box" id="pw2" name="pw2" onchange="pwChk()" required /><br>
						<span id="pw_chk"> 비밀번호가 일치하지 않습니다.</span></td>
					</tr>
					<tr>
						<td>*</td>
						<td><label>생년월일</label></td>
						<td>
							<select name="birthYear" id="birthYear" class="select_option">
								<c:forEach var="i" begin="0" end="${2020-1900}">
    							<c:set var="yearOption" value="${2020-i}" />
    								<option value="${yearOption}">${yearOption}년</option>
								</c:forEach>
							</select>
							<select name="birthMonth" id="birthMonth" class="select_option">
								<c:forEach var="i" begin="1" end="12">
									<option value="${i}">${i}월</option>
								</c:forEach>
							</select>
							<select name="birthDay" id="birthDay" class="select_option">
								<c:forEach var="i" begin="1" end="31">
									<option value="${i}">${i}일</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>*</td>
						<td><label for="email">이메일</label></td>
						<td><input type="email" class="box" id="email" name="email" required /></td>
					</tr>
					<tr>
						<td>*</td>
						<td><label for="firstPhone">전화번호</label></td>
						<td>
							<select id="firstPhone" name="firstPhone" required>
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="017">017</option>
								<option value="018">018</option>
								<option value="019">019</option>
							</select> -
							<input type="text" name="midPhone" class="numberBox" maxlength="4" required /> -
							<input type="text" name="lastPhone" class="numberBox" maxlength="4" required />
						</td>
					</tr>
				</table>
				<div class="join-bt">
					<input type="submit" value="회원가입" class="nmBtn" />
					<a href="/Project/index.do"><input type="button" value="취소" class="nmBtn" /></a>
				</div>
			</form>
		</div>
	</section>
<%@ include file="../public/footer.jsp" %>
</body>
</html>