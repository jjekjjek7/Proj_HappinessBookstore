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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/JS/chk.js"></script>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/member.css">
</head>
<body>
<c:if test="${empty sessionScope.id}">
	<script>
		location.href="login.do";
	</script>
</c:if>
<%@ include file="../public/header.jsp" %>
	<section id="order-section">
		<form action="orderOK.do" method="post" name="orderForm">
        <div id="member_info">
            <table>
                <tr>
                    <td>주문자 정보</td>
                    <td>${memberInfo.name} &nbsp;|&nbsp; ${memberInfo.phone.replace(" ", "-")} &nbsp;|&nbsp; ${memberInfo.email}</td>
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
                <c:forEach var="dto" items="${cartList}">
                    <c:set var="i" value="${i+1}" />
                    <tr>
                        <td class="orderDetail">
                            <div class="order_thumbnail">
                                <img src="${dto.cover}" />
                            </div>
                        </td>
                        <td class="order_contents">
                            <div class="cart_thumbnail">
                                <a href="${pageContext.servletContext.contextPath}/view/product/detail.do?itemNo=${dto.isbnNo}">
                                    ${dto.title}
                                </a>
                            </div>
                        </td>
                        <td>${String.format("%,d", dto.price)}원<br><sub>${dto.point}p</sub></td>
                        <c:set var="book_total" value="${dto.cnt * dto.price}" />
                        <td>${dto.cnt}권</td>
                        <td>${String.format("%,d", book_total)}원</td>
                    </tr>
                    <c:set var="total" value="${total+book_total}" />
                    <c:set var="total_point" value="${total_point+dto.point}" />
                </c:forEach>
            </table>
        </div>

        <div id="member_info">
            <div class="order_title"><h3>| 할인 정보</h3></div>
            <table>
                <tr>
                    <td>행복 포인트</td>
                    <td>
                        <span id="point_info">${memberInfo.point}p</span>&nbsp;
                        <input type="text" value="0" id="point_input" name="point_input" onchange="getPoint(${memberInfo.point})">
                        <a class="point_btn" href="javascript:allPoint(${memberInfo.point})">모두사용</a>
                      	<span id="point_ex">5,000p 이상 사용 가능</span>
                    </td>
                </tr>
            </table>
        </div>

        <div id="pay_info">
            <div class="order_title"><h3>| 결제 정보</h3></div>
            <table>
                <tr>
                    <td>
                        <input type="radio" value=1 name="payment_type" id="1" checked> <label for="1">무통장입금</label><br />
                        <input type="radio" value=2 name="payment_type" id="2"> <label for="2">체크카드</label><br />
                        <input type="radio" value=3 name="payment_type" id="2"> <label for="3">신용카드</label>
                    </td>
                    <td>
                        <strong>최종결제금액</strong>
                        <em id="sale-price">${String.format("%,d", total)}원</em><br />
                        <strong>상품 금액</strong><em>${String.format("%,d", total)}원</em><br />
                        <strong>적립 예정 포인트</strong><em>${String.format("%,d", total_point)}원</em><br />
                        <strong>사용 포인트</strong><em id="point">0p</em><br />
                    </td>
                </tr>
            </table>
        </div>
        	<input type="hidden" name="chkArr" value="${param.chkArr}" />
        	<input type="hidden" name="point" value="${total_point}" />
        	<input type="hidden" name="price" value="${total}" />
        </form>
        <div class="list-bt">
	        <a href="javascript:orderForm.submit();" class="order-btn">구매하기</a>
        </div>
    </section>
<%@ include file="../public/footer.jsp" %>
</body>
</html>