package kr.or.ddit.servlet04;

import java.io.IOException;
import java.time.Year;
import java.time.YearMonth;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.CalendarVO;

@WebServlet("/calendar2")
public class CalendarControllerServlet2 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.service(req, resp);
		String yearParam = req.getParameter("year");
		String monthParam = req.getParameter("month");
		String localeParam = req.getParameter("locale");
		
		Locale locale = Optional.ofNullable(localeParam).map(lp->Locale.forLanguageTag(lp))
										.orElse(req.getLocale());
		
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
		
		// locale targetMonth, berforeMonth, nextMonth
		CalendarVO calVO = new CalendarVO();
		calVO.setLocale(locale);
		calVO.setTargetMonth(targetMonth);
		calVO.setBeforeMonth(beforeMonth);
		calVO.setNextMonth(nextMonth);
		
		req.setAttribute("calVO", calVO);
		
//		req.setAttribute("locale", locale);
//		req.setAttribute("targetMonth", targetMonth);
//		req.setAttribute("beforeMonth", beforeMonth);
//		req.setAttribute("nextMonth", nextMonth);
		
		
		String view = "/WEB-INF/views/07/calendarView2.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}