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
				<div class="qboardTitle">
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
					<div class="answerHead${alist.abno}" style="margin-top: 10px">
						<p id="answerPHead${alist.abno}">${alist.qbno} : ${alist.abno}       작성자 : ${alist.awriter}    추천 : ${alist.likecnt}      ${alist.aregdate} 
						<c:if test="${qboard.adoption eq alist.abno}"> <img id="adoptionImage" alt="adoption" src="${pageContext.request.contextPath}/resources/images/qboard/check_Icon.png" width="20px" height="20px"></c:if></p>
						<c:choose>
							<c:when test="${user.id.equals(writer)}">
								<c:choose>
									<c:when test="${qboard.adoption eq alist.abno}">
									  <button class="adoptionBtn${alist.abno}" type="button" id="adoptionBtn${alist.abno}" value="${alist.abno}" onclick="adoptionAnswer(this.value);">채택 됨</button> 
									</c:when>
									<c:otherwise>
										<button class="adoptionBtn${alist.abno}" type="button" id="adoptionBtn${alist.abno}"  value="${alist.abno}" onclick="adoptionAnswer(this.value);">채택</button> 
									</c:otherwise>
								</c:choose>
								</c:when>
						</c:choose>
					</div>
					<div class="acontent${alist.abno}" style="margin-top: 3px">
						<p>${alist.acontent}	<button class="alikteBtn${alist.abno}" type="button" id="alikteBtn${alist.abno}" style="float : right;"  value="${alist.abno}" onclick="alikeUp(this.value);">추천</button> </p>
					
						<c:choose>
							<c:when test="${user.id.equals(alist.awriter)}">
								<div class="modifyAndDeleteForm">
									<button class="modifyAnswerBtn" type="button" value="${alist.abno}" id="modifyAnswerBtn" onclick="modifyAnswerForm(this.value);">답변 수정</button> 
									<button class="deleteAnswerBtn" type="button" value="${alist.abno}" id="deleteAnswerBtn" onclick="deleteAnswer(this.value);">답변 삭제</button> 
								</div>
							</c:when>
						</c:choose>
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
	var aModifyStatus = 0;
	
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
					location.reload();
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

	// 질문 수정
	function modifyQBoard(){
		var conf = confirm("게시글을 수정하시겠습니까?")
		if(conf == false)
			return ;
		
		document.location.href = "./moveModifyQBoard?qbno=" + qbno;
	}
	
	// 질문 삭제
	function deleteQBoard(){
		var conf = confirm("게시글을 삭제하시겠습니까?")
		if(conf == false)
			return ;
	
		document.location.href = "./deleteQBoard?qbno=" + qbno;
	}

	// 질문 채택
	function adoptionAnswer(abno){
		$.ajax({
			url : "${pageContext.request.contextPath}/answer/adoptionAnswer",
			type : "post",
			dataType : "json",
			data : {"abno" : abno, "qbno" : qbno},
			success : function(data){
				if(data == 1){
					alert("답변을 채택하였습니다.");
					location.reload();
				}
				else if(data == 2){
					var conf = confirm("채택을 취소하시겠습니까?");
					if(conf == false)
						return ;
					$.cancleAnswer(qbno);
				}
				else if(data == 3)
					alert("이미 다른 답변을 채택하였습니다. 채택을 취소하고 다시 시도해주세요!");
				
				
			}
		})
	}

	// 채택 취소
	$.cancleAnswer = function(abno){
		$.ajax({
			url : "${pageContext.request.contextPath}/answer/cancleAnswer",
			type : "post",
			dataType : "json",
			data : {"abno" : abno, "qbno" : qbno},
			success : function(data){
				if(data == 1){
					alert("답변 채택을 취소 하였습니다.");
					location.reload();
				}
					
				else
					alert("답변 채택 취소 실패");
			}
		})
	}

	// 답변 수정 폼 가져오기
	function modifyAnswerForm(abno){
		var conf = confirm("답변을 수정하시겠습니까?")
		if(conf == false)
			return ;
		if(aModifyStatus == 0 ){
			aModifyStatus =1;
		}
		else{
			alert("이미 다른 답변을 수정중입니다. 취소 후 다시 시도해주세요.")
			return 0;
		}
		
		$('.acontent'+abno).empty();
		var pageHTML = "";
		pageHTML += "<div id=\"answerFormDiv\">";
		pageHTML += "<textarea name=\"answerContent\" id=\"answerContent\"></textarea>";
		pageHTML += "<button class=\"answerModify\" type=\"button\" id=\"answerModify\" onclick=\"modifyAnswer(" + abno + ");\">수정</button>";
		pageHTML += "<button class=\"canaleModifyAnswer\" type=\"button\" id=\"canaleModifyAnswer\" value=\"none\" onclick=\"cancleModifyAnswer("+ abno +");\">취소</button>";
		pageHTML += "</div>";

		$('.acontent'+abno).append(pageHTML);
		$.callSummernote();
	}

	// 답변 수정
	function modifyAnswer(abno){
		var mAContent = $("#answerContent").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/answer/modifyAnswer",
			type : "post",
			dataType : "json",
			data : {"abno" : abno, "acontent" : mAContent},
			success : function(data){
				aModifyStatus = 0;
				if(data == 1){
					alert("답변을 수정하였습니다.");
					location.reload();
				}
					
				else
					alert("답변 수정 실패");
			}
		})
	}

	// 답변 수정 취소
	function cancleModifyAnswer(abno){
		aModifyStatus = 0;

		$.ajax({
			url : "${pageContext.request.contextPath}/answer/getAnswerContent",
			type : "post",
			dataType : "text",
			data : {"abno" : abno},
			success : function(data){
				$("#answerFormDiv").remove();
				console.log(data);
				var rollBackHTML = "";
				rollBackHTML += "<div class=\"acontent" + abno + "\" style=\"margin-top: 3px\">";
				rollBackHTML += data;
				rollBackHTML += "<div class=\"modifyAndDeleteForm\">"
				rollBackHTML += "<button class=\"modifyAnswerBtn\" type=\"button\" value=\"" + abno + "\" id=\"modifyAnswerBtn\" onclick=\"modifyAnswerForm(this.value);\">답변 수정</button>"
				rollBackHTML += "<button class=\"deleteAnswerBtn\" type=\"button\" value=\"" + abno + "\" id=\"deleteAnswerBtn\" onclick=\"deleteAnswer(this.value);\">답변 삭제</button>"
				rollBackHTML += "</div>";
				rollBackHTML += "</div>";
				console.log(rollBackHTML);
				$('.answerHead'+abno).after(rollBackHTML);
			},
			error : function(error) {
				console.log(error);
			}
		})
	}

	
	
	function deleteAnswer(abno){
		var conf = confirm("답변을 삭제하시겠습니까?");
		if(conf == false)
			return ;
		$.ajax({
			url : "${pageContext.request.contextPath}/answer/deleteAnswer",
			type : "post",
			dataType : "json",
			data : {"abno" : abno},
			success : function(data){
				if(data == 1){
					alert("답변을 삭제하였습니다.");
					location.reload();
				}
				else
					alert("답변 삭제 실패");
			}
		})
	}

	function alikeUp(abno){
	var userId = "${user.id}";
		
		$.ajax({
			url : "${pageContext.request.contextPath}/alike/alikeUp",
			type : "post",
			dataType : "json",
			data : {"abno" : abno, "userId" : userId},
			success : function(data){
				if(data.likeStatus==-1){
					var conf = confirm("이미 추천하셨습니다. 추천을 취소하시겠습니까?");
					if(conf == false)
						return ;
					$.alikeDown(abno);
				}
				else{
					alert("추천하였습니다.");
					location.reload();
				}
			}
		})
	}

	$.alikeDown = function(abno){
	var userId = "${user.id}";
		
		$.ajax({
			url : "${pageContext.request.contextPath}/alike/alikeDown",
			type : "post",
			dataType : "json",
			data : {"abno" : abno, "userId" : userId},
			success : function(data){
				if(data.likeStatus==1){
					alert("추천을 취소하였습니다.");
					location.reload();
				}
				else{
					alert("추천 취소에 실패했습니다!");
				}
			}
		})
	}
	
	/*
	추가해야 할 것 : 답변추천
	*/
	
</script>

</body>
</html>
