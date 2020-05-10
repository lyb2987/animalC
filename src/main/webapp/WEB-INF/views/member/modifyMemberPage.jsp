<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
	<title>회원정보 수정</title>
	<%@ include file="../tamplate/header.jsp"%>
</head>
<script type="text/javascript">

</script>

<body>
	<form action="./ModifyMember.do" method=post>		
		<table>
			<tr>
				<td> 아이디     :  <input type="text" name="id" value="${user.id}" readonly="readonly"></td>
			</tr>
			<tr>
				<td> 비밀번호     :  <input type="text" name="pw" value="${user.pw}"></td>
			</tr>
			<tr>
				<td> 이름         :  <input type="text" name="name" value="${user.name}" readonly="readonly"></td>
			</tr>
			<tr>
				<td> 닉네임       :  <input type="text" name="nickname" value="${user.nickname}" ></td>
			</tr>
			<tr>
				<td> 생년월일    :   <input type="text" name="birth" value="${user.birth}" readonly="readonly"></td>
			</tr>
			<tr>
				<td> 성별         :   <input type="text" name="sex" value="${user.sex}" readonly="readonly"></td>
			</tr>
			<tr>
				<td> 이메일      :  <input type="text" name="email" value="${user.email}"></td>
			</tr>
			<tr>
				<td> 핸드폰번호 :  <input type="text" name="phone" value="${user.phone}"></td>
			</tr>
			<tr>
				<td> 주소        :  <input type="text" name="addr" value="${user.addr}"></td>
			</tr>
			<tr>
				<td> <input type="submit" value="수정"></td>
			</tr>

			
		</table>
	</form>
	
</body>
</html>
