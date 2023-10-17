package kr.or.ddit.adrs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.adrs.service.AddressService;
import kr.or.ddit.adrs.service.AddressServiceImpl;
import kr.or.ddit.vo.AddressVO;

@WebServlet({"/adrs/address", "/adrs/address/*"})
public class AddressDataControllerServlet extends HttpServlet{
	private AddressService service = new AddressServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
		int lastIdx = uri.lastIndexOf("/");
		int uriLen = uri.length();
		int baseLen = "/adrs/address".length();
		
		boolean valid = lastIdx >= baseLen && lastIdx < uriLen - 1 ;
		System.out.printf("%s : %b\n",uri,valid);
		
		HttpSession session = req.getSession();
		
		String memId = (String)session.getAttribute("authId");
		if(StringUtils.isBlank(memId)) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		String goPage = null;
		List<AddressVO> list = service.retriveAddressList(memId);
		req.setAttribute("list", list);
		
		
		
		goPage = "/jsonView.view";
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	private ObjectMapper mapper = new ObjectMapper();
	// 파라미터 검증 메소드
	private boolean validate(AddressVO vo, Map<String,String> errors) {
		boolean valid = true;
		if(StringUtils.isBlank(vo.getAdrsName())) {
			valid = false;
			errors.put("adrsName", "이름 누락");
		}
		if(StringUtils.isBlank(vo.getAdrsHp())) {
			valid = false;
			errors.put("adrsHp", "휴대폰 번호 누락");
		}
		return valid;
	}
	
	private boolean validateUri(String uri) {
		boolean valid = true;
		System.out.println(uri);
		if(StringUtils.isBlank(uri)||!uri.matches("\\d+")) {
			valid = false;
		}
		return valid;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 이거 또 빼먹음
		req.setCharacterEncoding("utf-8");
		
		String memId = (String)req.getSession().getAttribute("authId");		
		
		
		try(
			InputStream is =req.getInputStream();
		){
			AddressVO vo = new AddressVO();
			vo = mapper.readValue(is, AddressVO.class);
			req.setAttribute("originalData", vo);
			vo.setMemId(memId);
			// 검증도 또 빼먹음..
			Map<String,String> errors = new HashMap<String, String>();
			req.setAttribute("errors", errors);
			boolean valid = validate(vo,errors);
			boolean success = false;
			String message = null;
			if(valid) {
				if(service.createAddress(vo)) {
					success = true;
				}else {
					message = "등록 실패";
				}// valid = true 일때 service 의 결과 if 문
			}else {
				message = "필수 파라미터 누락";
			}
			
			req.setAttribute("success", success);
			req.setAttribute("message", message);
			
		}
		
		
//		req.setAttribute("result", true);
		
		String goPage = "/jsonView.view";
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
		int lastIdx = uri.lastIndexOf("/");
		int uriLen = uri.length();
		int baseLen = "/adrs/address".length();
		
		boolean valid = lastIdx >= baseLen && lastIdx < uriLen - 1 ;
		String adrsNoPart = uri.substring(lastIdx+1);
//		if(valid) {
//			adrsNoPart = uri.substring(lastIdx+1);
//			valid = StringUtils.isNumeric(adrsNoPart);
//		}
		int adrsNo = -1;
		try {
			adrsNo = Integer.parseInt(adrsNoPart);
		} catch (NumberFormatException e) {
			valid = false;
		}
		if(!valid) {
			resp.sendError(400,"주소록 번호가 없음.");
			return;
		}
		boolean success = service.removeAddress(adrsNo);
		req.setAttribute("success", success);
		if(!success) {
			req.setAttribute("message", "삭제 실패");
		}
		
		String goPage = "/jsonView.view";
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
		
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 이거 또 빼먹음
		req.setCharacterEncoding("utf-8");
		
		try(
			InputStream is =req.getInputStream();
		){
			AddressVO vo = new AddressVO();
			vo = mapper.readValue(is, AddressVO.class);
			req.setAttribute("changeData", vo);
			// 검증도 또 빼먹음..
			Map<String,String> errors = new HashMap<String, String>();
			req.setAttribute("errors", errors);
			boolean valid = validate(vo,errors);
			boolean success = false;
			String message = null;
			if(valid) {
				if(service.modifyAddress(vo)) {
					success = true;
				}else {
					message = "수정 실패";
				}// valid = true 일때 service 의 결과 if 문
			}else {
				message = "필수 파라미터 누락";
			}
			
			req.setAttribute("success", success);
			req.setAttribute("message", message);
			
		}
		
		
//		req.setAttribute("result", true);
		
		String goPage = "/jsonView.view";
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	
	
	/*
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();
		
		uri = uri.substring(req.getContextPath().length());
		String adrsNoStr = uri.substring("/adrs/address/".length());
		// uri를 검증한다
		boolean valid = validateUri(adrsNoStr);
		boolean success = false;
		String message = null;
		if(valid) {
			// uri 검증에 성공했을 경우
			int adrsNo = Integer.parseInt(adrsNoStr);
			if(service.removeAddress(adrsNo)) {
				// 삭제 성공시
				success = true;
				
			}else {
				// 삭제 실패시
				message = "삭제 실패";
			}
			
		}else {
			// uri 검증 실패시
			message = "파라미터가 잘못됨!";
		}
		
		req.setAttribute("success", success);
		req.setAttribute("message", message);
		
		String goPage = "/jsonView.view";
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	*/
}
