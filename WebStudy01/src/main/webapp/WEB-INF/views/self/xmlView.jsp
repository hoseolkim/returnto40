<%@page import="java.util.Arrays"%>
<%@page contentType="apllication/xml; charset=utf-8" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%
	
	Map<String, Object> target = (HashMap<String, Object>)request.getAttribute("target");
	
	
	String ptrn = "<%1$s>%2$s</%1$s>";
	StringBuffer sb = new StringBuffer();
	sb.append("<root>");
	target.forEach((k,v)->{
		if(!k.equals("message")){
			sb.append(String.format(ptrn,k,Arrays.toString((String[])v)));
		}else{
			sb.append(String.format(ptrn,k,v));
		}
		});
	sb.append("</root>");
%>
	<%=
		sb
	%>
