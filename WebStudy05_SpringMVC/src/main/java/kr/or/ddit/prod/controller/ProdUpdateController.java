package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdUpdateController{
	
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
	
	@RequestMapping("/prod/prodUpdate.do")
	public String doGet(
			Model model
			, @RequestParam(value="what", required = true) String prodId
	){
		ProdVO prod = service.retrieveProd(prodId);
		
		model.addAttribute("prod", prod);
		
		return "prod/prodEdit";
	}
	
	/**
	 * 스프링을 이용한 객체 검증 (H/V + Spring)
	 * 1. 검증 대상이 되는 command object 에 @Valid / @Validated(group hints) 를 사용
	 * 2. command object 바로 다음 파라미터로 검증 결과(Errors / BindingResult) 객체 선언.
	 * 3. errors.hasErrors 로 검증 통과 여부 확인.
	 * 4. 검증 결과 메시지 출력시, form:errors 커스텀 태그 활용.
	 * 
	 */
	@RequestMapping(value = "/prod/prodUpdate.do", method = RequestMethod.POST)
	public String doPost(
	@Validated(value = UpdateGroup.class) @ModelAttribute("prod")
	ProdVO prod
	, Errors errors
	, Model model
	) throws IOException {
		
//		Map<String, List<String>> errors = new LinkedHashMap<>();
//		model.addAttribute("errors", errors);
//		3. 검증 (대상 : ProdVO)
//		boolean valid = ValidationUtils.validate(prod, errors, UpdateGroup.class);
		boolean valid = !errors.hasErrors();
		String viewName = null;
		if(valid) {
//			통과
//				4. createProd 등록 처리
			ServiceResult result = service.modifyProd(prod);
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
				viewName = "prod/prodEdit";
				break;
			}
		}else {
//			불통
//				prodForm 으로 이동 (기존 입력 데이터, 검증 결과 메시지들.., dispatch)
			viewName = "prod/prodEdit";
		}

		return viewName;
		
	}
}
