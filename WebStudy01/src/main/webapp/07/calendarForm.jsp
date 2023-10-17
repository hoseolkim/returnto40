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
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.7.1.min.js"></script>
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
	<form id="calForm" method="POST" action="<%=request.getContextPath() %>/calendar">
		<input type="number" name="year"/>
		<select name="month">
			<%=
				Stream.of(Month.values())
						.map(m->String.format(locale,
								OPTPTRN,
								String.valueOf(m.getValue()),
								m.getDisplayName(TextStyle.FULL, locale)))
						.collect(Collectors.joining("\n"))
			%>
		</select>
		<select name="locale">
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
	
// 	console.log(calForm);
// 	console.log($(calForm));

	$(":input[name]").on("change",function(event){
		this.form.requestSubmit();
	});


	$(calForm).on('submit',function(event){
		event.preventDefault();
		console.log(event.target);
		console.log(this);
		console.log($(this));
		// 동기 요청 -> 비동기 요청으로 전환
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
//		ex) year=2023&month=8&locale=ko-KR
		let settings = {
		url : url,
		method : method,
		data : data,
		dataType : "html",
		success : function(resp){
			$(resultArea).html(resp);
		}
		};
		$.ajax(settings);
	});
	$("#resultArea").on("click", "a", function(event){
		console.log("a tag clicked !!!");
		let year = $(this).data("year");
		let month = $(this).data("month");
		// data function은 데이터의 타입을 그대로 핸들링 할 수 있다.
		console.log(event);
		event.preventDefault();
		let aTag = this;
		console.log(aTag.dataset);
		calForm.year.value = year;
		calForm["month"]["value"] = month;
// 		calForm.requestSubmit();
		$(calForm).submit();
	})
</script>
</body>

</html>