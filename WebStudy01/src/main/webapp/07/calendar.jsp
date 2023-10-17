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
	
	
//	Locale locale = request.getLocale(); // request header(Accept-Language)
	
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
	<a href="javascript:;" onclick="clickHandler(event);"
	data-year="<%=beforeMonth.getYear()%>" data-month="<%=beforeMonth.getMonthValue() %>" >&lt;&lt;&lt;</a>
	
	<%=String.format(locale, "%1$tY, %1$tB", targetMonth) %>
	
	<a href="javascript:;" onclick="clickHandler(event);"
	data-year="<%=nextMonth.getYear()%>" data-month="<%=nextMonth.getMonthValue() %>">&gt;&gt;&gt;</a>
	</h4>
	<%!
	final String OPTPTRN = "<option value='%s' %s>%s</option>";
	%>
	<form id="calForm" onchange="this.requestSubmit();" method="POST">
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
		<select name="locale"  onchange="console.log(this);">
		<%--
			Local -> option tag String : map
			element collection : collect(Collectors) 
		 --%>
			<%=
								Stream.of(Locale.getAvailableLocales())
								.filter((l)->!l.getDisplayName(l).isEmpty())
								.map((l)->String.format(locale,OPTPTRN,l.toLanguageTag(),l.equals(locale)? "selected":"",l.getDisplayName(l)))
								//.map((l)->String.format(locale,OPTPTRN,l.toLanguageTag(),l.getDisplayName(locale).equals(locale.getDisplayName(locale))? "selected":"",l.getDisplayName(l)))
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
// 				DayOfWeek[] weeks = DayOfWeek.values();
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
// 				int offset = firstDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue();
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
// 				for(int row = 0; row<6 ; row++){
// 					out.println("<tr>");
// 					for(int col=0;col<7;col++){
// 						String clz = "";
// 						if(firstDate.minusMonths(1).getMonth() == date.getMonth()){
// 							clz = "before";
// 						}else if(firstDate.plusMonths(1).getMonth() == date.getMonth()){
// 							clz = "after";
// 						}else {
// 							if(col==5){
// 								clz = "SATURDAY";
// 							}else if(col==6){
// 								clz = "SUNDAY";
// 							}
// 						}
// 						out.println(
// 							String.format(ptrn, date.getDayOfMonth(),clz)	
// 						);
// 						date = date.plusDays(1);
// 					}
// 					out.println("</tr>");
// 				}
			%>
		</tbody>
	</table>
<script>
	function clickHandler(event){
		console.log(event);
		let aTag = event.target;
		console.log(aTag.dataset);
		let year = aTag.dataset.year;
		let month = aTag.dataset["month"];
		calForm.year.value = year;
		calForm["month"]["value"] = month;
		calForm.requestSubmit();
		
	}
</script>
</body>

</html>