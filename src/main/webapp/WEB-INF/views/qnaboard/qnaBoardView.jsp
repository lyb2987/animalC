<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home.css">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<title>질문 게시글</title>
	<%@ include file="../tamplate/header.jsp"%>
</head>
<body>

<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			<div class="boardView" style="width:100%;">
				<div class="boardTitle">
					<h3>${qboard.qbno} ${qboard.qbtitle}</h3>
				</div>
				<!-- 게시글 작성자와 조회하는 사람이 같을시 수정, 삭제버튼 배치 -->
				<c:set var="writer" value="${qboard.qbwriter}"/>
				<c:choose>
					<c:when test="${user.id.equals(writer)}">
						<div class="modifyAndDeleteForm">
							<button class="modifyBtn" type="button" id="modifyBtn" onclick="modifyQBoard();">수정</button> 
							<button class="deleteBtn" type="button" id="deleteBtn" onclick="deleteQBoard();">삭제</button> 
						</div>
					</c:when>
				</c:choose>
				<div class="boardContent">
					<p>${qboard.qbcontent}</p>
				</div>
				<div class="baordInfo">
					<p>${qboard.qbwriter} ${qboard.qregdate} ${qboard.viewcnt} ${qboard.likecnt}</p>
					<p id="likeCnt"> ${qboard.likecnt}</p>
				</div>
				
				<div class="qblike">
					<button class="qblikeBtn" type="button" id="qblikeBtn" onclick="qblikeUp();">추천</button>
				</div>
			</div>
			<div class="answerWriteDiv" style="margin-top: 20px;">
				<button class="answerFormBtn" type="button" id="answerFormBtn" onclick="getAnswerForm();">답변 달기</button> 
			</div>
			<hr width="100%" color="black">
			
			<div class = "answerDiv" style="margin-top: 10px;">
				<c:forEach var="alist" items="${qboard.alist}">
					<div class="answerHead" style="margin-top: 10px">
						<p>${alist.qbno} : ${alist.abno}       작성자 : ${alist.awriter}    추천 : ${alist.likecnt}      ${alist.aregdate}</p>
					</div>
					<div class="acontent" style="margin-top: 3px">
						<p>${alist.acontent}</p>
					</div>
					<hr width="100%" color="black"  style="margin-top: 5px">
				</c:forEach>
			</div>
		</div>
		<div class="sub-col-wrap" style="background: yellow">
		</div>
	</div>
	<div class="sub-wrap" style="background: yellow">

	</div>
</div>

