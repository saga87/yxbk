<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<link href="system/js/uploadfy/uploadify.css"
     rel="stylesheet" type="text/css" />
 <script type="text/javascript"
   src="systemdev/js/uploadfy/jquery.uploadify.min.js"></script>
 <script type="text/javascript"
   src="systemdev/js/uploadfy/jquery.uploadify.js"></script>
    <script type="text/javascript"
   src="systemdev/js/module/module.js"></script>
<script type="text/javascript">
       $(function (){
          //  $("#module_addWindow_form").ligerForm();
           // var form = liger.get("module_addWindow_form");
			//console.dir(form);
	       // var data = form.getData();
	       // alert(JSON.stringify(data));
	       $dialog = top.$("iframe[src='systemdev/module/module_add.jsp']")[0].contentWindow;//用于关闭dialog
	      
    	   $("#uploadify").uploadify({
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
    		          
    		          $("input[name='module_icon']").val(filename);
    		          var data = liger.get("module_addWindow_form").getData();
    			      if(liger.get("module_parent_id")){
    					var module_parent_id = liger.get("module_parent_id").getValue();
    					data.module_parent_id = module_parent_id;
    				  }
    					
    				  if(liger.get("module_is_display")){
    					var module_is_display = liger.get("module_is_display").getValue();
    					data.module_is_display = module_is_display;
    				  }
    		          
    		          realAddModule(filename,0,data,top.$("iframe[src='wkrjsystemdev/wkrjModule/getPage.wk']")[0].contentWindow.$("#maingrid").ligerGetGridManager(),$dialog.parent.$.ligerDialog);
    		          top.$(".l-window-mask").hide();
    		     }  
    		    });
       });
      
	    
</script>

<!-- <div id="form1" class="liger-form">
          <div class="fields">
            <input data-type="text" data-label="标题" data-name="Title" />
             <input data-type="date" data-label="入职日期" data-name="addDate" data-newline="false"/>
              <div data-type="select" data-label="国家" data-name="Country" >
                 <input class="editor"  data-data="getCountryData()" data-onSelected="f_onCountryChanged" data-textField="Country" data-valueField="Country"/> 
              </div>
              <div data-type="select" data-label="城市" data-name="City">
                  <input class="editor"  data-textField="City" data-valueField="City"/>  
             </div>
         </div>  
    </div>  -->

<!--添加菜单开始-->
<form id="module_addWindow_form" method="post" class="liger-form">
	<div style="overflow:hidden;">
		<div class="Co">
			<label>菜单名称:</label>
			<input type="text" name="module_name" class="ui-textbox" />
		</div>
		<div class="Co">
			<label>菜单等级:</label> 
			<select id="module_level" name="module_level" >  
	   			<option value="0">一级菜单</option>  
	   			<option value="1">二级菜单</option>  
			</select>  
		</div>
		<div id="module_parent_id_div" style="display:none" class="Co">
			<label>上级菜单:</label>
			<input id="module_parent_id" name="module_parent_id" />
		</div>
		<div class="Co">
			<label>菜单地址:<br/></label>
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
			<label>菜单图标:</label> <img style="display:none;" src="" id="module_icon">
			<input type="hidden" style="display:none;" name="module_icon"  />
		</div>
		<div style="clear:both">
			<input type="file" name="module_icon_file" id="uploadify" />  
		</div>
	</div>
	<%-- <div id="AppendBill_Div" style="display:none;">    上传 - 单  
     <table style="height:100%;width:100%">  
           <tr style="height:40px">  
               <td style="width:20%">  
                   图标:  
               </td>  
               <td><input type="file"  style="width:200px" id="fileupload" name="fileupload"/>  
               </td>  
           </tr>  
  
     </table>  
    </div> --%>
</form>