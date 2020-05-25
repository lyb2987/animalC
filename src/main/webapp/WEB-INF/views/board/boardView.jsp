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
				<!-- 게시글 작성자와 조회하는 사람이 같을시 수정, 삭제버튼 배치 -->
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
				<div class="commentsDiv" id="commentsDiv" style="margin-top: 20px;">
				
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
	var lastC = "";
	
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

	// 댓글달기
	function commentWrite(){
		$.ajax({
			url : "${pageContext.request.contextPath}/board/commentWrite",
			type : "post",
			dataType : "json",
			data : {"bno" : bno, "ccontent" : $("#ccontent").val()},
			success : function(data){
				if(data==1){
					alert("댓글을 달았습니다!");
					$("#commentsDiv").empty();
					$("#ccontent").val("");
					$.getCList();
				}
				else{
					alert("댓글달기에 실패했습니다!")
				}
			}
		})
		
	}

	// 댓글 불러오기
	$.getCList = function(){
		// 쌍 따옴표로 묶어줘야 에러 안남
		var userId = "${user.id}";
		$.ajax({
			url : "${pageContext.request.contextPath}/board/getCommentList",
			type : "post",
			dataType : "json",
			data : {"bno" : bno},
			success : function(data){
				var pageHTML = "";
				for(var i=0; i<data.length; i++){
					if(data[i]){
						pageHTML += "<div class=\"commentDiv\">\n";
							pageHTML += "<div class=\"chead\">\n";
								pageHTML += "<p style=\"margin-top : 5px;\">" + data[i].cwriter + "     " + data[i].cregdate + "</p>\n";
							pageHTML += "</div>\n";
								//조건문으로 댓글 작성자 확인후 배치, 비교식때 equals 사용이 아니고 == 를 사용해야함 
							pageHTML += "<div class=\"commentbody\" id=\"commentbody"+ data[i].cno +"\">\n";
								if(userId == data[i].cwriter){
									pageHTML += "<div class=\"cModifyAndcDelete\" id=\"cModifyAndcDelete" + data[i].cno + "\" style=\"float: right; margin-top : 10px;\">\n";
										pageHTML += "<button class=\"cModifyBtn\" type=\"button\" id=\"cModifyBtn\" value=\"" + data[i].cno + "\" onclick=\"cModifytry(" + data[i].cno + ")\">수정</button>\n";
										pageHTML += "<button class=\"cDeleteBtn\" type=\"button\" id=\"cDeleteBtn\" onclick=\"cDelete(" + data[i].cno + ")\">삭제</button>\n";
									pageHTML += "</div>\n";
								}
								pageHTML += "<div class=\"cContentDiv\" id=\"cContentDiv" + data[i].cno + "\">\n";
									pageHTML += "<p id=\"cContent"+ data[i].cno +"\">" + data[i].ccontent +  "</p>\n";
								pageHTML += "</div>\n";	
							pageHTML += "</div>\n";
						pageHTML += "</div>\n";
						pageHTML += "<hr width=\"100%\" color=\"#aaa\" style=\"margin-top : 5px;\">\n"
					}
				}
				console.log(pageHTML);
				$("#commentsDiv").append(pageHTML);
			}
		})
	}

	// 댓글 수정 폼생성
	function cModifytry(cno){
		lastC = $("#cContent"+ cno).text();
		
		$("#cModifyAndcDelete" + cno).remove();
		$("#cContent" + cno).remove();
		var pageHTML = "";
		pageHTML += "<div id=\"modifyDiv\">";
		pageHTML += "<textarea style=\"width: 100%; height: 50px;\" name=\"cmodifycontent\" id=\"cmodifycontent\" placeholder=\"댓글의 내용을 입력해주세요.\"></textarea>";
		pageHTML += "<button class=\"cmodifyBtn\" type=\"button\" id=\"cmodifyBtn\" onclick=\"cmodify(" + cno + ");\">수정</button>";
		pageHTML += "<button class=\"ccancleBtn\" type=\"button\" id=\"ccancleBtn\" value=\"none\" onclick=\"ccancle(" + cno + ");\">취소</button>";
		pageHTML += "</div>";
		$("#commentbody" + cno).append(pageHTML);
		$("#ccancleBtn").attr("value", lastC);

	}


	// 댓글 삭제
	function cDelete(cno){
		var conf = confirm("댓글을 삭제하시겠습니까?")
		if(conf == false)
			return ;
		
		$.ajax({
			url : "${pageContext.request.contextPath}/board/deleteComment",
			type : "post",
			dataType : "json",
			data : {"cno" : cno},
			success : function(data){
				if(data == 1){
					$("#commentsDiv").empty();
					alert("댓글을 삭제했습니다!");
					$.getCList();
				}
				else{
					$("#commentsDiv").empty();
					alert("댓글 삭제에 실패했습니다!");
					$.getCList();
				}
			}
			
		})
	}

	// 댓글 수정 
	function cmodify(cno){
		$.ajax({
			url : "${pageContext.request.contextPath}/board/modifyComment",
			type : "post",
			dataType : "json",
			data : {"cno" : cno, "mcomment" : $("#cmodifycontent").val()},
			success : function(data){
				if(data == 1){
					$("#commentsDiv").empty();
					alert("댓글을 수정했습니다!");
					$.getCList();
				}
				else{
					$("#commentsDiv").empty();
					alert("댓글 수정에 실패했습니다!");
					$.getCList();
				}
			}
			
		})
	}

	// 댓글 수정 취소
	function ccancle(cno){
		var lastValue = $("#ccancleBtn").attr("value");
		$("#modifyDiv").remove();
		var pageHTML = "";
		pageHTML += "<div class=\"cModifyAndcDelete\" id=\"cModifyAndcDelete" + cno + "\" style=\"float: right; margin-top : 10px;\">\n";
		pageHTML += "<button class=\"cModifyBtn\" type=\"button\" id=\"cModifyBtn\" value=\"" + cno + "\" onclick=\"cModifytry(" + cno + ")\">수정</button>\n";
		pageHTML += "<button class=\"cDeleteBtn\" type=\"button\" id=\"cDeleteBtn\" onclick=\"cDelete(" + cno + ")\">삭제</button>\n";
		pageHTML += "</div>\n";
		pageHTML += "<div class=\"cContentDiv\" id=\"cContentDiv" + cno + "\">\n";
		pageHTML += "<p id=\"cContent"+ cno +"\">" + lastValue +  "</p>\n";
		pageHTML += "</div>\n";	

		$("#commentbody" + cno).append(pageHTML);
		
	}
	
</script>

</body>
</html>
