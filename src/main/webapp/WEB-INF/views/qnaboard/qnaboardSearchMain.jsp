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
			<div class="qboard" style="width:100%;">
				<h2 style="margin-left: 400px; margin-top:15px; font-size: 25px; color: rgb(0,0,0);">질문게시판</h2>
				<form action="./searchQBoard" method=post id="writeForm">
					<div class="searchDiv" style="margin-top: 10px; float: right;">
					 	<select id="searchBoundary" name="searchBoundary">
						 	<option id="Title">제목</option>
						 	<option id="Content">내용</option>
						 	<option id="TitleAndContent">제목 + 내용</option>
					 	</select>
					 	<input type="text" name="searchTerm" id="searchTerm" placeholder="검색어를 입력해주세요.">
						<input class="writebtn" id="writebtn" type="submit" value="확인">
					</div>
				</form>
				<table style="width:100%; text-align: center; border-bottom: 1px solid #aaa;">
					<tr id="qboardhead" style="border-bottom: 1px solid #aaa;">
						<td>글 번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>답변수</td>
						<td>조회수</td>
						<td>추천수</td>
						<td>채택 여부</td>
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
	var searchB = "${paging.sb}";
	var searchT = "${paging.st}"; 
	var searchBcount = ${paging.boardCnt};
	var searchCP = ${paging.currentPage};
	var pageSize = ${paging.pageCnt};
	
	$(document).ready(function(){
		console.log("ready");
		$.getList(1);
		$.getPageButton(1);
	});

	// 게시글 뿌려주는 ajax
	$.getList = function(pageNum){
		$.ajax({
			url : "${pageContext.request.contextPath}/qnaboard/getSearchQnABoardPageList",
			type : "post",
			dataType : "json",
			data : {"searchB" : searchB, "searchT" : searchT, "searchBcount" : searchBcount, "searchCP" : searchCP},
			success : function(data){
				var pageHTML2 = "";
				var bnoString = "";
				$("tr").remove(".qboardList");
				for(var i=0; i<=data.length; i++){
					if(data[i]){
						pageHTML2 += "<tr id=\"qboardTable\" class=\"qboardList\" style=\"border-bottom: 1px solid #aaa;\">\n"
						pageHTML2 += "<td>" + data[i].qbno + "</td>\n";
						pageHTML2 += "<td> <a href=\"./viewQBoard?qbno=" + data[i].qbno + "\" id=\"qbtitle" + data[i].qbno +"\" style=\"color : black; font-weight : bold;\">" + data[i].qbtitle + "</a> </td>\n";						
						pageHTML2 += "<td>" + data[i].qbwriter + "</td>\n";
						pageHTML2 += "<td>" + data[i].qregdate + "</td>\n";
						pageHTML2 += "<td>" + data[i].acount + "</td>\n";
						pageHTML2 += "<td>" + data[i].viewcnt + "</td>\n";
						pageHTML2 += "<td id=\"likeCnt\">" + data[i].likecnt + "</td>\n"
						if(data[i].adoption == "")
							pageHTML2 += "<td> 채택 안 됨 </td>\n";
						else
							pageHTML2 += "<td> 채택 됨 </td>\n";
						pageHTML2 += "</tr>"
					}
				}
				console.log(pageHTML2);
				$("#qboardhead").after(pageHTML2);
				console.log("현재 페이지 : " + searchCP);	
			}
		})
	}

	// 버튼 뿌려주는 ajax
	$.getPageButton = function(searchCP){
		$.ajax({
			url : "${pageContext.request.contextPath}/qnaboard/getSearchPageBtn",
			type : "post",
			dataType : "json",
			data : {"searchB" : searchB, "searchT" : searchT, "searchBcount" : searchBcount, "searchCP" : searchCP},
			success : function(data){
				var searchCP = data.currentPage;
				var pageHTML ="";
				$("#pageUl").empty();
				for(var i=data.startPage; i<=data.endPage; i++){
					pageHTML += "<li id=\"pageList\" class=\"pageList\">\n";
					pageHTML += "\t<a href=\"#none\" onclick=\"goPageBtn(" + i + ")\" id=\"pagebtn" + i + "\" style=\"color : black;\">" + i + "</a>\n" 
					pageHTML += "</li>\n"
				}
				$("#pageUl").append(pageHTML);
				$("#pagebtn"+searchCP).css("font-weight", "bold");
			}
		})
	}
	
	function goFront(){
		searchCP=1;
		$.getList(1);
		$.getPageButton(1);
	}
	
	function goEnd(){
		searchCP=pageSize;
		$.getList(searchCP);
		$.getPageButton(searchCP);
	}

	function goPageBtn(i){
		$("#pagebtn"+searchCP).css("font-weight", "normal");
		searchCP = i;
		$("#pagebtn"+searchCP).css("font-weight", "bold");
		console.log("현재 페이지 : " + searchCP);
		$.getList(i);
	}

	// 이전버튼 기능
	function goPrevious(){
		if(searchCP <= 10){
			alert("이미 첫 페이지 입니다.");
			return ;
		}

		console.log("다음 버튼 시작 전 현재 페이지 : " + searchCP);
		
		$.ajax({
			url : "${pageContext.request.contextPath}/qnaboard/moveSearchPreviousPage",
			type : "post",
			dataType : "json",
			data : {"searchB" : searchB, "searchT" : searchT, "searchBcount" : searchBcount, "searchCP" : searchCP},
			success : function(data){
				var pageHTML ="";
				$("#pageUl").empty();
				for(var i=data.startPage; i<=data.endPage; i++){
					pageHTML += "<li id=\"pageList\" class=\"pageList\">\n";
					pageHTML += "\t<a  href=\"#none\" onclick=\"goPageBtn("+i+")\" id=\"pagebtn" + i + "\" style=\"color : black;\">" + i + "</a>\n" 
					pageHTML += "</li>\n"
				}
				$("#pageUl").append(pageHTML);
				searchCP = data.currentPage;
				$("#pagebtn"+searchCP).css("font-weight", "bold");
				console.log("다음 클릭 후 현재 페이지 : " + searchCP);
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
			if(searchCP%10==0){
				if((searchCP/10)*10+1 > pageSize){
					alert("이미 마지막 페이지 입니다.");
					return ;
				}
			}
			else{
				if((searchCP/10)*10+11 > pageSize){
					alert("이미 마지막 페이지 입니다.");
					return ;
				}
			}
		}
	
		console.log("다음 버튼 시작 전 현재 페이지 : " + searchCP);
		
		$.ajax({
			url : "${pageContext.request.contextPath}/qnaboard/moveSearchNextPage",
			type : "post",
			dataType : "json",
			data : {"searchB" : searchB, "searchT" : searchT, "searchBcount" : searchBcount, "searchCP" : searchCP},
			success : function(data){
				var pageHTML ="";
				$("#pageUl").empty();
				for(var i=data.startPage; i<=data.endPage; i++){
					pageHTML += "<li id=\"pageList\" class=\"pageList\">\n";
					pageHTML += "\t<a  href=\"#none\" onclick=\"goPageBtn("+i+")\" id=\"pagebtn" + i + "\" style=\"color : black;\">" + i + "</a>\n" 
					pageHTML += "</li>\n"
				}
				$("#pageUl").append(pageHTML);
				searchCP = data.currentPage;
				$("#pagebtn"+searchCP).css("font-weight", "bold");
				console.log("다음 클릭 후 현재 페이지 : " + searchCP);
				$.getList(data.currentPage);
			}
		})
	}
	

	
</script>

</body>
</html>
