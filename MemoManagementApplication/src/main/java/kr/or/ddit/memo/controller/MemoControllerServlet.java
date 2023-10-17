package kr.or.ddit.memo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.memo.service.AuthenticateMemoTableSchema;
import kr.or.ddit.memo.service.AuthenticateMemoTableSchemaImpl;
import kr.or.ddit.memo.service.MemoAthenticateService;
import kr.or.ddit.memo.service.MemoAthenticateServiceImpl;
import kr.or.ddit.memo.service.MemoService;
import kr.or.ddit.memo.service.MemoServiceImpl;
import kr.or.ddit.vo.MemoVO;

@WebServlet({"/memo","/memo/*"})
public class MemoControllerServlet extends HttpServlet{
	
	private MemoAthenticateService authService;
	private MemoService service;
	private AuthenticateMemoTableSchema authScema;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		authService = MemoAthenticateServiceImpl.getInstance();
		service = MemoServiceImpl.getInstacne();
		authScema = new AuthenticateMemoTableSchemaImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MemoVO> memoList = service.retrieveMemoList();
		req.setAttribute("memoList", memoList);
		String goPage = "/jsonView.view";
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	private boolean validEmailReg(String email) {
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	private boolean validDataType(MemoVO memoVO) {
		ServiceResult serviceResult = authScema.authenticateTableSchema(memoVO);
		return ! serviceResult.equals(ServiceResult.LARGEVALUE);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		MemoVO memoVO = null;
		boolean valid = true;
		boolean success = false;
		String message= null;
		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);
		try(
			ServletInputStream is = req.getInputStream();
		){
			memoVO = new ObjectMapper().readValue(is, MemoVO.class);
		}
		/*
		if(validEmailReg(memoVO.getEmail())) {
			valid = false;
			errors.put("emailerror", "이메일 형식 불일치!");
		}
		if(validDataType(memoVO)) {
			valid = false;
			errors.put("dataLarge", "입력값이 너무 길어요!");
		}
		*/
		if(valid) {
			memoVO.setWrdate(LocalDate.now());
			ServiceResult result = service.createMemo(memoVO);
			if(result.equals(ServiceResult.OK)) {
				success = true;
			}else {
				message = "등록 실패";
			}
		}// valid 할 경우 끝
		req.setAttribute("message", message);
		req.setAttribute("success", success);
		req.setAttribute("originData", memoVO);
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
		req.setCharacterEncoding("utf-8");
		MemoVO memoVO = null;
		boolean success = false;
		String message = null;
		try(
			ServletInputStream is = req.getInputStream();
		){
			memoVO = new ObjectMapper().readValue(is, MemoVO.class);
		}
		
		if(ServiceResult.OK.equals(authService.authMemo(memoVO))){
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		/*
		if(validDataType(memoVO)) {
		}
		*/
		if(ServiceResult.OK.equals(service.modifyMemo(memoVO))){
			success = true;
		}else {
			message = "수정 실패";
		}
		req.setAttribute("message", message);
		req.setAttribute("success", success);
		req.setAttribute("changeData", memoVO);
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
		req.setCharacterEncoding("utf-8");
		String uri = StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
		int lastIdx = uri.lastIndexOf("/");
		int uriLen = uri.length();
		int baseLen = "/memo".length();
		boolean valid = lastIdx >= baseLen && lastIdx < uriLen - 1 ;
		String part = uri.substring(lastIdx+1);
		String[] parts = part.split(";");
		int code = -1;
		try {
			code = Integer.parseInt(parts[0]);
		} catch (NumberFormatException e) {
			valid = false;
		}
		if(!valid) {
			resp.sendError(400,"메모 번호가 없음.");
			return;
		}
		boolean success = false;
		String email = null;
		if(parts.length==2) {
			email = parts[1];
		}else {
			email = "";
		}
		String message = null;
		/*
		if(validEmailReg(email)) {
		}else {
			message = "이메일 형식 불일치";
		}// 이메일 형식 확인 if문
		*/
		MemoVO memoVO = new MemoVO();
		memoVO.setCode(code);
		memoVO.setEmail(email);
		/*
		if(ServiceResult.OK.equals(authService.authMemo(memoVO))){
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		*/
		ServiceResult result = service.removeMemo(memoVO);
		if(result.equals(ServiceResult.OK)) {
			success = true;
		}else {
			message = "삭제 실패";
		}
		
		req.setAttribute("message", message);
		req.setAttribute("success", success);
		String goPage = "/jsonView.view";
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
}