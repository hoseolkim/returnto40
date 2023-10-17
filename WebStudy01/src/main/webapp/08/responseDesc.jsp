<%@ page language="java" 
    pageEncoding="UTF-8"%>
<%--
	response.setContentType("text/plain; charset=utf-8");
	response.setContentLengthLong(100);
--%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>08/responseDesc.jsp</title>
<style>
	.중요 {
		color : red;
		background-color: black;
	}
</style>
</head>
<body>
	<h4>HttpServletResponse</h4>
	<pre>
		1. Response Line : Status Code(응답 상태 코드) - response.sendError(sc[, message]) , setStatus(sc)
			Status Code : 서버에서 요청 처리 결과의 성공 여부를 표현하는 상태 코드
					Http : connectLess + stateLess
				100~ : ING... WebSocket(connectFull)
				200~ : OK
				300~ : 요청 처리가 최종적으로 완료되려면, 클라이언트로부터 추가 액션이 필요함. response body 가 없음.
					302, 307(Moved ) + Location(자원의 새로운 주소) 헤더 사용
					304(Not Moidified) : 일반적으로 브라우저는 정적 자원에 대해 캐싱을 해서 사용함.
						한 번 캐싱된 자원이 변경된 적 없으므로, 캐시 자원을 그대로 사용하라는 표현.
					<%// HttpServletResponse.SC_ %>
				
				400~ : 처리 실패의 원인이 클라이언트측에 있을 때
					404 : (Not Found) 클라이언트의 요청 url이 잘못되었는지, 컨트롤러에서 view를 선택할때 경로가 잘못되었는지 봐야함.
					405 : (Method Not Allowed) 요청한 method의 방식을 오버라이드 하지 않았을 때
					400 : (Bad Request, 요청 검증에 주로 활용됨.)
					
					-- 어플리케이션 보호를 위한 <span class="중요">접근</span> 제어에서 활요됨.
					401 : (UnAthorized) 보호되어있는 자원이라 인증이 필요합니다
						신원 확인.. 인증
					403 : (Forbidden) 보호되어있는 자원이라 이용이 불가합니다
						권한 확인.. 인가
					
					406 : (Not Acceptable) 클라이언트가 요청한 Mime content 를 전송할 수 없음.
							request header(Accept), response header(Content-Type)
					415 : (Unsupported Media Type == UnSupported Mime Type) 클라이언트가 전송한 content 를 판독할 수 없음.
							request header(Content-Type)
					
				500~ : 처리 실패의 원인이 서버에 있을 때 500(Internal Server Error)
				
		2. Response Header - response.set[Int|Date]Header(name, value), addHeader()...
			1) Content-* : Content-Type, Content-Length - response body 컨텐츠를 수식해줌.
			2) Cache 제어 : Cache-Control(Http 1.1버전 / 캐싱하지마!라고 할수 있음), Expires (캐시데이터의 만료시한을 정할 수 있음), Pragma(Http 버전 1.0)
							Cache-Control과 Pragma를 둘다 사용해야 1.1버전과 1.0버전에 동시에 대응할 수 있음. -> 웹 표준화전략
			<%
				response.setHeader("Cache-Control", "no-cache");
				response.addHeader("Cache-Control", "no-store");
				response.setHeader("Pragma", "no-cache");
				response.addHeader("Pragma", "no-store");
				response.setDateHeader("Expires", 0);
				
			%>
			3) Auto Request : Refresh(정해진 주기마다 갱신을 요청해야할 때 사용)
				<a href="<%=request.getContextPath() %>/08/autoRequest.jsp">auto request</a>
			4) Redirection : Location
				
		3. Respnose Body (Content Body, Message Body)
			response.getWriter() : char 기반의 문자 컨텐츠를 기록할 출력 스크림 
			response.getOutputStream() : byte 기반의 스트림(binary) 컨텐츠를 기록할 출력 스트림
	</pre>
</body>
</html>





















