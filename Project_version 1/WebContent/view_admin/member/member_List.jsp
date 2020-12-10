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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/admin.css">
<script src="${pageContext.servletContext.contextPath}/static/JS/admin.js"></script>
</head>
<body>
<c:if test="${sessionScope.id ne 'admin' || empty sessionScope.id}">
	<script>
		alert("접근 권한이 없습니다.");
		location.href="/Project/index.do";
	</script>
</c:if>
	<%@ include file="../../view/public/header.jsp" %>
	
	<section id="memberList-section">
		<%@ include file="../../view/public/admin_nav.jsp" %>
		<div class="search">
		    <form action="member_List.do?curPage=1" method="get">
				<select name="search">
					<option value="id" <c:if test="${param.search eq 'id'}"> selected</c:if>>아이디</option>
					<option value="name" <c:if test="${param.search eq 'name'}"> selected</c:if>>이름</option>
				</select>
				<input type="text" name="search_value" value="${param.search_value}" />
				<input type="hidden" value="1" name="curPage" />
				<input type="submit" value="검색" class="search-btn" />
			</form>
        </div>
		<table>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>삭제</th>
			</tr>
			
			<c:if test="${empty memberInfo}"><tr><td colspan="6" style="text-align: center">회원이 존재하지 않습니다.</td></tr></c:if>
			<c:forEach var="dto" items="${memberInfo}">
				<tr>
					<td>${dto.no}</td>
					<td>${dto.name}</td>
					<td><a href="member_Info.do?id=${dto.id}">${dto.id}</a></td>
					<td>${dto.email}</td>
					<td>${dto.phone.replace(" ", "-")}</td>
					<td><a href="javascript:delete_Member('${dto.id}')"><input type="button" value="삭제" class="nmBtn" /></a></td>
				</tr>
			</c:forEach>
		</table>
        
        <div class="list-bt">
	        <a href="member_Add.do"><input type="button" value="회원추가" class="nmBtn" /></a>
        </div>
        
        <div class="pageNum">
	        <c:forEach var="i" begin="1" end="${boardCnt}">
				<c:choose>
					<c:when test="${i == param.curPage}">
						${i}
					</c:when>
					<c:otherwise>
						<a href="member_List.do?search=${param.search}&search_value=${param.search_value}&curPage=${i}">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
        
	</section>
	<%@ include file="../../view/public/footer.jsp" %>
</body>
</html>