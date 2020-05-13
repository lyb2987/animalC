<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
	<title>아이디 비밀번호 찾기</title>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home.css">
	<%@ include file="../tamplate/header.jsp"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/login.css">
	
</head>
<body>
<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			<div class="forgetdiv" style="margin-left: 10px; margin-right: 5px;">
				<form action="./member/forgetidpw.do" method=post id="forgetForm">
					<div class="idpwInput" style="margin-top: 110px">
						<input type = "radio" name = "idpw" value = "id" checked = "checked"> 아이디    
        				<input type = "radio" name = "idpw" value = "pw"> 비밀번호
					</div>
					<div class="emailText" style="margin-top: 10px;">
						이메일
					</div>
					<div class="emailInput">
						<input type="text" name="email" id="email">
						<button class="sendEmailbtn" type="button" id="sendEmailbtn" 
							onclick="sendEmail();" value="N" style="margin-left: 3px">메일전송</button>
					</div>
					<div class="aed" style="margin-top: 10px;">
						인증번호
					</div>
					<div class="aeinputd">
					 	<input type="text" name="anum" id="anum">
					 	<button class="checkNumbtn" type="button" id="checkNumbtn" 
							onclick="checkNum();" value="N" style="margin-left: 3px">확인</button>
					</div>
					
					<div class="forgetbtnd" style="margin-top: 10px;">
						<input class="forgetbtn" id="forgetbtn" type="submit" value="찾기">
					</div>
				</form>
				<img alt="jjunie" style="margin-left: 270px; margin-top: -165px;" src="${pageContext.request.contextPath}/resources/images/member/jjunie.png" width="160px" height="200px">
					
			</div>
		</div>
		<div class="sub-col-wrap">

		</div>
	</div>
	<div class="sub-wrap">
	</div>
</div>
<script type="text/javascript">
	// 찾기버튼 비활성화
	$(function(){	
	    $("#forgetbtn").attr("disabled", true);
	});
	
	//이메일 전송
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
			var mailchk = $("#sendEmailbtn").attr("value");
			if(mailchk == 'Y'){
		        $("#forgetbtn").attr("disabled", false);
				alert("메일인증에 성공하였습니다. 찾기 버튼을 클릭해주세요!");
			}
		}
		else{
			alert("메일인증에 실패했습니다. 다시 시도해주세요!");
		}
	}

	$("#forgetbtn").on("click", function(){
		var idpw = $('input:radio[name="idpw"]:checked').val();
		var email = $("#email").val();
		var idpwemail = idpw.concat(" ", email);
		console.log(idpwemail);
		$("#idpw").val(idpwemail);
		$("#joinForm").submit();
	});
</script>


</body>
</html>