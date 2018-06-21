/*************************
**洛城楼村房管理平台**
*********page.js**********
*************************/
$(function () {
	var ow=$(window).width()/640;
	$("body,html").css("font-size",ow*25);
	$("#bgbg").height($("#bgbg2").height());
	
		var text = $("#addto1").html();
	   $(".Addto a").click(function(){
	        $(".AdhisArt").append(text);
	   });
});