<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
</c:if>
<h4>가입(수정) 양식</h4>
<form:form modelAttribute="member" enctype="multipart/form-data">
	<table>
		
		<tr>
			<th><label for="memId"><spring:message code="member.memId" /></label></th>
			<td><form:input type="text" path="memId" class="form-control"
					 />
				<form:errors path="memId" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th><label for="memPass"><spring:message code="member.memPass" /></label></th>
			<td><form:input type="text" path="memPass" class="form-control"
					 />
				<form:errors path="memPass" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memImage"><spring:message code="member.memImg" /></label></th>
			<td><form:input type="file" path="memImage" class="form-control" />
				<form:errors path="memImage" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memName"><spring:message code="member.memName" /></label></th>
			<td><form:input type="text" path="memName" class="form-control"
					 />
				<form:errors path="memName" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memRegno1"><spring:message code="member.memRegno1" /></label></th>
			<td><form:input type="text" path="memRegno1"
					class="form-control" />
				<form:errors path="memRegno1" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memRegno2"><spring:message code="member.memRegno2" /></label></th>
			<td><form:input type="text" path="memRegno2"
					class="form-control" />
				<form:errors path="memRegno2" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memBir"><spring:message code="member.memBir" /></label></th>
			<td><form:input type="date" path="memBir" class="form-control" />
				<form:errors path="memBir" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memZip"><spring:message code="member.memZip" /></label></th>
			<td><form:input type="text" path="memZip" class="form-control"
					 />
				<form:errors path="memZip" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memAdd1"><spring:message code="member.memAdd1" /></label></th>
			<td><form:input type="text" path="memAdd1" class="form-control"
					 />
				<form:errors path="memAdd1" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memAdd2"><spring:message code="member.memAdd2" /></label></th>
			<td><form:input type="text" path="memAdd2" class="form-control"
					 />
				<form:errors path="memAdd2" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memHometel"><spring:message code="member.memHometel" /></label></th>
			<td><form:input type="text" path="memHometel"
					class="form-control" />
				<form:errors path="memHometel" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memComtel"><spring:message code="member.memComtel" /></label></th>
			<td><form:input type="text" path="memComtel"
					class="form-control" />
				<form:errors path="memComtel" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memHp"><spring:message code="member.memHp" /></label></th>
			<td><form:input type="text" path="memHp" class="form-control" />
				<form:errors path="memHp" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memMail"><spring:message code="member.memMail" /></label></th>
			<td><form:input type="text" path="memMail" class="form-control"
					 />
				<form:errors path="memMail" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memJob"><spring:message code="member.memJob" /></label></th>
			<td><form:input type="text" path="memJob" class="form-control" />
				<form:errors path="memJob" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memLike"><spring:message code="member.memLike" /></label></th>
			<td><form:input type="text" path="memLike" class="form-control" />
				<form:errors path="memLike" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memMemorial"><spring:message code="member.memMemorial" /></label></th>
			<td><form:input type="text" path="memMemorial"
					class="form-control" />
				<form:errors path="memMemorial" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th><label for="memMemorialday"><spring:message code="member.memMemorialday" /></label></th>
			<td><form:input type="date" path="memMemorialday"
					class="form-control" />
				<form:errors path="memMemorialday" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="전송" class="btn btn-primary" />
				<input type="reset" value="취소" class="btn btn-warning" />	
			</td>
		</tr>
		
	</table>
</form:form>
















