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
              $("#recommendtime").val(dialogData.content.recommendtime);
              var id = dialogData.content.video_id;
              $('#demo1').append('<img src="'+ dialogData.content.coverpath +'"class="layui-upload-img">');
              $.ajax({
                    url: "video/WkrjVideoController/listVideoFile",
                    data: {video_id:id},
                    dataType : "json",  
                    type : "POST",
                    success: function(res){        
                          if (res.length>0) {
                          for (var i = 0; i < res.length; i++) {
                          $(".bookfile").append('<div class="pdffile"><span>'+res[i].file_name+'.'+res[i].file_type+'</span>'+
            '<div class="delebook"><i class="layui-icon" style="font-size: 20px; color: #000;background:#d0d0d0;border-radius:5px; margin-right: 11px;cursor: pointer;">&#xe601;</i></div>'+
            '</div>');
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
                    </div>
                 </div>
                <div class="Co" >
                    <label><font color="red">*</font>视频名称:</label>
                    <input name="video_name" id="video_name" type="text" disabled="disabled">
                </div>
                <div class="Co" >
                    <label><font color="red">*</font>视频分类:</label>
                    <div class="r_div">
                      <input name="data_name" id="data_name"  disabled="disabled"/>
                </div>
                </div>
                <div class="Co" >
                    <label>主讲人</label>
                    <input name="speaker" id="speaker" type="text"  disabled="disabled">
                </div>
                <div class="Co" >
                    <label>主讲人单位:</label>
                    <input name="speaker_unit" id="speaker_unit" type="text"  disabled="disabled">
                </div>
                <div class="Co" >
                    <label>是否推荐:</label>
                    <select name="recommend"  id="recommend">
                        <option value=""></option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </div>
                <div class="Co" >
                    <label>推荐时间:</label>
                    <input name="recommendtime" id="recommendtime" type="date"  disabled="disabled">
                </div>
                <div class ="Co">
                    <label>介绍:</label>
                    <textarea name="content" id="content"  disabled="disabled" style="width:670px;height:235px;"  maxlength="1000"></textarea>
                </div>
                <div class ="Co">
                    <label style="    display: block; width: 100%;text-align: left;">附件:</label>
                    <div class="bookfile" style="margin-left:35px"></div>
                    
                </div>
    </form>
 </div>
 </body>
 <script>
 
$("#recommend").ligerComboBox().setDisabled();

  </script>
 </html>