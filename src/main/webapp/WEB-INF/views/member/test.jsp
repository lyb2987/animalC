<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/test.css">
	<title>회원가입</title>
	<%@ include file="../tamplate/header.jsp"%>
</head>
<body>


<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			<div class="logindiv" style="margin-left: 10px; margin-right: 5px;">	
				<h2 style="margin-left: 175px; margin-top:15px; font-size: 25px; color: rgb(0,0,0);">회원가입</h2>
				<form action="./joinMember.do" method=post id="joinForm">
					<div class="idtext" style="margin-top: 30px;">
						<a style="color: red;">*</a>아이디
					</div>
					<div class="idinput">
						<input type="text" class="inputc" name="id" id="id">
						<button class="checkIdbtn" type="button" id="checkIdbtn" 
							onclick="checkId();" value="N" style="margin-left: 3px">중복확인</button>
					</div>
					<div class="guide" style=" margin-left:5px;">
						<a style="color:black;" id="idrule"> 5~15글자, 영문과 숫자만 사용 가능</a>
					</div>
					
					<div class="pwtext" style="margin-top: 8px;">
						<a style="color: red;">*</a>비밀번호
					</div>
					<div class="pwinput">
					 	<input type="password" class="inputc" name="pw" id="pw">
					 	<a style="color: black;" id="pwrule"> 8글자 이상, 영문과 숫자 조합 </a>
					</div>
					
					<div class="nametext" style="margin-top: 10px;">
						<a style="color: red;">*</a>이름
					</div>
					<div class="nameinput">
					 	<input type="text" class="inputc" name="name" id="name">
					</div>
					
					<div class="nicknametext" style="margin-top: 10px;">
						<a style="color: red;">*</a>닉네임
					</div>
					<div class="nicknameinput">
					 	<input type="text" class="inputc" name="nickname" id="nickname">
					</div>
					
					<div class="birthtext" style="margin-top: 10px;">
						<a style="color: red;">*</a>생년월일
					</div>
					<div class="birthinput">
					 	<input type="text" class="inputc" name="birth" id="birth" placeholder="ex) 19950101">
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
					 	<input type="text" class="inputc" name="email" id="email">
					 	<button class="sendEmailbtn" type="button" id="sendEmailbtn" 
							onclick="sendEmail();" value="N" style="margin-left: 3px">메일전송</button>
					</div>
					
					<div class="emailtext" style="margin-top: 10px;">
						<a style="color: red;">*</a>인증번호
					</div>
					<div class="emailinput">
					 	<input type="text" class="inputc" name="anum" id="anum">
					 	<button class="checkNumbtn" type="button" id="checkNumbtn" 
							onclick="checkNum();" value="N" style="margin-left: 3px">확인</button>
					</div>
					
					
					<div class="phonetext" style="margin-top: 10px;">
						핸드폰번호
					</div>
					<div class="phoneinput">
					 	<input type="text" class="inputc" name="phone" id="phone" value="입력안함">
					</div>
					
					<div class="addrtext" style="margin-top: 10px;">
						주소
					</div>
					<div class="addrinput">
					 	<input type="text" class="inputc" name="addr" id="addr" value="입력안함">
					</div>
					
					<div class="joinbtnd" style="margin-top: 10px;">
						<input class="joinbtn" class="submitbtn" id="joinbtn" type="submit" value="회원가입">
					</div>
				</form>
			</div>
			<img alt="jessica" style="margin-left: 270px; margin-top: -750px;" src="${pageContext.request.contextPath}/resources/images/member/jessica.png" width="190px" height="210px">
			<div class="guide" style="margin-top: 15px; margin-left: 10px; float: right;">
				<a style="color:red;">*가 표시된 항목은 필수 입력 항목입니다!</a>
			</div>
			
		</div>
		<div class="sub-col-wrap">
		</div>
	</div>
	<div class="sub-wrap">
	</div>
