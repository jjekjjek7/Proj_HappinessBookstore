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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/product.css?jrgrf">
</head>
<body>
<%@ include file="../public/header.jsp" %>
    <section id="best-section">
        <div class="best-select">
            <input id="fic" type="radio" name="bestSeller" value="f" checked onclick="window.location.href='best.do?curPage=1&category=f';" />
            <input id="eco" type="radio" name="bestSeller" value="e" <c:if test="${param.category eq 'e'}"> checked </c:if> onclick="window.location.href='best.do?curPage=1&category=e';" />
            <section class="bt">
                <label for="fic">${categoryName[0]}</label>
                <label for="eco">${categoryName[1]}</label>
            </section>

            <div id="best-fic">
                <div class="book-content">
                	<ul>
	                	<c:forEach var="dto" items="${bestFic}" begin="${9*(param.curPage-1)}" end="${9*param.curPage-1}">
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
            </div>

            <div id="best-eco">
                <div class="book-content">
                	<ul>
	                	<c:forEach var="dto" items="${bestEco}" begin="${9*(param.curPage-1)}" end="${9*param.curPage-1}">
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
									<c:if test="${i % 3 == 0 && i != 18}">
										</ul>
										</div>
										<div class="book-content">
										<ul>
									</c:if>
							</li>
	                	</c:forEach>
                	</ul>
                </div>
            </div>
        </div>
    </section>
    <div class="pageNum">
    	<fmt:parseNumber var= "pages" integerOnly= "true" value= "${ bestFic.size() / 9 }" />
		<c:forEach var="i" begin="1" end="${pages}">
			<c:choose>
				<c:when test="${i == param.curPage}">
					${i}
				</c:when>
				<c:otherwise>
					<a href="best.do?curPage=${i}&category=${param.category}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
    </div>

<%@ include file="../public/footer.jsp" %>
</body>
</html>