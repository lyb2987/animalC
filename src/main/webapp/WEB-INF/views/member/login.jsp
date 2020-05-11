<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
	<title>회원가입</title>
	<%@ include file="../tamplate/header.jsp"%>
</head>
<body>
	<form action="${pageContext.request.contextPath}/member/loginRequest1.do" method=post>
		ID : <input type="text" name="id">
		PW : <input type="text" name="pw">
		<input type="submit" value="로그인">
	</form>
	
</body>
</html>
