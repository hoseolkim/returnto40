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
<%
	
	String yearParam = request.getParameter("year");
	String monthParam = request.getParameter("month");
	String localeParam = request.getParameter("locale");
	
	Locale locale = Optional.ofNullable(localeParam).map(lp->Locale.forLanguageTag(lp))
									.orElse(request.getLocale());
	
	int year = Optional.ofNullable(yearParam)
						.filter((yp)->yp.matches("\\d{4}"))
						.map((yp)->Integer.parseInt(yp))
						.orElse(Year.now().getValue());
	
	YearMonth targetMonth = Optional.ofNullable(monthParam)
									.filter((mp)->mp.matches("[1-9]|1[0-2]"))
									.map((mp)->Integer.parseInt(mp))
									.map((m)->YearMonth.of(year, m))
									.orElse(YearMonth.now());
				
	
	
	YearMonth beforeMonth = targetMonth.minusMonths(1);
	YearMonth nextMonth = targetMonth.plusMonths(1);
	
	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
	<h4>
	<a href="javascript:;"
	data-year="<%=beforeMonth.getYear()%>" data-month="<%=beforeMonth.getMonthValue() %>" >&lt;&lt;&lt;</a>
	
	<%=String.format(locale, "%1$tY, %1$tB", targetMonth) %>
	
	<a href="javascript:;"
	data-year="<%=nextMonth.getYear()%>" data-month="<%=nextMonth.getMonthValue() %>">&gt;&gt;&gt;</a>
	</h4>
	<%!
	final String OPTPTRN = "<option value='%s' %s>%s</option>";
	%>
	<form id="calForm" method="POST">
		<input type="number" name="year" value="<%=targetMonth.getYear()%>"/>
		<select name="month">
			<%=
				Stream.of(Month.values())
						.map(m->String.format(locale,
								OPTPTRN,
								String.valueOf(m.getValue()),
								m.equals(targetMonth.getMonth())? "selected" : "" ,
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
								.map((l)->String.format(locale,OPTPTRN,l.toLanguageTag(),l.equals(locale)? "selected":"",l.getDisplayName(l)))
								.collect(Collectors.joining("\n"))
				
			%>
		</select>
	</form>
	<table>
		<thead>
			<tr>
				<%
				WeekFields weekFields = WeekFields.of(locale);
				DayOfWeek firstDayOfWeek = weekFields.getFirstDayOfWeek();
				String ptrn = "<td class='%2$s'>%1$s</td>";
				for(int col=0;col<7;col++){
					DayOfWeek tmp = firstDayOfWeek.plus(col);
					out.println(
						String.format(ptrn, tmp.getDisplayName(TextStyle.SHORT , locale), tmp.name() )	
					);
				}
				%>
			</tr>
		</thead>
		<tbody>
			<%
				LocalDate firstDate = targetMonth.atDay(1);
 				int offset = firstDate.get(weekFields.dayOfWeek()) - firstDayOfWeek.get(weekFields.dayOfWeek());
				LocalDate date = firstDate.minusDays(offset);
				for(int row = 0; row<6 ; row++){
					out.println("<tr>");
					for(int col=0;col<7;col++){
						String clz = "";
						clz = YearMonth.from(date).isBefore(YearMonth.from(firstDate)) ? "before" : YearMonth.from(date).isAfter(YearMonth.from(firstDate)) ? "after" : date.getDayOfWeek().name();
						
						out.println(
							String.format(ptrn, date.getDayOfMonth(),clz)	
						);
						date = date.plusDays(1);
					}
					out.println("</tr>");
				}
			%>
		</tbody>
	</table>
<script>
	$(":input[name]").on("change",function(event){
		this.form.requestSubmit();
	});
	$('a').on('click',function(event){
		let year = this.dataset.year;
		let month = this.dataset["month"];
		calForm.year.value = year;
		calForm["month"]["value"] = month;
		calForm.requestSubmit();
	})
	
// 	$(calForm).on("submit",function(event){
// 		event.preventDefault();
		
// 		let url = this.action;
// 		let method = this.method;
// 		let formdata = this.serialize();
// 		let settings = {
// 			url : url,
// 			data : formdata,
// 			method : method,
// 			dataType : "html"
// 			success : function(res){
				
// 			},
// 			error : function(xhr){
// 				alert(`오류 : \${xhr.status}`)
// 			}
// 		};
// 		$.ajax()
		
// 	})
	
	
	
</script>
</body>

</html>