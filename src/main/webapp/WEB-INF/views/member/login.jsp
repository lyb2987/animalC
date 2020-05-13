<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
	<title>로그인</title>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home.css">
	<%@ include file="../tamplate/header.jsp"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/login.css">
	
</head>
<body>

<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			<div class="logindiv" style="margin-left: 10px; margin-right: 5px;">
				<img alt="aclogo" style="margin-left: 50px; margin-top: 100px;" src="${pageContext.request.contextPath}/resources/images/tamplate/aclogo.png" width="310px" height="70px">
				
				<form action="./loginRequest1.do" method=post id="loginForm">
					<div class="idtext" style="margin-top: 110px;">
						ID
					</div>
					<div class="idinput">
						<input type="text" name="id" id="id">
					</div>
					<div class="pwtext" style="margin-top: 10px;">
						PW
					</div>
					<div class="pwtext">
					 	<input type="password" name="pw" id="pw">
					</div>
					<div class="loginbtnd" style="margin-top: 10px;">
						<input class="loginbtn" id="loginbtn" type="submit" value="로그인">
					</div>
				</form>
				<img alt="nergoori" style="margin-left: 270px; margin-top: -165px;" src="${pageContext.request.contextPath}/resources/images/member/nergoori.png" width="100px" height="200px">
					
			</div>
			<div class="aforget" style="margin-top: 20px; margin-right: 10px; float: right;">
				<a href="./member/forgetPage.do" style="color:blue;">아이디와 비밀번호가 기억나지 않아요!</a>
			</div>
			<div class="joinbtnd" style="margin-top: 50px; margin-left: 386px;" >
				<a href="./member/loginPage.do" style="color:blue;">회원가입</a>
			</div>
		</div>
		<div class="sub-col-wrap">
		</div>
	</div>
	<div class="sub-wrap">

	</div>
</div>
	
<script type="text/javascript">

	// 로그인 버튼 클릭 이벤트 : id, pw 공란 로그인 방지
	$("#loginbtn").on("click", function(){
		if($("#id").val()==""){
			alert("아이디를 입력해주세요.");
			$("#id").focus();
			return false;
		}
		if($("#pw").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#pw").focus();
			return false;
		}
		$("#loginForm").submit();
	});

</script>
	
	

</body>
</html>
