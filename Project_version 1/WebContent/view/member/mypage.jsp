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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/JS/Address.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/JS/chk.js"></script>
</head>
<body>
<c:if test="${empty sessionScope.id}">
	<script>
		location.href="login.do";
	</script>
</c:if>
<%@ include file="../public/header.jsp" %>
	<section id="mypage-section">
		<%@ include file="../public/mypage_nav.jsp" %>
        <form action="memberModify.do" method="POST" name="memberForm" onsubmit="return pwChk_submit()">
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
                    <td>보유 행복포인트</td>
                    <td>${String.format("%,d", info.point)}점</td>
                </tr>
                <tr>
					<td><label for="pw">비밀번호</label></td>
					<td><input type="button" class="nmBtn" onClick="input_pw()" value="비밀번호 변경" /></td>
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
            <div class="modify-bt">
                <input type="submit" class="nmBtn" value="수정">
                <a href="../main/index.do"></a><input type="button" class="nmBtn" value="취소">
            </div>
            <a href="inputPW.do" id="remove">회원탈퇴</a>
        </form>
    </section>
<%@ include file="../public/footer.jsp" %>
</body>
</html>