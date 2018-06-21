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
<script type="text/javascript" src="page/syg/video/js/video.js"></script>
<script type="text/javascript">
 $(function (){
	  var dialog = frameElement.dialog;
	  var dialogData = dialog.get('data');//获取data参数
	  if(dialogData != null){
	          $("#videoManage_Window_updateform").ligerForm().setData(dialogData.content);
	          var id = dialogData.content.video_id;
	          $('#demo1').append('<img src="'+ dialogData.content.coverpath +'"class="layui-upload-img">');
	          var combox = $("#data_id").ligerComboBox({
		            width :300,height: 30,selectBoxWidth:300,selectBoxHeight: 200, 
		            valueField: 'id', treeLeafOnly: false, isMultiSelect: true,
		            tree: { checkbox: false, url: 'video/WkrjVideoController/getDataTree?data_parent_id=01', ajaxType: 'post', idField: 'id' }
		         });
		       combox.selectValue(dialogData.content.data_id);
	          $.ajax({
                    url: "video/WkrjVideoController/listVideoFile",
                    data: {video_id:id},
                    dataType : "json",  
                    type : "POST",
                    success: function(res){        
                          if (res.length>0) {
                          for (var i = 0; i < res.length; i++) {
                          $(".bookfile").append('<div class="pdffile"><span>'+res[i].file_name+'.'+res[i].file_type+'</span>'+
            '<div class="delebook"><i class="layui-icon" style="font-size: 20px; color: #000;background:#d0d0d0;border-radius:5px; margin-right: 11px;cursor: pointer;">&#xe640;</i></div>'+
            ' <input type="hidden" name="file_path" value="'+res[i].file_path+'"><input type="hidden" name="file_name" value="'+res[i].file_name+'"><input type="hidden" name="file_type" value="'+res[i].file_type+'"></div>');
                          } 
                          }
                     }
               }); 
	  }
});
</script>
 </head>
 <body>
 <div style="overflow:auto;height: 100%;">
    <form id="videoManage_Window_updateform" method="post" class="liger-form">
	            <div class="Co" style="display: none;">
                    <label style="text-align:right;">id:</label>
                    <input name="video_id" type="text" class="ui-textbox">
                </div>
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
                    <label><font color="red">*</font>视频名称:</label>
                    <input name="video_name" id="video_name" type="text" maxlength="100">
                </div>
				 <div class="Co" >
                    <label><font color="red">*</font>视频路径:</label>
                    <input name="video_path" id="video_path" type="text" maxlength="100">
                </div>
                <div class="Co" >
                    <label><font color="red">*</font>视频分类:</label>
                    <div class="r_div">
                      <input name="data_id" id="data_id" data-options="method:'post'"/>
                </div>
                </div>
                <div class="Co" >
                    <label>主讲人</label>
                    <input name="speaker" id="speaker" type="text" maxlength="40">
                </div>
                <div class="Co" >
                    <label>主讲人单位:</label>
                    <input name="speaker_unit" id="speaker_unit" type="text" maxlength="40">
                </div>
                <div class="Co" >
                    <label><font color="red">*</font>是否推荐:</label>
                    <select name="recommend">
                        <option value=""></option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </div>
                <div class ="Co">
                    <label>介绍:</label>
                    <textarea name="content" id="content" style="width:670px;height:235px;"  maxlength="1000"></textarea>
                </div>
                <div class ="Co">
                    <label style="    display: block; width: 100%;text-align: left;"><font color="red">*</font>附件:</label>
                    <div class="bookfile" style="margin-left:35px"></div>
                    <div style=" margin-left: 100px;"><button type="button" class="layui-btn" id="test3"><i class="layui-icon"></i>上传MP4文件</button></div>
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
            ,url: 'video/WkrjVideoController/uploadFile'
            ,before: function(obj){
                $('#demo1').html('');
                var old_url = $("#coverpath").val();
                if(null != old_url && old_url != '' && old_url != "undefined"){
                    $.post('video/WkrjVideoController/deleFile',{path:old_url});
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
    ,url: 'video/WkrjVideoController/uploadFile'
    ,accept: 'file' //普通文件
    ,exts: 'pdf|PDF' //只允许上传PDF文件
    ,multiple: true//可选多个文件
    ,done: function(res){
      if (res.length>0) {
        for (var i = 0; i < res.length; i++) {
            $(".bookfile").append('<div class="pdffile"><span>'+res[i].filename+'.'+res[i].fileextend+'</span>'+
            '<div class="delebook"><i class="layui-icon" style="font-size: 20px; color: #000;background:#d0d0d0;border-radius:5px; margin-right: 11px;cursor: pointer;">&#xe640;</i></div>'+
            ' <input type="hidden" name="file_path" value="'+res[i].fileurl+'"><input type="hidden" name="file_name" value="'+res[i].filename+'"><input type="hidden" name="file_type" value="'+res[i].fileextend+'"></div>');
        }
    }
    }
  });
 //删除文件
$('.bookfile').on('click','.delebook',function(){
    var path = $(this).parent().find('input').val();
    if(null != path && path != '' && path != "undefined"){
        $.post('video/WkrjVideoController/deleFile', {  path : path },
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