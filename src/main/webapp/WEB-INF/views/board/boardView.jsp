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
	<title>게시글</title>
	<%@ include file="../tamplate/header.jsp"%>
</head>
<body>

<c:set></c:set>	

<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			<div class="boardView" style="width:100%;">
				<label> ${board.bno} </label>
				<label> ${board.btitle} </label>
				<label> ${board.bkind} </label>
				<label> ${board.bcontent} </label>
				<label> ${board.bwriter} </label>
				<label> ${board.regdate} </label>
				<label> ${board.viewCnt} </label>
			</div>
		</div>
		<div class="sub-col-wrap" style="background: yellow">
		</div>
	</div>
	<div class="sub-wrap" style="background: yellow">

	</div>
</div>

<script type="text/javascript">
	
</script>

</body>
</html>
