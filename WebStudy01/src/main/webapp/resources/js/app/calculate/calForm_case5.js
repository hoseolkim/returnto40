/**
 * 
 */
$(function(){ // $(document).on("load|ready",function(){});
	let selectValue = $(calForm.operator).data("initValue");
	$(calForm.operator).val(selectValue);
	
	$(calForm).on('submit',function (event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize(); // query String
		let settings = {
			url : url,
			method : method,
			data : data,
			dataType : "json", // Accept Request Header : Content-Type Response Header
			success : function(resp) {
				let expr;
				if(resp.calVO){
					expr = resp.calVO.expression;
				}else{
					expr = JSON.stringify( resp.errors );
				}
				$(resultArea).html(expr)
			},
			error : function(jqXhr, status, error) {
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("error : ", error);
			}
		};
		$.ajax(settings);
		return false;
	});
})