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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/product.css">
</head>
<body>
<%@ include file="../public/header.jsp" %>
	<section id="new-section">
        <input type="radio" id="lbt" name="tab" checked />
        <input type="radio" id="rbt" name="tab" />
        <div class="arrow-bt">
            <label for="lbt"><img src="${pageContext.servletContext.contextPath}/static/media/other/lbt.png" /></label>
            <label for="rbt"><img src="${pageContext.servletContext.contextPath}/static/media/other/rbt.png" /></label>
        </div>
        
        <section>
        
            <div class="section-title">
                <h3>주요 신간</h3>
            </div>
            
			<div class="book-content">
				<ul>
					<c:forEach var="dto" items="${newList}" end="5">
					<c:set var="i" value="${i+1}" />
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
            
            <div class="book-content">
				<ul>
					<c:forEach var="dto" items="${newList}" begin="6" end="11">
					<c:set var="i" value="${i+1}" />
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
            
        </section>
    </section>
<%@ include file="../public/footer.jsp" %>
</body>
</html>