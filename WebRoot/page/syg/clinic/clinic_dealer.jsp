<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <meta charset="UTF-8">
<script type="text/javascript" src="page/syg/clinic/js/clinic.js"></script>
<script type="text/javascript">
 $(function (){
      var dialog = frameElement.dialog;
      var dialogData = dialog.get('data');//获取data参数
      if(dialogData != null){
              $("#clinicManage_Window_dealerform").ligerForm().setData(dialogData.content);
              $("#publish_time").val(dialogData.content.publish_time);
              
              
              $("#clinic_type").val(dialogData.content.clinic_type);
              
              var id = dialogData.content.clinic_id;
              var path = dialogData.content.coverpath;
              $('#demo1').append('<img src="'+path+'"class="layui-upload-img">');
              $.ajax({
                    url: "clinic/WkrjClinicController/listClinicFile",
                    data: {clinic_id:id},
                    dataType : "json",  
                    type : "POST",
                    success: function(res){        
                          if (res.length>0) {
                          for (var i = 0; i < res.length; i++) {
                          $(".clinicfile").append('<div class="pdffile" style="margin-left:35px"><span>'+res[i].file_name+'.'+res[i].file_type+'</span>'+
                          '<div class="readfile"><i class="layui-icon" style="font-size: 20px; color: #000;background:#d0d0d0;border-radius:5px; margin-right: 11px;cursor: pointer;">&#xe615;</i></div>'+
                          
            ' <input type="hidden" name="file_path" value="'+res[i].file_path+'"><input type="hidden" name="file_name" value="'+res[i].file_name+'"><input type="hidden" name="file_type" value="'+res[i].file_type+'"></div>');
                          } 
                          }
                     }
               }); 
      }
      
    $('.clinicfile').on('click','.readfile',function(){
	
		var path = $(this).parent().find('input').val();
	    if(null != path && path != '' && path != "undefined"){
	        window.open(path);
	    } 
    
      
    });
    
      
});
</script>


<body>
	<form id="clinicManage_Window_dealerform" method="post" class="liger-form">
             
                    <input name="clinic_id" id="clinic_id" type="hidden" >
                
                <div class="Co" >
                 <div class="layui-upload">
                      <label>封面:</label>
                     <div class="layui-upload-list" id="demo1">
                      </div>
                        <input type="hidden"  name="coverpath" id="coverpath">
                    </div>
                 </div>
    
	            <div class="Co" >
                    <label><font color="red">*</font>指南名称:</label>
                    <input name="clinic_name" id="clinic_name" type="text" maxlength="100" disabled="disabled">
                </div>
                <div class="Co" >
                    <label>关键字</label>
                    <input name="keyword" id="keyword" type="text" maxlength="36" disabled="disabled">
                </div>
               <div class="Co" >
                    <label><font color="red">*</font>类别:</label>
                    <input name="data_name" id="data_name" type="text" maxlength="36" disabled="disabled">
                </div> 
                <div class="Co" >
                    <label>出版时间:</label>
                    <input name="publish_time" id="publish_time" type="date" disabled="disabled">
                </div>
                
                <div class="Co" >
                    <label>中文标题:</label>
                    <input name="chinesetitle" id="chinesetitle" type="text" maxlength="100" disabled="disabled">
                </div>
                <div class="Co" >
                    <label>制定者:</label>
                    <input name="constitutor" id="constitutor" type="text" maxlength="40" disabled="disabled">
                </div>
                
                <div class="Co" >
                    <label>出处:</label>
                    <input name="provenance" id="provenance" type="text" maxlength="100" disabled="disabled">
                </div>
                <div class="Co" >
                    <label>相关指南:</label>
                    <input name="correlationguide" id="correlationguide" type="text" maxlength="100" disabled="disabled">
                </div>
                
                <div class="Co" >
                    <label>临床类型:</label>
                    <input name="clinic_type" id="clinic_type" type="text" maxlength="2" disabled="disabled">
                </div>
                
                 <div class ="Co">
                    <label>介绍:</label>
                    <textarea name="content" id="content" style="width:670px;height:235px;margin-top: 25px;"  maxlength="1000" disabled="disabled"></textarea>
                </div>
                <div class ="Co">
                    <label style="    display: block; width: 100%;text-align: left;">附件:</label>
                    <div class="clinicfile"></div>
                </div>
                
    </form>
</body>
</html>