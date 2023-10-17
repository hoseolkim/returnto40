<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.MonthDay"%>
<%@page import="java.time.Month"%>
<%@page import="java.time.Year"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
	int cntDay = 1;
	private int getMaxday(int year, int month){
	int days = 0;
	int[] dayArr = {31,28,31,30,31,30,31,31,30,31,30,31};
	if(month==2){
		if((year % 4 == 0 && year%100!=0) || year % 400 == 0){
			days = 29;
		}else{
			days = dayArr[month]; 
		}
	}else{
			days = dayArr[month]; 
	}
	
	return days;
	}
	
	private StringBuffer getPrevMonth(Locale locale, TimeZone timezone, int year, int month, int firstDay){
		if(firstDay==0) {
			return new StringBuffer().append("");
		}
		int[] days = new int[firstDay];
		Calendar calendar = Calendar.getInstance(timezone, locale);
		if(month!=0){
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month-1);
		}else{
			calendar.set(Calendar.YEAR, year-1);
			calendar.set(Calendar.MONTH, 11);
		}
		int prevLast = getMaxday(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH));
		
		prevLast -= (firstDay-1);
		
		
		
		// 빈칸 이전달 로직
		StringBuffer sb = new StringBuffer();
		if(firstDay!=0){
				sb.append("<tr>");
			for(int i = 0 ; i < firstDay; i++){
				// 이전달 달력 여기서 추가해주기~
				sb.append(String.format("<td class='prevmonth'>%d</td>",prevLast));
				this.cntDay++;
				prevLast++;
			}
		}
		return sb;
	}

	
	private StringBuffer getCurrMonth(Locale locale, TimeZone timezone,int year, int month,int lastDay){
		StringBuffer sb = new StringBuffer();
		Calendar calendar = Calendar.getInstance(timezone, locale);
		
		// 진짜 달 로직
		for(int i = 1 ; i <= lastDay ; i++){
			if(i==1&&cntDay!=1){
			}else if(i!=1&&this.cntDay!=1){
				if(cntDay%7==1){
					sb.append("<tr>");
				}
			}else if(i==1&&this.cntDay==1){
					sb.append("<tr>");
			}
			if(calendar.get(Calendar.DATE)==i){
				if(calendar.get(Calendar.YEAR)==year && calendar.get(Calendar.MONTH)==month){
					sb.append(String.format("<td class='today'>%d</td>", i));
				}else{
					sb.append(String.format("<td>%d</td>", i));
				}
			}else{
				sb.append(String.format("<td>%d</td>", i));
			}
			
			if(cntDay%7==0){
				sb.append("</tr>");
			}
			this.cntDay++;
		}
		return sb;
	}
	private StringBuffer getNextMonth(){
		StringBuffer sb = new StringBuffer();
		// 빈칸 다을달 로직
	 	for(int i = 1 ; this.cntDay <= 42 ; i++){
	 		if(this.cntDay%7==1){
	 			sb.append("<tr>");
	 		}
	 		sb.append("<td class='nextmonth'>");
	 		sb.append(i);
	 		sb.append("</td>");
			
	 		if(this.cntDay%7==0){
	 			sb.append("</tr>");
	 		}
	 		this.cntDay++;
	 	}
	 	this.cntDay=1;
		return sb;
	}
	private StringBuffer getAllLocale(Locale inLocale){
		StringBuffer sb = new StringBuffer();
		Locale[] allLocale = Locale.getAvailableLocales();
		for(Locale loc : allLocale){
			if(loc.getDisplayName(inLocale).equals(inLocale.getDisplayName(inLocale))){
				sb.append(String.format("<option selected>%s</option>", loc.getDisplayName(inLocale)));
			}else{
				sb.append(String.format("<option>%s</option>", loc.getDisplayName(inLocale)));
			}
		}
		return sb;
	}
	
	private Locale searchLocale(String value, HttpServletRequest req){
		Locale locale = null;
		
		Locale[] allLocale = Locale.getAvailableLocales();
		for(Locale loc : allLocale){
			if(loc.getDisplayName().equals(value)){
				locale = loc;
			}
		}
		if(locale==null){
			locale = req.getLocale();
		}
		return locale;
	}
	private StringBuffer getAllTimeZone(Locale inLocale,TimeZone tz){
		StringBuffer sb = new StringBuffer();
		String[] timezones = TimeZone.getAvailableIDs();
		
		for(String str : timezones){
			TimeZone timezone = TimeZone.getTimeZone(str);
			if(tz!=null&&tz.getDisplayName(inLocale).equals(timezone.getDisplayName(inLocale))){
				sb.append(String.format("<option selected>%s</option>", timezone.getDisplayName(inLocale)));
			}else{
				sb.append(String.format("<option>%s</option>", timezone.getDisplayName(inLocale)));
			}
		}
		return sb;
	}
	
	private TimeZone searchTimeZone(Locale inLocale,String value){
		TimeZone tz = null;
		String[] timezones = TimeZone.getAvailableIDs();
		for(String str : timezones){
			TimeZone timezone = TimeZone.getTimeZone(str);
			if(timezone.getDisplayName(inLocale).equals(value)){
				tz = timezone;
			}
		}
		if(tz==null){
			tz = TimeZone.getDefault();
		}
		
		return tz;
	}
	private StringBuffer getMonthName(Locale locale, int month){
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i <= 11 ; i++){
			if(i==month){
				sb.append(String.format("<option value='%d' selected>%s</option>", i, Month.of(i+1).getDisplayName(TextStyle.SHORT_STANDALONE, locale)));
			}else{
				sb.append(String.format("<option value='%d'>%s</option>", i, Month.of(i+1).getDisplayName(TextStyle.SHORT_STANDALONE, locale)));
			}
		}
		
		return sb;
	}
	private StringBuffer getDayWeekName(Locale locale){
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("<th>%s</th>",DayOfWeek.of(1).getDisplayName(TextStyle.FULL, locale)));
		sb.append("<th>DayOfWeek.of(1).getDisplayName(TextStyle.FULL, locale)</th>");
		sb.append("<th>DayOfWeek.of(1).getDisplayName(TextStyle.FULL, locale)</th>");
		sb.append("<th>DayOfWeek.of(1).getDisplayName(TextStyle.FULL, locale)</th>");
		sb.append("<th>DayOfWeek.of(1).getDisplayName(TextStyle.FULL, locale)</th>");
		sb.append("<th>DayOfWeek.of(1).getDisplayName(TextStyle.FULL, locale)</th>");
		sb.append("<th>DayOfWeek.of(1).getDisplayName(TextStyle.FULL, locale)</th>");
		
		return sb;
	}
	