</div>
<script type="text/javascript">
	// 아이디 중복 및 유효성 확인
	function checkId(){
		if($("#id").val()==""){
			alert("아이디를 입력해주세요.");
			$("#id").focus();
			return false;
		}
		$.ajax({
			url : "./checkId",
			type : "post",
			dataType : "json",
			data : {"id" : $("#id").val()},
			success : function(data){
				if(data == 0){
					alert("아이디 규칙에 위배되었거나 중복된 아이디 입니다!");
				}else if(data == 1){
					$("#checkIdbtn").attr("value", "Y");
					alert("사용가능한 아이디입니다.");
				    $("#idrule").text("중복확인 및 유효성 검사 완료!");
			        $("#idrule").css("color", "green");
				}
			}
		})
	}
	
	// 비밀번호 유효성 검사
	$("#pw").change(function(){
	    checkPassword($('#pw').val());
	});

	function checkPassword(password){
		var checkNumber = password.search(/[0-9]/g);
	    var checkEnglish = password.search(/[a-z]/ig);
		if(password.length < 8 || password.length > 15){
		    $("#pwrule").text("너무 길거나 짧습니다!");
	        $("#pwrule").css("color", "red");
	        $("#joinbtn").attr("disabled", true);
	        return false;
	    }    
		else if(checkNumber < 0 || checkEnglish < 0){
	        $("#pwrule").text("영문자와 숫자가 조합되어야 합니다.");
	        $("#pwrule").css("color", "red");
	        $("#joinbtn").attr("disabled", true);
	        return false;
	    }  
		else {
	        $("#pwrule").text("사용 가능!");
	        $("#pwrule").css("color", "green");
	        $("#joinbtn").attr("disabled", false);
		}
	    return true;
	}

	// document ready와 같은 효과 준비되면 실행되야 될 것들을 작성
	$(function(){	
		console.log("ready");
		//회원가입 버튼 비활성화
        $("#joinbtn").attr("disabled", true);
	});

	
	// 생년월일 예시를 포커스 줬을때 지워줌
	/*
	$("#birth").focus(function(){
		$("#birth").val("");
	});
	*/

	
	// 핸드폰 포커스 줬을때 지워줌
	$("#phone").focus(function(){
		$("#phone").val("");
	});

	// 주소 포커스 줬을때 지워줌
	$("#addr").focus(function(){
		$("#addr").val("");
	});
	
	// 이메일 전송
	function sendEmail(){
		$.ajax({
			url : "${pageContext.request.contextPath}/email/sendEmail",
			type : "post",
			dataType : "json",
			data : {"email" : $("#email").val()},
			success : function(data){
				$("#sendEmailbtn").attr("value", data);
				alert("메일이 전송되었습니다. 메일을 확인해주세요!");
			}
		})
	}
	
	// 인증값 확인
	function checkNum(){
		var num =$("#sendEmailbtn").attr("value");
		var anum = $('#anum').val();
		if(num == anum){
			$("#sendEmailbtn").attr("value", "Y");
			var idchk = $("#checkIdbtn").attr("value");
			var mailchk = $("#sendEmailbtn").attr("value");
			if(idchk == 'Y' && mailchk == 'Y'){
		        $("#joinbtn").attr("disabled", false);
				alert("메일인증에 성공하였습니다. 회원가입을 진행해주세요!");
			}
		}
		else{
			alert("메일인증에 실패했습니다. 다시 시도해주세요!");
		}

		// 회원가입 클릭 이벤트
		$("#joinbtn").on("click", function(){
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
			if($("#name").val()==""){
				alert("이름을 입력해주세요.");
				$("#name").focus();
				return false;
			}
			if($("#nickname").val()==""){
				alert("닉네임을 입력해주세요.");
				$("#name").focus();
				return false;
			}
			if($("#birth").val()==""){
				alert("생년월일을 입력해주세요.");
				$("#birth").focus();
				return false;
			}
			if($("#email").val()==""){
				alert("이메일을 입력해주세요.");
				$("#email").focus();
				return false;
			}
			$("#joinForm").submit();
		});
	}
	
	
</script>
</body>
</html>
