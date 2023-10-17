package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.paging.BootstrapPaginationRenderer;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.SearchVO;

/**
 *   목록 조회 : /member/memberList.do
 *   마이페이지 : /mypage
 *   정보 수정 : /member/memberUpdate.do
 *   탈퇴 : /member/memberDelete.do
 *   가입 : /member/memberInsert.do
 *   
 *   상세조회 : /member/memberView.do?who=a001
 *
 */
@Controller
public class MemberListController{
	
	@Inject
	private MemberService service;

	@GetMapping("/member/memberList.do")
	public String doGet(
		@RequestParam(value="searchType", required = false) String searchType
		, @RequestParam(value="searchWord", required = false) String searchWord
		, @RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, Model model
		, @ModelAttribute("simpleCondition") SearchVO simpleCondition
	){
		
		PaginationInfo<MemberVO> paging = new PaginationInfo<>(5, 2);
		paging.setSimpleCondition(simpleCondition); // 키워드 검색 조건
		paging.setCurrentPage(currentPage);
		
		service.retrieveMemberList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		model.addAttribute("paging", paging);
		
		return "member/memberList";
	}
		
}




















