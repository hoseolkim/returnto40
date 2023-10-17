package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage")
public class MyPageControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Principal principal = req.getUserPrincipal();
		String memId = principal.getName();
		MemberVO member = service.retrieveMember(memId);
		
		req.setAttribute("member", member);
		
		String viewName = "member/myPage";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
