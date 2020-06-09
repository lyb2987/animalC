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
	<title>게시글 수정</title>
	<%@ include file="../tamplate/header.jsp"%>
</head>
<body>
	
<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			<div id="writediv" style="margin-top: 0px;">
				<form action="./modifyQBoard" method=post id="writeForm">
					<div class="titlediv" style="margin-top: 10px;">
					 	질문 번호 : <input  type="text" style="width: 40px;" name="qbno" id="qbno" value="${qboard.qbno}" readonly="readonly">
					 	<input type="text" name="qbtitle" id="qbtitle" value="${qboard.qbtitle}">
					</div>
					
					<textarea name="qbcontent" id="qbcontent">${qboard.qbcontent}</textarea>
					
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

$(document).ready(function() { 
	$('#qbcontent').summernote();

});

function sleep(delay){
	var start = new Date().getTime();
	while(new Date().getTime() < start + delay);
}

$('#qbcontent').summernote({
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
              url:"${pageContext.request.contextPath}/board/fileInsert",
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
				 
                 $("#qbcontent").summernote('editor.insertImage',imageName);
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

</script>
</body>
</html>
