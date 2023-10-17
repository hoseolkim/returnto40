<%@page contentType="apllication/json; charset=utf-8" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%
	Map<String, Object> target = (HashMap<String, Object>)request.getAttribute("target");
	// 이렇게 하면 페이지 로딩 후 io가 먼저 닫히는 오류가 발생한다.
	//new ObjectMapper().writeValue(out, target);
	new ObjectMapper().writeValue(response.getWriter(), target);
%>