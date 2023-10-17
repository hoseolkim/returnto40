<%@page import="java.io.InputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/applicationDesc.jsp</title>
</head>
<body>
<h4>ServletContext</h4>
<pre>
   : 서버와 현재 컨텍스트에 대한 정보를 가진 싱글턴 객체
   
   1. 서버의 정보 획득
      <%=application.getServerInfo() %>
      <%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
      <%-- <%=request.getParts() %> --%>
      <%=application.getMimeType("sample.jpg") %>
      <%=application.getMimeType("sample.hwp") %> <!-- nullpoint에 안전한 optional .. 쓰자.. -->
   2. context parameter 획득 : web.xml 을 통해 등록된 파라미터 획득
      <%
         Enumeration<String> names = application.getInitParameterNames();
         while(names.hasMoreElements()){
            String paramName = names.nextElement();
            String paramValue = application.getInitParameter(paramName);
            out.println(
                  String.format("%s : %s", paramName, paramValue)
                  );
         }
      %>
   3. log 기록
      <% application.log("샘플 로그 메세지"); %>
      
   4.★★★ 웹 리소스(url) 획득
   <%
      String url = "/resources/images/cat6.png";
      String realPath = application.getRealPath(url);
      File srcFile = new File(realPath);
      
      
      
      String folderUrl = "/11";
      String destPath = application.getRealPath(folderUrl);
      File destFolder = new File(destPath);
      File destFile = new File(destFolder, srcFile.getName());
      String destUrl = folderUrl + "/" + destFile.getName() ;
      
      try(
         InputStream is = application.getResourceAsStream(url);
         //FileInputStream fin = new FileInputStream(srcFile); // 1차 스트림..
         BufferedInputStream bin = new BufferedInputStream(is); // 2차 스트림(연결형)
         FileOutputStream fout = new FileOutputStream(destFile); // 1차 스트림..
         BufferedOutputStream bout = new BufferedOutputStream(fout); // 2차 스트림(연결형)
      ){
         int c=-1;
         while((c=bin.read())!=-1){
            bout.write(c);
         }
         bout.flush();
      }
   %>
   srcFile : <%=srcFile.getCanonicalPath() %>
</pre>
<img src="<%=request.getContextPath() %><%=destUrl %>">
</body>
</html>