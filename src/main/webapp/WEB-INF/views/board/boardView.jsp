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

<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			<div class="boardView" style="width:100%;">
				<div class="boardTitle">
					<h3>${board.bno} ${board.bkind} ${board.btitle}</h3>
				</div>
				<c:set var="writer" value="${board.bwriter}"/>
				<c:choose>
					<c:when test="${user.id.equals(writer)}">
						<div class="modifyAndDeleteForm">
							<button class="modifyBtn" type="button" id="modifyBtn" onclick="modifyBoard();">수정</button> 
							<button class="deleteBtn" type="button" id="deleteBtn" onclick="deleteBoard();">삭제</button> 
						</div>
					</c:when>
				</c:choose>
				<div class="boardContent">
					<p>${board.bcontent}</p>
				</div>
				<div class="baordInfo">
					<p>${board.bwriter} ${board.regdate} ${board.viewCnt}</p>
				</div>
			</div>
			
			<div class="commentAreaDiv" style="margin-top: 150px;">
				<hr width="100%" color="black">
				<div class="commentWriteDiv" style="margin-top: 7px;">
					<textarea style="width: 100%; height: 50px;" name="ccontent" id="ccontent" placeholder="댓글의 내용을 입력해주세요."></textarea>	
					<button class="commentBtn" type="button" id="commentBtn" onclick="commentWrite();">댓글달기</button> 
				</div>
				<div class="commentDiv" id="commentDiv" style="margin-top: 20px;">
				
				</div>
			</div>
		</div>
		<div class="sub-col-wrap" style="background: yellow">
		</div>
	</div>
	<div class="sub-wrap" style="background: yellow">

	</div>
</div>

<script type="text/javascript">
	var bno = ${board.bno};
	
	$(document).ready(function(){
		$.getCList();
	});
	
	function modifyBoard(){
		var conf = confirm("게시글을 수정하시겠습니까?")
		if(conf == false)
			return ;
		
		document.location.href = "./moveModifyBoard?bno=" + bno;
	}

	function deleteBoard(){
		var conf = confirm("게시글을 삭제하시겠습니까?")
		if(conf == false)
			return ;

		document.location.href = "./deleteBoard?bno=" + bno;
	}

	function commentWrite(){
		var cno = bno;
		console.log(cno);
		$.ajax({
			url : "${pageContext.request.contextPath}/board/commentWrite",
			type : "post",
			dataType : "json",
			data : {"cno" : cno, "ccontent" : $("#ccontent").val()},
			success : function(data){
				if(data==1){
					alert("댓글을 달았습니다!");
					$.getCList();
				}
				else{
					alert("댓글달기에 실패했습니다!")
				}
			}
		})
		
	}
	
	$.getCList = function(){
		var cno = bno;
		$.ajax({
			url : "${pageContext.request.contextPath}/board/getCommentList",
			type : "post",
			dataType : "json",
			data : {"cno" : cno},
			success : function(data){
				var pageHTML = "";
				for(var i=0; i<data.length; i++){
					if(data[i]){
						pageHTML += "<p style=\"margin-top : 5px;\">" + data[i].cwriter + "     " + data[i].cregdate + "</p>\n";
						pageHTML += "<p>" + data[i].ccontent +  "</p>\n";
						pageHTML += "<hr width=\"100%\" color=\"#aaa\" style=\"margin-top : 5px;\">\n"
					}
				}
				console.log(pageHTML);
				$("#commentDiv").after(pageHTML);
			}
		})
	}
	
</script>

</body>
</html>
