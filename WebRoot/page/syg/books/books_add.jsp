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
<script type="text/javascript" src="page/syg/books/js/books.js"></script>
<script type="text/javascript">
$(function (){
       $("#data_id").ligerComboBox({
            width :300,height: 30,selectBoxWidth:300,selectBoxHeight: 200, 
            valueField: 'id', treeLeafOnly: false, isMultiSelect: true,
            tree: { checkbox: false, url: 'books/WkrjBooksController/getDataTree?data_parent_id=03', ajaxType: 'post', idField: 'id' }
       });
});
</script>
 </head>
 <body>
 <div style="overflow:auto;height: 100%;">
    <form id="booksManage_Window_addform" method="post" class="liger-form">
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
                    <label><font color="red">*</font>图书名称:</label>
                    <input name="books_name" id="books_name" type="text" maxlength="100">
                </div>
                <div class="Co" >
                    <label><font color="red">*</font>图书路径:</label>
                    <input name="books_path" id="books_path" type="text" maxlength="100">
                    <label  style="width:150px"><font color="red">若不填入，需上传附件</font></label>
                </div>
				
				 <div class="Co" >
                    <label><font color="red">*</font>图书分类:</label>
                   
                      <input name="data_id" id="data_id" data-options="method:'post'"/>
                </div>
				
                <div class="Co" >
                    <label>作者</label>
                    <input name="books_author" id="books_author" type="text" maxlength="40">
                </div>
                <div class="Co" >
                    <label>出版社:</label>
                    <input name="publishinghouse" id="publishinghouse" type="text" maxlength="40">
                </div>
                <div class="Co" >
                    <label>出版日期:</label>
                    <input name="publishing_time" id="publishing_time" type="date">
                </div>
                <div class="Co" >
                    <label>页码:</label>
                    <input name="pagination" id="pagination" type="text" maxlength="12">
                </div>
                <div class="Co" >
                    <label><font color="red">*</font>语言类型:</label>
                    <select name="languagetype">
                        <option value="1">中文</option>
                        <option value="2">外文</option>
                    </select>
                </div>
                <div class ="Co">
                    <label>介绍:</label>
                    <textarea name="content" id="content" style="width:670px;height:235px;"  maxlength="1000"></textarea>
                </div>
                <div class ="Co">
                    <label style="    display: block; width: 100%;text-align: left;"><font color="red">*</font>附件:</label>
                    <div class="bookfile"></div>
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
            ,url: 'books/WkrjBooksController/uploadFile'
            ,before: function(obj){
                $('#demo1').html('');
                var old_url = $("#coverpath").val();
                if(null != old_url && old_url != '' && old_url != "undefined"){
                    $.post('books/WkrjBooksController/deleFile',{path:old_url});
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
    ,url: 'books/WkrjBooksController/uploadFile'
    ,accept: 'file' //普通文件
    ,exts: 'pdf|PDF' //只允许上传PDF文件
    ,multiple: true//可选多个文件
    ,done: function(res){
      if (res.length>0) {
		for (var i = 0; i < res.length; i++) {
			$(".bookfile").append('<div class="pdffile" style="margin-left:35px"><span>'+res[i].filename+'.'+res[i].fileextend+'</span>'+
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
        $.post('books/WkrjBooksController/deleFile', {  path : path },
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