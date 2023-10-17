package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.do")
public class indexControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//모델만들기
		String title = "컨트롤러에서 만든 Model 타이틀";
		
		//모델공유
		req.setAttribute("title", title);
		
		// 뷰를 선택하고 이동
		String viewName = "/WEB-INF/views/index.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}