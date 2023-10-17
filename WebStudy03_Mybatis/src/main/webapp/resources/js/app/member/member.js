/**
 * 
 */

$(function(){
	let cPath = $(document.body).data('contextPath');
	$('tr[data-mem-id]').on('click',function(){
		let memId = $(this).data('memId');
		$.ajax(`${cPath}/member/memberView.do?who=${memId}`)
			.done((resp)=>{
				$(memberDetailModal).modal('show');
				$(memberDetailArea).html(resp);
			});
		
		
	})
	
})