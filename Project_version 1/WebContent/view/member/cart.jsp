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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/member.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<c:if test="${empty sessionScope.id}">
	<script>
		location.href="login.do";
	</script>
</c:if>
<%@ include file="../public/header.jsp" %>
	<section id="cart-section">
		<%@ include file="../public/mypage_nav.jsp" %>
        <form action="order.do" method="POST">
            <table>
            <colgroup>
            	<col width="35px" />
            	<col width="80px" />
            	<col width="375px" />
            	<col width="100px" />
            	<col width="70px" />
            	<col width="100px" />
            </colgroup>
                <tr>
                    <td><input type="checkbox" name="chkBook" class="chkBook" id="chk_all" ></td>
                    <td colspan="2">상품명</td>
                    <td>금액</td>
                    <td>수량</td>
                    <td>합계</td>
                </tr>
               	<c:if test="${empty cartList}"><tr><td colspan="6" style="text-align: center">장바구니가 비었습니다.</td></tr></c:if>
                <c:forEach var="dto" items="${cartList}">
                	<c:set var="i" value="${i+1}" />
	                <tr>
	                    <td>
	                        <input type="checkbox" name="chkBook" class="chkBook" id="chk${i}" data-cartNum="${dto.no}" >
	                    </td>
	                    <td class="cart_contents">
	                        <div class="cart_thumbnail">
	                            <img src="${dto.cover}" />
	                        </div>
	                    </td>
	                    <td class="cart_contents">
	                        <div class="cart_thumbnail">
		                        <a href="${pageContext.servletContext.contextPath}/view/product/detail.do?itemNo=${dto.isbnNo}">
		                            ${dto.title}
		                        </a>
	                    	</div>
	                    </td>
	                    <td>${String.format("%,d", dto.price)}원</td>
	                    <td>1권</td>
	                    <td>${String.format("%,d", dto.price)}원</td>
	                </tr>
                    <c:set var="total" value="${total+dto.price}" />
                </c:forEach>
                <tr>
                    <td colspan="6">
                        최종 결제금액:&nbsp; ${empty total ? 0 : String.format("%,d", total)}원
                    </td>
                </tr>
            </table>
            <div id="btnClass">
                <input type="button" class="all_btn nmBtn" value="전체 상품 구매" />
                <input type="button" class="select_btn nmBtn" value="선택 상품 구매" />
                <button type="button" class="delete_btn nmBtn">삭제</button>
			</div>
            <script src="${pageContext.servletContext.contextPath}/static/JS/checkbox.js"></script>
        </form>
    </section>
<%@ include file="../public/footer.jsp" %>
</body>
</html>