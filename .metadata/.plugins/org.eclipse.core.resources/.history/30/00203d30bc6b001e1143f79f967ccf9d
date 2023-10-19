package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController{
	
	@RequestMapping(value="/login/logout.do", method = RequestMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session == null || session.isNew()) {
			resp.sendError(400, "로그인 하지도 않았는뒈!!");
			return null;
		}
		
//		session.removeAttribute("authId");
		session.invalidate();
		
		return "redirect:/";
	}
}

















