<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/CSS/public_nav.css">

<nav id="admin-menu">
	<div id="admin-title">
		<div id="main-title">
			<h3>관리자 페이지</h3>
		</div>
		<div id="sub-title">
			<h3>${sub_title}</h3>
		</div>
	</div>
	<div>
		<ul>
			<li><a href="${pageContext.servletContext.contextPath}/view_admin/member/member_List.do">회원관리</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/view_admin/member/admin_Order_List.do">주문관리</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/view/help/main.do?sys=admin">문의내역</a></li>
		</ul>
	</div>
</nav>