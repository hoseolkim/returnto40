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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		1. 요청의 헤더에서 accept를 꺼낸다
		String accept = req.getHeader("accept");
		String goPage = "/WEB-INF/13/jdbcDescCase2.jsp";
//		2-1. accept에 json이 있는 경우
		if(accept.contains("json")) {
//			3-1. service에서 데이터를 받아온다
			DataBasePropertyService service = new DataBasePropertyServiceImpl();
			List<DataBasePropertyVO> list = service.retrieveDBPropertyList();
//			4-1. 받아온 데이터를 그대로 jsonview로 전달한다.
			req.setAttribute("list", list);
//			5-1. goPage를 json view로 선택한다
			goPage = "/jsonView.view";
		}
		
//		2-2. accept에 json이 없는 경우
	//		3-1 원래 페이지로 포워딩한다
		
//		이동한당..
		if(goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
	}
}