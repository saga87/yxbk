$(function(){
		//alert("sdf");
		  $("#userName").focus();
		  	
		//用户名
			$("#userName").focus(function(){
				$("#userName").css("color","#000");
				if($(this).val()=='用户名'){
					$(this).val('');
					//$(this).css("color","#999");
				}
			 });
			$("#userName").blur(function(){
				if($(this).val()==''){
//					$(this).val('用户名');
					$(this).css("color","#999");
				}else{
					$(this).css("color","#000");
				}
			 });
		 //密码
			 $("#passwords").focus(function(){
				$(this).hide();
				$("#passwords2").show();
				$("#passwords2").focus();
			    $("#passwords2").css("color","#000");
			 })
			 $("#passwords2").blur(function(){
			    //alert("sadf");
				if($("#passwords2").val()==''){ 
					$("#passwords2").hide();
			   		$("#passwords").show();
				}
		 })
		
	});