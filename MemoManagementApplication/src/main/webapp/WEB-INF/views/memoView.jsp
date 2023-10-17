<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>메모 목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
   integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
       crossorigin="anonymous">
<jsp:include page="/includee/preScript.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
       crossorigin="anonymous"></script>
</head>
<body>
<p><button type="button" data-bs-toggle="modal" data-bs-target="#insertModal">메모 추가</button></p>
	<table>
		<thead>
			<tr>
				<th>작성자</th>
				<th>작성일</th>
				<th colspan="3">내용</th>
			</tr>
		</thead>
		<tbody id="tbodyArea">
		</tbody>
	</table>
	
	
<!-- 메모 추가용 모달Modal -->
<div class="modal fade" id="insertModal" tabindex="-1" aria-labelledby="insertModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="insertModalLabel">메모추가</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        	<form id="memoInsert">
				작성자<input type="text" id="insertwriter" name="writer" placeholder="작성자" /><br>
				이메일<input type="email" id="insertemail" name="email" placeholder="이메일" /><br>
				내용<input type="text" id="insertcontent" name="content" placeholder="메모내용" /><br>
        	</form>
      </div>
      <div class="modal-footer">
        <button type="button" id="addbtn" class="btn btn-primary">추가하기</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<!-- 수정용 모달Modal -->
<div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="updateModalLabel">메모수정</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        	<form id="memoUpdate">
				<input type="hidden" id="memocode" name="code" value="" /><br>
				작성자<input type="text" id="updatewriter" readonly name="writer" value="" /><br>
				이메일확인<input type="email" id="updateemail" name="email" required placeholder="이메일확인" /><br>
				내용<input type="text" id="updatecontent" name="content" placeholder="내용입력" /><br>
        	</form>
      </div>
      <div class="modal-footer">
        <button type="button" id="modbtn" class="btn btn-primary">수정하기</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

</body>
<script src="<%=request.getContextPath() %>/resources/js/app/memo.js"></script>
</html>