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
</head>
<body>
<c:if test="${empty detailDTO}">
	<script>
		history.go(0);
	</script>
</c:if>
<%@ include file="../public/header.jsp" %>
	<section id="detail-section">

        <div id="detail-main">

            <div id="detail-img">
                <img src="${detailDTO.coverLargeUrl}" >
            </div>
            <div id="detail-info">
                <h2>${detailDTO.title}</h2>
                <h4>${detailDTO.author} | ${detailDTO.publisher} | ${detailDTO.pubDate}</h4>
                <span id="stars">
	                <c:forEach begin="1" end="${detailDTO.customerReviewRank%2 == 0 ? detailDTO.customerReviewRank/2 : detailDTO.customerReviewRank/2+1}">
	                    <img class="star_rank" src="${pageContext.servletContext.contextPath}/static/media/other/star.png" alt="star">
	                </c:forEach>
                </span>
                <em>${detailDTO.customerReviewRank}</em>
            </div>
            <hr />
            <div id="price-info">
                <table>
                    <tr>
                        <th>정가</th>
                        <td>${String.format("%,d", detailDTO.priceStandard)}원</td>
                    </tr>
                    <tr>
                        <th>판매가</th>
                        <td><em id="sale-price">${String.format("%,d", detailDTO.priceSales)}원<em></td>
                    </tr>
                    <tr>
                    	<fmt:parseNumber var= "price" integerOnly= "true" value= "${(detailDTO.priceSales*0.05)+(10-(detailDTO.priceSales*0.05)%10)%10  }" />
                        <th>행복 포인트</th>
                        <td>${String.format("%,d", price)}원 (5% 적립)</td>
                    </tr>
                    </table>
            </div>
            <div id="order-btn">
            	<form action="insertCart.do" method="post">
            		<input type="hidden" value="${detailDTO.title}" name="title" />
            		<input type="hidden" value="${detailDTO.author}" name="author" />
            		<input type="hidden" value="${detailDTO.coverLargeUrl}" name="cover" />
            		<input type="hidden" value="${detailDTO.priceSales}" name="price" />
            		<input type="hidden" value="${price}" name="point" />
            		<input type="hidden" value="${detailDTO.isbnNo}" name="isbn" />
	                <input type="submit" value="장바구니 담기" class="nmBtn">
            	</form>
            	<form action="/Project/view/member/order.do" method="post">
            		<input type="hidden" value="${detailDTO.title}" name="title" />
            		<input type="hidden" value="${detailDTO.author}" name="author" />
            		<input type="hidden" value="${detailDTO.coverLargeUrl}" name="cover" />
            		<input type="hidden" value="${detailDTO.priceSales}" name="price" />
            		<input type="hidden" value="${price}" name="point" />
            		<input type="hidden" value="${detailDTO.isbnNo}" name="isbn" />
            		<input type="hidden" value="one" name="chkArr" />
	                <input type="submit" value="바로 구매" class="nmBtn">
            	</form>
            </div>
        </div>
        
        <div id="detail_contents">
            <h3 id="contents_title">소개</h3>
            ${detailDTO.description}
        </div>
        
        <div class="list-bt">
	        <a href="javascript:window.history.back();" class="order-btn">목록</a>
        </div>
    </section>
<%@ include file="../public/footer.jsp" %>
</body>
</html>