%>

<%
	
	
	boolean valid = true;
	
	
	
	
	String param1 = request.getParameter("year");
	String param2 = request.getParameter("month");
	String param3 = request.getParameter("pickedLocale");
	String param4 = request.getParameter("pickedTimezone");
// 	TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
	TimeZone timezone = TimeZone.getDefault();
	Locale locale = request.getLocale();
	Calendar c = Calendar.getInstance(timezone, locale);
	// c2객체는 설정한 달의 첫째날의 요일과 마지막날의 숫자를 구하기 위한 객체
	Calendar c2 = Calendar.getInstance(timezone, locale);
	
	
	
	MonthDay md = MonthDay.now();
	
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	
	if(param1!=null&& !param1.equals("")){
		if(param1.matches("[\\d]{4}")){
			year = Integer.parseInt(param1);
		}else{
			valid = false;
		}
	}
	
	if(param2!=null && !param2.equals("")){
		if(param2.matches("[\\d]+")){
			int pr2 = Integer.parseInt(param2);
			if(pr2>=0 && pr2<=11){
				month = pr2;
			}else{
				valid = false;
			}
		}else{
			valid = false;
		}
	}else if(param2!=null && !param2.matches("[\\d]+")){
		valid = false;
	}
	
	
	
	if(param4!=null && !param4.equals("")){
		timezone = searchTimeZone(locale, param4);
	}
	
	
	
	if(param3!=null && !param3.equals("")){
		locale = searchLocale(param3,request);
	}
	if(!valid){
		response.sendError(HttpServletResponse.SC_BAD_REQUEST,"파라미터에 문제 있어 검증 실패");
		return;
	}
	
	
	c2.set(Calendar.DATE, 1);
	c2.set(Calendar.MONTH,month);
	c2.set(Calendar.YEAR,year);
	
	// 설정한 년도,달에 해당하는 마지막날이 언제인지
	int lastDay = getMaxday(year, month);
	// 설정한 년도, 달에 해당하는 첫날이 무슨 요일인지. 일-0 토-6
	int dayWeek = c2.get(Calendar.DAY_OF_WEEK)-1;
	