<script type="text/javascript">
	var qbno = ${qboard.qbno};
	
	$(document).ready(function(){
	});

	// 답변 폼 생성
	function getAnswerForm(){
		var userId = "${user.id}";
		if(userId == ""){
			alert("답변을 달기 위해서 로그인해주세요!");
			return 0;
		}
		$("#answerFormBtn").remove();
		var pageHTML = "";
		pageHTML += "<div id=\"answerFormDiv\">";
		pageHTML += "<textarea name=\"answerContent\" id=\"answerContent\"></textarea>";
		pageHTML += "<button class=\"answerWrite\" type=\"button\" id=\"answerWrite\" onclick=\"answerWrite(" + qbno + ");\">완료</button>";
		pageHTML += "<button class=\"canaleAnswer\" type=\"button\" id=\"canaleAnswer\" value=\"none\" onclick=\"cancleAnswer();\">취소</button>";
		pageHTML += "</div>";

		$('.answerWriteDiv').append(pageHTML);
		$.callSummernote();
		
	}	
	// 답변 취소
	function cancleAnswer(){
		$("#answerFormDiv").remove();
		var pageHTML = "";
		pageHTML += "<button class=\"answerFormBtn\" type=\"button\" id=\"answerFormBtn\" onclick=\"getAnswerForm();\">답변 달기</button>";
		$('.answerWriteDiv').append(pageHTML);
	}

	// 답변 쓰기
	function answerWrite(qbno){
		var userId = "${user.id}";
		var answerContent = $("#answerContent").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/answer/answerWrite",
			type : "post",
			dataType : "json",
			data : {"qbno" : qbno, "userId" : userId, "acontent" : answerContent},
			success : function(data){
				if(data == 1){
					alert("답변 작성 완료!");
					$("#answerFormDiv").remove();
					var pageHTML = "";
					pageHTML += "<button class=\"answerFormBtn\" type=\"button\" id=\"answerFormBtn\" onclick=\"getAnswerForm();\">답변 달기</button>";
					$('.answerWriteDiv').append(pageHTML);
				}
				else{
					alert("답변 작성 실패..");
					$("#answerFormDiv").remove();
					var pageHTML = "";
					pageHTML += "<button class=\"answerFormBtn\" type=\"button\" id=\"answerFormBtn\" onclick=\"getAnswerForm();\">답변 달기</button>";
					$('.answerWriteDiv').append(pageHTML);
				}
			}
		})
		
		
	}
	
	// 답변 섬머노트 적용
	$.callSummernote = function(){
		 $("#answerContent").summernote({
			    placeholder: '내용을 입력해주세요.',
			    height: 300,                 
			    minHeight: null,             
			    maxHeight: null,           
			    focus: true,
			    callbacks : {
			        onImageUpload : function(files, editor) {
			
			           var formData = new FormData(); //html의 폼태그와 같은 역할
			           formData.append('files',files[0]); //<input type="file" name="">
			           
			           $.ajax({
			              type:"post",
			              url:"${pageContext.request.contextPath}/answer/fileInsert",
			              data:formData,
			              enctype:"multipart/form-data",
			              cache:false,
			              contentType:false,
			              processData:false,
			              success:function(imageName){
			
			                 imageName = imageName.trim();
			
							 // server.xml에 외부 리소스 폴더를 명시해 놨으므로 그부분을 붙여서 사용하면 잘 가져옴
			                 imageName = "/getImages/"+imageName;
			                 console.log("<"+imageName+">");
							 
			                 $("#answerContent").summernote('editor.insertImage',imageName);
			              }
			           });
			        },
			        
			        onMediaDelete:function(files){
			           
			           var fileName = $(files[0]).attr("src");
			           fileName = fileName.substring(fileName.lastIndexOf("/"));
			           console.log(fileName);
			           
			           $.ajax({
			              type:"post",
			              url:"../boardFile/summerDelete",
			              data:{fileName:fileName},
			              success:function(data){
			                 data = data.trim();
			                 console.log(data);
			              }
			           })
			        }
			        
			     }
			 });
	}

	// 추천 
	function qblikeUp(){
		var userId = "${user.id}";
		
		$.ajax({
			url : "${pageContext.request.contextPath}/qlike/qblikeUp",
			type : "post",
			dataType : "json",
			data : {"qbno" : qbno, "userId" : userId},
			success : function(data){
				if(data.likeStatus==-1){
					var conf = confirm("이미 추천하셨습니다. 추천을 취소하시겠습니까?");
					if(conf == false)
						return ;
					$.qblikeDown();
				}
				else{
					alert("추천하였습니다.");
					$("#likeCnt").empty();
					$("#likeCnt").text(data.likeCnt);
				}
			}
		})
	}
	
	// 추천 취소
	$.qblikeDown = function(){
		var userId = "${user.id}";
		$.ajax({
			url : "${pageContext.request.contextPath}/qlike/qblikeDown",
			type : "post",
			dataType : "json",
			data : {"qbno" : qbno, "userId" : userId},
			success : function(data){
				if(data.likeStatus==1){
					alert("추천을 취소하였습니다.");
					$("#likeCnt").empty();
					$("#likeCnt").text(data.likeCnt);
				}
				else{
					alert("추천취소에 실패하였습니다.");
				}
			}
		})
	}
	
	function modifyQBoard(){
		var conf = confirm("게시글을 수정하시겠습니까?")
		if(conf == false)
			return ;
		
		document.location.href = "./moveModifyQBoard?qbno=" + qbno;
	}

	function deleteQBoard(){
		var conf = confirm("게시글을 삭제하시겠습니까?")
		if(conf == false)
			return ;
	
		document.location.href = "./deleteQBoard?qbno=" + qbno;
	}
	

	
	/*
	추가해야 할 것 : 답변 불러오기, 답변추천, 채택, 답변 수정, 답변 삭제
	
	
	*/
	
</script>

</body>
</html>
