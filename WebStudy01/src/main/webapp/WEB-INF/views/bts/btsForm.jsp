<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/btsForm</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<select id="memSel">
	<option value="">멤버선택</option>
	<%
		
		Map<String,String[]> btsMap = (Map) application.getAttribute("btsMap");
		String options = btsMap.entrySet().stream()
								.map((en)->String.format("<option value='%s'>%s</option>", en.getKey(),en.getValue()[0]))
								.collect(Collectors.joining("\n"));
		
		String savedMemCode = (String) request.getAttribute("savedMemCode");
		
		
	%>
	<%= options %>
</select>
<div id="resultArea">
</div>
</body>
<script>
	$(memSel).on('change',function(event){
		let memCode = $(this).val();
// 		let tergetUrl = location.pathname + "/" + memCode;
// 		location.href = location.href+ "/" + memCode;


		let settings = {
// 			url : tergetUrl,
			url : `\${location.href}/\${memCode}`,
			method : "get",
			dataType : "html", // Accept Request Header : Content-Type Response Header
			success : function(resp) {
				$(resultArea).html(resp)
			},
			error : function(jqXhr, status, error) {
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("error : ", error);
			}

		};
		
		$.ajax(settings);
	}).val("<%=savedMemCode %>").trigger('change');
</script>
</html>