%>




<html lang="ko">
<head>
<style>
	table {
		border-collapse: collapse;
		width : 1000px
		
	}
	thead>tr {
		height : 20px;
	}
	th,td {
		border : 1px solid black;
		text-align: center;
	}
	tr {
		height : 40px;
	}
	th:first-child,tr>td:first-child {
		color : red;
	}
	th:last-child,tr>td:last-child {
		color : blue;
	}
	.prevmonth, .nextmonth {
		color : gray !important;
	}
	.today {
		background-color: lightgreen;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form id="gogoForm">
		<table>
		<caption>
			<a href="#" onclick="getPrev()">◀◀◀</a>
			<%=year %>. <%= c2.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, locale) %>
			<a href="#" onclick="getNext()">▶▶▶</a><br>
				YEAR : <input type="number" name="year" value="<%=year%>">
				MONTH : 
				<select name="month" onChange="this.form.requestSubmit()">
					<%=getMonthName(locale, month) %>
				</select>
				<select name="pickedLocale" onChange="this.form.requestSubmit()">
					<%=getAllLocale(locale) %>
				</select>
				<select name="pickedTimezone" onChange="this.form.requestSubmit()">
					<%=getAllTimeZone(locale, timezone) %>
				</select>
		</caption>
			<thead>
				<tr>
					<th id="sun"><%=DayOfWeek.of(7).getDisplayName(TextStyle.FULL, locale) %></th>
					<th><%=DayOfWeek.of(1).getDisplayName(TextStyle.FULL, locale) %></th>
					<th><%=DayOfWeek.of(2).getDisplayName(TextStyle.FULL, locale) %></th>
					<th><%=DayOfWeek.of(3).getDisplayName(TextStyle.FULL, locale) %></th>
					<th><%=DayOfWeek.of(4).getDisplayName(TextStyle.FULL, locale) %></th>
					<th><%=DayOfWeek.of(5).getDisplayName(TextStyle.FULL, locale) %></th>
					<th id="sat"><%=DayOfWeek.of(6).getDisplayName(TextStyle.FULL, locale) %></th>
				</tr>
			</thead>
			<tbody>
				<%=getPrevMonth(locale, timezone, year, month, dayWeek) %>
				<%=getCurrMonth(locale, timezone, year, month, lastDay) %>
				<%=getNextMonth() %>
			</tbody>
		</table>
	</form>
</body>
<script>
	function getPrev(){
		let year = parseInt("<%=year%>");
		let month = parseInt("<%=month%>");
		if(month==0){
			year -= 1;
			month = 11
		}else{
			month -= 1
		}
		let path = location.pathname;
		path += `?year=\${year}&month=\${month}`;
		location.href = location.origin+path;
	}
	function getNext(){
		let year = parseInt("<%=year%>");
		let month = parseInt("<%=month%>");
		if(month==11){
			year += 1;
			month = 0
		}else{
			month += 1
		}
		let path = location.pathname;
		path += `?year=\${year}&month=\${month}`;
		location.href = location.origin+path;
	}
</script>
</html>