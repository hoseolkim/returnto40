package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.filter.wrapper.DummyHttpServletRequestWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/member/memberView.do")
public class MemberViewControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req instanceof DummyHttpServletRequestWrapper) {
			String data = ((DummyHttpServletRequestWrapper) req).getSpecificData();
			log.info("request specific data : {}",data);
			Cookie sessionCookie = ((DummyHttpServletRequestWrapper) req).getCookie("JSESSIONID");
			log.info("session id : {}, {}",sessionCookie.getValue(), req.getSession().getId());
		}
		
		String memId = req.getParameter("who");
		/*
		if(StringUtils.isBlank(memId)) {
			resp.sendError(400, "잘못된 요청!!!!!");
			return;
		}
		*/
		
		
		
		MemberVO member =  service.retrieveMember(memId);
		req.setAttribute("member", member);
		
		String viewName = "member/ajax/memberView";
		
		new ViewResolverComposite().resolveView(viewName, req, resp);
		
		
	}
}
