<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Arrays"%>
<%@page import="kr.or.ddit.calculate.NumericOperatorType"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>/calForm.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
<script src="${pageContext.request.contextPath}/resources/js/app/calculate/calForm_case7.js"></script>
</head>
<body>
<div>
	<h4>request content Type</h4>
	<input type="radio" name="contentType" checked />PARAMETER
	<input type="radio" name="contentType" data-content-type="application/json;charset=utf-8"/>JSON
</div>
<form id="calForm" method="post">
	<input type="number" name="leftOp" required value="${param.leftOp}"/>
	<select name="operator" required data-init-value="${param.operator}">
		<option value>연산자</option>
		<%
			String options = Arrays.stream(NumericOperatorType.values())
					.map(e->String.format("<option value='%s'>%c</option>", e.name(), e.getSign())).collect(Collectors.joining("\n"));
		%>
		<%=options %>
	</select>
	<input type="number" name="rightOp" required value="${param.rightOp}" />
	<input type="submit" value="=" />
</form>
<div id="resultArea">

</div>
</body>
</html>