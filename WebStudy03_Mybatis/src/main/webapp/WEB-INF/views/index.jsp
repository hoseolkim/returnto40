<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h4>웰컴페이지 : ${title }</h4>

<c:set var="cPath" value="${pageContext.request.contextPath }" scope="application"/>
<c:set value="${pageContext.request.userPrincipal }" var="principal" />
<h4>principal 객체 : ${principal }</h4>
<c:choose>
	<c:when test="${not empty principal}">
		<form method="post" action="${cPath }/login/logout.do" id="logoutForm"></form>
		<h4>
			<a href="${cPath }/mypage">${principal.realUser.memName }</a> 
			<a class="btn btn-danger" href="javascript:;" id="logoutBtn">로그아웃</a>
		</h4>
		<h4><a href="${cPath }/adrs/view">주소록</a></h4>
		<script>
			$(logoutBtn).on("click", function(event){
				event.preventDefault();
				logoutForm.requestSubmit();
		// 		$(logoutForm).submit();
			});
		</script>
	</c:when>
	<c:otherwise>
		<a href="${cPath }/login/loginForm.jsp">로그인</a>
		<a href="${cPath }/member/memberInsert.do">회원가입</a>
	</c:otherwise>
</c:choose>
