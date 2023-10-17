<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.red {
		background-color: red;
	}
	/* tr>td:first-child[text=user-agent] {
		background-color: red;
	} */
</style>
</head>
<body>
	<form method=""></form>
	<h4>HttpServletRequest : reqeust packaging</h4>
	<pre>
		1. Request Line : URL(URI), Request Method
			Request Method : 요청의 목적이자 의도이며, 포장 규칙을 정의하는 단어
				GET(default method) : img태그의 src나 a태그의 href도 get방식이다..
				POST(Form 기반의 요청) : request Body 가 필요함
				PUT/PATCH (자원의 갱신)
				DELETE(자원의 삭제)
				OPTION(preFlight 요청)
				HEAD(Response content 가 없음)
				TRACE(server tracking/debugging)
				
				<%
					StringBuffer requestURL = request.getRequestURL();
					String requestURI = request.getRequestURI();
					String requestMethod = request.getMethod();
		
				%>
				url : <%=requestURL %>
				uri : <%=requestURI %>
				method : <%=requestMethod %>
		2. Request Header : meta data (client + content), name(String)/value(String)
			<%
				String userAgent = request.getHeader("user-agent");
			%>
			<%=userAgent %>
		3. Request Body(Content Body, Message Body)
			1) parameter (String) :
					String getParameter(name), String[] getParameterValues(name), getParameterMap()
				- query string 형태 전송 (보안 취약)
				- content body 전송
			2) multipart (stream) post요청만 가능
			3) payload(JSON/XML.., unmarshalling...) post요청만 가능
		
	</pre>
	<div>
		<a href="?param1=value1&param2=한글히히히히히">Query String 형태 전송</a>
		<form method="post">
			<input type="text" name="param3" value="value3">
			<input type="text" name="param3" value="value3-1">
			<input type="text" name="param4" value="value4">
			<input type="date" name="date1"/>
			<input type="datetime-local" name="date2"/>
			<input type="submit" value="전송">
		</form>
	</div>
	<table>
		<thead>
			<tr>
				<th>파라미터이름</th>
				<th>파라미터값</th>
			</tr>
		</thead>
		<tbody>
			<%
				// 파라미터를 확보하기 전에 미리 설정해줘야함!!
				// POST요청의 request body에 적용됨.
				//request.setCharacterEncoding("utf-8");
				Map<String,String[]> paramMap = request.getParameterMap();
				
				StringBuffer sb2 = new StringBuffer();
				String ptrn2 = "<tr><td>%s</td><td>%s</td></tr>";
				// isEmpty() 를 사용하자!
				if(paramMap==null||paramMap.size()==0){
					sb2.append("<tr><td colspan='2'>파라미터가 하나도 없습니다</td></tr>");
				}else{
					// 여기서도 엔트리.. map에서 entrySet()을 향상for문의 반복자로 사용하자!
					Enumeration<String> paramNames = request.getParameterNames();
					while(paramNames.hasMoreElements()){
						String names = paramNames.nextElement();
						// Arrays.toString(파라미터의 배열) => 출력 따란
						for(String str : paramMap.get(names)){
							sb2.append(String.format(ptrn2, names,str));
						}
					}
				}
				
				
			%>
			<%=sb2 %>
		</tbody>
	</table>
	<hr/>
	<table>
		<thead>
			<tr>
				<th>헤더이름</th>
				<th>헤더 값</th>
			</tr>
		</thead>
		<tbody>
			<%
				String ptrn = "<tr class='%3$s'><td>%1$s</td><td>%2$s</td></tr>";
				Enumeration<String> headerNames = request.getHeaderNames();
				StringBuffer sb = new StringBuffer();
				while(headerNames.hasMoreElements()){
					String name = headerNames.nextElement();
					String clzValue = "user-agent".equals(name) ? "red" : "";
						// 여기서 포맷팅하는게 같으니까.. 같은 포맷을 하나의 변수로 빼서 선언해 놓을 수 있다.
						sb.append(String.format(ptrn,name,request.getHeader(name),clzValue));
				}
			%>
			<%=sb %>
		</tbody>
	</table>
</body>
</html>