package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

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
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

@MultipartConfig
@WebServlet("/prod/prodUpdate.do")
public class ProdUpdateControllerServlet extends HttpServlet{
	private String prodImagesUrl = "/resources/prodImages";
	private ProdService service = new ProdServiceImpl();
	private OthersDAO other = new OthersDAOImpl();
	private void setList(HttpServletRequest req) {
		req.setAttribute("lprodList", other.selectLprodList());
		req.setAttribute("buyerList", other.selectBuyerList(null));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("what");
		String viewName = null;
		if(StringUtils.isBlank(param)) {
			resp.sendError(400,"뭔 요청이여?");
			return;
		}
		ProdVO prod = service.retrieveProd(param);
		req.setAttribute("prod", prod);
		setList(req);
		viewName = "prod/prodEdit";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProdVO prodVO = new ProdVO();
		PopulateUtils.populate(prodVO, req.getParameterMap());
		setList(req);
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile prodImage = ((StandardMultipartHttpServletRequest) req).getFile("prodImage");
			
			if(prodImage != null && !prodImage.isEmpty()) {
				String realPath = req.getServletContext().getRealPath(prodImagesUrl);
				File saveFolder = new File(realPath);
				
				String fileName = UUID.randomUUID().toString();
				
				File saveFile = new File(saveFolder, fileName);
				// 상품 이미지의 2진 데이터 저장
				prodImage.transferTo(saveFile);
				prodVO.setProdImg(fileName);
			}
		}
		
		Map<String, List<String>> errors = new LinkedHashMap<String, List<String>>();
		req.setAttribute("errors", errors);
		
		String message = null;
		req.setAttribute("prod", prodVO);
		boolean valid = ValidationUtils.validate(prodVO, errors, UpdateGroup.class);
		String viewName = "prod/prodEdit";
		if(!valid) {
//			- 검증 실패시 그대로 들고, 에러메시지 들고 포워드로 돌아간다.
		}else {
//			- 검증 성공시
//				service로 인서트 요청을 보낸다
			ServiceResult result = service.modifyProd(prodVO);
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
		
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
