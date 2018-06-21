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
<script type="text/javascript" src="page/syg/notice/js/notice.js"></script>
<script type="text/javascript">
 $(function (){
	  var dialog = frameElement.dialog;
	  var dialogData = dialog.get('data');//获取data参数
	  if(dialogData != null){
	          $("#noticeManage_Window_updateform").ligerForm().setData(dialogData.content);
	  }
});
</script>
 </head>
 <body>
 <div style="overflow:auto;height: 100%;">
    <form id="noticeManage_Window_updateform" method="post" class="liger-form">
	            <div class="Co" style="display: none;">
                    <label style="text-align:right;">id:</label>
                    <input name="notice_id" type="text" class="ui-textbox">
                </div>
                <div class="Co" >
                    <label><font color="red">*</font>标题:</label>
                    <input name="notice_title" id="notice_title" type="text" >
                </div>
                <div class ="Co">
                    <label><font color="red">*</font>内容:</label>
                    <textarea name="notice_content" style="width:688px;height:235px" rows="" cols=""></textarea>
                </div>
    </form>
 </div>
 </body>
 </html>