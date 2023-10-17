package kr.or.ddit.common.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.or.ddit.mvc.View;

public class JsonView implements View{
	@Override
	public void render(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String contentType = "application/json;charset=UTF-8";
		
		resp.setContentType(contentType);
		
		Map<String,Object> target = new HashMap<>();
		Enumeration<String> attrNames = request.getAttributeNames();
		while(attrNames.hasMoreElements()){
			String name = attrNames.nextElement();
			Object value = request.getAttribute(name);
			target.put(name, value);
		}
		
//		Object content = new ObjectMapper().writeValueAsString(target);
		try(
			PrintWriter out = resp.getWriter();
		){
			new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.writeValue(out, target);
		}
	}
}












