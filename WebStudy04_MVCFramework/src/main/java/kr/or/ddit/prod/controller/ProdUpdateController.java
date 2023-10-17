package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdUpdateController{
	private String prodImagesUrl = "/resources/prodImages";
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	private void addRequestAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	}
	
	@RequestMapping("/prod/prodUpdate.do")
	public String doGet(
			HttpServletRequest req
			, @RequestParam(value="what", required = true) String prodId
	){
		addRequestAttribute(req);
		
		ProdVO prod = service.retrieveProd(prodId);
		
		req.setAttribute("prod", prod);
		
		return "prod/prodEdit";
	}
	
	@RequestMapping(value = "/prod/prodUpdate.do", method = RequestMethod.POST)
	public String doPost(@ModelAttribute("prod") ProdVO prod, HttpServletRequest req) throws IOException {
		addRequestAttribute(req);
		
		// multipart 처리
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile prodImage = ((StandardMultipartHttpServletRequest) req).getFile("prodImage");
			if(!prodImage.isEmpty()) {
				String realPath = req.getServletContext().getRealPath(prodImagesUrl);
				File saveFolder = new File(realPath);
				
				String filename = UUID.randomUUID().toString();
				
				File saveFile = new File(saveFolder, filename);
				// 상품이미지의 2진 데이터 저장
				prodImage.transferTo(saveFile);
				prod.setProdImg(filename);
			}
		} 
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
//		3. 검증 (대상 : ProdVO)
		boolean valid = ValidationUtils.validate(prod, errors, UpdateGroup.class);
		
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
				req.setAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
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
