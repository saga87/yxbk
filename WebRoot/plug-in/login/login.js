$(function(){


	
});

function checkUsr(){
	
	var name=$("input[name='username']").val();
	var pw=$("input[name='password']").val();
	
	$.ajax({
		url:'login/login/checkUser',
		type:'post',
		dataType:'json',
		data:{name:name,pw:pw},
		success:function(res){
			
			if(res.success==true){
				window.location.href="login/login/login";
			}else{
				$("#messageDiv").html(res.msg);
			}
		}
	});
}