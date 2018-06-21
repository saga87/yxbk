<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<link href="systemdev/js/uploadfy/uploadify.css"
     rel="stylesheet" type="text/css" />
 <script type="text/javascript"
   src="systemdev/js/uploadfy/jquery.uploadify.min.js"></script>
 <script type="text/javascript"
   src="systemdev/js/uploadfy/jquery.uploadify.js"></script>
    <script type="text/javascript"
   src="systemdev/js/module/module.js"></script>
   
<script type="text/javascript">
       $(function (){
    	   var dialog = frameElement.dialog;
    	   var dialogData = dialog.get('data');//获取data参数
    	   $("#perm_icon").show();
       	   $("#perm_icon").attr("src","system/icons/"+dialogData.content.perm_icon+".png");
    	   
    	   $("#perm_updateWindow_form").ligerForm().setData(dialogData.content);
    	   
		   $dialog = top.$("iframe[src='systemdev/module/perm_update.jsp']")[0].contentWindow;//用于关闭dialog
    	   
    	   $("#perm_uploadify").uploadify({
    	         'buttonText' : '请选择',  
    	         'height' : 30,  
    	         'swf' : 'systemdev/js/uploadfy/uploadify.swf',  
    	         'uploader' : '../../wkrjsystemdev/wkrjModule/uploadPic',  
    	         'width' : 120,  
    	         'auto':false,  
    	         'multi':false,//单选
    	         'fileTypeExts':'*.png',
    	         'fileObjName'   : 'module_icons',  
    	         'onUploadSuccess' : function(file, data, response) {  
    	             
    	              var d = eval('('+data+')');
    	              d = eval('('+d+')');
    	              var filename = d.filename;
    	              $("input[name='perm_icon']").val(filename);
    	              //$("input[name='module_id']").val(dialogData.content.module_id);
    	              var data = liger.get("perm_updateWindow_form").getData();
    		          
    				  realUpdatePerm(data,top.$("iframe[src='wkrjsystemdev/wkrjModule/getPage.wk']")[0].contentWindow.$("#permissiongrid").ligerGetGridManager(),$dialog.parent.$.ligerDialog);
    		          top.$(".l-window-mask").hide();
    	         }  
    	    });
    	 
       });
</script>

<!--添加菜单开始-->
<form id="perm_updateWindow_form" method="post" class="liger-form">
	<div style="overflow:hidden;">
        <div class="Co">
            <label>权限名称:</label> <input type="text" class="ui-textbox" name="perm_name" style="width: 320px;"/>
        </div>
        
        <div class="Co">
            <label>权限标记:</label> <input type="text" class="ui-textbox"  name="perm_flag" style="width: 320px;"/>
        </div>
        
        <input type="hidden" style="display:none;" name="perm_id"  />
        <input type="hidden" style="display:none;" name="module_id" id="module_id" />
    </div>
        
        <div style="overflow:hidden;">
            <div class="Co">
                <label>菜单图标:</label> <img style="display:none;" width="16px" height="16px" src="" id="perm_icon">
                <input type="hidden" style="display:none;" name="perm_icon"  />
            </div>
            <div style="clear:both">
                <input type="file" name="perm_perm_icon_file" id="perm_uploadify" />  
            </div>
        </div>
</form>