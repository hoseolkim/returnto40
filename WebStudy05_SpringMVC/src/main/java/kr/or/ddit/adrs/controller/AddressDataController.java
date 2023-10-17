package kr.or.ddit.adrs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.adrs.service.AddressService;
import kr.or.ddit.adrs.service.AddressServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.AddressVO;

@Controller
public class AddressDataController{
	private AddressService service = new AddressServiceImpl();
	
	@RequestMapping("/adrs/address")
	public String adrsList(Principal principal, HttpServletRequest req) {
		
		String uri= StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
		int lastIdx = uri.lastIndexOf("/");
		int uriLen = uri.length();
		int baseLen = "/adrs/address".length();
		boolean valid = lastIdx >= baseLen && lastIdx < uriLen - 1;
		System.out.printf("%s : %b\n", uri, valid);
		
		String memId = principal.getName();
		List<AddressVO> adrsList = service.retriveAddressList(memId);
		
		req.setAttribute("adrsList", adrsList);
		
		return "jsonView";
	}
	
    private ObjectMapper mapper = new ObjectMapper();
	
	private boolean validate(AddressVO vo, Map<String, String> errors) {
		boolean valid = true;
		if(StringUtils.isBlank(vo.getAdrsName())) {
			errors.put("adrsName", "이름 누락");
			valid = false;
		}
		if(StringUtils.isBlank(vo.getAdrsHp())) {
			errors.put("adrsHp", "휴대폰 번호 누락");
			valid = false;
		}
		return valid;
	}
	
	@RequestMapping(value="/adrs/address", method = RequestMethod.POST)
	public String doPost(HttpServletRequest req, Principal principal) throws IOException{
		try(
			InputStream is = req.getInputStream();
		){
			AddressVO vo = mapper.readValue(is, AddressVO.class);
			req.setAttribute("originalData", vo);
			
//			String authId = (String) req.getSession().getAttribute("authId");
			
			vo.setMemId(principal.getName());
			
			Map<String, String> errors = new HashMap<>();
			req.setAttribute("errors", errors);
			boolean valid = validate(vo, errors);
			
			boolean success = false;
			String message = null;
			if(valid) {
				if(service.createAddress(vo)) {
					success = true;
				}else {
					message = "등록 실패";
				}
			}else {
				message = "필수파라미터 누락";
			}
			
			req.setAttribute("success", success);
			req.setAttribute("message", message);
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="/adrs/address", method = RequestMethod.DELETE)
	public String doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri= StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
		int lastIdx = uri.lastIndexOf("/");
		int uriLen = uri.length();
		int baseLen = "/adrs/address".length();
		boolean valid = lastIdx >= baseLen && lastIdx < uriLen - 1;
		String adrsNoPart = uri.substring(lastIdx+1);
//		if(valid) {
//			adrsNoPart = uri.substring(lastIdx+1);
//			valid = StringUtils.isNumeric(adrsNoPart);
//		}
		int adrsNo = -1;
		try {
			adrsNo = Integer.parseInt(adrsNoPart);
		}catch (NumberFormatException e) {
			valid = false;
		}
		if(!valid) {
			resp.sendError(400, "주소록 번호가 없음.");
			return null;
		}
		boolean success = service.removeAddress(adrsNo);
		req.setAttribute("success", success);
		if(!success) {
			req.setAttribute("message", "삭제 실패");
		}
		
		return "jsonView";
	}
}












