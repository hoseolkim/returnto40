/**
 * 
 */
$(function(){
	let settings = {
		dataType : "json",
		success : function(resp){
			let result = "";
			if(resp.list.length>0){
				$.each(resp.list,function(i,v){
					result += `
					<tr>
						<td>${v.propertyName}</td>
						<td>${v.propertyValue}</td>
						<td>${v.description}</td>
					</tr>
					`;
				})
			}else{
				result += "<tr><td colspan='3'>데이터가 없음..</td></tr>";
			}
			$('tbody').html(result);
		},
		error : function(jqXhr,status,error){
			console.log("jqXhr : ", jqXhr);
			console.log("status : ", status);
			console.log("error : ", error);
		}
	}
	
	
	$.ajax(settings);
});