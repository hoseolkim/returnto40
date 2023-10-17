<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<a href="<c:url value='/prod/prodInsert.do' />" class="btn btn-primary">상품 추가</a>
<table class="table table-bordered">
	<thead class="table-dark">
		<tr>
			<th>일련번호</th>
			<th>상품명</th>
			<th>상품분류명</th>
			<th>제조사명</th>
			<th>판매가</th>
			<th>세일가</th>
			<th>마일리지</th>
			<th>구매자수</th>
		</tr>
	</thead>
	<c:set value="${paging.dataList }" var="prodList" />
	
	<tbody>
		<c:if test="${not empty prodList }">
			<c:forEach items="${prodList }" var="prod">
				<c:url value="/prod/prodView.do" var="prodViewURL">
						<c:param name="what" value="${prod.prodId }" />
				</c:url>
				<tr>
					<td>${prod.rnum }</td>
					<td><a href="${prodViewURL }">${prod.prodName }</a></td>
					<td>${prod.lprod.lprodNm }</td>
					<td>${prod.buyer.buyerName }</td>
					<td>${prod.prodPrice }</td>
					<td>${prod.prodSale }</td>
					<td>${prod.prodMileage }</td>
					<td>${prod.buyerCnt }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty prodList }">
			<tr>
				<td colspan="8">
					상품 목록 없음..
				</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="8" id="paginationArea">
					${paging.pagingHTML }
				<div id="searchUI" class="text-center">
					<select name="prodLgu">
						<option value>상품분류</option>
						<c:if test="${not empty lprodList }">
							<c:forEach items="${lprodList }" var="lprod">
								<option value="${lprod.lprodGu }" label="${lprod.lprodNm }" />
							</c:forEach>
						</c:if>
					</select>
					<select name="prodBuyer">
						<option value>제조사</option>
						<c:if test="${not empty buyerList }">
							<c:forEach items="${buyerList }" var="buyer">
								<option class="${buyer.buyerLgu }" value="${buyer.buyerId }" label="${buyer.buyerName }" />
							</c:forEach>
						</c:if>
					</select>
					<input type="text" name="prodName" placeholder="상품명" />
					<input type="button" value="검색" id="searchBtn" />
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm">
	<input type="text" name="prodLgu" />
	<input type="text" name="prodBuyer" />
	<input type="text" name="prodName" />
	<input type="text" name="page" />
</form>
<script>
	$("select[name=prodLgu]").on("change",function(event){
		let lgu = $(this).val();
		let $sel = $("select[name=prodBuyer]");
		let options = $sel.find("option");
		$sel.val("");
		$(options).hide();
		
// 		$(options).filter(':first').show();
		$(options).filter((i,e)=>i==0).show();
		
		if(lgu){
			$(options).filter(`.\${lgu}`).show();
		}else{
			$(options).show();
		}
	});
	
	
	$(':input[name=prodLgu]').val("${paging.detailCondition.prodLgu}").trigger("change");
	$(':input[name=prodBuyer]').val("${paging.detailCondition.prodBuyer}");
	$(':input[name=prodName]').val("${paging.detailCondition.prodName}");
	function fn_paging(page){
		searchForm.page.value = page;
		searchForm.requestSubmit();
	}
	$(searchUI).on("click","#searchBtn",function(event){
		let inputs = $(this).parents('#searchUI').find(':input[name]');
		$.each(inputs,function(idx, ipt){
			let name = ipt.name;
			let value = $(ipt).val();
			$(searchForm).find(`:input[name=\${name}]`).val(value);
		});
		
		$(searchForm).submit();
		
	});
</script>