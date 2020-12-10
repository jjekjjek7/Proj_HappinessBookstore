<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/CSS/public.css">
<header id="main-header">
        <div id="title">
            <a href="${pageContext.servletContext.contextPath}/index.do">
                <img id="logo" src="${pageContext.servletContext.contextPath}/static/media/other/Logo_ver2.png" alt="이미지가...ㅇㅁㅇ" />
            </a>
        </div>
        <div id="top-menu">
            <ul>
            	<c:choose>
            		<c:when test="${empty sessionScope.id}">
	                	<li><a href="${pageContext.servletContext.contextPath}/view/member/login.do">로그인</a></li>
	                	<li><a href="${pageContext.servletContext.contextPath}/view/member/join.do">회원가입</a></li>
            		</c:when>
            		<c:otherwise>
            			<li><a href="${pageContext.servletContext.contextPath}/view/member/mypage.do">${sessionScope.id}님 환영합니다</a></li>
            			<li><a href="${pageContext.servletContext.contextPath}/view/member/logout.do">로그아웃</a></li>
            		</c:otherwise>
            	</c:choose>
            	
            	<c:choose>
            		<c:when test="${sessionScope.id eq 'admin'}">
            			<li><a href="${pageContext.servletContext.contextPath}/view_admin/member/member_List.do">관리 페이지</a></li>
            		</c:when>
            		<c:otherwise>
		                <li><a href="${pageContext.servletContext.contextPath}/view/member/cart.do">장바구니</a></li>
		                <li><a href="${pageContext.servletContext.contextPath}/view/member/order_List.do">주문내역</a></li>
		                <li class="drop"><a href="${pageContext.servletContext.contextPath}/view/member/mypage.do">마이페이지</a>
			                <div id="mypage-drop">
			                	<ul>
			                		<li class="mypage-second">
			                			<a href="${pageContext.servletContext.contextPath}/view/help/main.do">고객문의</a>
			                		</li>
			                	</ul>
			                </div>
		                </li>
                </c:otherwise>
            	</c:choose>
            </ul>
        </div>

        <nav id="main-nav">
            <ul>
                <li class="drop"><a href="${pageContext.servletContext.contextPath}/view/category/main_category.do">분야 찾기</a>
                    <div class="dropdown">
                        <ul>
                            <li class="second-drop">
                                <a href="${pageContext.servletContext.contextPath}/view/category/category.do?curPage=1&category=38396">소설/시/희곡</a>
                            </li>
                            <li class="second-drop">
                                <a href="${pageContext.servletContext.contextPath}/view/category/category.do?curPage=1&category=38398">경제/경영</a>
                            </li>
                            <li class="second-drop">
                                <a href="${pageContext.servletContext.contextPath}/view/category/category.do?curPage=1&category=38400">자기계발</a>
                            </li>
                            <li class="second-drop">
                                <a href="${pageContext.servletContext.contextPath}/view/category/category.do?curPage=1&category=38404">사회과학</a>
                            </li>
                            <li class="second-drop">
                                <a href="${pageContext.servletContext.contextPath}/view/category/category.do?curPage=1&category=56388">건강/취미/레저</a>
                            </li>
                            <li class="second-drop">
                                <a>교재/수험서</a>
                                <div class="dropdown-two">
                                    <ul>
                                        <li>
                                            <a href="${pageContext.servletContext.contextPath}/view/category/category.do?curPage=1&category=38412">수험서/자격증</a>
                                            <a href="${pageContext.servletContext.contextPath}/view/category/category.do?curPage=1&category=38401">컴퓨터/인터넷</a>
                                            <a href="${pageContext.servletContext.contextPath}/view/category/category.do?curPage=1&category=38422">전공/대학교재</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>

                </li>
                <li><a href="${pageContext.servletContext.contextPath}/view/product/recommand.do">추천 도서</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/view/product/best.do?curPage=1">베스트</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/view/product/new.do">신상품</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/view/event/event.do">이벤트</a></li>
            </ul>
        </nav>

        <div id="search-icon">
        <form action="${pageContext.servletContext.contextPath}/view/product/search.do">
            <input type="search" id="search-box" name="book_search" placeholder="책 검색" value="${param.book_search}" />
            <input type="hidden" value="1" name="curPage" />
            <input type="image" src="${pageContext.servletContext.contextPath}/static/media/other/icon.png" alt="search_btn" id="search_icon_img" />
        </form>
        </div>
    </header>