<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
	th{
		text-align: center;
	}
	td{
		padding-bottom: 2px;
	}
</style>
<table>
<form:form enctype="multipart/form-data" modelAttribute="prod">
		<form:hidden path="prodId"/>
		<tr>
			<th>상품명</th>
			<td>
				<form:input path="prodName" class="form-control" required="true" />
				<form:errors path="prodName" element="span" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td>
				<form:select path="prodLgu" items="${lprodList }" itemLabel="lprodNm" itemValue="lprodGu" class="form-select" required="true"/>
				<form:errors path="prodLgu" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th>제조사</th>
			<td>
				<form:select path="prodBuyer" class="form-select" >
					<option value>제조사</option>
					<c:forEach items="${buyerList }" var="buyer">
						<form:option value="${buyer.buyerId }" label="${buyer.buyerName }" class="${buyer.buyerLgu}" />
					</c:forEach>
				</form:select>
				<form:errors path="prodBuyer" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><form:input path="prodCost" type="number"
					class="form-control" required="true" /><form:errors path="prodCost" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><form:input path="prodPrice" type="number"
					class="form-control" required="true" /><form:errors path="prodPrice" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><form:input path="prodSale" type="number"
					class="form-control" required="true" /><form:errors path="prodSale" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td><form:input path="prodOutline" class="form-control" required="true" /><form:errors path="prodOutline" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><form:input path="prodDetail" class="form-control" /><form:errors path="prodDetail" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<input name="prodImage" id="prodImage" type="file" class="form-control" />
				<form:errors path="prodImage" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td><form:input path="prodTotalstock" type="number"
					class="form-control" required="true" /><form:errors path="prodTotalstock" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><form:input path="prodInsdate" type="date"
					class="form-control" /><form:errors path="prodInsdate" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><form:input path="prodProperstock" type="number"
					class="form-control" required="true" /><form:errors path="prodProperstock" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><form:input path="prodSize" class="form-control" />
			<form:errors path="prodSize" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><form:input path="prodColor" class="form-control" /><form:errors path="prodColor" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><form:input path="prodDelivery" class="form-control" />
			<form:errors path="prodDelivery" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><form:input path="prodUnit" class="form-control" /><form:errors path="prodUnit" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><form:input path="prodQtyin" type="number"
					class="form-control" /><form:errors path="prodQtyin" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><form:input path="prodQtysale" type="number"
					class="form-control" /><form:errors path="prodQtysale" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><form:input path="prodMileage" type="number"
					class="form-control" /><form:errors path="prodMileage" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="row g-2 d-flex justify-content-center mt-1">
					<div class="col-auto">
						<button type="submit" class="btn btn-primary">저장</button>
					</div>
					<div class="col-auto">
						<button type="reset" class="btn btn-warning">취소</button>
					</div>
					<div class="col-auto">	
						<a class="btn btn-secondary" href="<c:url value='/prod/prodList.do'/>">목록으로</a>
					</div>		
				</div>
			</td>
		</tr>
</form:form>
</table>

<script>
let $prodBuyer = $("select[name=prodBuyer]");
$("select[name=prodLgu]").on("change", function(event){
	let lgu = $(this).val();
	let $options = $prodBuyer.find("option");
	$options.hide();
	$options.filter((i,e)=>i==0).show();
	if(lgu){
		$options.filter(`.\${lgu}`).show();
	}else{
		$options.show();
	}
}).change();

</script>