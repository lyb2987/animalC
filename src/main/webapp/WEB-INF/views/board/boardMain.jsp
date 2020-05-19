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
					<tr style="border-bottom: 1px solid #aaa;">
						<td>글 번호</td>
						<td>글 종류</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>
					<c:forEach var="row" items="${list}">
						<tr style="border-bottom: 1px solid #aaa;">
							<td>${row.bno}</td>
							<td>${row.bkind}</td>
							<td><a href="./viewBoard" style="color: black;">${row.btitle}</a></td>
							<td>${row.bwriter}</td>
							<td>${row.regdate}</td>
							<td>${row.viewCnt}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		
			<div class="pagingAndWrite">
				<ul class="pageUl">
					<li>
						<button onclick="goFront();" value="">맨 앞</button>
					</li>
					<li>
						<button onclick="goPrevious();" value="">이전</button>
					</li>
					
					<c:choose>
						<c:when test="${paging.currentPage <= 10}">
							<c:set var="startPageNum" value="1"/>
							<c:set var="endPageNum" value="10"/>
						</c:when>	
						<c:otherwise>
							<c:choose>
								<c:when test="${(paging.currentPage%10)==0}">
									<c:set var="startPageNum" value="${paging.currentPage-9}"/>
									<c:set var="endPageNum" value="${paging.currentPage}"/>
								</c:when>
								<c:otherwise>
									<c:set var="startPageNum" value="${(paging.currentPage/10)*10+1}"/>
									<c:set var="endPageNum" value="${paging.currentPage+9}"/>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					
					<c:if test="${endPageNum > paging.endPage}">
						<c:set var="endPageNum" value="${paging.endPage}"/>
					</c:if>
					
					<c:forEach var="pageNum" begin="${startPageNum}" end="${endPageNum}" step="1">
						<li class="pageList">
							<a href="" style="color: black;">${pageNum}</a>
						</li>
					</c:forEach>
					
					
					<li>
						<button onclick="goNext();" value="">다음</button>
					</li>
					<li>
						<button onclick="goEnd();" value="">맨 끝</button>
					</li>
				</ul>
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
		
	});

	function goPrevious(){
	}
	function goFront(){
	}
	function goEnd(){
	}
	function goNext(){
		$.ajax({
			url : "${pageContext.request.contextPath}/board/moveNextPage",
			type : "post",
			dataType : "json",
			data : {"currentP" : "${paging.currentPage}", "startP" : "${startPageNum}", "endP" : "${endPageNum}"},
			success : function(data){
				
			}
		})
		
		/*var currentPage = "${paging.currentPage}";
		${startPageNum} = ${startPageNum} + 10;
		${endPageNum} = ${endPageNum} + 10;
		
		if("${endPageNum}" > "${paging.endPage}")
			"${endPageNum}" = "${paging.endPage}";
		alert("${startPageNum}" "${endPageNum}" );
		*/
	}
</script>

</body>
</html>
