<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
	<thead>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:set value="${paging.dataList }" var="boardList" />
		<c:if test="${empty boardList }">
			<tr>
				<td colspan="5">
					조회된 게시글 없음
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty dataList }">
			<c:forEach items="${dataList }" var="board">
				<tr>
					<td></td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">
				${ paging.paginHTML }
			</td>
		</tr>
	</tfoot>
</table>