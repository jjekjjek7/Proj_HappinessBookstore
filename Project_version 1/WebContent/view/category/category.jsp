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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/category.css">
</head>
<body>
<%@ include file="../public/header.jsp" %>
	<section id="categoryDetail-section">
		<div class="section-title">
			<h3>${categoryName}</h3>
		</div>
        <div class="book-content">
			<ul>
				<c:forEach var="dto" items="${categoryList}" begin="${9*(param.curPage-1)}" end="${9*param.curPage-1}">
					<c:set var="i" value="${i + 1}" />
						<li>
							<a href="${pageContext.servletContext.contextPath}/view/product/detail.do?itemNo=${dto.isbnNo}">
								<div class="book_thumbnail"><img src="${dto.coverLargeUrl}" /></div>
								<div class="book_title">
									<p>${dto.title}</p>
									<p>${dto.author}</p>
									<p>${String.format("%,d", dto.priceSales)}원</p>
								</div>
							</a>
						</li>
						<c:if test="${i % 3 == 0 && i != 9}">
							</ul>
							</div>
							<div class="book-content">
							<ul>
							</c:if>
				</c:forEach>
			</ul>
		</div>
    </section>
    
    <div class="pageNum">
    	<fmt:parseNumber var= "pages" integerOnly= "true" value= "${ categoryList.size() / 9 }" />
		<c:forEach var="i" begin="1" end="${pages}">
			<c:choose>
				<c:when test="${i == param.curPage}">
					${i}
				</c:when>
				<c:otherwise>
					<a href="category.do?curPage=${i}&category=${param.category}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
    </div>
<%@ include file="../public/footer.jsp" %>
</body>
</html>