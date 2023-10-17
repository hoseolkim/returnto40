package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/login/logout.do")
public class LogoutControllerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session == null || session.isNew()) {
			resp.sendError(400, "로그인 하지도 않았는뒈!!");
			return;
		}
		
//		session.removeAttribute("authId");
		session.invalidate();
		
		String viewName = "redirect:/";
		
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}

















