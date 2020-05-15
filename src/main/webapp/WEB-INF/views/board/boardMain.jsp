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
	<title>게시판 메인</title>
	<%@ include file="../tamplate/header.jsp"%>
</head>
<body>
	
<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			<div id="topMenu" style="margin-top: 0px;">
				<a href="./writeBoardPage" style="color: black;"> 게시글 작성</a>
			</div>
		</div>
		<div class="sub-col-wrap" style="background: yellow">
		</div>
	</div>
	<div class="sub-wrap" style="background: yellow">

	</div>
</div>

</body>
</html>
