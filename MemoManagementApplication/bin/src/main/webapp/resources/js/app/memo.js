/**
 * 
 */
$(function(){
	const baseUrl = "/memo";
	$.getJSON(baseUrl)
		.done((resp)=>{
			let list =resp.memoList;
			code = "";
			if(list.length>0){
				$.each(list,function(i,v){
					code += `
						<tr data-code="${v.code}">
							<td>${v.writer}</td>
							<td>${v.wrdate}</td>
							<td>${v.content}</td>
							<td><button type="button" class="modBtn">수정</button></td>
							<td><button type="button" class="delBtn">삭제</button></td>
						</tr>
					`;
				})
			}else{
				code ="<tr id='no-memo'><td colspan='3'>작성된 메모가 없습니다</td></tr>";
			}
			$(tbodyArea).html(code);
		});
	
	
	
	
	
	
});