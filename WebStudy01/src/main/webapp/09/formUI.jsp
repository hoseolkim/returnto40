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
	<div>
		<h4>Request Content Type</h4>
		<input type="radio" name="contentType" data-content-type="application/json;charset=utf-8"/>JSON
		<input type="radio" name="contentType" checked/>PARAMETER
	</div>
	<div>
		<h4>Response Content Type</h4>
		<input type="radio" name="dataType" data-data-type="json" data-success="jsonSuccess"/>JSON
		<input type="radio" name="dataType" data-data-type="xml" data-success="xmlSuccess"/>XML
		<input type="radio" name="dataType" data-data-type="html" data-success="htmlSuccess" checked/>HTML
	</div>
	<form id="sampleForm" action="<%=request.getContextPath() %>/09/model2/formDataProcess" method="post" enctype="application/x-www-form-urlencoded"  >
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
	let functionObj = {
			jsonSuccess : function(resp){
				$resultArea.html(JSON.stringify(resp));
			},
			xmlSuccess : function(resp){
				let msg = $(resp).find('message').text();
				$resultArea.html(msg);
			},
			htmlSuccess : function(resp){
				$resultArea.html(resp)
			}
	}
	let submitHandler = function(event){
		event.preventDefault();
		let $form = $(this);
		
		let requestContentType = $('input[name=contentType]:checked').data('contentType');
		if(!requestContentType){
			requestContentType="application/x-www-form-urlencoded"
		}
		let $dataType = $('input[name=dataType]:checked');
		let responseContentType = $dataType.data('dataType');
		let getfunction = $dataType.data('success');
		
		let sendData;
		if(requestContentType=="application/json;charset=utf-8"){
			sendData = JSON.stringify($form.serializeJSON());
		}else {
			sendData = $form.serialize();
		}
		
		console.log(requestContentType);
		console.log(getfunction);
		let settings = {
			url : $form.attr('action'),
			method : $form.attr('method'),
			data : sendData,
// 			data : $form.serialize(), // application/x-www-form-urlencoded : 파라미터를 전송하겠다
// 			data : JSON.stringify($form.serializeJSON()),
// 			contentType :"application/json;charset=utf-8", // json payload가 넘어간다
			contentType : requestContentType,
// 			dataType : "json", // Accept Request Header : Content-Type Response Header
			dataType : responseContentType, // Accept Request Header : Content-Type Response Header
			success : functionObj[getfunction],
			error : function(jqXhr, status, error) {
				console.log("jqXhr : ", jqXhr);
				console.log("status : ", status);
				console.log("error : ", error);
			}

		};
		$.ajax(settings);
		
		
	};
	
	
	
	$(sampleForm).on("submit", submitHandler);



</script>
</body>
</html>















