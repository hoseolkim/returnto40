package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.filter.wrapper.MemberVOWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MyPageController{
	@Inject
	private MemberService service;
	
	@RequestMapping("/mypage")
	public String myPage(MemberVOWrapper principal, HttpServletRequest req){
		
		String memId = principal.getName();
		MemberVO member = service.retrieveMember(memId);
		
		req.setAttribute("member", member);
		
		return "member/myPage";
	}
}
