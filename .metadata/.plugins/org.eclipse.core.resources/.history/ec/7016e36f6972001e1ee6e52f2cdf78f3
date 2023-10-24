/**
 * 
 */
$(function(){
	let settings = {
		dataType:"json",
		success:function(resp){
			let dataList = resp.dataList;
			let trTags = "";
			if(dataList.length > 0){
				$.each(dataList, function(idx, vo){
					trTags += `
						<tr>
							<td>${vo.propertyName}</td>
							<td>${vo.propertyValue}</td>
							<td>${vo.description}</td>
						</tr>
					`;
				});
			}else{
				trTags += "<tr><td colspan='3'>조회 결과 없음.</td></tr>";
			}
			$(listBody).html(trTags);
		}
	};
	$.ajax(settings);
});