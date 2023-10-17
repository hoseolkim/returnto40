package kr.or.ddit.login.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet{
	private AuthenticateService service = new AuthenticateServiceImpl();
	
	
	private boolean validate(MemberVO member) {
		boolean valid = true;
//		valid &= memId != null && !memId.trim().isEmpty();
		valid &= StringUtils.isNotBlank(member.getMemId());
//		valid &= memPass != null && !memPass.trim().isEmpty();
		valid &= StringUtils.isNotBlank(member.getMemPass());
		return valid;
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 아이디나 비밀번호가 누락된 경우, Bad Request 전송
		// 2. 인증 성공 : 웰컴 페이지로 이동 -> "a001" 님 로그인 성공이라는 메시지를 웰컴페이지에 alert창으로 출력. 
		// 3. 인증 실패 : 로그인 폼으로 이동 -> "아이디나 비밀번호 오류" 라는 메시지를 로그인 폼에서 alert 창으로 출력.
		
//		1. request body 영역에 대한 디코딩 설정.
		//-------------------------------------------------
		req.setCharacterEncoding("utf-8");// 모든 컨트롤러에서 제일 먼저 들어가야할 코드..
		// 이것도 빼먹고 무슨..
		//-------------------------------------------------
//		2. 파라미터 획득
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		String saveIds[] = req.getParameterValues("idSave");
		String saveId = "";
		
		
		if(saveIds!=null && saveIds.length != 0 ) {
			saveId = saveIds[0];
		}
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);
		
		int sc = 200;
		String goPage = null;
//		3. 요청 검증
		boolean valid = validate(inputData);
		if(valid) {
//		4-1. 검증 통과
//			5-1. 인증 여부 판단
				boolean authenticated = service.authenticate(inputData);
				HttpSession session = req.getSession();
//				6-1. 인증 성공
				if(authenticated) {
	//					- 웰컴 페이지 이동
					goPage = "redirect:/";
					session.setAttribute("authId", memId);
					Cookie idCookie = new Cookie("saveId",memId);
					Cookie saveStateCookie = new Cookie("saveState","checked");
					if(saveId!=null && saveId.equals("saveId")) {
						idCookie.setMaxAge(7*24*60*60);
						saveStateCookie.setMaxAge(7*24*60*60);
					}else {
						idCookie.setMaxAge(0);
						saveStateCookie.setMaxAge(0);
					}
					idCookie.setPath(req.getContextPath()+"/login");
					saveStateCookie.setPath(req.getContextPath()+"/login");
					resp.addCookie(idCookie);
					resp.addCookie(saveStateCookie);
				}else {
//				6-2. 인증 실패
//					- loginForm 으로 이동
					goPage = "redirect:/login/loginForm.jsp";
					session.setAttribute("message", "아이디나 비밀번호 오류");
				} // if(authenticated) end
			
		}else {
//		4-2. 검증 불통과
//			5-2. Bad request 전송
			sc = HttpServletResponse.SC_BAD_REQUEST;
		} // if(valid) end
		
		if(sc == 200) {
			// goPage로 이동
			if(goPage.startsWith("redirect:")) {
				String location = req.getContextPath() + goPage.substring("redirect:".length());
				resp.sendRedirect(location);
			}else {
				req.getRequestDispatcher(goPage).forward(req, resp);
			}
		}else {
			resp.sendError(sc);
		}
	}
}