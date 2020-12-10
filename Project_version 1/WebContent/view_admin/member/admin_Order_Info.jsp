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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/admin.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/member.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
	
	<section id="order-section">
		<%@ include file="../../view/public/admin_nav.jsp" %>
        <div id="memberDetail_info">
            <table>
                <tr>
                    <td>주문번호</td>
                    <td>H${String.format("%08d", orderdto.no)}</td>
                    <td>주문일</td>
                    <td>
                    	<fmt:parseDate value="${orderdto.date}" var="foramt_date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${foramt_date}" pattern="yyyy.MM.dd"/>
                    </td>
                    <td>주문상태</td>
                    <td>
                    	<select name="state" id="state" onchange="updateState(${orderdto.no})">
                    		<option value="0" <c:if test="${orderdto.state == 0}"> selected</c:if>>주문대기</option>
                    		<option value="1" <c:if test="${orderdto.state == 1}"> selected</c:if>>주문완료</option>
                    	</select>
                    </td>
                </tr>
            </table>
        </div>
        <div id="book_info">
            <div class="order_title"><h3>| 상품 정보</h3></div>
            <table>
                <tr>
                    <td colspan="2">상품명</td>
                    <td>금액</td>
                    <td>수량</td>
                    <td id="sum">합계</td>
                </tr>
                <c:forEach var="dto" items="${orderList}">
                    <c:set var="i" value="${i+1}" />
                    <tr>
                        <td class="orderDetail">
                            <div class="orderDetail_thumbnail">
                                <img src="${dto.cover}" />
                            </div>
                        </td>
                        <td class="orderDetail_contents">
                            <div class="cart_thumbnail">
                                <a href="${pageContext.servletContext.contextPath}/view/product/detail.do?itemNo=${dto.isbnNo}">
                                    ${dto.title}
                                </a>
                            </div>
                        </td>
                        <td>${String.format("%,d", dto.price)}원<br><sub>${String.format("%,d", dto.point)}p</sub></td>
                        <c:set var="book_total" value="${dto.cnt * dto.price}" />
                        <td>${dto.cnt}권</td>
                        <td>${String.format("%,d", book_total)}원</td>
                    </tr>
                    <c:set var="total" value="${total+book_total}" />
                    <c:set var="total_point" value="${total_point+dto.point}" />
                </c:forEach>
            </table>
        </div>

        <div id="pay_info">
            <div class="order_title"><h3>| 결제 정보</h3></div>
            <table>
                <tr>
                    <td>
                   		<c:if test="${orderdto.payment_type eq 1}">무통장입금</c:if>
                   		<c:if test="${orderdto.payment_type eq 2}">체크카드</c:if>
                   		<c:if test="${orderdto.payment_type eq 3}">신용카드</c:if>
                    </td>
                    <td>
                        <strong>최종결제금액</strong>
                        <em id="sale-price">${String.format("%,d", orderdto.price)}원</em><br />
                        <strong>상품 금액</strong><em>${String.format("%,d", total)}원</em><br />
                        <strong>사용 적립금</strong><em id="point">${total_point ne orderdto.point ? orderdto.point-total_point : 0}원</em><br />
                    </td>
                </tr>
            </table>
        </div>
        	<input type="hidden" name="chkArr" value="${param.chkArr}" />
        	<input type="hidden" name="point" value="${total_point}" />
        	<input type="hidden" name="price" value="${total}" />
        <div class="list-bt">
	        <a href="javascript:window.history.back();" class="order-btn">목록</a>
        </div>
    </section>
	
	<%@ include file="../../view/public/footer.jsp" %>
</body>
</html>