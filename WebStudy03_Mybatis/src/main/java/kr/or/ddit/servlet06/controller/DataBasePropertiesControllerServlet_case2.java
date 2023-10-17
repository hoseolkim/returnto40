package kr.or.ddit.servlet06.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.servlet06.service.DataBasePropertyService;
import kr.or.ddit.servlet06.service.DataBasePropertyServiceImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

@WebServlet("/13/case2/jdbcDesc.do")
public class DataBasePropertiesControllerServlet_case2 extends HttpServlet{
//	1. 의존관계 형성
	private DataBasePropertyService service = new DataBasePropertyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		2. 사용자의 요청이 어떤 컨텐츠에 대한 요청인지 식별(Accept 헤더 활용)
		String accept = req.getHeader("Accept");
		String goPage;
		
		if(accept.contains("json")) {
//			- data 요청
			List<DataBasePropertyVO> list = service.retrieveDBPropertyList();
			req.setAttribute("dataList", list);
			goPage = "/jsonView.view";
		}else {
//			- UI 요청
			goPage = "/WEB-INF/views/13/jdbcDescCase2.jsp"; // HTML
		}
		
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}

















