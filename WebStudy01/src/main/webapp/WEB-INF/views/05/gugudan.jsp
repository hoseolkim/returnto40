<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int minDan = (int)request.getAttribute("minDan");
	int maxDan = (int)request.getAttribute("maxDan");
	
%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
<style>
	table {
		border-collapse : collapse;
	}
	th,td {
		border : 1px solid black;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!
	private StringBuffer gugudan(int a, int b){
		StringBuffer sb = new StringBuffer();
		for(int i = a; i <=b;i++){
			sb.append("<tr>");
			for(int j = 1; j<=9 ; j++){
				sb.append(String.format("<td>%d * %d = %d</td>", i, j , i*j));
			}
			sb.append("</tr>");
		}
		return sb;
}
%>
<body>
	<form id="gugudanForm">
		<input type="number" name="minDan" placeholder="min dan">
		<input type="number" name="maxDan" placeholder="max dan">
		<button type="submit">전송</button>
	</form>
	
	<h4>table 태그를 이용한 구구단 출력(<%=minDan %>단~<%=maxDan %>단, 승수 1~9)</h4>
	<table>
		<%=gugudan(minDan, maxDan) %>
	</table>
	<hr/>
	<table>
		<%
			StringBuffer trTags = new StringBuffer();
			for(int i = minDan; i <=maxDan;i++){
				trTags.append("<tr>");
				for(int j = 1; j<=9 ; j++){
					trTags.append(String.format("<td>%d * %d = %d</td>", i, j , j*i));
				}
				trTags.append("</tr>");
			}
		%>
		<%=trTags %>
	</table>
	<hr/>
	<table>
		<%
			for(int i = minDan; i <=maxDan;i++){
				out.println("<tr>");
				for(int j = 1; j<=9 ; j++){
					out.println(String.format("<td>%d * %d = %d</td>", i, j , j*i));
				}
				out.println("</tr>");
			}
		%>
	</table>
	<hr />
	<h4>table 태그를 이용한 구구단 출력(2단~9단, 승수 1~9)</h4>
	<table>
		<%
			for(int i = minDan; i <= maxDan;i++){
		%>
			<tr>
			<%
				for(int j = 1; j<=9 ; j++){
			%>
				<td><%=String.format("%d * %d = %d", j, i , j*i) %></td>
			<%		
				}
			%>
			</tr>
		<%		
			}
		%>
	</table>
	<hr>
	

	
		
	
		
		
		
		
		
</body>
</html>