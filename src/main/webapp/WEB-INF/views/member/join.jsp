<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home.css">
	<title>회원가입</title>
	<%@ include file="../tamplate/header.jsp"%>
</head>
<body>

<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			<div class="logindiv" style="margin-left: 10px; margin-right: 5px;">
				<form action="./joinMember.do" method=post>
					<div class="idtext" style="margin-top: 50px;">
						<a style="color: red;">*</a>아이디
					</div>
					<div class="idinput">
						<input type="text" name="id">
					</div>
					
					<div class="pwtext" style="margin-top: 10px;">
						<a style="color: red;">*</a>비밀번호
					</div>
					<div class="pwinput">
					 	<input type="password" name="pw">
					</div>
					
					<div class="nametext" style="margin-top: 10px;">
						<a style="color: red;">*</a>이름
					</div>
					<div class="nameinput">
					 	<input type="text" name="name">
					</div>
					
					<div class="nicknametext" style="margin-top: 10px;">
						<a style="color: red;">*</a>닉네임
					</div>
					<div class="nicknameinput">
					 	<input type="text" name="nickname">
					</div>
					
					<div class="birthtext" style="margin-top: 10px;">
						<a style="color: red;">*</a>생년월일
					</div>
					<div class="birthinput">
					 	<input type="text" name="birth">
					</div>
					
					<div class="sextext" style="margin-top: 10px;">
						<a style="color: red;">*</a>성별
					</div>
					<div class="sexinput">
						<input type = "radio" name = "sex" value = "남" checked = "checked"> 남성    
        				<input type = "radio" name = "sex" value = "여"> 여성
					</div>
					
					<div class="emailtext" style="margin-top: 10px;">
						<a style="color: red;">*</a>이메일
					</div>
					<div class="emailinput">
					 	<input type="text" name="pw">
					</div>
					<div class="mailcheck" style="margin-top: 5px;">
						<input class="mailcheckbtn" type="submit" value="메일전송">
					</div>
					
					
					<div class="phonetext" style="margin-top: 10px;">
						핸드폰번호
					</div>
					<div class="phoneinput">
					 	<input type="text" name="pw">
					</div>
					
					<div class="addrtext" style="margin-top: 10px;">
						주소
					</div>
					<div class="addrinput">
					 	<input type="text" name="pw">
					</div>
					
					<div class="loginbtnd" style="margin-top: 10px;">
						<input class="loginbtn" type="submit" value="회원가입">
					</div>
				</form>
			</div>
			<img alt="jessica" style="margin-left: 270px; margin-top: -750px;" src="${pageContext.request.contextPath}/resources/images/member/jessica.png" width="150px" height="200px">
			<div class="aforget" style="margin-top: 15px; margin-left: 10px; float: right;">
				<a style="color:red;">*가 표시된 항목은 필수 입력 항목입니다!</a>
			</div>
		</div>
		<div class="sub-col-wrap">
		</div>
	</div>
	<div class="sub-wrap">
	</div>
</div>

	
</body>
</html>
