/**
 * 
 */
$(function(){
	let makeTrTag = (memoVO)=>{
		let trTag = `<tr data-code="${memoVO.code}">
			<td>${memoVO.writer}</td>
			<td>${memoVO.wrdate}</td>
			<td>${memoVO.content}</td>
			<td><button type="button" data-bs-toggle="modal" data-bs-target="#updateModal">수정</button></td>
			<td><button type="button" class="delBtn">삭제</button></td>
		</tr>
		`;
		return trTag;
	}
	const baseUrl = "/memo";
	$.getJSON(baseUrl)
		.done((resp)=>{
			let list =resp.memoList;
			code = "";
			if(list.length>0){
				$.each(list,function(i,v){
					code += makeTrTag(v);
				})
			}else{
				code ="<tr id='no-memo'><td colspan='3'>작성된 메모가 없습니다</td></tr>";
			}
			$(tbodyArea).html(code);
		});
	
	$(tbodyArea).on('click','.delBtn',function(event){
		if(!confirm('정말 삭제 하시겠습니까?')){
			return false;
		}
		let email = prompt('메모에 입력했던 이메일을 입력해주세요');
		
		let btn = event.target;
		let trTag = $(btn).parents('tr');
		let code = $(trTag).data('code');
		
		let method = "delete";
		let url = `${baseUrl}/${code};${email}`;
		let settings = {
			url : url,
			method : method,
			dataType : "json"
		};
		
		$.ajax(settings)
			.done((resp)=>{
				if(resp.success){
					alert('삭제 성공!!!!');
					trTag.remove();
					let inText = tbodyArea.innerText;
					if(inText==""){
						code ="<tr id='no-memo'><td colspan='3'>작성된 메모가 없습니다</td></tr>";
						$(tbodyArea).html(code);
					}
				}else{
					alert('삭제 실패!');
				}
			})
			.fail((xhr, textStatus, errorThrown)=>{
				if(xhr.satus==400){
					alert('메모가 없습니다!');
				}else if(xhr.status == 401){
					alert('메모할때 입력한 이메일과 다릅니다!');
				}
			});
		
	});
	
	
	
	$(addbtn).on('click',function(){
		let data = $(memoInsert).serializeJSON();
		let json = JSON.stringify(data);
		let method = "post";
		let settings = {
			url : baseUrl,
			method : method,
			data : json,
			contentType : "application/json;charset=utf-8",
			dataType : "json"
		};
		
		$.ajax(settings)
			.done((resp)=>{
				if(resp.success){ // 등록 성공시
					let v = resp.originData;
					let code = makeTrTag(v);
					if($('#no-memo').find('td')){
						$(tbodyArea).html("");
					}
					$(tbodyArea).prepend(code);
					$('#insertModal').modal('hide');
				}else{
					let code = `등록 실패!\r\n
						${resp.errors}
					`;
					alert(code);
				}
			});
	});
	
	$(modbtn).on('click',function(){
		let data = $(memoUpdate).serializeJSON();
		let json = JSON.stringify(data);
		let method = "put";
		let settings = {
			url : baseUrl,
			method : method,
			data : json,
			contentType : "application/json;charset=utf-8",
			dataType : "json"
		};
		
		$.ajax(settings)
			.done((resp)=>{
				if(resp.success){
					alert('수정 성공!');
					let tds = $modTargetTrTag.find('td');
					let v = resp.changeData;
					$(tds[0]).text(v.writer);
					$(tds[1]).text(v.wrdate);
					$(tds[2]).text(v.content);
					$('#updateModal').modal('hide');
				}else{
					alert(`${resp.message}`);
				}
			})
			.fail((xhr, textStatus, errorThrown)=>{
				if(xhr.satus==401){
					alert('메모할때 입력한 이메일과 다릅니다!');
				}
			});
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 메모추가 Modal Open시
	$('#insertModal').on('shown.bs.modal', function (e) {
	})
	// 메모추가 Modal Close시
	$('#insertModal').on('hidden.bs.modal', function (e) {
		memoInsert.reset();
	})
	
	let $modTargetTrTag;
	
	// 메모수정 Modal Open시
	$('#updateModal').on('shown.bs.modal', function (e) {
		let calledBtn = e.relatedTarget;
		let $trTag = $(calledBtn).parents('tr');
		$modTargetTrTag = $trTag;
		let tds = $trTag.find('td');
		$(updatewriter).val($(tds[0]).text());
		$(memocode).val($trTag.data('code'));
		$(updatecontent).val($(tds[2]).text());
	})
	// 메모수정 Modal Close시
	$('#updateModal').on('hidden.bs.modal', function (e) {
		memoUpdate.reset();
		$modTargetTrTag = null;
	})
});