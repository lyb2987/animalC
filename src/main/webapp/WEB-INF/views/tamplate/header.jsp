<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

</script>


<c:choose>
	<c:when test="${user.id == null}">
		<form action="${pageContext.request.contextPath}/member/loginRequest1.do" method=post>
			ID : <input type="text" name="id">
			PW : <input type="text" name="pw">
			<input type="submit" value="로그인">
		</form>
		<a href="${pageContext.request.contextPath}/member/joinPage.do">회원가입</a>
	</c:when>
	<c:otherwise>
		${user.nickname} 님
		<a href="${pageContext.request.contextPath}/member/logoutRequest.do">로그아웃</a>
		<a href="${pageContext.request.contextPath}/member/myPage.do">마이페이지</a>
	</c:otherwise>
</c:choose>