/******************/
$(function () {
	$('.LoginForm form').submit(function () {
		var username=$('.username input');
		var password=$('.password input');
		$(this).find('input').removeClass('cur');
		if(username.val()==''){
			username.focus().addClass('cur');
			return false;
		}else if(password.val()==''){
			password.focus().addClass('cur');
			return false;
		}else{
			return true;
		}
	});
	//系统登录页
	$('.SysLoginForm form').submit(function () {
		var username=$('.Sysuser input');
		var password=$('.Syspass input');
		$(this).find('input').removeClass('cur');
		if(username.val()==''){
			username.focus().addClass('cur');
			return false;
		}else if(password.val()==''){
			password.focus().addClass('cur');
			return false;
		}else{
			return true;
		}
	});
	//
/*	$('.menu a').click(function () {
		var Oindex=$(this).index();
		var OLen=$(this).parent().find("a").length;
		if(OLen!=1){
			switch(Oindex){
				case 0:
					$('.Msc').show();
					$('.adDiv').show();
					$('.adDiv p').eq(1).hide();
					$('.adDiv p').eq(0).show().children('textarea').focus();
					break;
				case 1:
					$('.Msc').show();
					$('.adDiv').show();
					$('.adDiv p').eq(0).hide();
					$('.adDiv p').eq(1).show().children('textarea').focus();
					break;
				case 2:
					$('.Msc').hide();
					$('.adDiv').hide();
					break;
			}
		}
	});*/
	//
	$('.File input[type=file]').change(function () {
		$(this).siblings('input[type=text]').val($(this).val());
	});
	//
//	$('.UpdateFile .InfoUpInp a').click(function () {
//		$(this).parent().remove();
//	});
	var OlistIndex = 0;
	$('.UpdateNews ul li span').click(function () {
		OlistIndex = $(this).parent().index();
		$('.Msc').show();
		$('.DeleteTileBar').show();
		$('.DeleteTile b:eq(0)').attr('index',OlistIndex);
	});
	$('.DeleteTile b:eq(0)').click(function () {
		var OcurIndex=$(this).attr('index');
		//$('.UpdateNews ul li').eq(OcurIndex).remove();
		$('.Msc').hide();
		$('.DeleteTileBar').hide();
	});
	$('.DeleteTile b:eq(1)').click(function () {
		$('.Msc').hide();
		$('.DeleteTileBar').hide();
	});
	//
	$('.UpfileList ul li').each(function (index) {
		$(this).find('span').html(index+1);
	});
	//
	$('.UpdateNews ul li a').click(function () {
		var Obj = $(this);
		if(Obj.hasClass('Upico2')){
			$(this).removeClass('Upico2');
		}else{
			$(this).addClass('Upico2');
		}
	});
	//
	$('.AddSub').bind('touchstart',function () {
		var Ohtml = $('.addMate').html();
		$('.NewAdd').append("<div class='addMate'>"+Ohtml+"</div>")
	});
	//
	$('.File input[type=file]').change(function () {
		if(!!$(this).val()){
			$(this).siblings('input[type=text]').val($(this).val());
			$(this).siblings('input[type=button]').addClass('cur');
		}else{
			$(this).siblings('input[type=text]').val('');
			$(this).siblings('input[type=button]').removeClass('cur');
		}
	});
});