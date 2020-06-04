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
	<title>게시글 쓰기</title>
	<%@ include file="../tamplate/header.jsp"%>
</head>
<body>
	
<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			<div id="writediv" style="margin-top: 0px;">
				<form action="./modifyBoard" method=post id="writeForm">
					<div class="titleandbkinddiv" style="margin-top: 10px;">
						글 번호 : <input  type="text" style="width: 40px;" name="bno" id="bno" value="${board.bno}" readonly="readonly">
					 	<input type="text" name="btitle" id="btitle" value="${board.btitle}">
					 	<select id="bkind" name="bkind">
						 	<option id="etc">기타</option>
						 	<option id="Humor">유머</option>
						 	<option id="Chat">잡담</option>
						 	<option id="dailyLife">일상</option>
						 	<option id="Game">게임</option>
					 	</select>
					</div>
					<div class="contentdiv" style="margin-top: 10px;">
						<textarea style="width: 500px; height: 300px;" name="bcontent" id="bcontent">${board.bcontent}</textarea>
					</div>
					<div class="writebtndiv">
							<input class="writebtn" id="writebtn" type="submit" value="확인">
					</div>
				</form>
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
