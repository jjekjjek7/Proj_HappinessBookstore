<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/CSS/public_nav.css">

<nav id="mypage-menu">
	<div id="mypage-title">
		<div id="main-title">
			<h3>마이페이지</h3>
		</div>
		<div id="sub-title">
			<h3>${sub_title}</h3>
		</div>
	</div>
	<div>
		<ul>
			<li><a href="${pageContext.servletContext.contextPath}/view/member/mypage.do">회원정보</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/view/member/order_List.do">주문내역</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/view/member/cart.do">장바구니</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/view/help/main.do">고객문의</a></li>
		</ul>
	</div>
</nav>