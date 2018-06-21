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
	          $("#clinicManage_Window_updateform").ligerForm().setData(dialogData.content);
	          
	          $("#publish_time").val(dialogData.content.publish_time);//设置时间
	          $("#clinic_type").val(dialogData.content.clinic_type);
	          
	          var id = dialogData.content.clinic_id;
	          $('#demo1').append('<img src="'+ dialogData.content.coverpath +'"class="layui-upload-img">');
	          $.ajax({
                    url: "clinic/WkrjClinicController/listClinicFile",
                    data: {clinic_id:id},
                    dataType : "json",  
                    type : "POST",
                    success: function(res){        
                          if (res.length>0) {
                          for (var i = 0; i < res.length; i++) {
                          $(".clinicfile").append('<div class="pdffile"><span>'+res[i].file_name+'.'+res[i].file_type+'</span>'+
            '<div class="deletefile"><i class="layui-icon" style="font-size: 20px; color: #000;background:#d0d0d0;border-radius:5px; margin-right: 11px;cursor: pointer;">&#xe640;</i></div>'+
            ' <input type="hidden" name="file_path" value="'+res[i].file_path+'"><input type="hidden" name="file_name" value="'+res[i].file_name+'"><input type="hidden" name="file_type" value="'+res[i].file_type+'"></div>');
                          } 
                          }
                     }
               }); 
               
               
          var combox1 = $("#data_id").ligerComboBox({
            width :300,height: 30,selectBoxWidth:300,selectBoxHeight: 200, 
            valueField: 'id', treeLeafOnly: false, isMultiSelect: true,
            tree: { checkbox: false, url: 'clinic/WkrjClinicController/getData',
             ajaxType: 'post', idField: 'id' }
	   });
	   
	    if (dialogData.content.data_id == -1) {
	       combox1.setText("");
	     } else {
	       combox1.selectValue(dialogData.content.data_id);
	     }
               
	  }
});


</script>
</head>
 <body>
 <div style="overflow:auto;height: 100%;">
    <form id="clinicManage_Window_updateform" method="post" class="liger-form">
             
                    <input name="clinic_id" id="clinic_id" type="hidden" >
                
                <div class="Co" >
                 <div class="layui-upload">
                      <label>封面:</label>
                     <div class="layui-upload-list" id="demo1">
                      </div>
                        <input type="hidden"  name="coverpath" id="coverpath">
                       <div style=" margin-left: 100px;"><button  type="button" class="layui-btn" id="test1">上传图片</button></div>
                    </div>
                 </div>
    
	            <div class="Co" >
                    <label><font color="red">*</font>指南名称:</label>
                    <input name="clinic_name" id="clinic_name" type="text" maxlength="100">
                </div>
				
				<div class="Co" >
                    <label><font color="red">*</font>指南路径:</label>
                    <input name="clinic_path" id="clinic_path" type="text" maxlength="100">
                </div>
				
                <div class="Co" >
                    <label>关键字</label>
                    <input name="keyword" id="keyword" type="text" maxlength="36">
                </div>
               <div class="Co" >
                    <label><font color="red">*</font>类别:</label>
                    <input name="data_id" id="data_id" data-options="method:'post'"/>
                </div> 
                <div class="Co" >
                    <label>出版时间:</label>
                    <input name="publish_time" id="publish_time" type="date">
                </div>
                
                <div class="Co" >
                    <label>中文标题:</label>
                    <input name="chinesetitle" id="chinesetitle" type="text" maxlength="100">
                </div>
                <div class="Co" >
                    <label>制定者:</label>
                    <input name="constitutor" id="constitutor" type="text" maxlength="40">
                </div>
                
                <div class="Co" >
                    <label>出处:</label>
                    <input name="provenance" id="provenance" type="text" maxlength="100">
                </div>
                <div class="Co" >
                    <label>相关指南:</label>
                    <input name="correlationguide" id="correlationguide" type="text" maxlength="100">
                </div>
                
                <div class="Co" >
                    <label>临床类型:</label>
                    <select name="clinic_type" maxlength="10">
                        <option value="指南">指南</option>
                        <option value="共识">共识</option>
                        <option value="解读">解读</option>
                        <option value="规范">规范</option>
                        <option value="翻译">翻译</option>
                    </select>
                </div>
                
                 <div class ="Co">
                    <label>介绍:</label>
                    <textarea name="content" id="content" style="width:670px;height:235px;margin-top: 25px;"  maxlength="1000"></textarea>
                </div>
                <div class ="Co">
                    <label style="    display: block; width: 100%;text-align: left;"><font color="red">*</font>附件:</label>
                    <div class="clinicfile" style="margin-left:35px"></div>
                    <div style=" margin-left: 100px;"><button type="button" class="layui-btn" id="test3"><i class="layui-icon"></i>上传PDF文件</button></div>
                </div>
                
    </form>
 </div>
 </body>
 <script>
layui.use('upload', function(){
       var $ = layui.jquery  ,upload = layui.upload;
        //普通图片上传
          var uploadInst = upload.render({
            elem: '#test1'
            ,url: 'clinic/WkrjClinicController/uploadFile'
            ,before: function(obj){
                $('#demo1').html('');
                var old_url = $("#coverpath").val();
                if(null != old_url && old_url != '' && old_url != "undefined"){
                    $.post('clinic/WkrjClinicController/deleteFile',{path:old_url});
                 }
              //预读本地文件示例，不支持ie8
              obj.preview(function(index, file, result){
                $('#demo1').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">'); //图片链接（base64）
              });
            }
            ,done: function(res){
                  $("#coverpath").val(res[0].fileurl);
                  //如果上传失败
                  if(res.code > 0){
                    return layer.msg('上传失败');
                  }
                  //上传成功
            }
  });
//指定允许上传的文件类型
  upload.render({
    elem: '#test3'
    ,url: 'clinic/WkrjClinicController/uploadFile'
    ,accept: 'file' //普通文件
    ,exts: 'pdf|PDF' //只允许上传PDF文件
    ,multiple: true//可选多个文件
    ,done: function(res){
      if (res.length>0) {
        for (var i = 0; i < res.length; i++) {
            $(".clinicfile").append('<div class="pdffile" ><span>'+res[i].filename+'.'+res[i].fileextend+'</span>'+
            '<div class="deletefile"><i class="layui-icon" style="font-size: 20px; color: #000;background:#d0d0d0;border-radius:5px; margin-right: 11px;cursor: pointer;">&#xe640;</i></div>'+
            ' <input type="hidden" name="file_path" value="'+res[i].fileurl+'"><input type="hidden" name="file_name" value="'+res[i].filename+'"><input type="hidden" name="file_type" value="'+res[i].fileextend+'"></div>');
        }
    }
    }
  });
 //删除文件
$('.clinicfile').on('click','.deletefile',function(){
    var path = $(this).parent().find('input').val();
    console.log(path)
    if(null != path && path != '' && path != "undefined"){
        $.post('clinic/WkrjClinicController/deleteFile', {  path : path },
            function(data) {
                if (data.success) {
                }else{
                    $.messager.alert('提示','删除失败');
                }
        },"json");
    } 
    $(this).parent().remove();
      
}); 
 });
  </script>
</html>

