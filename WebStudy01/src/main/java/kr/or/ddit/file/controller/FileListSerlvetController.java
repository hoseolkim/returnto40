package kr.or.ddit.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet(value ="/fileBrowser.do3333", loadOnStartup = 1)
public class FileListSerlvetController extends HttpServlet{
	
	private ServletContext application;
	
	private boolean isGetFile(File dir) {
		boolean getFile = false;
		
		if(!dir.isDirectory()) {
			return getFile;
		}
		
		for(File f : dir.listFiles()) {
			if(f.isFile()) {
				getFile = true;
			}
		}
		
		return getFile;
	}
	
	private List<File> getFileList(HttpServletRequest req,String path){
		
		List<File> fileList = new ArrayList<File>();
		
		if(path.startsWith(req.getContextPath())) {
			path = path.substring(req.getContextPath().length());
		}
		Set<String> resources = application.getResourcePaths(path);
		List<String> resourcesList = new ArrayList<String>(resources);
//		Map<String,File> fileList = new LinkedHashMap<String,File>();
		Collections.sort(resourcesList);
		for(String resource : resourcesList) {
//			System.out.println(resource);
			File f = new File(application.getRealPath(resource));
//			fileList.put(resource, f);
			fileList.add(f);
//			if(f1.isDirectory()) {
//				System.out.println("폴더입니다");
//			}
		}
		
		return fileList;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("들어옴");
		req.getRequestDispatcher("/WEB-INF/views/self/fileListForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String filePath = req.getParameter("path");
		List<File> fileList = null;
		if(filePath == null || filePath.trim().isEmpty()) {
			filePath = "/";
		}
		
		fileList = getFileList(req, filePath);
		
		ObjectMapper jackson = new ObjectMapper();
//		
//		jackson.writev
//		
		
		// 모르겠습니다 이게 최선이에요
	}
	
}