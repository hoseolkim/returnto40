package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProdInsertController{
	
	@Inject
	private ProdService service;
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<LprodVO> lprodList(){
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList(){
		return othersDAO.selectBuyerList(null);
	}
	
	@ModelAttribute("prod")
	public ProdVO prod() {
		return new ProdVO();
	}
	
	@RequestMapping("/prod/prodInsert.do")
	public String prodForm(){
		return "prod/prodForm";
	}
	
	@RequestMapping(value="/prod/prodInsert.do", method = RequestMethod.POST)
	public String prodInsert(
			@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod
			, BindingResult errors
			, Model model) throws IOException{
		
//		Map<String, List<String>> errors = new LinkedHashMap<>();
//		model.addAttribute("errors", errors);
//		3. 검증 (대상 : ProdVO)
		boolean valid = ! errors.hasErrors();
		
		String viewName = null;
		if(valid) {
//			통과
//				4. createProd 등록 처리
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
//					2) OK 
//						상품 상세 페이지로 이동 (redirect)
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;
			default:
//					3) FAIL
//						prodForm 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				model.addAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "prod/prodForm";
				break;
			}
		}else {
//			불통
//				prodForm 으로 이동 (기존 입력 데이터, 검증 결과 메시지들.., dispatch)
			viewName = "prod/prodForm";
		}


		return viewName;
		
	}
}
