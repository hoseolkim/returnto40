package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@MultipartConfig
@WebServlet("/prod/prodInsert.do")
@Slf4j
public class ProdInsertControllerServlet extends HttpServlet{
	
	private String prodImagesUrl = "/resources/prodImages";
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO other = new OthersDAOImpl();
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("lprodList", other.selectLprodList());
		req.setAttribute("buyerList", other.selectBuyerList(null));
		String viewName = "prod/prodForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		controller 의사코드
//		1. 파라미터 맵을 ProdVO로 Populate한다
		
		ProdVO prodVO = new ProdVO();
		PopulateUtils.populate(prodVO, req.getParameterMap());
		
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile prodImage = ((StandardMultipartHttpServletRequest) req).getFile("prodImage");
			
			if(!prodImage.isEmpty()) {
				String realPath = req.getServletContext().getRealPath(prodImagesUrl);
				File saveFolder = new File(realPath);
				
				String fileName = UUID.randomUUID().toString();
				
				File saveFile = new File(saveFolder, fileName);
				// 상품 이미지의 2진 데이터 저장
				prodImage.transferTo(saveFile);
				prodVO.setProdImg(fileName);
			}
		}
		
		
		String viewName = "prod/prodForm";
		req.setAttribute("lprodList", other.selectLprodList());
		req.setAttribute("buyerList", other.selectBuyerList(null));
//		2. ValidateUtils로 검증한다
		Map<String, List<String>> errors = new HashMap<String, List<String>>();
		req.setAttribute("errors", errors);
		String message = null;
		boolean valid = ValidationUtils.validate(prodVO, errors, InsertGroup.class);
		req.setAttribute("prod", prodVO);
		if(!valid) {
//			- 검증 실패시 그대로 들고, 에러메시지 들고 포워드로 돌아간다.
			log.error("검증실패");
			log.error(errors.toString());
		}else {
//			- 검증 성공시
//				service로 인서트 요청을 보낸다
			ServiceResult result = service.createProd(prodVO);
			if(result == ServiceResult.OK) {
//					- 인서트 성공시
//						view를 prodList로 설정후 리다이렉트한다
				viewName = "redirect:/prod/prodView.do?what="+prodVO.getProdId();
			}else {
//					- 인서트 실패시
//						그래도 들고, 에러메시지 들고 포워드로 돌아간다.
				message = "서버오류! 잠시후 다시 시도해주세요!";
			}
		}
//		끝	
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	
	
}