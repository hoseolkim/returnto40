package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
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
@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		SearchVO simpleCondition = new SearchVO(searchType, searchWord);
		
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PaginationInfo<MemberVO> paging = new PaginationInfo<>(5,2);
		paging.setSimpleCondition(simpleCondition); // 키워드 검색 조건
		paging.setCurrentPage(currentPage);
		service.retrieveMemberList(paging);
		
//		paging.setRenderer(new BootstrapPaginationRenderer());
		
		
		req.setAttribute("paging", paging);
		
		String viewName = "member/memberList";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}