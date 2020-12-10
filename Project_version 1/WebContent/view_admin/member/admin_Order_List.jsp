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
	
	<section id="memberList-section">
		<%@ include file="../../view/public/admin_nav.jsp" %>
		<div class="search">
			<select name="state" id="state_search" onchange="searchState('${param.search}', '${param.search_value}')">
				<option value="all" <c:if test="${param.state eq 'all'}"> selected</c:if>>전체</option>
				<option value="0" <c:if test="${param.state eq '0'}"> selected</c:if>>주문대기</option>
				<option value="1" <c:if test="${param.state eq '1'}"> selected</c:if>>주문완료</option>
			</select>
		    <form action="admin_Order_List.do?curPage=1" method="get">
				<select name="search">
					<option value="id" <c:if test="${param.search eq 'id'}"> selected</c:if>>아이디</option>
					<option value="name" <c:if test="${param.search eq 'name'}"> selected</c:if>>이름</option>
				</select>
				<input type="text" name="search_value" value="${param.search_value}" />
				<input type="hidden" value="1" name="curPage" />
				<input type="hidden" value="all" name="state" />
				<input type="submit" value="검색" class="search-btn" />
			</form>
        </div>
		<table>
			<tr>
                <th>주문번호</th>
                <th>주문일자</th>
                <th>주문자</th>
                <th>주문금액</th>
                <th>주문상태</th>
            </tr>
            <c:if test="${empty orderList}"><tr><td colspan="5" style="text-align: center">주문내역이 없습니다.</td></tr></c:if>
            <c:forEach var="dto" items="${orderList}">
	            <tr>
	                <td class="link_contents">
	                	<a href="admin_Order_Info.do?no=${dto.no}&member_no=${dto.member_no}">
	                		H${String.format("%08d", dto.no)}
	                	</a>
	                </td>
	                <td>
	                	<fmt:parseDate value="${dto.date}" var="foramt_date" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:formatDate value="${foramt_date}" pattern="yyyy.MM.dd"/>
	                </td>
	                <td>${dto.name}</td>
	                <td>${String.format("%,d", dto.price)}원</td>
	                <td>${dto.state == 1 ? '주문완료' : '주문대기'}</td>
	            </tr>
            </c:forEach>
		</table>
        
        <div class="pageNum">
	        <c:forEach var="i" begin="1" end="${boardCnt}">
				<c:choose>
					<c:when test="${i == param.curPage}">
						${i}
					</c:when>
					<c:otherwise>
						<a href="admin_Order_List.do?search=${param.search}&search_value=${param.search_value}&curPage=${i}&state=${param.state}">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
        
	</section>
	
	<%@ include file="../../view/public/footer.jsp" %>
</body>
</html>