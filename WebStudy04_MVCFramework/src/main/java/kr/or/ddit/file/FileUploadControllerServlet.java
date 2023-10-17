package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.ViewResolverComposite;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/15/fileUpload.do")
@MultipartConfig
public class FileUploadControllerServlet extends HttpServlet{
	
	private String imagesUrl = "/resources/images";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploader = req.getParameter("uploader");
		
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile uploadFile = 
						((StandardMultipartHttpServletRequest) req).getFile("uploadFile");
		
			if(!uploadFile.getContentType().startsWith("image/")) {
				resp.sendError(400);
				return;
			}
			
			log.info("uploader : {}", uploader);
			log.info("업로드 파일 part : {}", uploadFile);
			String realPath = req.getServletContext().getRealPath(imagesUrl);
			File imageFolder = new File(realPath);
			String filename = uploadFile.getOriginalFilename();
			File saveFile = new File(imageFolder, filename);
			try(
				InputStream is = uploadFile.getInputStream();
			){
				uploadFile.transferTo(saveFile);
				String fileUrl = imagesUrl + "/" + saveFile.getName();
				HttpSession session = req.getSession();
				session.setAttribute("uploader", uploader);
				session.setAttribute("fileUrl", fileUrl);
			}
		
		}
		
		String viewName = "redirect:/15/fileUploadForm.jsp";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}






















