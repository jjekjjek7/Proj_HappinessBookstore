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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/help.css">
<script src="${pageContext.servletContext.contextPath}/static/JS/admin.js"></script>
</head>
<body>
<%@ include file="../../view/public/header.jsp" %>

	<section id="read-section">
		
		<c:if test="${!empty param.sys}"><%@ include file="../../view/public/admin_nav.jsp" %></c:if>
		<c:if test="${empty param.sys}"><%@ include file="../../view/public/help_nav.jsp" %></c:if>
		
		<form action="help_modify.do?no=${readInfo.no}" method="post">
			<table>
				<tr>
					<td>이름</td>
					<td>${memberInfo.name}</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>${memberInfo.id}</td>
				</tr>
				<tr>
					<td>문의 유형</td>
					<td>${readInfo.type}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>${readInfo.title}</td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="contents" cols="30" rows="10" <c:if test="${!empty param.sys}"> readonly</c:if>>${readInfo.contents}</textarea></td>
				</tr>
				<tr>
					<c:if test="${sessionScope.id eq 'admin'}">
							<td>답변</td>
							<td><textarea id="comment">${readInfo.comment}</textarea></td>
					</c:if>
					<c:if test="${sessionScope.id ne 'admin' && !empty readInfo.comment}">
							<td>답변</td>
							<td><textarea id="comment" disabled >${readInfo.comment}</textarea></td>
					</c:if>
				</tr>
			</table>
				<div>
					<td colspan="2">
						<c:if test="${sessionScope.id eq 'admin'}"><input type="button" class="nmBtn" value="답변 등록" onclick="update_comment(${readInfo.no}, '${memberInfo.email}')" /></c:if>
						<c:if test="${sessionScope.id ne 'admin'}"><input type="submit" value="수정" class="nmBtn" /></c:if>
						<a href="javascript:delete_qna('${readInfo.no}')"><input type="button" value="삭제" class="nmBtn" /></a>
						<a href="main.do<c:if test="${sessionScope.id eq 'admin'}">?sys=admin</c:if>"><input type="button" value="목록" class="nmBtn" /></a>
					</td>
				</div>
		</form>
	</section>
<%@ include file="../../view/public/footer.jsp" %>
</body>
</html>