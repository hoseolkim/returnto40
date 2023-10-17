package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodView.do")
public class ProdViewControllerServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String prodId = req.getParameter("what");
		
		ProdVO prod = new ProdVO();
		
		prod.setProdId(prodId);
		
		prod = service.retrieveProd(prodId);
		
		if(prod==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,"해당 물품에 대한 상세 정보가 없음");
			return;
		}
		
		
		req.setAttribute("prod", prod);
		String viewName = "prod/prodView";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}