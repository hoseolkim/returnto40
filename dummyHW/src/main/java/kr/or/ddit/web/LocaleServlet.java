package kr.or.ddit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Locale clientLocale = req.getLocale();
		Locale serverLocale = Locale.getDefault(); 
		
//		data -> info -> content
		
		String clientLocaleText = clientLocale.getDisplayName(clientLocale);
		String serverLocaleText = serverLocale.getDisplayName(clientLocale);
		
		StringBuilder content = new StringBuilder();
		content.append("<html>");
		content.append("<body>");
		content.append(MessageFormat.format("<h4>client side locale : {0} </h4>", clientLocaleText));
		content.append(MessageFormat.format("<h4>server side locale : {0} </h4>", serverLocaleText));
		
		content.append("</body>");
		content.append("</html>");
		String mime = "text/html; charset=utf-8";
		resp.setContentType(mime);
		PrintWriter out = resp.getWriter();
		out.print(content);
		out.close();
		
	}
}
