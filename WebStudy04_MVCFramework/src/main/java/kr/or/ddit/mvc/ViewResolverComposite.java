package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolverComposite implements ViewResolver {
	private List<ViewResolver> viewResolvers;
	
	private String prefix;
	private String suffix;
	
	public ViewResolverComposite() {
		super();
		viewResolvers = new ArrayList<>();
	}

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void addViewResolver(ViewResolver resolver) {
		viewResolvers.add(resolver);
	}


	@Override
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (viewName.startsWith("redirect:")) {
			String location = req.getContextPath() + viewName.substring("redirect:".length());
			resp.sendRedirect(location);
		} else {
			for(ViewResolver single : viewResolvers) {
				try {
					single.resolveView(viewName, req, resp);
					break;
				}catch(Exception e) {
					System.err.println(e.getMessage());
					continue;
				}
			}
		}
	}

}













