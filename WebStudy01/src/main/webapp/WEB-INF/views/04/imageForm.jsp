<%@ page contentType="text/html; charset=utf-8" %>
<html lang="ko">
<body>
	<pre>
		이미지 스트리밍 서비스에서 쿠키 생성 위치
		- 음악을 들을 때, BTS 멤버 컨텐츠가 서비스 될 때, 이미지 스트리밍이 될 때
	</pre>
	<%
		String savedImageName = (String) request.getAttribute("savedImageName");
	%>
	<form onsubmit='submitHandler(event);' action='<%=request.getContextPath() %>/image.do'>
		<select name='image' id="image" onchange='this.form.requestSubmit();' required>
			<option value="">사진 선택</option>
			<%=request.getAttribute("options") %>
		</select>
		<input type='submit' value='전송'>
	</form>
	<div id="imageArea">
		
	</div>
	<script>
		function submitHandler(event) {
			event.preventDefault();
			let imageName = image.value;
			imageArea.innerHTML = `<img src="<%=request.getContextPath() %>/image.do?image=\${imageName}"/>`;
		}
		
		window.onload = function(){
			
			let options = document.getElementById('image');
			for(i = 0 ; i < options.length ; i++){
				let currVal = options[i];
				if(currVal.value=="<%=savedImageName%>"){
					currVal.selected = true;
				}
			}
			options.form.requestSubmit();
		}
		
	</script>

</body>
</html>
