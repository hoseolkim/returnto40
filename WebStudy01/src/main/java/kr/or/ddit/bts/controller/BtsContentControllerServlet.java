package kr.or.ddit.bts.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bts/*")
public class BtsContentControllerServlet extends HttpServlet{
	
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reqUri = req.getRequestURI();
		System.out.printf("request uri : %s\n",reqUri);
		int lastIdx = reqUri.lastIndexOf("/");
		
		String memCode = reqUri.substring(lastIdx+1);
		System.out.printf("selected member : %s\n",memCode);
		
		if(memCode==null || memCode.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"멤버 코드가 없음.");
			return;
		}
		
		Map<String, String[]> btsMap = (Map) application.getAttribute("btsMap");
		if(!btsMap.containsKey(memCode)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,String.format("%s 에 해당하는 멤버는 없음.", memCode));
			return;
		}
		
		Cookie btsCookie = new Cookie("btsCookie",memCode);
		btsCookie.setMaxAge(3*24*60*60);
		btsCookie.setPath(req.getContextPath()+"/bts");
		resp.addCookie(btsCookie);
		
		
		String[] memRec =btsMap.get(memCode);
		String goPage = "/WEB-INF/views/bts/btsLayOut.jsp";
		req.setAttribute("member", memRec);
		
		if(goPage.startsWith("redirect:")) {         
			String location = req.getContextPath()+goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}