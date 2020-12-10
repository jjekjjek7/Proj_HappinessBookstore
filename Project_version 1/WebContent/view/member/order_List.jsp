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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/member.css">
</head>
<body>
<c:if test="${empty sessionScope.id}">
	<script>
		location.href="login.do";
	</script>
</c:if>
<%@ include file="../public/header.jsp" %>
	<section id="orderList-section">
		<%@ include file="../public/mypage_nav.jsp" %>
        <table id="orderList_table">
        <colgroup>
        	<col width="115px "/>
        	<col width="115px "/>
        	<col width="420px "/>
        	<col width="115px "/>
        </colgroup>
            <tr>
                <th>주문번호</th>
                <th>주문일자</th>
                <th>주문내역</th>
                <th>주문금액</th>
            </tr>
            <c:if test="${empty orderList}"><tr><td colspan="4" style="text-align: center">주문내역이 없습니다.</td></tr></c:if>
            <c:forEach var="dto" items="${orderList}">
	            <tr>
	                <td class="link_contents">
	                	<a href="order_Detail.do?no=${dto.no}">
	                		H${String.format("%08d", dto.no)}
	                	</a>
	                </td>
	                <td>
	                	<fmt:parseDate value="${dto.date}" var="foramt_date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${foramt_date}" pattern="yyyy.MM.dd"/>
	                </td>
	                <td>${dto.title}</td>
	                <td>${String.format("%,d", dto.price)}원</td>
	            </tr>
            </c:forEach>
        </table>
    </section>
<%@ include file="../public/footer.jsp" %>
</body>
</html>