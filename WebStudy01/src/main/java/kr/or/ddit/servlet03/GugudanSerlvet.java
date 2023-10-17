package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/05/gugudan.do")
public class GugudanSerlvet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String minDanStr = req.getParameter("minDan");
		String maxDanStr = req.getParameter("maxDan");
		int minDan = 2;
		int maxDan = 9;
		boolean valid = true;
		if(minDanStr!=null&&minDanStr.matches("[2-9]")){
			minDan = Integer.parseInt(minDanStr);
		} else if(minDanStr != null && !minDanStr.matches("[2-9]")){
			// 검증 실패
			valid = false;
		}
		if(maxDanStr!=null&&maxDanStr.matches("[2-9]")){
			maxDan = Integer.parseInt(maxDanStr);
		} else if(maxDanStr != null && !maxDanStr.matches("[2-9]")){
			// 검증 실패
			valid = false;
		}
		
		if(!valid){
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"파라미터에 문제 있어 검증 실패");
			return;
		}
		
		if(minDan>maxDan){
			int temp = maxDan;
			maxDan = minDan;
			minDan = temp;
		}
		
		// 1번 
		
		req.setAttribute("minDan", minDan);
		req.setAttribute("maxDan", maxDan);
		
		
		String viewName = "/WEB-INF/views/05/gugudan.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
		
		
		
		
	}
	
}
