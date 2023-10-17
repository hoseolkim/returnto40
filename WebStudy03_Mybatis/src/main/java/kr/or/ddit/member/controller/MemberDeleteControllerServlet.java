package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteControllerServlet extends HttpServlet{
	MemberService service = new MemberServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Principal principal = req.getUserPrincipal();
		String memId = principal.getName();
		String password = req.getParameter("password");
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(password);
		
		Map<String, List<String>> errors = new HashMap<String, List<String>>();
		
		boolean valid = ValidationUtils.validate(inputData, errors, DeleteGroup.class);
		String viewName = null;
		if(valid) {
			ServiceResult result = service.removeMember(inputData);
			switch (result) {
			case INVALIDPASSWORD:
				viewName = "redirect:/mypage";
				session.setAttribute("message", "비밀 번호 오류");
				// flash attribute
				break;
			case OK:
				viewName = "redirect:/";
				session.invalidate();
				break;
			default:
				viewName = "redirect:/mypage";
				session.setAttribute("message", "서버 오류");
				// flash attribute
				break;
			}
			
		}else {
			viewName = "redirect:/mypage";
			session.setAttribute("message", "비밀 번호 누락");
		}
		
		
		
		
		
		
		
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	/*
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		세션을 가져온다
		HttpSession session = req.getSession(); 
//		세션에서 아이디를꺼낸다
		String memId = (String)session.getAttribute("authId");
//		파라미터로 전달된 비밀번호를 꺼낸다
		String memPass = req.getParameter("memPass");
//		MemberVO로 만든다
		MemberVO member = new MemberVO();
		member.setMemId(memId);
		member.setMemPass(memPass);
		String message = null;
		String viewName = null;
		Map<String, List<String>> errors = new HashMap<String, List<String>>();
		req.setAttribute("errors", errors);
//		vo를 검증한다
		boolean valid = ValidationUtils.validate(member, errors,DeleteGroup.class);
		if(valid) {
//			검증 성공시
//					탈퇴 요청을 날린다
			ServiceResult result = service.removeMember(member);
			switch (result) {
			case INVALIDPASSWORD:
//					패스워드 틀릴 경우
//						패스워드가 다르다는 메시지와 웰컴페이지로 보낸다
				message = "패스워드가 달라요!";
				member = service.retrieveMember(memId);
				req.setAttribute("member", member);
				req.setAttribute("message", message);
				viewName = "member/myPage";
				break;
			case OK:
//					탈퇴 성공시
//						세션을 죽이고 웰컴페이지로 보낸다
				session.invalidate();
				viewName = "redirect:/";
				break;

			default:
//					탈퇴 실패시
//						서버 오류라는 메시지와 마이페이지로 보낸다
				message = "서버오류! 잠시 후 다시 시도해주세요!"; 
				member = service.retrieveMember(memId);
				req.setAttribute("member", member);
				req.setAttribute("message", message);
				viewName = "member/myPage";
				break;
			}
		}else {
//			검증 실패시
			message = "비밀번호 검증 실패!";
			member = service.retrieveMember(memId);
			req.setAttribute("member", member);
			req.setAttribute("message", message);
			viewName = "member/myPage";
		}
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	*/
	
	
	
}
