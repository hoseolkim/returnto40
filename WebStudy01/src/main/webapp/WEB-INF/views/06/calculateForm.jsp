<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>엉성한 계산기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	table{
		border-collapse: collapse;
	}
	th,td {
		border : 1px solid black;
		text-align: center;
	}
</style>
</head>
<body>
<!-- 비동기 처리 기반의 사칙연산기. -->
<%-- 개발당시 QuailifiedName /WebStudy01/src/main/webapp/WEB-INF/views/06/calculateForm.jsp --%>
<label><input type="radio" name="dataType" onclick="getresult(this)" value="json" />JSON</label>
<label><input type="radio" name="dataType" onclick="getresult(this)" value="xml" />XML</label>
<label><input type="radio" name="dataType" onclick="getresult(this)" value="html" />HTML</label>
<form action="<%=request.getContextPath() %>/calculate.do" >
	<input type="number" name="leftOp" />
	<select name="operator">
		<option value>연산자</option>
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option>
	</select>
	<input type="number" name="rightOp" />
	<input type="submit" value="="/>
</form>
<div id="resultArea">
<%-- 원래 동기방식을 이쪽 페이지로 넘기면서 setAttribute로 가져오는 방식이어서 생긴 잔재 --%>
<%String result =(String)request.getAttribute("result"); %>
	<%= result != null ? result : "ex) 2 + 2 = 4" %>
</div>
</body>
<script>
	const ajaxURL = "<%=request.getContextPath()%>/calculate.do"
	const ajaxType = "POST"
	function getresult(x) {
		let parsetype = x.value;
		let formdata = $('form').serialize();
		<%--console.log(formdata);--%>
		let check = true;
		$.each(formdata.split("&"),function(i,v){
			if(v.length ==(v.lastIndexOf("=")+1)){
				check = false;
			}
		});
		if(!check){
			alert("피연산자와 연산자를 먼저 채워주세요!");
			return;
		}
		formdata = "parsetype="+parsetype+"&" + formdata;
		$.ajax({
			url : ajaxURL,
			data : formdata,
			type : ajaxType,
			success : function(res){
				switch(parsetype){
				case "json":
					<%--console.log(res);--%>
					htmlCode = `\${res.num1} \${res.oper} \${res.num2}= \${res.result}`;
					$('#resultArea').html(htmlCode)
					break;
				case "xml":
					let xmldata = $(res).find('datas');
					let num1 = $(xmldata).find('num1').text();
					let num2 = $(xmldata).find('num2').text();
					let oper = $(xmldata).find('oper').text();
					let result = $(xmldata).find('result').text();
					htmlCode = `\${num1} \${oper} \${num2}= \${result}`;
					$('#resultArea').html(htmlCode);
					break;
				case "html":
					<%--
					console.log("html여기로 들어옴");
					console.log(res);
					--%>
					$('#resultArea').html(res);
					break;
				}
			},
			error : function(xhr){
				alert('오류..ㅠ 오류코드 : '+xhr.status);
			},
			dataType : parsetype
		})
		
	}
	
	
</script>
</html>