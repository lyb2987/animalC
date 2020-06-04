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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/board/boardMain.css">
	<title>Home</title>
	<%@ include file="tamplate/header.jsp"%>
	
</head>
<body>

<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			test
		</div>
		<div class="sub-col-wrap">
			test
		</div>
	</div>
	<div class="sub-wrap">
		test
	</div>
</div>


	<%@ include file="tamplate/footer.jsp"%>
	
<script type="text/javascript">

	$(document).ready(function(){
		console.log("ready");
		//$.getUserId();
	});
	
	/*
	$.getUserId = function(){
		var userId = "${user.id}";
		if(userId == "bintest12"){
			console.log("userid : " + userId);
		}
		else{
			console.log("확인");
		}
	}
	*/

</script>
	
</body>

</html>
