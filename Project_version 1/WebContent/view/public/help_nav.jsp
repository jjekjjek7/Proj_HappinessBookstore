<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/CSS/public_nav.css">
<nav id="help-menu">
	<div id="help-title">
		<div id="main-title">
			<h3>고객 문의</h3>
		</div>
		<div id="sub-title">
			<h3>${sub_title}</h3>
		</div>
	</div>
	<div>
		<ul>
			<li><a href="${pageContext.servletContext.contextPath}/view/help/main.do">1:1 문의내역</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/view/help/help_write.do">1:1 문의하기</a></li>
		</ul>
	</div>
</nav>