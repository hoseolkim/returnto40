<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script src="<c:url value='/resources/js/ckeditor/ckeditor.js'/>"></script>
<table class="table table-bordered">
	<form:form modelAttribute="board" enctype="multipart/form-data" >
		<input type="hidden" name="_method" value="put" readonly/>
		<form:input type="hidden" path="boNo" class="form-control" readonly="true" />
		<tr>
			<th><label for="boTitle">제목</label></th>
			<td><form:input type="text" path="boTitle" class="form-control"
					required="true" />
				<form:errors path="boTitle" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="boWriter">작성자</label></th>
			<td><form:input type="text" path="boWriter" class="form-control"
					required="true" />
				<form:errors path="boWriter" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="boIp">IP</label></th>
			<td><input type="text" name="boIp" class="form-control" value="${pageContext.request.remoteAddr }" readonly />
				<form:errors path="boIp" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="boMail">이메일</label></th>
			<td><form:input type="text" path="boMail" class="form-control" />
				<form:errors path="boMail" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="boPass">비번 확인</label></th>
			<td><input type="password" id="boPass" name="boPass" class="form-control"/>
				<form:errors path="boPass" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<input type="file" name="boFile">
				<input type="file" name="boFile">
				<input type="file" name="boFile">
			</td>
		</tr>
		<tr>
			<th><label for="boContent">내용</label></th>
			<td>
				<form:textarea path="boContent"/>
				<form:errors path="boContent" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" class="btn btn-primary" />
				<input type="reset" value="취소" class="btn" />
				<a href="<c:url value='/board/${boNo }'/>" class="btn btn-success">뒤로 가기</a>
			</td>
		</tr>
	</form:form>
</table>

<script>
	CKEDITOR.replace('boContent', {
		filebrowserImageUploadUrl : "<c:url value='/board/image?type=image'/>"
	});
</script>