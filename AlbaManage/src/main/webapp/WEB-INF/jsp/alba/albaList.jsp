<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
	<c:set value="${paging.dataList }" var="dataList" />
	<thead>
		<tr>
			<th>알바생 이름</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty dataList }">
			<tr>
				<td>알바생 목록이 없습니다</td>
			</tr>
		</c:if>
		<c:if test="${not empty dataList }">
			<c:forEach items="${dataList }" var="alba">
				<tr>
					<td>${alba.alName }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td>${paging.pagingHTML }</td>
		</tr>
	</tfoot>
</table>