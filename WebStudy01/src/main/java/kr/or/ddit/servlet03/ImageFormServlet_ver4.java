package kr.or.ddit.servlet03;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Model 2 아키텍쳐 기반의 책임의 분리 구조
 * Model 1 : request 와 response 가 하나의 객체(서블릿/JSP)에 의해 처리되는 구조
 * Model 2 : request(servlet, controller) 처리 객체와  response(servlet/JSP, view) 처리 객체가 분리된 구조
 * 			Model : content 의 원형이 되는 information -> MVC pattern
 * Controller 의 역할
 * 1. 요청 접수
 * 2. 요청 검증/분석
 * 3. Model(information) 생성
 * 4. scope 를 통해 model 공유
 * 5. view 선택
 * 6. view 로 이동
 * 
 * View 역할
 * model 로부터 content 를 생성해 응답으로 전송
 *
 */
@WebServlet("/ver4/imageForm.do")
public class ImageFormServlet_ver4 extends HttpServlet{
	
	private File imageFolder;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		application = getServletContext();
		
		String value = application.getInitParameter("imageFolder");
		
		imageFolder = new File(value);
		
	}
	/*
	1. 이미지를 처리하는 곳에서 선택된 이미지값을 받는다..
	2-1 이미지가 있는경우
	3-1 쿠키를 만든다
	4-1 담아서 전송한다
	
	2-2 이미지가 없는 경우
	3-2 쿠키를 공백을담아서 만든다
	4-2 담아서 전송한다
	
	5. 담겨있는 쿠키의 값을 꺼낸다
	5-1 그에따라 처리한다.
	
	
	*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] imageFileNames = imageFolder.list(
				(d,n)->Optional.ofNullable(application.getMimeType(n)).orElse("").startsWith("image/")
				);
		
		String savedImageName = Optional.ofNullable(req.getCookies()).map(cs->{
			return Arrays.stream(cs).filter(c->"savedImageName".equals(c.getName())).findFirst().map(fc -> fc.getValue()).orElse("");
		}).orElse("");
		
		
		
		String options = Stream.of(imageFileNames)
							.map((in)->MessageFormat.format("<option>{0}</option>", in)
							).collect(Collectors.joining("\n"));
		
		
		StringBuilder content = new StringBuilder();
		
		// 여기가 scope의 역할.. 전달하는 모델이 두 개..
		req.setAttribute("cPath", req.getContextPath());
		req.setAttribute("options", options);
		req.setAttribute("savedImageName", savedImageName);
		
		req.getRequestDispatcher("/WEB-INF/views/04/imageForm.jsp").forward(req, resp);
		
	}
}