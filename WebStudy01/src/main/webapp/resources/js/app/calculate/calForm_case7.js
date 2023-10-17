/**
 * 
 */
$(function(){ // $(document).on("load|ready",function(){});
	let selectValue = $(calForm.operator).data("initValue");
	$(calForm.operator).val(selectValue);
	$(calForm).on('submit',function (event){
//		let requestContentType = $('input[name=contentType]:checked').data('contentType');
		event.preventDefault();
		let contentType = $('[name=contentType]:checked').data('contentType');
		
		let url = this.action;
		let method = this.method;
		let settings = {
			url : url,
			method : method,
			dataType : "json", // Accept Request Header : Content-Type Response Header
			success : function(resp) {
				$(resultArea).html(resp.calVO.expression)
			},
			error : function(jqXhr, status, error) {
				if(jqXhr.status==400){
					$(resultArea).html("파라미터에 문제가 있어요");
				}else if(jqXhr.status==415){
					$(resultArea).html("처리 할 수 없는 컨텐츠 타입이 전송됨");
				}else{
					$(resultArea).html("어디선가 오류 났는데 어디서 났는지 모름");
				}
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("error : ", error);
			}
		};
		
		if(contentType && contentType.indexOf('json')>=0){
			let data = $(this).serializeJSON(); // js object
			let json = JSON.stringify(data);
			settings.data = json;
			settings.contentType = contentType;
		}else{
			let formData = $(this).serialize(); // query String
			settings.data = formData;  
		}
		$.ajax(settings);
		return false;
	});
})