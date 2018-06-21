/*************************
**洛城楼村房管理平台**
*********Login.js*********
*************************/
$(function () {
	$(".username").focus(function(){
		if($(this).val()=='用户名'){
			$(this).val('');
		}
	});
	$(".username").blur(function(){
		if($.trim($(this).val())==''){
			$(this).val('用户名');
		}
	});
	$(".pass1").focus(function(){
		$(this).hide();
		$(".pass2").show().focus();
	})
	$(".pass2").blur(function(){
		if($(".pass2").val()==''){ 
			$(".pass2").hide();
			$(".pass1").show();
		}
	});
	$(".verif input").focus(function(){
		if($(this).val()=='验证码'){
			$(this).val('');
		}
	});
	$(".verif input").blur(function(){
		if($(this).val()==''){
			$(this).val('验证码');
		}
	});
	$('form').submit(function () {
		var username=$('.username');
		var password1=$('.pass1');
		var password2=$('.pass2');
		var verif=$('.verif input');
		$(this).find('input').removeClass('cur');
		if(username.val()=='用户名'){
			username.focus().addClass('cur');
			return false;
		}else if(password2.val()==''){
			password1.hide();
			password2.show().focus().addClass('cur');
			return false;
		}else if (verif.val()=='验证码'){
			verif.focus().addClass('cur');
			return false;
		}else{
			return true;
		}
	});
});