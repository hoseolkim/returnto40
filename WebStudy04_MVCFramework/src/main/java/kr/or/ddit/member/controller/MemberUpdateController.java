package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandardMultipartHttpServletRequest;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberUpdateController{
	private MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberUpdate.do")
 	public String updateForm(Principal principal, HttpServletRequest req) {
		
		String memId = principal.getName();
		
		MemberVO member = service.retrieveMember(memId);
		
		req.setAttribute("member", member);
		
		return "member/memberForm";
	}
	

	@RequestMapping(value = "/member/memberUpdate.do", method = RequestMethod.POST)
	public String updateProcess(@ModelAttribute("member") MemberVO member, HttpServletRequest req) throws IOException{
		
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile memImage = ((StandardMultipartHttpServletRequest) req).getFile("memImage");
			if(memImage!=null && !memImage.isEmpty()) {
				member.setMemImg(memImage.getBytes());
			}
		}
		
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
//		3. 검증 (대상 : MemberVO)
		boolean valid = ValidationUtils.validate(member, errors, UpdateGroup.class);
		String viewName = null;
		if(valid) {
//			통과
//				4. modifyMember 수정 처리
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
//					1) INVALIDPASSWORD 
//						memberForm 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "비밀번호 오류");
				viewName = "member/memberForm";
				break;
			case OK:
//					2) OK 
//						/mypage 로 이동 (redirect)
				viewName = "redirect:/mypage";
				break;
			default:
//					3) FAIL
//						memberForm 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "member/memberForm";
				break;
			}
		}else {
//			불통
//				memberForm 으로 이동 (기존 입력 데이터, 검증 결과 메시지들.., dispatch)
			viewName = "member/memberForm";
		}
		return viewName;
	}

}











