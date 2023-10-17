/**
 * 
 */
$(function(){
   const cPath = $(this.body).data("contextPath");
   let makeTrTag = function(adrsVO){
      let trTag = `
         <tr data-adrs-no="${adrsVO.adrsNo}">
            <td>${adrsVO.adrsName}</td>
            <td>${adrsVO.adrsHp}</td>
            <td>${adrsVO.adrsAdd}</td>
            <td><input type="button" value="삭제" class="delBtn" /><td>
         </tr>
      `;
      return trTag;
   };
   const baseUrl = `${cPath}/adrs/address`;
   
   $.getJSON(baseUrl, function(resp){
      let adrsList = resp.adrsList;
      trTags = "";
      if(adrsList?.length > 0){
         $.each(adrsList, function(){
            trTags += makeTrTag(this);
         });
      }else{
         trTags += `
            <tr>
               <td colspan='3'>주소록 없음.</td>
            </tr>
         `;
      }// if~else end
      
      $(listBody).html(trTags);
      
   }); // getJSON end
   
   
   //-----------------------------------------------------

   
   $(adrsForm).on("submit", function(event) {
      
      event.preventDefault();
      
      let url = this.action;
      let method = this.method;
      let data = $(this).serializeJSON();
      let json = JSON.stringify(data);
      
      let settings ={
         url    : url,
         method    : method,
         data    : json,
         headers:{
            "Content-Type" : "application/json;charset=utf-8"
         },
         dataType : "json"
      };
      
      $.ajax(settings)
         .done(function(resp){
            if(resp.success){
               let trTag = makeTrTag(resp.originData);
               $(listBody).prepend(trTag);
               adrsForm.reset();//제이쿼리에는 리셋이 없어서 태그 그대로 들어가야함
               
            }else{
               alert(resp.message);
            }
            
      });
         
      return false;       
   });
   //-----------------------------------------------------
   /*
   $(document).on("click", ".delBtn", function() {
      
        let adrsNo = $(this).data("adrsNo"); // 클릭한 버튼에 연결된 주소록 번호 가져오기
        let url = `${cPath}/adrs/address/${adrsNo}`; // 삭제할 주소록 항목의 URL 설정
        let method = "DELETE"; // HTTP 메서드 설정

        let settings ={
            url: url,
            method: method,
      
            dataType: "json"
        };

        $.ajax(settings)
            .done(function(resp) {
                if(resp.success){
                    // 삭제에 성공하면 해당 행을 삭제
                    $(`tr[data-adrs-no="${adrsNo}"]`).remove();
                } else {
                    alert(resp.message);
                }
            });

        return false;
    });
   
   */
   
   
   //동적으로 만든 태그에는 바인드 하지 않는다
   $(listBody).on("click",".delBtn", function(){
      
      let flag = confirm("삭제 할뀨야?");
      if(! flag) return;
      let adrsTr = $(this).parents("tr:first");
      
      let $adrsTr =  $(adrsTr);
      let adrsNo = $adrsTr.data("adrsNo");
      
      let url = `${baseUrl}/${adrsNo}`;
      
      let settings={
         url : url,
         method : "delete",
         dataType : "json"
         
         
      };
      $.ajax(settings)
         .done(function(resp){
            if(resp.success){
               $adrsTr.remove();
            }else{
               alert(resp.message);
            }
      });
   }); 
});