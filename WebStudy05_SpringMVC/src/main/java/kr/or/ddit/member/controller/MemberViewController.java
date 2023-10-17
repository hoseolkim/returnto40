package kr.or.ddit.member.controller;


import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberViewController{
	@Inject
	private MemberService service;
	
	@GetMapping("/member/memberView.do")
	public String memberView(@RequestParam("who") String memId, Model model){
		MemberVO member = service.retrieveMember(memId);
		
		model.addAttribute("member", member);
		
		return "member/ajax/memberView";
	}
}













