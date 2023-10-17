<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<table class="table table-bordered">
	<thead class="table-light">
		<tr>
			<th>일련번호</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>생일</th>
			<th>거주지역</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="memberList" value="${paging.dataList }" />
		<c:if test="${empty memberList }">
			<tr>
				<td colspan="7">검색 조건에 맞는 회원 없음.</td>
			</tr>
		</c:if>
		<c:if test="${not empty memberList }">
			<c:forEach items="${memberList }" var="member">
				<tr data-mem-id="${member.memId}" data-bs-toggle="modal" data-bs-target="#exampleModal">
					<td>${member.rnum }</td>
					<td>${member.memName }[${member.prodCount }]</td>
					<td>${member.memHp }</td>
					<td>${member.memMail }</td>
					<td>${member.memBir }</td>
					<td>${member.memAdd1 }</td>
					<td>${member.memMileage }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				${paging.pagingHTML }
				<div id="searchUI"  class="row g-3 d-flex justify-content-center">
					<div class="col-auto">
						<select name="searchType" class="form-select">
							<option value>전체</option>
							<option value="name">이름</option>
							<option value="address">지역</option>
						</select>
					</div>
					<div class="col-auto">
						<input type="text" name="searchWord" class="form-control" placeholder="검색키워드"/>
					</div>
					<div class="col-auto">
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
					</div>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm" class="border">
	<h4>전송 UI</h4>
	<input type="text" name="searchType" readonly="readonly" placeholder="searchType"/>
	<input type="text" name="searchWord" readonly="readonly" placeholder="searchWord"/>
	<input type="text" name="page" readonly="readonly" placeholder="page"/>
</form>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<script>
	$(":input[name=searchType]").val("${simpleCondition.searchType}");
	$(":input[name=searchWord]").val("${simpleCondition.searchWord}");
	function fn_paging(page){
		searchForm.page.value = page;
		searchForm.requestSubmit();
	}
	$(searchUI).on("click", "#searchBtn", function(event){
		let inputs = $(this).parents("#searchUI").find(":input[name]");
		$.each(inputs, function(idx, ipt){
			let name = ipt.name;
			let value = $(ipt).val();
			$(searchForm).find(`:input[name=\${name}]`).val(value);
			$(searchForm).submit();
		});
	});
	// EDD(Event-Driven-Development)
	$(exampleModal).on("show.bs.modal", function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let who = $(trTag).data("memId");
// 		location.href="${pageContext.request.contextPath}/member/memberView.do?who="+who;
		let url = "${pageContext.request.contextPath}/member/memberView.do?who="+who;
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);
			});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
</script>


















