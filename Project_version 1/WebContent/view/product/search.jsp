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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/product.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../public/header.jsp" %>
<fmt:parseNumber var= "pages" integerOnly= "true" value= "${ searchList.size()%9==0 ? searchList.size() : searchList.size()/9+1 }" />
<c:set var="endBook" value="${searchList.size()%9}" />
	<section id="search-section">
		<div class="book-content">
				<ul>
					<c:forEach var="dto" items="${searchList}" begin="${9*(param.curPage-1)}" end="${9*param.curPage-1}">
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
						<c:if test="${ !(param.curPage == pages && i%3 == 0 && i == endBook) && i%3 == 0 && i != 9 }">
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
		<c:forEach var="i" begin="1" end="${pages}">
			<c:choose>
				<c:when test="${i == param.curPage}">
					${i}
				</c:when>
				<c:otherwise>
					<a href="search.do?curPage=${i}&book_search=${param.book_search}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
<%@ include file="../public/footer.jsp" %>
</body>
</html>