/**
 * 
 */

$(function(){
	const cPath = $('body').data('contextPath');
	const baseUrl = `${cPath}/adrs/address`;
	
//	$.ajax(settings);
	$.getJSON(baseUrl,selectFunction);
	$(adrsForm).on('submit',function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serializeJSON();
		let json = JSON.stringify(data);
		
		
		let settings = {
			url : baseUrl,
			method : method,
			data : json,
			headers : {
				"Content-Type" : "application/json;charset=utf-8"
			},
			dataType : "json"
		};
		
		$.ajax(settings)
			.done(function(resp){
				if(resp.success){
					let trTag = makeTrTag(resp.originalData);
					$(listBody).prepend(trTag);
					adrsForm.reset();
				}else{
					alert(resp.message);
				}
			})
		return false;
	});
	
	$(listBody).on('click','.delBtn',function(){
		let flag = confirm("진짜 삭제 ?");
		if(!flag){
			return false;
		}
		let adrsTr = $(this).parents('tr:first');
		let $adrsTr = $(adrsTr);
		let adrsNo = $adrsTr.data('adrsNo');
		let url = `${baseUrl}/${adrsNo}`;
		let settings = {
			url : url,
			method : "delete",
			dataType : 'json'
		};
		
		$.ajax(settings)
			.done(function(resp){
				if(resp.success){
					$adrsTr.remove();
				}else{
					alert(resp.message);
				}
			})
			.fail()
		
	});
	
	let $targetTag;
	
	// Modal Open시
	$('#exampleModal').on('shown.bs.modal', function (e) {
		let calledBtn = e.relatedTarget;
		let $trTag = $(calledBtn).parents('tr');
		$targetTag = $trTag; 
		let adrsNoValue = $trTag.data('adrsNo');
		let tds = $trTag.find('td');
		let adrsNameValue = $(tds[0]).text();
		let adrsHpValue = $(tds[1]).text();
		let adrsAddValue = $(tds[2]).text();
		
		$(adrsNo).val(adrsNoValue);
		$(adrsName).val(adrsNameValue);
		$(adrsHp).val(adrsHpValue);
		$(adrsAdd).val(adrsAddValue);

	})
	
	$('#exampleModal').on('hidden.bs.modal', function (e) {
		$(adrsNo).val("");
		$(adrsName).val("");
		$(adrsHp).val("");
		$(adrsAdd).val("");
		$targetTag = null;
	})
	
	$(modbtn).on('click',function(){
		let flag = confirm("진짜 수정 ?");
		if(!flag){
			return false;
		}
		
		let data = $(adrsUpdate).serializeJSON();
		let json = JSON.stringify(data);
		
		let settings = {
			url : baseUrl,
			method : "put",
			data : json,
			contentType : 'application/json;charset=utf-8',
			dataType : 'json'
		};
		
		$.ajax(settings)
			.done(function(resp){
				if(resp.success){
					let tds = $targetTag.find('td');
					$(tds[0]).text(resp.changeData.adrsName);
					$(tds[1]).text(resp.changeData.adrsHp);
					$(tds[2]).text(resp.changeData.adrsAdd);
					$('#exampleModal').modal('hide');
				}else{
					alert(resp.message);
				}
			});
		
		
		
	});
	
	
	
	
	// delete function
	/*
	$('table').on('click','.delBtn',function(){
		console.log(this);
		let $btn = $(this);
		let $trTag = $btn.parents('tr');
		let adrsNo = $trTag.data('adrsNo');
		
		let settings = {
			url : baseUrl + "/" + adrsNo,
			method : "delete",
			dataType : "json"
		};
		
		$.ajax(settings)
			.done(function(resp){
				if(resp.success){
					alert('삭제 성공!');
					$trTag.remove();
				}else{
					alert(resp.message);
				}
			});
		
		
	})
	*/
	
	
	
	
	
	/*
	$(adrsForm).on('submit',function(event){
		event.preventDefault();
		let formTag = event.target;
		let data = $(formTag).serializeJSON();
		let json = JSON.stringify(data)
		let method = formTag.method;
		let settings = {
			url : baseUrl,
			method : method,
			data : json,
			contentType : "application/json;charset=utf-8",
			dataType : "json", // Accept Request Header : Content-Type Response Header
			success : function(resp) {
				if(resp.success == true){
					alert('추가 성공!')
					$.getJSON(url,selectFunction);
				}
			},
			error : function(jqXhr, status, error) {
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("error : ", error);
			}
		};
		$.ajax(settings);
		
	});
	*/
});

let makeTrTag = (adrsVO)=>{
	let trTag = `
		<tr data-adrs-no="${adrsVO.adrsNo}">
			<td>${adrsVO.adrsName}</td>
			<td>${adrsVO.adrsHp}</td>
			<td>${adrsVO.adrsAdd}</td>
			<td><input type="button" value="삭제" class="delBtn" /></td>
			<td><button type="button" data-bs-toggle="modal" data-bs-target="#exampleModal">수정</button></td>
		</tr>
	`;
	return trTag;
}

let selectFunction = function(resp){
	code = "";
	let dataList = resp.list; 
	if(dataList?.length > 0){
		$.each(dataList, function(i,v){
			code += makeTrTag(this);
		})
	}else{
		code = "<tr><td colspan='3'>주소록 없음...</td></tr>"
	}//if~else end
	
	$(listBody).html(code);
}
