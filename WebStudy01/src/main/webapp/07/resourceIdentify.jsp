<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>07/resouceIdentify.jsp</title>
<style>
	img {
		width : 100px;
		height : 100px;
	}
</style>
</head>
<body>
	<h4>자원의 종류와 식별 방법</h4>
	<pre>
	* 자원을 식별할때는 변경되는 경로는 사용하지 않는다.
	1. file system resource : 자원의 실제 파일 시스템상의 경로(물리 주소)를 그대로 사용해서 식별함.
		ex) D:\02.medias\images\cute1.PNG (OS의 경로 구분자를 그대로 사용함)
		<%
			File res1 = new File("D:\\02.medias\\images\\cute1.PNG");
		%>
		파일 크기 : <%= res1.length()	 %>
	2. class path resource : classpath 이후의 논리 주소 형태로 자원을 식별함.
		경로의 시작점이 cp인 자원..?
		ex) /kr/or/ddit/images/cat1.jpg
		<%
			ClassLoader loader = DescriptionServlet.class.getClassLoader();
			URL url = loader.getResource("kr/or/ddit/images/cat1.jpg");
			if(url!=null){
				String realPath = url.getFile(); 
				
				File res2 = new File(realPath);
		%>
		파일 크기 : <%=res2.length() %>
		파일의 물리 주소 : <%=realPath %>
		<%
			}
		%>
		
	3. web resource (context resource) : URL 형태의 식별자 체계로 네트워크 반대편의 클라이언트가 접근할 수 있는 자원.
		ex) http://localhost/WebStudy01/resources/images/cat4.png (URL)
		<%
			String logical = "/resources/images/cat4.png";
			String physical = application.getRealPath(logical);
			File res3 = new File(physical);
		%>
		파일 크기 : <%=res3.length() %>
		파일의 물리 주소 : <%=physical %>
		
		
* 웹 자원의 식별자
	URI(Uniform Resource Identifier, 통합 자원 식별자) : 네트워크 자원을 식별하는 체계
	URI를 구현하는 구체 적인 방법
	 - URN(Uniform Resource Naming) -> 식별자가 애매해서 유일성 보장 못함 
	 - URC(Uniform Resource Content) ex) 책 검색시 출판사, 저자, 제목 등을 조합해서 검색한다. 단점 ) 유일성을 보장하지 못한다.
	 - URL(Uniform Resource Locator)
	 
	 case 1 - http://localhost/WebStudy01/resources/images/cat4.png
	 case 2 - http://localhost/WebStudy01/ver4/imageForm.do (URI)
	 
	 URL 표기 방식
	 protocol://IP[domain]:port/context/depth../resource_name
	 
	 절대경로
	 	1) http://localhost/WebStudy01/resources/images/cat4.png
	 	2) //localhost/WebStudy01/resources/images/cat4.png
	 	3) client side - <%=request.getContextPath() %>/resources/images/cat4.png (*****)
	 		-- 도메인이 바뀌더라도 현재 페이지의 소스만 잘 표현하면 알아서 처리해준다...
	 	4) server side - /resources/images/cat4.png
	 상대경로 : 현재 페이지의 출처를 기준으로 경로를 표기함.
	 	../resources/images/cat4.png
	 
	</pre>
	<img src="http://localhost/WebStudy01/resources/images/cat4.png"/>
	<img src="//localhost/WebStudy01/resources/images/cat4.png"/>
	<img src="<%=request.getContextPath() %>/resources/images/cat4.png"/>
	<img src="../resources/images/cat4.png"/>
</body>
</html>