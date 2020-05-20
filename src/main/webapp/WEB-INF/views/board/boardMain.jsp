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
	<title>게시판 메인</title>
	<%@ include file="../tamplate/header.jsp"%>
</head>
<body>
	
<div class="wrapper">
	<div class="main-wrap">
		<div class="main-col-wrap">
			<div class="board" style="width:100%;">
				<table style="width:100%; text-align: center; border-bottom: 1px solid #aaa;">
					<tr id="boardhead" style="border-bottom: 1px solid #aaa;">
						<td>글 번호</td>
						<td>글 종류</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>

				</table>
			</div>
		
			<div>
				<div id="beforePageBtn">
					<button onclick="goFront();" value="">맨 앞</button>
					<button onclick="goPrevious();" value="">이전</button>
				</div>
						
				<ul id="pageUl" class="pageUl">
				</ul>
	
				<div id="afterPageBtn">
					<button onclick="goNext();" value="">다음</button>
					<button onclick="goEnd();" value="">맨 끝</button>
				</div>
			</div>
		
			<div class="Write">
				<a href="./writeBoardPage" style="color: black;"> 게시글 작성</a>
			</div>
		</div>
		<div class="sub-col-wrap" style="background: yellow">
		</div>
	</div>
	<div class="sub-wrap" style="background: yellow">

	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		console.log("ready");
		var pageNum = 1;
		$.getList(pageNum);
		$.getPageButton(pageNum);
	});

	// 게시글 뿌려주는 ajax
	$.getList = function(pageNum){
		$.ajax({
			url : "${pageContext.request.contextPath}/board/getBoardPageList",
			type : "post",
			dataType : "json",
			data : {"currenP" : pageNum},
			success : function(data){

				var pageHTML2 ="";
				$("#boardTable").empty();
				console.log(data.length);
				
				for(var i=0; i<=data.length; i++){
					// 리스트로 되있을때 if문으로 검사한해주면 요효하지 않은 애들이라고 에러 개나옴 ㅅㅂ
					if(data[i]){
						pageHTML2 += "<tr id=\"boardTable\" style=\"border-bottom: 1px solid #aaa;\">\n"
						pageHTML2 += "<td>" + data[i].bno + "</td>\n";
						pageHTML2 += "<td>" + data[i].bkind + "</td>\n";
						pageHTML2 += "<td> <a href=\"./viewBoard\" style=\"color : black;\">" + data[i].btitle + "</a> </td>\n";
						pageHTML2 += "<td>" + data[i].bwriter + "</td>\n";
						pageHTML2 += "<td>" + data[i].regdate + "</td>\n";
						pageHTML2 += "<td>" + data[i].viewCnt + "</td>\n";
						pageHTML2 += "</tr>"
					}
				}
				//console.log(pageHTML2);
				$("#boardhead").after(pageHTML2);
			}
		})
	}

	// 버튼 뿌려주는 ajax
	$.getPageButton = function(pageNum){
		$.ajax({
			url : "${pageContext.request.contextPath}/board/getPageBtn",
			type : "post",
			dataType : "json",
			data : {"currentP" : ${paging.currentPage}},
			success : function(data){
				var currentP = data.currentPage;
				var pageHTML ="";
				$("#pageUl").empty();
				for(var i=data.startPage; i<=data.endPage; i++){
					console.log(i);
					pageHTML += "<li id=\"pageList\" class=\"pageList\">\n";
					pageHTML += "\t<a href=\"\" id=\"pagebtn" + i + "\" style=\"color : black;\">" + i + "</a>\n" 
					pageHTML += "</li>\n"
				}
				console.log(pageHTML);
				$("#pageUl").append(pageHTML);
			}
		})
	}
	
	function goPrevious(){
	}
	function goFront(){
	}
	function goEnd(){
	}
	
	function goNext(){
		console.log(${paging.currentPage});
		$.ajax({
			url : "${pageContext.request.contextPath}/board/moveNextPage",
			type : "post",
			dataType : "json",
			data : {"currentP" : ${paging.currentPage}, "startP" : ${paging.startPage}, "endP" : ${paging.endPage}},
			success : function(data){
				var currentP = data.currentPage;
				var pageHTML ="";
				$("#pageUl").empty();
				for(var i=data.startPage; i<=data.endPage; i++){
					console.log(i);
					pageHTML += "<li id=\"pageList\" class=\"pageList\">\n";
					pageHTML += "\t<a href=\"\" id=\"pagebtn" + i + "\" style=\"color : black;\">" + i + "</a>\n" 
					pageHTML += "</li>"
					console.log(pageHTML);
					//$("#pageUl").html(data.currentPage);
					$.ajax({
						url : "${pageContext.request.contextPath}/board/getBoardPageList",
						type : "post",
						dataType : "json",
						data : {"currenP" : currentP},
						success : function(data){
							var pageHTML2 ="";
							$("#boardTable").empty();
							console.log(data.length);
							for(var i=0; i<=data.length; i++){
								pageHTML2 ="";
								pageHTML2 += "<td>" + ${data[i].bno} + "</td>\n";
								pageHTML2 += "<td>" + ${data[i].bkind} + "</td>\n";
								pageHTML2 += "<td> <a href=\"./viewBoard\" style=\"color : black;\">" + ${data[i].btitle} + "</a> </td>\n";
								pageHTML2 += "<td>" + ${data[i].bwriter} + "</td>\n";
								pageHTML2 += "<td>" + ${data[i].regdate} + "</td>\n";
								pageHTML2 += "<td>" + ${data[i].viewCtn} + "</td>\n";
							}
							console.log(pageHTML2);
						}
					})
				}
			}
		})
	}
	
	

	
</script>

</body>
</html>
