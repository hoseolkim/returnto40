<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginForm.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
<%!
	private String cookieValueByCookieName(Cookie[] cookies, String name){
		String cookieValue = "";
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(name)){
				cookieValue = cookie.getValue();
			}
		}
		return cookieValue;
	}
%>
<%
	Cookie[] cookies = request.getCookies();
	String saveId = cookieValueByCookieName(cookies,"saveId");
	String saveState = cookieValueByCookieName(cookies,"saveState");
	
	String message = (String)session.getAttribute("message");
	session.removeAttribute("message"); // flash attribute
	if(message!=null && !message.trim().isEmpty()){
%>
<script>
 alert("<%=message%>");
</script>
<%
	}
%>

</head>
<body>
	<form method="post" action="<%=request.getContextPath() %>/login/loginProcess.do" enctype="application/x-www-form-urlencoded">
		<ul>
			<li>
				아이디 : <input type="text" name="memId" value="<%=saveId %>" />
				<input type="checkbox" name="idSave" value="saveId" <%= saveState%>/>아이디저장하기
			</li>
			<li>
				비밀번호 : <input type="text" name="memPass" />
				<input type="submit" value="로그인" />
			</li>
		</ul>
	</form>
</body>
</html>