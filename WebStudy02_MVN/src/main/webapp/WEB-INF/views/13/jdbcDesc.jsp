<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/jdbcDesc.jsp</title>
<style>
	table {
		border-collapse: collapse;
	}
	th, td {
		border : 1px solid black;
	}
</style>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	1. 드라이버를 빌드패스에 추가
	2. 드라이버(클래스) 로딩
	3. Connection 생성
	4. 쿼리 객체 생성
		- Statement : 쿼리 객체 생성시 쿼리가 고정되지 않기 때문에, runtime 에 동적 쿼리 실행이 가능.
		- PreparedStatement(선컴파일된 쿼리 객체) : 쿼리를 미리 컴파일하고 쿼리 객체를 생성함.
										runtime 에 쿼리에 사용되는 literal(값)을 변경하여 쿼리를 재사용함. 
		- CallableStatement : 절차적 코드집합인 function/procedure 를 호출할 때 사용함.
	5. 쿼리 실행
	6. 결과 집합 핸들링(select..)
	7. close(***) - try with resoure 구문 활용
</pre>
<%
	
		List<DataBasePropertyVO> list = (List<DataBasePropertyVO>) request.getAttribute("list");

%>
<table>
	<thead>
		<tr>
			<th>PROPERTY_NAME</th>
			<th>PROPERTY_VALUE</th>
			<th>DESCRIPTION</th>
		</tr>
	</thead>
	<tbody>
		<%
			if(list.isEmpty()){
				%>
				<tr>
					<td colspan="3">조회 결과 없음.</td>
				</tr>
		<%
			}else{
				for(DataBasePropertyVO vo : list){
		%>
					<tr>
						<td><%=vo.getPropertyName() %></td>
						<td><%=vo.getPropertyValue() %></td>
						<td><%=vo.getDescription() %></td>
					</tr>
		<%
				}
			}
		%>

	</tbody>
</table>
</body>
</html>