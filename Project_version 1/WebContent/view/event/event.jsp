<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>행복을 담는 행복서점</title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/event.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/JS/admin.js"></script>
</head>
<body>
<%@ include file="../public/header.jsp" %>
	<div id="event-section">
		<div id="event-state">
			<c:if test="${sessionScope.id eq 'admin'}">
				<select name="state" id="event_state" onchange="event_state()">
					<option value="all" selected>전체</option>
					<option value="0" <c:if test="${param.state eq '0'}"> selected</c:if>>진행</option>
					<option value="1" <c:if test="${param.state eq '1'}"> selected</c:if>>종료</option>
				</select>
			</c:if>
		</div>
	
		<c:if test="${empty eventList}"><p style="text-align: center">이벤트가 없습니다.</p></c:if>
		<c:forEach items="${eventList}" var="dto">
			<c:if test="${sessionScope.id eq 'admin'}">
				<div id="img_title">
					<p>
						<fmt:parseDate value="${dto.date}" var="foramt_date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${foramt_date}" pattern="yyyy.MM.dd"/>
					</p>
					<h4>
						<c:if test="${dto.state eq 0}">[진행중]</c:if>
						<c:if test="${dto.state eq 1}">[종료]</c:if>
						${dto.name}
					</h4>
				</div>
			</c:if>
			<a href="event_content.do?no=${dto.no}">
				<img src="${pageContext.servletContext.contextPath}/EventFile/${dto.img_cover}" id="cover" />
			</a>
		</c:forEach>	

		<div class="pageNum">
	        <c:forEach var="i" begin="1" end="${boardCnt}">
				<c:choose>
					<c:when test="${i == param.curPage}">
						${i}
					</c:when>
					<c:otherwise>
						<a href="event.do?&curPage=${i}">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	
		<c:if test="${sessionScope.id eq 'admin'}">
			<div class="list-bt" style="text-align: right">
	            <a href="event_upload.do"><input type="button" value="이벤트 업로드" class="nmBtn" /></a>
			</div>
		</c:if>
	</div>
<%@ include file="../public/footer.jsp" %>
</body>
</html>