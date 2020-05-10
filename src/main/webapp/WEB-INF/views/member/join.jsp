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
	
	<form action="./joinMember.do" method=post>		
		<table>
			<tr>
				<td> 아이디     :  <input type="text" name="id"></td>
			</tr>
			<tr>
				<td> 비밀번호     :  <input type="text" name="pw"></td>
			</tr>
			<tr>
				<td> 이름         :  <input type="text" name="name"></td>
			</tr>
			<tr>
				<td> 닉네임       :  <input type="text" name="nickname"></td>
			</tr>
			<tr>
				<td> 생년월일    :  <input type="text" name="birth"></td>
			</tr>
			<tr>
				<td for = "sex"> 성별         :   <input type = "radio" name = "sex" value = "남" checked = "checked"> 남성    
        						 <input type = "radio" name = "sex" value = "여"> 여성
				</td>
			</tr>
			<tr>
				<td> 이메일      :  <input type="text" name="email"></td>
			</tr>
			<tr>
				<td> 핸드폰번호 :  <input type="text" name="phone"></td>
			</tr>
			<tr>
				<td> 주소        :  <input type="text" name="addr"></td>
			</tr>
			<tr>
				<td> <input type="submit" value="회원가입"></td>
			</tr>
		</table>
	</form>
	
</body>
</html>
