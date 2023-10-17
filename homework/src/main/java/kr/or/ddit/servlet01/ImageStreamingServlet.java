package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @WebServlet 어노테이션으로 등록하는 경우, servlet-name은 CoC 에 따라 클래스의 심플 네임이 사용됨.
 * CoC (Convetion Over Configuration) CoC 패러다임 : 명시적인 설정이 없는 경우, 일반적인 관행이 코드에 반영됨
 */
@WebServlet(loadOnStartup = 1, value="/image.do", initParams = {@WebInitParam(name = "imageFolder", value = "D:/01.medias/images")})
public class ImageStreamingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File imageFolder;
	private ServletContext application; // 싱글톤 
	
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); // ServletContext가 생성됨..?
		
		application = getServletContext();
		System.out.printf(" application hash code : %d \n", application.hashCode());
		
		
//		String value = config.getInitParameter("imageFolder");
		
		String value = application.getInitParameter("imageFolder");
		
		this.imageFolder = new File(value);
		System.out.println(MessageFormat.format("{0} 서블릿 초기화 완료", this.getClass().getSimpleName()));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String imageName = request.getParameter("image");
		
		if(imageName==null||imageName.isEmpty()) {
			// 400번 오류 : 요청에대한 검증오류 ( 대표적으로 파라미터가 누락되었을 때 )
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		File imageFile = new File(imageFolder, imageName);
		
		if(!imageFile.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, MessageFormat.format("{0} 이미지 파일이 없음.", imageName));
			return;
		}
	
		
		ServletContext application = getServletContext();
		String mime = application.getMimeType(imageFile.getName());
		
		response.setContentType(mime);
		response.setContentLengthLong(imageFile.length());
		
		FileInputStream fis = new FileInputStream(imageFile);
		
		ServletOutputStream sos = response.getOutputStream();
		
		// EOF(End Of File) 문자를 만날 때까지 반복 작업
		byte[] buffer = new byte[1024];
		int length = -1;
		while((length = fis.read(buffer)) != -1) {
			// 버퍼 : 읽어들인 데이터를 갖고있다
			// int length : 지금 얼만큼 읽었는지 정보를 가지고 있다
			sos.write(buffer, 0 , length);
		}
		
		sos.flush();
		
		fis.close();
		sos.close();
		
	}
}