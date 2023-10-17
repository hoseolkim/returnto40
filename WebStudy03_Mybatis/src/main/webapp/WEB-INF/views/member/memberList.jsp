<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<table class="table table-bordered">
	<thead>
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
		<c:set value="${paging.dataList }" var="memberList" />
		<c:if test="${empty memberList }">
			<tr>
				<td colspan="5">검색 조건에 맞는 회원 없음.</td>
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
				<div id="searchUI">
					<select name="searchType">
						<option value>전체</option>
						<option value="name">이름</option>
						<option value="major">지역</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="button" value="검색" id="searchBtn" />
				</div>
			</td>
		</tr>
	</tfoot>
</table>

<form id="searchForm">
	<input type="text" name="searchType" />
	<input type="text" name="searchWord" />
	<input type="text" name="page" />
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
<!-- 내가 작성한 것 -->
<div class="modal fade" id="memberDetailModal" tabindex="-1" aria-labelledby="memberModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="memberModal">회원 상세정보</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="memberDetailArea">
           
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
<script>
	$(':input[name=searchType]').val("${paging.simpleCondition.searchType}");
	$(':input[name=searchWord]').val("${paging.simpleCondition.searchWord}");
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
	
	
	

	// Event-Driven-Development
	$(exampleModal).on('show.bs.modal',function(event){
		let $modal = $(this);
		let trTag = event.relatedTarget;
		let who = $(trTag).data('memId');
// 		location.href = "${pageContext.request.contextPath}/member/memberView.do?who="+who;
		let url = "${pageContext.request.contextPath}/member/memberView.do?who=" + who ;
		$.get(url)
			.done(function(resp){
				$modal.find('.modal-body').html(resp);
			})
	}).on('hidden.bs.modal',function(event){
		$(this).find('.modal-body').empty();
	});
	
 </script>

<%-- <script src="${pageContext.request.contextPath }/resources/js/app/member/member.js"></script> --%>