<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
	<title>회원 탈퇴 페이지</title>
	<%@ include file="../tamplate/header.jsp"%>
</head>
<body>
	<form action="./deleteMember.do" method=post>
			<a>회원 탈퇴를 위해 다음 내용을 입력해주세요.</a>
			ID : <input type="text" name="id">
			PW : <input type="text" name="pw">
			<input type="submit" value="회원탈퇴">
	</form>

</body>
</html>
