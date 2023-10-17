<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>12/cookieDesc.jsp</title>
</head>
<body>
	<h4>Cookie recipe</h4>
	<pre>
		<%--
			Cookie samplecookie = new Cookie("sampleCookie", "SAMPLE_COOKIE_VALUE");
			response.addCookie(samplecookie);
		--%>		
		1. 쿠키 생성(name/value 필수 조건)
		2. response header(set-cookie) 를 통해 클라이언트쪽으로 전송(20개 제한)
		
		3. 각 브라우저가 소유한 쿠키 저장소에 저장됨.
		4. 다음번 request header(cookie) 에 서버로 재전송됨
		
		5. request header 에 포함된 쿠키를 통해 상태를 복원.
		
		쿠키의 속성
		1. name/value(required) : value 에 특수 문자가 포함된 경우, encoding/decoding 과정이 필요함.
		2. domain(host) : 다음번 요청을 통해 재전송 할 지 여부를 결정함(생성할 때 도메인이 기본값).
				ex) .naver.com : naver 가 소유한 모든 호스트를 대상으로 재전송.
		3. path : 다음번 요청을 통해 재전송 할 지 여부를 결정함(생성할 때 경로가 기본값).
		4. max-age : 쿠키 만료 시간 결정(초단위) 
			0 : maxAge 를 제외한 나머지 모든 속성이 동일한 쿠키 삭제.
			-1 : 브라우저가 종료시 쿠키 삭제.
		<%
// 			String koreanValue = URLEncoder.encode("한글 쿠키 값", "utf-8");
// 			Cookie koreanCookie = new Cookie("koreanCookie",koreanValue);
// 			response.addCookie(koreanCookie);

			String samePathValue = URLEncoder.encode("동일 경로에서만 확인 가능한 쿠키", "utf-8");
			Cookie samePathCookie = new Cookie("samePathCookie", samePathValue);
			samePathCookie.setMaxAge(0);
			response.addCookie(samePathCookie);
			
// 			String allPathValue = URLEncoder.encode("모든 경로에서 확인 가능한 쿠키", "utf-8");
// 			Cookie allPathCookie = new Cookie("allPathCookie", allPathValue);
// 			allPathCookie.setPath(request.getContextPath()+"/10");
// 			response.addCookie(allPathCookie);

			Cookie longLiveCookie = new Cookie("loneLiveCookie", "Long~Live~");
			longLiveCookie.setMaxAge(7*24*60*60);
			response.addCookie(longLiveCookie);
		%>
		<a href="cookieView.jsp">동일경로</a>
		<a href="<%=request.getContextPath() %>/11/cookieView.jsp">다른경로</a>
		<%--
			Cookie[] cookies = request.getCookies();
			Cookie findedCookie = null;
			if(cookies != null){
				for(Cookie tmp : cookies){
					if("koreanCookie".equals(tmp.getName())){
						findedCookie = tmp;
						break;
					}
				}
			}
			
			if(findedCookie!=null){
				String findedValue = URLDecoder.decode(findedCookie.getValue(), "utf-8");
				out.println(String.format("찾은 쿠기의 값 : %s", findedValue));
			}
		--%>
	</pre>
</body>
</html>