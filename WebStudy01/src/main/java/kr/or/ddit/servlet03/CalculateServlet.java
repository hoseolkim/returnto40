package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculate.do")
public class CalculateServlet extends HttpServlet{
	
	// 개발 당시 QualifiedName kr.or.ddit.servlet03.CalculateServlet
	
	@FunctionalInterface
    private interface Calculator {
        double calc(int a, int b);
    }
	final String OPERATOR = "operator";
	final String LEFTOP = "leftOp";
	final String RIGHTOP = "rightOp";
	
	enum Operator {
		PLUS("+"),
		MINUS("-"),
		MULTIPLY("*"),
		DIVIDE("/");

		private String oper;
		
		public String getOper() {
			return this.oper;
		}
		
		Operator(String oper){
			this.oper = oper;
		}

	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		String qString = req.getQueryString();
		if(method.equals("GET")&&(qString==null||qString.equals(""))){
			req.getRequestDispatcher("/WEB-INF/views/06/calculateForm.jsp").forward(req, resp);
			return;
		}
		
		boolean valid = true;
		
//		Map<String,String[]> paramMap =req.getParameterMap();
//		
//		if(paramMap.isEmpty()) {
//			valid = false;
//		}
		
		String param1 = req.getParameter(LEFTOP);
		String param2 = req.getParameter(RIGHTOP);
		String param3 = req.getParameter(OPERATOR);
		int num1 = 0;
		int num2 = 0;
		String oper = "";
		
		// 첫 번째 숫자에 대한 검증
		if(param1!=null&&param1.matches("[\\d]+")) {
			num1 = Integer.parseInt(param1);
		}else {
			valid = false;
		}
		
		// 두 번째 숫자에 대한 검증
		if(param2!=null&&param2.matches("[\\d]+")) {
			num2 = Integer.parseInt(param2);
		}else {
			valid = false;
		}
		
		
		// 연산자에 대한 검증
//		Operator[] opers = Operator.values();
//		Stream<Operator> stream1 = Arrays.stream(opers);
		for(Operator op : Operator.values()) {
			if(op.name().equals(param3)) {
				oper = op.getOper();
			}
		}
		if(oper.equals("")) valid = false;
		
		if(!valid) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"파라미터가 잘못되었습니다");
			return;
		}
		
		Calculator c = null;
		
		switch(oper) {
		case "+": c = (a, b) -> 1.0*a+b; break;
		case "-": c = (a, b) -> 1.0*a-b; break;
		case "*": c = (a, b) -> 1.0*a*b; break;
		case "/": c = (a, b) -> 1.0*a/b; break;
		}
		
		double res = c.calc(num1, num2);
		
//		Calculator c = (a, b, o) -> {
//			double r = 0;
//			switch (o) {
//			case "+": r = a+b; break;
//			case "-": r = a-b; break;
//			case "*": r = a*b; break;
//			case "/": r = a/b; break;
//			}
//			return r;
//		};
		String result = "";
		
		if("GET".equals(method)) {
			if(oper.equals("/")) {
				result = String.format("%d %s %d = %f", num1, oper, num2, res);
			}else{
				result = String.format("%d %s %d = %d", num1, oper, num2, (int)res);
			}
			// 페이지자체를 없애버리고 출력문만 남기는 처리방식
			/*
			try(PrintWriter out = resp.getWriter(); ){
				out.println(result);
			}
			*/
			// 페이지는 같은페이지를 출력해주고 setAttribute로 넘기는 방식
			req.setAttribute("result", result);
			req.getRequestDispatcher("/WEB-INF/views/06/calculateForm.jsp").forward(req, resp);
			return;
		}else if("POST".equals(method)){
			String type = req.getParameter("parsetype").toLowerCase();
			StringBuffer sb = null;
			switch (type) {
			case "json":
				resp.setContentType("application/json; charset=utf-8");
				if(oper.equals("/")) {
					result = String.format("{\"num1\":\"%d\", \"num2\":\"%d\",\"oper\":\"%s\",\"result\":\"%f\"}",num1,num2,oper,res);
				}else {
					result = String.format("{\"num1\":\"%d\", \"num2\":\"%d\",\"oper\":\"%s\",\"result\":\"%d\"}",num1,num2,oper,(int)res);
				}
				break;
			case "xml":
				resp.setContentType("text/xml; charset=utf-8");
				sb = new StringBuffer();
				sb.append("<datas>");
				sb.append(String.format("<num1>%d</num1>",num1));
				sb.append(String.format("<num2>%d</num2>",num2));
				sb.append(String.format("<oper>%s</oper>",oper));
				if(oper.equals("/")) {
					sb.append(String.format("<result>%f</result>",res));
				}else {
					sb.append(String.format("<result>%d</result>",(int)res));
				}
				sb.append("</datas>");
				result = sb.toString();
				break;
			case "html":
				resp.setContentType("text/html; charset=utf-8");
				sb = new StringBuffer();
				// ajax datatype html의 차이를 알기위해 html요소를 작성해서 건내준다.
				// div안에 출력해주니까 html오픈없이 바로 요소를 만든다
				sb.append("<table><thead><tr>");
				sb.append("<th>피연산자1</th>");
				sb.append("<th>연산자</th>");
				sb.append("<th>피연산자2</th>");
				sb.append("<th>등호</th>");
				sb.append("<th>결과값</th>");
				sb.append("</tr></thead><tbody><tr>");
				sb.append(String.format("<td>%d</td>",num1));
				sb.append(String.format("<td>%s</td>",oper));
				sb.append(String.format("<td>%d</td>",num2));
				sb.append("<td>=</td>");
				if(oper.equals("/")) {
					sb.append(String.format("<td>%f</td>",res));
				}else {
					sb.append(String.format("<td>%d</td>",(int)res));
				}
				sb.append("</tr></tbody></table>");
				result = sb.toString();
				break;
			}// switchcase 끝부분
			
			try(PrintWriter out = resp.getWriter()){
				out.println(result);
			}
		} // 메소드 분기점 if 끝부분
		
	}// service 메소드 끝부분
}
