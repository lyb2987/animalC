<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tamplate/header.css">
</head>


<script type="text/javascript">

</script>


<div class="header" id="header">
	<div class="header-inner">
		<div class="brand" id="brand">
			<a href="${pageContext.request.contextPath}/" class="logoimage"> 
				<img alt=" AnimalC" src="${pageContext.request.contextPath}/resources/images/tamplate/icon.png" width="20px" height="20px"> 
				AnimalC
			</a>
		</div>
		<div class="header-menu">	
			<c:choose>
				<c:when test="${user.id == null}">
					<ul>
						<li class="_item1">
							<a href="${pageContext.request.contextPath}/member/loginPage.do"> 로그인 </a>
						</li>
						<li class="_item"> 
							<a href="${pageContext.request.contextPath}/member/joinPage.do"> 회원가입 </a>
						</li>
						<li class="_item"> 
							<a href="${pageContext.request.contextPath}/board/moveSummerNoteWrite"> 테스트페이지 </a> 
						</li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul>
						<li class="_id"> <a> ${user.nickname} 님  </a> </li>
						<li class="_item"> 
							<a href="${pageContext.request.contextPath}/member/logoutRequest.do"> 로그아웃 </a> 
						</li>
						<li class="_item"> 
							<a href="${pageContext.request.contextPath}/member/myPage.do"> 마이페이지 </a> 
						</li>
						<li class="_item"> 
							<a href="${pageContext.request.contextPath}/member/tespage.do"> 테스트페이지 </a> 
						</li>
						<!--
						<li class="_item"> 
							<a href="${pageContext.request.contextPath}/board/writeboardloop"> 인서트 </a> 
						</li>
						-->
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<div class="menu-bar" id="menubar">
	<div class="menu-inner" id="menuinner">
		<ul class="menu-items">
			<li class="menu-item1">
				<a class="menu-a" href="${pageContext.request.contextPath}/"> 인기 게시글 </a>
			</li>
			<li class="menu-item"> 
				<a class="menu-a" href="${pageContext.request.contextPath}/"> 공략 </a>
			</li>
			<li class="menu-item"> 
				<a class="menu-a" href="${pageContext.request.contextPath}/"> 도감 </a>
			</li>
			<li class="menu-item"> 
				<a class="menu-a" href="${pageContext.request.contextPath}/"> 질문 </a>
			</li>
			<li class="menu-item"> 
				<a class="menu-a" href="${pageContext.request.contextPath}/board/moveBoardMain"> 자유게시판 </a>
			</li>
			<li class="menu-item">
				<a class="menu-a" href="${pageContext.request.contextPath}/"> 검색 </a>
			</li>
			<li class="menu-item"> 
				<a class="menu-a" href="${pageContext.request.contextPath}/"> 마켓 </a>
			</li>
		</ul>
	</div>
</div>

<hr width="100%" color="black">
