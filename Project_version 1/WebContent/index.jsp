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
<link rel="stylesheet" type="text/css" href="static/CSS/main.css">
</head>
<body>
<%@ include file="view/public/header.jsp" %>
	<section id="main-section">
        <section>
            <div class="section-title">
                <h3>베스트셀러 - ${categoryName[0]}</h3>
            </div>
			<div class="book-content">
				<ul>
					<c:forEach var="dto" items="${bestFic}" end="3">
						<li>
							<a href="view/product/detail.do?itemNo=${dto.isbnNo}">
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
			<div class="more-data"><a href="view/product/best.do?curPage=1&category=f"><h5>더보기</h5></a></div>
        </section>
        <section>
            <div class="section-title">
                <h3>베스트셀러 - ${categoryName[1]}</h3>
            </div>
            <div class="book-content">
                <ul>
					<c:forEach var="dto" items="${bestEco}" end="3">
						<li>
							<a href="view/product/detail.do?itemNo=${dto.isbnNo}">
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
            <div class="more-data"><a href="view/product/best.do?curPage=1&category=e"><h5>더보기</h5></a></div>
        </section>
        
        <section>
            <div class="section-title">
                <h3>신작 도서</h3>
            </div>
            <div class="book-content">
                <ul>
					<c:forEach var="dto" items="${newList}" end="3">
						<li>
							<a href="view/product/detail.do?itemNo=${dto.isbnNo}">
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
            <div class="more-data"><a href="view/product/new.do"><h5>더보기</h5></a></div>
        </section>
    </section>
<%@ include file="view/public/footer.jsp" %>
</body>
</html>