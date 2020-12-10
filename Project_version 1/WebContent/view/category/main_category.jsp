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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/category.css">
</head>
<body>
<%@ include file="../public/header.jsp" %>
	<div id="category-section">
		<c:forEach var="list" items="${categoryList}">
		<c:set var="i" value="${i+1}"></c:set>
        	<section>
		        <div class="section-title">
		        	<h3>${categoryName[i-1]}</h3>
		        </div>
	            <div class="book-content">
					<ul>
						<c:forEach var="dto" items="${list}">
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
		                </c:forEach>
	                </ul>
				</div>
	            <div class="more-data"><a href="${pageContext.servletContext.contextPath}/view/category/category.do?curPage=1&category=${categoryIdArr[i-1]}"><h5>더보기</h5></a></div>
        	</section>
        </c:forEach>
    </div>
<%@ include file="../public/footer.jsp" %>
</body>
</html>