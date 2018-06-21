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
      var dialog = frameElement.dialog;
      var dialogData = dialog.get('data');//获取data参数
      if(dialogData != null){
              $("#booksManage_Window_updateform").ligerForm().setData(dialogData.content);
              $("#publishing_time").val(dialogData.content.publishing_time);
              var id = dialogData.content.books_id;
              var path = dialogData.content.coverpath;
              $('#demo1').append('<img src="'+path+'"class="layui-upload-img">');
              $.ajax({
                    url: "books/WkrjBooksController/listBooksFile",
                    data: {books_id:id},
                    dataType : "json",  
                    type : "POST",
                    success: function(res){        
                          if (res.length>0) {
                          for (var i = 0; i < res.length; i++) {
                          $(".bookfile").append('<div class="pdffile" style="margin-left:35px"><span>'+res[i].file_name+'.'+res[i].file_type+'</span>'+
            '<div class="delebook"><i class="layui-icon" style="font-size: 20px; color: #000;background:#d0d0d0;border-radius:5px; margin-right: 11px;cursor: pointer;">&#xe615;</i></div>'+
            '<input type="hidden" name="file_path" value="'+res[i].file_path+'"></div>');
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
    <form id="booksManage_Window_updateform" method="post" class="liger-form">
                <div class="Co" style="display: none;">
                    <label style="text-align:right;">id:</label>
                    <input name="books_id" type="text" class="ui-textbox">
                </div>
                <div class="Co" >
                 <div class="layui-upload">
                      <label>封面:</label>
                     <div class="layui-upload-list" id="demo1">
                      </div>
                    </div>
                 </div>
                <div class="Co" >
                    <label>图书名称:</label>
                    <input name="books_name" id="books_name" type="text" maxlength="100" disabled="disabled"> 
                </div>
				
				<div class="Co" >
                    <label>图书分类:</label>
                    
                      <input name="data_name" id="data_name"  disabled="disabled"/>
                </div>
				
                <div class="Co" >
                    <label>作者</label>
                    <input name="books_author" id="books_author" type="text" maxlength="40" disabled="disabled">
                </div>
                <div class="Co" >
                    <label>出版社:</label>
                    <input name="publishinghouse" id="publishinghouse" type="text" maxlength="40" disabled="disabled">
                </div>
                <div class="Co" >
                    <label>出版日期:</label>
                    <input name="publishing_time" id="publishing_time" type="date" disabled="disabled">
                </div>
                <div class="Co" >
                    <label>页码:</label>
                    <input name="pagination" id="pagination" type="text" maxlength="12" disabled="disabled">
                </div>
                <div class="Co" >
                    <label>语言类型:</label>
                    <select name="languagetype" id="language">
                        <option value="1">中文</option>
                        <option value="2">外文</option>
                    </select>
                </div>
                <div class ="Co">
                    <label>介绍:</label>
                    <textarea name="content" id="content" style="width:670px;height:235px;"  maxlength="1000" disabled="disabled"></textarea>
                </div>
                <div class ="Co">
                    <label style="    display: block; width: 100%;text-align: left;">附件:</label>
                    <div class="bookfile"></div>
                </div>
    </form>
 </div>
 </body>
 <script>
 //下载文件
$('.bookfile').on('click','.delebook',function(){
    var path = $(this).parent().find('input').val();
    if(null != path && path != '' && path != "undefined"){
        window.open(path);
    } 
      
}); 
$("#language").ligerComboBox().setDisabled();

  </script>
 </html>