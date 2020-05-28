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
				<h2 style="margin-left: 400px; margin-top:15px; font-size: 25px; color: rgb(0,0,0);">자유게시판</h2>
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
			<div class="Write" style="margin-top: 5px; float : right;" >
				<a href="./writeBoardPage" style="color: black;"> 게시글 작성</a>
			</div>
		
			<div id="bottomDiv" style="margin-left : 175px; margin-top: 20px;">
				<div id="buttonAndPaging" style="margin-top: 20px;">
					<button onclick="goFront();" value="">맨 앞</button>
					<button onclick="goPrevious();" value="">이전</button>

						
				<ul id="pageUl" class="pageUl" style="margin-left: 100px; margin-top: -25px;">
				</ul>
	
					<button onclick="goNext();" value="" style="margin-left : 8px;">다음</button>
					<button onclick="goEnd();" value="">맨 끝</button>
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
	var currentP = ${paging.currentPage};
	var pageSize = ${paging.pageCnt};
	
	$(document).ready(function(){
		console.log("ready");
		$.getList(1);
		$.getPageButton(1);
	});

	// 게시글 뿌려주는 ajax
	$.getList = function(pageNum){
		$.ajax({
			url : "${pageContext.request.contextPath}/board/getBoardPageList",
			type : "post",
			dataType : "json",
			data : {"currentP" : pageNum},
			success : function(data){
				var pageHTML2 ="";
				$("tr").remove(".boardList");
				for(var i=0; i<=data.length; i++){
					// 리스트로 되있을때 if문으로 검사한해주면 요효하지 않은 애들이라고 에러 개나옴 ㅅㅂ
					if(data[i]){
						pageHTML2 += "<tr id=\"boardTable\" class=\"boardList\" style=\"border-bottom: 1px solid #aaa;\">\n"
						pageHTML2 += "<td>" + data[i].bno + "</td>\n";
						pageHTML2 += "<td>" + data[i].bkind + "</td>\n";
						// 테스트를 위해 viewBoard -> viewBoardTest로 변경
						pageHTML2 += "<td> <a href=\"./viewBoard?bno=" + data[i].bno + "\" style=\"color : black; font-weight : bold;\">" + data[i].btitle + "</a> </td>\n";						
						pageHTML2 += "<td>" + data[i].bwriter + "</td>\n";
						pageHTML2 += "<td>" + data[i].regdate + "</td>\n";
						pageHTML2 += "<td>" + data[i].viewCnt + "</td>\n";
						pageHTML2 += "</tr>"
					}
				}
				//console.log(pageHTML2);
				$("#boardhead").after(pageHTML2);
				console.log("현재 페이지 : " + currentP);	
			}
		})
	}

	
	// 버튼 뿌려주는 ajax
	$.getPageButton = function(currentP){
		$.ajax({
			url : "${pageContext.request.contextPath}/board/getPageBtn",
			type : "post",
			dataType : "json",
			data : {"currentP" : currentP},
			success : function(data){
				var currentP = data.currentPage;
				var pageHTML ="";
				$("#pageUl").empty();
				for(var i=data.startPage; i<=data.endPage; i++){
					pageHTML += "<li id=\"pageList\" class=\"pageList\">\n";
					pageHTML += "\t<a href=\"#none\" onclick=\"goPageBtn(" + i + ")\" id=\"pagebtn" + i + "\" style=\"color : black;\">" + i + "</a>\n" 
					pageHTML += "</li>\n"
				}
				$("#pageUl").append(pageHTML);
				$("#pagebtn"+currentP).css("font-weight", "bold");
			}
		})
	}
	
	function goFront(){
		currentP=1;
		$.getList(1);
		$.getPageButton(1);
	}
	
	function goEnd(){
		currentP=pageSize;
		$.getList(currentP);
		$.getPageButton(currentP);
	}

	function goPageBtn(i){
		$("#pagebtn"+currentP).css("font-weight", "normal");
		currentP = i;
		$("#pagebtn"+currentP).css("font-weight", "bold");
		console.log("현재 페이지 : " + currentP);
		$.getList(i);
	}

	// 이전버튼 기능
	function goPrevious(){
		if(currentP <= 10){
			alert("이미 첫 페이지 입니다.");
			return ;
		}

		console.log("다음 버튼 시작 전 현재 페이지 : " + currentP);
		
		$.ajax({
			url : "${pageContext.request.contextPath}/board/movePreviousPage",
			type : "post",
			dataType : "json",
			data : {"currentP" : currentP},
			success : function(data){
				var pageHTML ="";
				$("#pageUl").empty();
				for(var i=data.startPage; i<=data.endPage; i++){
					pageHTML += "<li id=\"pageList\" class=\"pageList\">\n";
					pageHTML += "\t<a  href=\"#none\" onclick=\"goPageBtn("+i+")\" id=\"pagebtn" + i + "\" style=\"color : black;\">" + i + "</a>\n" 
					pageHTML += "</li>\n"
				}
				$("#pageUl").append(pageHTML);
				currentP = data.currentPage;
				$("#pagebtn"+currentP).css("font-weight", "bold");
				console.log("다음 클릭 후 현재 페이지 : " + currentP);
				$.getList(data.currentPage);
			}
		})
	}
		
	// 다음버튼 기능
	function goNext(){
		//페이지 사이즈가 10보다 작거나 같으면 리턴
		if(pageSize <= 10){
			alert("이미 마지막 페이지 입니다.");
			return ;
		}
		else{
			if(currentP%10==0){
				if((currentP/10)*10+1 > pageSize){
					alert("이미 마지막 페이지 입니다.");
					return ;
				}
			}
			else{
				if((currentP/10)*10+11 > pageSize){
					alert("이미 마지막 페이지 입니다.");
					return ;
				}
			}
		}
	
		console.log("다음 버튼 시작 전 현재 페이지 : " + currentP);
		
		$.ajax({
			url : "${pageContext.request.contextPath}/board/moveNextPage",
			type : "post",
			dataType : "json",
			data : {"currentP" : currentP},
			success : function(data){
				var pageHTML ="";
				$("#pageUl").empty();
				for(var i=data.startPage; i<=data.endPage; i++){
					pageHTML += "<li id=\"pageList\" class=\"pageList\">\n";
					pageHTML += "\t<a  href=\"#none\" onclick=\"goPageBtn("+i+")\" id=\"pagebtn" + i + "\" style=\"color : black;\">" + i + "</a>\n" 
					pageHTML += "</li>\n"
				}
				$("#pageUl").append(pageHTML);
				currentP = data.currentPage;
				$("#pagebtn"+currentP).css("font-weight", "bold");
				console.log("다음 클릭 후 현재 페이지 : " + currentP);
				$.getList(data.currentPage);
			}
		})
	}
	



	
</script>

</body>
</html>
