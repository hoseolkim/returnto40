package kr.or.ddit.member.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.filter.wrapper.MemberVOWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MyPageController{
	private MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/mypage")
	public String myPage(MemberVOWrapper principal, HttpServletRequest req){
		
		String memId = principal.getName();
		MemberVO member = service.retrieveMember(memId);
		
		req.setAttribute("member", member);
		
		return "member/myPage";
	}
}
