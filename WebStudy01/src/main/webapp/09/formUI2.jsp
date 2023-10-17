<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>09/formUI.jsp</title>
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.7.1.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/jquery.serializejson.js"></script>
</head>
<body>
	<h4>클라이언트가 서버로 전송할 데이터를 입력받은 UI</h4>
	<label><input type="radio" name="parsetarget" value="html" checked>HTML</label>
	<label><input type="radio" name="parsetarget" value="xml" >XML</label>
	<label><input type="radio" name="parsetarget" value="json">JSON</label>
	<form id="sampleForm" action="<%=request.getContextPath() %>/self/formDataProcess" method="post" enctype="application/x-www-form-urlencoded"  >
		<pre>
			<input type="hidden" name="hdnParam" value="HIDDEN" />
			<input type="text" name="txtParam" required />
			<input type="number" name="numParam"  />
			<input type="date" name="dateParam"  />
			<label><input type="radio" name="rdoParam" value="RDO1" />RDO1</label>
			<input type="radio" name="rdoParam" id="rdoParam2" value="RDO2" />
			<label for="rdoParam2" >RDO2</label>
			<label><input type="checkbox" name="chkParam" value="Chk1"/></label>
			<label><input type="checkbox" name="chkParam" value="Chk2"/></label>
			<label><input type="checkbox" name="chkParam" value="Chk3"/></label>
			<select name="selParam1" required>
				<option value>선택</option><!-- 프롬프트텍스트 -->
				<option value="selValue1">selText1</option>
				<option value="selValue2">selText2</option>
			</select>
			<select name="selParam2" multiple>
				<option>mulText1</option>
				<option>mulText2</option>
				<option>mulText3</option>
				<!-- prop() , attr() 차이
						prop()는 속성의 타입을 줄수 있고
						attr()은 속성에 텍스트만 줄 수 있다. 
				 -->
			</select>
			<input type="submit" value="전송"/>
			<button type="reset">취소</button>
			<button type="button">그냥버튼</button>
		</pre>
	</form>
	<div id="resultArea">
	</div>
<script>
// 	target 결정 -> event 종류 -> event Handler 구현 -> target에 handelr를 bind
	let $resultArea = $(resultArea);
	let successes = {
			'json':function(res){
				resultArea.innerHTML = JSON.stringify(res);
			},
			'xml':function(res){
				resultArea.innerHTML = $(res).find('message').text();
			},
			'html':function(res){
				resultArea.innerHTML = res;
			}
	}
	let submitHandler = function(event){
		event.preventDefault();
		
		let $form = $(this);
		let par = $('[name=parsetarget]:checked').val();
		let formData = $form.serializeArray();
		let formJson = dataSortToJson(formData)
		let settings = {
			url : $form.attr('action'),
			method : $form.attr('method'),
// 			data : $form.serialize(), // application/x-www-form-urlencoded : 파라미터를 전송하겠다
			data : JSON.stringify(formJson),
			contentType :"application/json;charset=utf-8", // json payload가 넘어간다
			dataType : par, // Accept Request Header : Content-Type Response Header
			success : 
				successes[par]
				
// 				let msg = $(resp).find('message').text();
// 				$resultArea.html(msg);
// 				response json
// 				$resultArea.html(JSON.stringify(resp));
// 				response html
// 				$resultArea.html(resp);
			,
			error : function(jqXhr, status, error) {
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("error : ", error);
			}

		};
		$.ajax(settings);
	};
	
	
	
	$(sampleForm).on("submit", submitHandler);
var multipleData = $('[multiple]')
var checkboxData = $('input[type=checkbox]');
	function dataSortToJson(formdata){
		let jsonData = {};
		
		$.each(formdata, function(i,v){
			let isMultiple = false;
			$.each(multipleData,function(k,y){
				if(y.name == v.name){
					isMultiple = true;
				}
			});
			$.each(checkboxData,function(k,y){
				if(y.name == v.name){
					isMultiple = true;
				}
			});
			
			if(isMultiple){
				// multiple속성을 가질 경우
				// jsonData의 name에 아무것도 없는 경우 배열로 먼저 추가
				if(!jsonData[v.name]){
					alert('여기..')
					jsonData[v.name] = [];
				}
				// 있을경우 그대로 내려와서 배열에 값을 push해준다
				jsonData[v.name].push(v.value);
				
			}else{
				// 멀티플이 아니라서 그냥 값만 넣어도 되는경우
				jsonData[v.name] = v.value;
			}
		})
		return jsonData;
	}


</script>
</body>
</html>















