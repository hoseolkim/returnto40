package kr.or.ddit.self.study;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/self/formDataProcess")
public class SelfFromDataProcessServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Map reqContent = null;
		String reqContentType = req.getContentType();
		if(reqContentType.contains("json")) {
			// json payload가 전송되었다
			InputStream is = req.getInputStream();
			reqContent =new ObjectMapper().readValue(is, HashMap.class);
		}else {
			reqContent = req.getParameterMap();
			reqContent.forEach((k,v)->System.out.printf("%s : %s\n", k , Arrays.toString((String[])v)));
		}
		
		//=============================================================================================
		
		// information을 만드는 작업
		
		Map<String, Object> target = new HashMap<String, Object>();
		target.put("message","파라미터 처리 완료");
		target.putAll(reqContent);
		String accept = req.getHeader("Accept");
		
		//=============================================================================================
		
		String view = null;
		// information -> content -> serialization
//		Object content = null;
		if(accept.contains("json")) {
			view = "/WEB-INF/views/self/jsonView.jsp";
//			content = new ObjectMapper().writeValueAsString(target);
		}else if(accept.contains("xml")){
			view = "/WEB-INF/views/self/xmlView.jsp";
//			content = "<root><message>"+target.get("message")+"</message></root>";
		}else {
			view = "/WEB-INF/views/self/htmlView.jsp";
//			content = "<div>"+target.get("message")+"</div>";
		}
		req.setAttribute("target", target);
		req.getRequestDispatcher(view).forward(req, resp);
	}
}