<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<link href="system/js/uploadfy/uploadify.css"
     rel="stylesheet" type="text/css" />
<script type="text/javascript"
   src="system/js/uploadfy/jquery.uploadify.min.js"></script>
 <script type="text/javascript"
   src="system/js/uploadfy/jquery.uploadify.js"></script>
    <script type="text/javascript"
   src="systemdev/js/module/module.js"></script>
<script type="text/javascript">
$(function (){
	var dialog = frameElement.dialog;
	var dialogData = dialog.get('data');//获取data参数
	
	$("#module_icon").show();
	$("#module_icon").attr("src","system/icons/"+dialogData.content.module_icon+".png");
		
	$("#module_updateWindow_form").ligerForm().setData(dialogData.content);
    	
  	if(1==dialogData.content.module_level){
  		$("#module_parent_id_div").show();
  		var combox1 = $("#module_parent_id").ligerComboBox({
  			width : 300,
  			height:30,
  			selectBoxWidth: 300,
  			selectBoxHeight: 300, valueField: 'id', treeLeafOnly: false,isMultiSelect :true,
  			tree: { checkbox:false,url: 'wkrjsystemdev/wkrjModule/getGridInfoNew.wk', ajaxType: 'post',idField:'id' }
      	});
  		
  		combox1.selectValue(dialogData.content.module_parent_id);
  	}
  	
  	$dialog = top.$("iframe[src='systemdev/module/module_update.jsp']")[0].contentWindow;//用于关闭dialog
  	
  	$("#uploadify").uploadify({
  		'buttonText' : '请选择',
  		'height' : 30,
  		'swf' : 'systemdev/js/uploadfy/uploadify.swf',
  		'uploader' : '../../wkrjsystemdev/wkrjModule/uploadPic',
  		'width' : 120,
  		'auto':false,
  		'multi':false,//单选
  		'fileTypeExts':'*.png',
  		'fileObjName':'module_icons',
  		'onUploadSuccess' : function(file, data, response){
  			var d = eval('('+data+')');
  			d = eval('('+d+')');
  			var filename = d.filename;
  			
  			$("input[name='module_icon']").val(filename);
  			var data = liger.get("module_updateWindow_form").getData();
  			if(liger.get("module_parent_id")){
  				var module_parent_id = liger.get("module_parent_id").getValue();
  				data.module_parent_id = module_parent_id;
  			}
  			
  			if(liger.get("module_is_display")){
  				var module_is_display = liger.get("module_is_display").getValue();
  				data.module_is_display = module_is_display;
  			}
  			realUpdateModule(data,top.$("iframe[src='wkrjsystemdev/wkrjModule/getPage.wk']")[0].contentWindow.$("#maingrid").ligerGetGridManager(),$dialog.parent.$.ligerDialog);
  			top.$(".l-window-mask").hide();
  		}  
	});
 });
</script>

<!--添加菜单开始-->
<form id="module_updateWindow_form" method="post" class="liger-form">
	<div style="overflow:hidden;">
		<div class="Co">
			<label>菜单名称:</label> <input type="text" name="module_name" class="ui-textbox" />
		</div>
		<div class="Co">
			<label>菜单等级:</label> 
			<select id="module_level" name="module_level" style="width:320px;">  
	   			<option value="0">一级菜单</option>  
	   			<option value="1">二级菜单</option>  
			</select>  
		</div>
		<div id="module_parent_id_div" style="display:none" class="Co">
			<label>上级菜单:</label>
			<input id="module_parent_id" name="module_parent_id" />
		</div>
		<div class="Co">
			<label>菜单地址:</label>
			<input type="text" name="module_url" class="ui-textbox"/>
		</div>
		<div class="Co">
			<label>菜单顺序:</label>
			<input type="text" ltype='spinner' ligerui="{type:'int'}" value="0" name="module_order" />
		</div>
		<div class="Co">
			<label>是否隐藏:</label>
			<input type="radio" name="module_is_display" value="0" checked="checked" />显示 
			<input type="radio" name="module_is_display" value="1" />隐藏 
		</div>
		<!-- <div class="Co">
			<label>是否隐藏:</label> 
			<input type="radio" name="module_is_display" value="0" />显示 
			<input type="radio" name="module_is_display" value="1" />隐藏 		
		</div> -->
		<div class="Co" >
			<label>备注信息:<br/></label>
			<!-- <input type="text" name="module_other" style="width: 320px;"/> -->
			<div class="r_div">
				<textarea rows="3" cols="20" name="module_other" ></textarea>
			</div>
		</div>
		
		<input type="hidden" style="display:none;" name="module_id"  />
	</div>
	
	<div style="overflow:hidden;">
		<div class="Co">
			<label>菜单图标:</label> <img style="display:none;" width="16px" height="16px" src="" id="module_icon">
			<input type="hidden" style="display:none;" name="module_icon"  />
		</div>
		<div style="clear:both">
			<input type="file" name="module_icon_file" id="uploadify" />
		</div>
	</div>
	
</form>