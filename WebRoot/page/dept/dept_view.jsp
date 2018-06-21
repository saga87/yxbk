<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/systemdev/myContext.jsp" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
<style>
#table_container {width:97%; margin:0px auto;float:left;}
#table_container table{-background:#666;}
#table_container table td{-background:#fff;font-size:14px; text-align:center;line-height:30px;height:30px;}
#table_container table td input{width:100% !important;outline: none;border:none;}
#table_container table td .l-text{width:100% !important;height:30px;margin:auto;border:none;}
.l-text-field{width:100% !important;background:#fff;height:28px;text-align: left;}
#table_container .td_gray{background-color:#f6f6f6;min-height:30px;line-height:30px;}
</style>
<script type="text/javascript">
	$(function (){
	    var dialog = frameElement.dialog;
	    var dialogData = dialog.get('data');//获取data参数
	
	    $("#dept_viewWindow_form").ligerForm().setData(dialogData.content);
	    	    
		var coc=dialogData.content.city_or_county;
		if(coc=="0"){
			r_city_click();
		}else if(coc=="1"){
			r_county_click();
		}else if(coc=="2"){
			r_xz_click();
		}
		
		//安全管理人员
		$.ajax({  
            url : "wkrjsystem/wkrjDept/getSafePerson",  
            async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected  
            type : "POST",
            data:{dept_id:dialogData.content.dept_id},
            dataType : "json",  
            success : function(res) {
                if(res.obj){
                	if(res.obj.length>0){
                		//科室人员，安全管理人员
                        var html="<tr>";
                        if(coc=="0"||coc=="1"){
    	                    html+="<td rowspan='"+(res.obj.length*2)+"' class='td_gray'>科室人员</td>";
                        }else{
    	                    html+="<td rowspan='"+(res.obj.length*2)+"' class='td_gray'>安全管理人员</td>";
                        }
                        $.each(res.obj,function(i,v){
                        	if(i==0){
                        		html+="<td rowspan='2'>"+v.safe_person_name+"</td>";
                        		html+="<td rowspan='2' class='td_gray'>电话</td>";
                        		html+="<td class='td_gray'>办公电话</td>";
                        		html+="<td class='td_gray'>手机</td>";
                        		html+="<td class='td_gray'>邮箱</td>";
                        		html+="</tr><tr>";
                        		html+="<td>"+v.safe_person_tel+"</td>";
                        		html+="<td>"+v.safe_person_phone+"</td>";
                        		html+="<td>"+v.safe_person_email+"</td>";
                        		html+="</tr>";
                        	}else{
                        		html+="<tr>";
                        		html+="<td rowspan='2'>"+v.safe_person_name+"</td>";
                        		html+="<td rowspan='2' class='td_gray'>电话</td>";
                        		html+="<td class='td_gray'>办公电话</td>";
                        		html+="<td class='td_gray'>手机</td>";
                        		html+="<td class='td_gray'>邮箱</td>";
                        		html+="</tr><tr>";
                        		html+="<td>"+v.safe_person_tel+"</td>";
                        		html+="<td>"+v.safe_person_phone+"</td>";
                        		html+="<td>"+v.safe_person_email+"</td>";
                        		html+="</tr>";
                        	}
                        });
                        $("#safe_person_tbody").html(html);
                        $("#safe_person_tbody").show();
                	}else{
                		//科室人员，安全管理人员
                        var html="<tr>";
                        if(coc=="0"||coc=="1"){
    	                    html+="<td rowspan='2' class='td_gray'>科室人员</td>";
                        }else{
    	                    html+="<td rowspan='2' class='td_gray'>安全管理人员</td>";
                        }
                        html+="<td rowspan='2'>&nbsp;</td>";
                		html+="<td rowspan='2' class='td_gray'>电话</td>";
                		html+="<td class='td_gray'>办公电话</td><td class='td_gray'>手机</td><td class='td_gray'>邮箱</td>";
                		html+="</tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
                        $("#safe_person_tbody").html(html);
                        $("#safe_person_tbody").show();
                	}
                }
            }  
        });
	});
	
	
	function r_city_click(){
		   $("#r_city").ligerRadio().setValue(true);
		   $("#r_county").ligerRadio().setValue(false);
		   $("#r_xz").ligerRadio().setValue(false);
		   
		   $("#juzhang_tbody").show();
		   $("#fenguan_p").html("分管局长");
		   $("#kezhang_p").html("科长（主任）");
		   $("#ptadmin_p").html("平台管理员");
		   $("#city_or_county").val(0);//0市1县2乡镇
	}
	function r_county_click(){
		   $("#r_city").ligerRadio().setValue(false);
		   $("#r_county").ligerRadio().setValue(true);
		   $("#r_xz").ligerRadio().setValue(false);
		   
		   $("#juzhang_tbody").hide();
		   $("#fenguan_p").html("分管局长");
		   $("#kezhang_p").html("科长（主任）");
		   $("#ptadmin_p").html("平台管理员");
		   $("#city_or_county").val(1);//0市1县2乡镇
	}
	function r_xz_click(){
		   $("#r_city").ligerRadio().setValue(false);
		   $("#r_county").ligerRadio().setValue(false);
		   $("#r_xz").ligerRadio().setValue(true);
		   
		   $("#juzhang_tbody").hide();
		   $("#fenguan_p").html("主任");
		   $("#kezhang_p").html("安全管理负责人");
		   $("#ptadmin_p").html("平台管理员");
		   $("#city_or_county").val(2);//0市1县2乡镇
	}
</script>
 </head>
 <body>
<!--添加菜单开始-->
<form id="dept_viewWindow_form" method="post" class="liger-form">


<div class="formdiv" style="display: none;">
    <label style="text-align:right;">组织结构id:</label>
    <input readonly="readonly" name="dept_id" id="dept_id" class="ui-textbox">
</div>
<div class="formdiv">
    <label style="text-align:right;">部门名称:</label>
    <input readonly="readonly" data-options="required:true" name="dept_name" style="border:none;background:#f4f4f4;">
</div>
<div class="formdiv">
    <label>是否县级市:</label>
    <div class="r_div">
    	<a href="javascript:void(0);" style="text-decoration:none;color:black;">
    		<input disabled="disabled" type="radio" id="r_city" name="radiotype" />地市&nbsp;&nbsp;&nbsp;&nbsp;
    	</a>
    	<a href="javascript:void(0);" style="text-decoration:none;color:black;">
	    	<input disabled="disabled" type="radio" id="r_county" name="radiotype" checked="checked" />县市区&nbsp;&nbsp;&nbsp;&nbsp;
    	</a>
    	<a href="javascript:void(0);" style="text-decoration:none;color:black;">
	    	<input disabled="disabled" type="radio" id="r_xz" name="radiotype" />镇（乡、街道）
    	</a>
    	<input type="hidden" value="1" id="city_or_county" name="city_or_county" />
    </div>
    <!-- <label style="text-align:right;">显示顺序:</label>
    <input readonly="readonly" data-options="required:true"
        name="dept_order" style="border:none;background:#f4f4f4;"/> -->
</div>
<div class="formdiv">
    <label style="text-align:right;">备　　注:</label>
    <input readonly="readonly" name="dept_other" style="width:700px;border:none;background:#f4f4f4;"/>
</div>


<div id="table_container">
<table border="1" cellspacing="0" cellpadding="0" width="100%" style="margin-top:20px;">
	<tbody id="juzhang_tbody" style="display:none;">
		<tr>
			<td width="20%" rowspan="2" class="td_gray"><p id="juzhang_p">局长</p></td>
			<td width="16%" rowspan="2"><p><input readonly="readonly" type="text" name="juzhang" id="juzhang" /></p></td>
			<td width="16%" rowspan="2" class="td_gray"><p>电话</p></td>
			<td width="16%" class="td_gray">办公电话</td>
			<td width="16%" class="td_gray">手机</td>
			<td width="16%" class="td_gray">邮箱</td>
		</tr>
		<tr>
			<td width="16%"><p><input readonly="readonly" type="text" name="juzhang_tel" id="juzhang_tel" /></p></td>
			<td width="16%"><p><input readonly="readonly" type="text" name="juzhang_phone" id="juzhang_phone" /></p></td>
			<td width="16%"><p><input readonly="readonly" type="text" name="juzhang_email" id="juzhang_email" /></p></td>
		</tr>
	</tbody>
	<tr>
		<td width="20%" rowspan="2" class="td_gray"><p id="fenguan_p">分管局长</p></td>
		<td width="16%" rowspan="2"><p><input readonly="readonly" type="text" name="fenguan" id="fenguan" /></p></td>
		<td width="16%" rowspan="2" class="td_gray"><p>电话</p></td>
		<td width="16%" class="td_gray">办公电话</td>
		<td width="16%" class="td_gray">手机</td>
		<td width="16%" class="td_gray">邮箱</td>
	</tr>
	<tr>
		<td width="16%"><p><input readonly="readonly" type="text" name="fenguan_tel" id="fenguan_tel" /></p></td>
		<td width="16%"><p><input readonly="readonly" type="text" name="fenguan_phone" id="fenguan_phone" /></p></td>
		<td width="16%"><p><input readonly="readonly" type="text" name="fenguan_email" id="fenguan_email" /></p></td>
	</tr>
	<tr>
		<td width="20%" rowspan="2" class="td_gray"><p id="kezhang_p">科长（主任）</p></td>
		<td width="16%" rowspan="2"><p><input readonly="readonly" type="text" name="kezhang" id="kezhang" /></p></td>
		<td width="16%" rowspan="2" class="td_gray"><p>电话</p></td>
		<td width="16%" class="td_gray">办公电话</td>
		<td width="16%" class="td_gray">手机</td>
		<td width="16%" class="td_gray">邮箱</td>
	</tr>
	<tr>
		<td width="16%"><p><input readonly="readonly" type="text" name="kezhang_tel" id="kezhang_tel" /></p></td>
		<td width="16%"><p><input readonly="readonly" type="text" name="kezhang_phone" id="kezhang_phone" /></p></td>
		<td width="16%"><p><input readonly="readonly" type="text" name="kezhang_email" id="kezhang_email" /></p></td>
	</tr>
	<tr>
		<td width="20%" rowspan="2" class="td_gray"><p id="ptadmin_p">平台管理员</p></td>
		<td width="16%" rowspan="2"><p><input readonly="readonly" type="text" name="ptadmin" id="ptamin" /></p></td>
		<td width="16%" rowspan="2" class="td_gray"><p>电话</p></td>
		<td width="16%" class="td_gray">办公电话</td>
		<td width="16%" class="td_gray">手机</td>
		<td width="16%" class="td_gray">邮箱</td>
	</tr>
	<tr>
		<td width="16%"><p><input readonly="readonly" type="text" name="ptadmin_tel" id="ptadmin_tel" /></p></td>
		<td width="16%"><p><input readonly="readonly" type="text" name="ptadmin_phone" id="ptadmin_phone" /></p></td>
		<td width="16%"><p><input readonly="readonly" type="text" name="ptadmin_email" id="ptadmin_email" /></p></td>
	</tr>
	<tbody id="safe_person_tbody" style="display:none;">
        
    </tbody>
</table>
</div>
</form>
 </body>
 </html>