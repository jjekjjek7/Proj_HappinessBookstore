<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/help.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/JS/admin.js"></script>
</head>
<body>
<%@ include file="../../view/public/header.jsp" %>
	<section id="helpMain-section">
		<c:if test="${sessionScope.id ne 'admin'}"><%@ include file="../../view/public/help_nav.jsp" %></c:if>
	
		<c:if test="${sessionScope.id eq 'admin'}">
			<%@ include file="../../view/public/admin_nav.jsp" %>
			
			<div class="list-bt">
				<div class="search">
					<select name="state" id="state_search" onchange="search_State('${param.search}', '${param.search_value}')">
						<option value="all" <c:if test="${param.state eq 'all'}"> selected</c:if>>전체</option>
						<option value="0" <c:if test="${param.state eq '0'}"> selected</c:if>>답변대기</option>
						<option value="1" <c:if test="${param.state eq '1'}"> selected</c:if>>답변완료</option>
					</select>
			        <form action="main.do" method="get">
						<select name="search">
							<option value="id" <c:if test="${param.search eq 'id'}"> selected</c:if>>아이디</option>
							<option value="name" <c:if test="${param.search eq 'name'}"> selected</c:if>>이름</option>
							<option value="type" <c:if test="${param.search eq 'type'}"> selected</c:if>>문의 유형</option>
						</select>
						<input type="text" name="search_value" value="${param.search_value}" />
						<input type="hidden" value="1" name="curPage" />
						<input type="hidden" value="admin" name="sys" />
						<input type="submit" value="검색" class="search-btn" />
					</form>
	        	</div>
	        </div>
		</c:if>
        
		<table>
			<tr>
				<th>번호</th>
				<th>문의 유형</th>
				<th>제목</th>
				<th>이름</th>
				<th>등록일</th>
				<th>답변상태</th>
			</tr>
			
			
			<c:set var="i" value="0" />
			<c:forEach var="dto" items="${helpList}">
				<c:if test="${sessionScope.id eq 'admin' || sessionScope.id eq dto.id}">
					<tr>
						<td>${dto.no}</td>
						<td>${dto.type}</td>
						<td><a href="help_read.do?no=${dto.no}<c:if test="${!empty param.sys}">&sys=admin</c:if>">${dto.title}</a></td>
						<td>${dto.name}</td>
						<td>
							<fmt:parseDate value="${dto.date}" var="foramt_date" pattern="yyyy-MM-dd HH:mm:ss"/>
							<fmt:formatDate value="${foramt_date}" pattern="yyyy.MM.dd"/>
						</td>
						<td>${dto.state==1 ? '완료' : '대기'}</td>
					</tr>
					<c:set var="i" value="${i+1}" />
				</c:if>
			</c:forEach>
			<c:if test="${i eq 0}"><tr><td colspan="6" style="text-align: center">문의내역이 없습니다.</td></tr></c:if>
		</table>
		
		<c:if test="${sessionScope.id eq 'admin'}">
			<div class="pageNum">
		        <c:forEach var="i" begin="1" end="${boardCnt}">
					<c:choose>
						<c:when test="${i == param.curPage}">
							${i}
						</c:when>
						<c:otherwise>
							<a href="main.do?search=${param.search}&search_value=${param.search_value}&curPage=${i}&sys=admin">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</c:if>
		
	</section>

<%@ include file="../../view/public/footer.jsp" %>
</body>
</html>