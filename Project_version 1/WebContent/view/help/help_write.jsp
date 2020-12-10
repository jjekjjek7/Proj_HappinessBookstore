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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/help.css">
</head>
<body>
<%@ include file="../../view/public/header.jsp" %>
	<section id="write-section">
		<%@ include file="../../view/public/help_nav.jsp" %>
	
		<form action="help_writeOK.do" method="post">
			<table>
				<tr>
					<td>아이디</td>
					<td>${info.id}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${info.name}</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${info.email}</td>
				</tr>
				<tr>
					<td>문의 유형</td>
					<td>
						<select name="type">
							<option value="회원">회원</option>
							<option value="상품">상품</option>
							<option value="주문/결제">주문/결제</option>
							<option value="취소">취소</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="contents" cols="30" rows="10"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" value="${info.id}" name="id" />
						<input type="hidden" value="${info.name}" name="name" />
						<input type="hidden" value="${info.no}" name="member_no" />
					</td>
				</tr>
			</table>
			<div>
				<input type="submit" value="입력" class="nmBtn" />
				<a href="main.do"><input type="button" value="목록" class="nmBtn" /></a>
			</div>
		</form>
	</section>
<%@ include file="../../view/public/footer.jsp" %>
</body>
</html>