<%@page import="java.time.temporal.WeekFields"%>
<%@page import="java.time.Month"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.time.Year"%>
<%@page import="java.util.Optional"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.DayOfWeek"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.before, .after {
		color : silver;
	}
	table {
		border-collapse: collapse;
		width : 100%;
		min-height: 500px;
		text-align: center;
		font-size : large;
	}
	th,td {
		border : 1px solid black;
	}
	.SUNDAY {
		color : red;
	}
	.SATURDAY {
		color : blue;
	}
</style>
</head>
<body>
	
	<%!
		final String OPTPTRN = "<option value='%s'>%s</option>";
	%>
	<%
		Locale locale = request.getLocale();
	%>
	<form id="calForm" method="POST" action="<%=request.getContextPath() %>/calendar2" onsubmit="getCalendar(event)">
		<input type="number" name="year" onchange="this.form.requestSubmit();"/>
		<select name="month" onchange="this.form.requestSubmit();">
			<%=
				Stream.of(Month.values())
						.map(m->String.format(locale,
								OPTPTRN,
								String.valueOf(m.getValue()),
								m.getDisplayName(TextStyle.FULL, locale)))
						.collect(Collectors.joining("\n"))
			%>
		</select>
		<select name="locale" onchange="this.form.requestSubmit();">
		<%--
			Local -> option tag String : map
			element collection : collect(Collectors) 
		 --%>
			<%=
								Stream.of(Locale.getAvailableLocales())
								.filter((l)->!l.getDisplayName(l).isEmpty())
								.map((l)->String.format(locale,OPTPTRN,l.toLanguageTag(),l.getDisplayName(l)))
								.collect(Collectors.joining("\n"))
			%>
		</select>
	</form>
	<div id="resultArea">
	</div>
<script>
	//selector : ex ) $("#calForm") - HtmlElement 를 jQuery 객체로 wrapping 함.
	// adapter dasign pattern
	
	//document.querySelectorAll("input[name]")
	
	function getCalendar(e){
		e.preventDefault();
		let formTag = e.target;
		let url = formTag.action;
		let method = formTag.method;
		let formdata = new FormData(formTag);
		let data = "";
 		let entries = formdata.entries();
 		let startNum = 0;
 		for (const pair of entries) {
 			startNum += 1;
			data += `\${pair[0]}=\${pair[1]}`;
			if(startNum != entries.length){
				data += "&"
			}
 		}
		let xHR = new XMLHttpRequest();
		
		xHR.open(method,url,true);
		
		xHR.onreadystatechange = function(){
			if(this.status == 200 && this.readyState == 4){
				console.log(this.responseText)
				resultArea.innerHTML = this.responseText;
			}
		}
		xHR.setRequestHeader('Content-Type' ,'application/x-www-form-urlencoded; charset=utf-8');
		xHR.send(data);
	}
	
	function eventHandler(event){
		console.log(event);
		let aTag = event.target;
		console.log(aTag.dataset);
		let year = aTag.dataset.year;
		let month = aTag.dataset["month"];
		calForm.year.value = year;
		calForm["month"]["value"] = month;
		calForm.requestSubmit();
		
	}


// 	$("#resultArea").on("click", "a", function(event){
// 		console.log("a tag clicked !!!");
// 		let year = $(this).data("year");
// 		let month = $(this).data("month");
// 		// data function은 데이터의 타입을 그대로 핸들링 할 수 있다.
// 		console.log(event);
// 		event.preventDefault();
// 		let aTag = this;
// 		console.log(aTag.dataset);
// 		calForm.year.value = year;
// 		calForm["month"]["value"] = month;
// // 		calForm.requestSubmit();
// 		$(calForm).submit();
// 	})
</script>
</body>

</html>