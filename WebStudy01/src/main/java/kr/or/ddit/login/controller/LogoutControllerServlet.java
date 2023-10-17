package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/login/logout.do")
public class LogoutControllerServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// 세션에 대한 검증을 먼저 한다.
		HttpSession session = req.getSession(false);
		if(session == null || session.isNew()) {
			// 만료시킬 세션이 아니거나  bad request일 경우에 이쪽으로 들어온다
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"로그인 하지도 않았는뒈!!!");
			return;
		}
		
		//1. authId를 지운다
//		session.removeAttribute("authId");
		
		//2. 세션을 만료시킨다
		session.invalidate();
		
		//3. 웰컴페이지로 이동한다
//		"redirect:/";
		
		String goPage = null;
		
		goPage = "redirect:/";
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
		return;
	}
}