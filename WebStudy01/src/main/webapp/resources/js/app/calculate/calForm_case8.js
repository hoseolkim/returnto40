/**
 * 
 */
$(function(){ // $(document).on("load|ready",function(){});
	const successes = {
		json : function(resp){
			$(resultArea).html(resp.calVO.expression);
		},
		html : function(resp){
			$(resultArea).html(resp);
		}
	};
	let selectValue = $(calForm.operator).data("initValue");
	$(calForm.operator).val(selectValue);
	$(calForm).on('submit',function (event){
//		let requestContentType = $('input[name=contentType]:checked').data('contentType');
		event.preventDefault();
		let contentType = $('[name=contentType]:checked').data('contentType');
//		if(!(dataType=="html"||dataType=="json")){
//			$(resultArea).html("데이터 타입 파라미터 오류입니다.");
//			return false;
//		}
		
		let dataType = $('[name=accept]:checked').val() ?? "json";
		let url = this.action;
		let method = this.method;
		let settings = {
			url : url,
			method : method,
//			success : successes[dataType],
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
//		settings.dataType = dataType ? dataType : "json";
		// 널 병합 연산자
		settings.dataType = dataType;
		settings["success"] = successes[dataType];
		
		$.ajax(settings);
		return false;
	});
})