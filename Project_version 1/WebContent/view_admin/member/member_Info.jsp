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
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/CSS/admin.css">
</head>
<body>
<c:if test="${sessionScope.id ne 'admin' || empty sessionScope.id}">
	<script>
		alert("접근 권한이 없습니다.");
		location.href="/Project/index.do";
	</script>
</c:if>
	<%@ include file="../../view/public/header.jsp" %>
	
	<section id="memberInfo-section">
		<%@ include file="../../view/public/admin_nav.jsp" %>
		<form action="member_Modify.do" method="POST" name="memberForm">
		<table>
                <tr>
                    <td>아이디</td>
                    <td>${info.id}</td>
                </tr>
                <tr>
                    <td>이름</td>
                    <td>${info.name}</td>
                </tr>
                <tr>
					<td>비밀번호</td>
					<td>${info.pw}&nbsp;&nbsp;&nbsp;<input type="button" class="nmBtn" onClick="input_pw()" value="비밀번호 변경" /></td>
				</tr>
                <tr>
                    <td>생년월일</td>
                    <td>
						<select name="birthYear" id="birthYear" class="select_option">
							<c:forEach var="i" begin="0" end="${2020-1900}">
    						<c:set var="yearOption" value="${2020-i}" />
    							<option value="${yearOption}" <c:if test="${yearOption eq birth[0]}"> selected</c:if>>${yearOption}년</option>
							</c:forEach>
						</select>
						<select name="birthMonth" id="birthMonth" class="select_option">
							<c:forEach var="i" begin="1" end="12">
								<option value="${i}" <c:if test="${i eq birth[1]}"> selected</c:if>>${i}월</option>
							</c:forEach>
						</select>
						<select name="birthDay" id="birthDay" class="select_option">
							<c:forEach var="i" begin="1" end="31">
								<option value="${i}" <c:if test="${i eq birth[2]}"> selected</c:if>>${i}일</option>
							</c:forEach>
						</select>
					</td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input type="email" name="email" value="${info.email}" required></td>
                </tr>
                <tr>
                    <td><label for="phone">전화번호</label></td>
					<td>
						<select id="firstPhone" name="firstPhone" required>
								<option value="010" <c:if test="${phone[0] eq '010'}"> selected</c:if>>010</option>
								<option value="011" <c:if test="${phone[0] eq '011'}"> selected</c:if>>011</option>
								<option value="016" <c:if test="${phone[0] eq '016'}"> selected</c:if>>016</option>
								<option value="017" <c:if test="${phone[0] eq '017'}"> selected</c:if>>017</option>
								<option value="018" <c:if test="${phone[0] eq '018'}"> selected</c:if>>018</option>
								<option value="019" <c:if test="${phone[0] eq '019'}"> selected</c:if>>019</option>
							</select> -
						<input type="text" name="midPhone" class="numberBox" maxlength="4" value="${phone[1]}" required /> -
						<input type="text" name="lastPhone" class="numberBox" maxlength="4" value="${phone[2]}" required />
					</td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td id="address">
                        <input type="text" id="sample4_postcode" name="postcode" value="${info.postcode}" placeholder="우편번호">
                        <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" id="adchk"><br>
                        <input type="text" id="sample4_roadAddress" name="address" value="${info.address}" placeholder="도로명주소">
                        <input type="hidden" id="sample4_jibunAddress" name="sample4_jibunAddress" placeholder="지번주소">
                        <input type="text" id="sample4_detailAddress" name="address_detail" value="${info.address_detail}" placeholder="상세주소">
                        <input type="hidden" id="sample4_extraAddress" name="sample4_extraAddress" placeholder="참고항목">
                    </td>
                </tr>
            </table>
            <div class="list-bt">
            	<input type="hidden" value="${info.id}" name="id" />
            	<input type="submit" value="수정" class="nmBtn" />
	            <a href="member_List.do"><input type="button" value="목록" class="nmBtn" /></a>
            </div>
		</form>
	</section>
	
	<%@ include file="../../view/public/footer.jsp" %>
</body>
</html>