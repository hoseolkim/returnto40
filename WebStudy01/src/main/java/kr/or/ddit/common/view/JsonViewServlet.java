package kr.or.ddit.common.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/jsonView.view")
public class JsonViewServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String contentType = "application/json; charset=utf-8";
		
		resp.setContentType(contentType);
		
		Enumeration<String> attrNames =  request.getAttributeNames();
		
		Map<String, Object> target = new HashMap<String, Object>();
		
		while(attrNames.hasMoreElements()){
			String name = attrNames.nextElement();
			Object value = request.getAttribute(name);
			target.put(name, value);
		}
		
		try(
		PrintWriter out = resp.getWriter();
		){
			new ObjectMapper().writeValue(out, target);
		}
		
		
		
		
	}//service 메소드 끝
}
