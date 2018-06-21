/*
*	JQuery Document
*/
$(function () {
	
});

$(document).keydown(function(event) {
	if (event.keyCode == 13) {
		$("#longinbutton").click();
	}
});
// 用户登录
function login() {
	var username = $("input[name='username']").val();
	var password =  $("input[name='password']").val();
	if (username == "" || username == "用户名") {
		alert('用户名不能为空');
		return;
	}
	if (password == "") {
		alert('密码不能为空');
		return;
	}
	// 用户提交
	$.post(base+'wkrjsystem/wkrjlogin/checkLogin', {
		username : username,
		password : password
	}, function(result) {
		try{
			var result = eval('(' + result + ')');
		}catch(e){
		}
		if(true==result.success){
			window.location.href =base+"wkrjsystem/wkrjlogin/login";
		}else{
			alert(result.msg);
		}
	});
}

//自动更新验证码
	function reloadImg(obj){
		obj.src="validateCodeServlet?param="+Math.random();
	}

