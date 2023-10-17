<%@page import="java.util.Arrays"%>
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%
	Map<String, Object> target = (HashMap<String, Object>)request.getAttribute("target");
	String ptrn = "<tr><td>%s</td><td>%s</td></tr>";
	StringBuffer sb = new StringBuffer();
	sb.append("<table>");
	target.forEach((k,v)->{
		if(!k.equals("message")){
			sb.append(String.format(ptrn,k,Arrays.toString((String[])v)));
		}else{
			sb.append(String.format(ptrn,k,v));
		}
		});
	sb.append("<table>");
// 	Stream.of(target.entrySet().).map((k,v)->{return String.format(ptrn,k,v.toString());}).collect(Collectors.joining("\n"));
%>
<%=sb%>