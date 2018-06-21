var func = function(ctt,id) {
    				var sum = ctt.length;
                          if (sum>0) {
                          for (var i = 0; i < sum; i++) {
                          
                          var path = ctt[i].coverpath;
                          	$("#"+id).append('<li> <a href="frontpage/video_detail.jsp?video_id='+ctt[i].video_id+'"> <img   src=' +path+ '  class="img_a " style="height: 25vh;"> <div class="text_name co_66 fs_14"><p class="co_387 font_s18">'+ctt[i].video_name+'</p><p>主讲：'+ctt[i].speaker+'</p><p>单位：'+ctt[i].speaker_unit+'</p><p>日期：'+ctt[i].addtime+'</p></div></a></li>');
                          	
                          	} 
                          }
				};


$(function (){
	var divid = "topdiv";
	//获取推荐视频
	$.ajax({
		url:'front/web/listRecommendVideo',
		type:'post',
		data:{page:1,rows:4},
		dataType:'json',
		success:function (data) {
			if (data.length>0) {
				for (var i = 0; i < data.length; i++) {
					$("#recommendVideo").append('<li ><a href="frontpage/video_detail.jsp?video_id='+data[i].video_id+'"> <img style=" width: 100%;height: 31vh;" src='+data[i].coverpath+' ></a><span class="li_span_yy fs2 fcolor">'+data[i].video_name+'</span><input type="hidden" value="'+data[i].video_id+'"/></li>');
				}
			}
		}
	});
	
	//获取最新视频
	$.ajax({
		url:'front/web/video/listNewVideo',
		type:'post',
		data:{page:1,rows:7},
		dataType:'json',
		success:function (data) {
			if (data.length>0) {
				for (var i = 0; i < data.length; i++) {
					$("#newvideo").append('<li ><a href="frontpage/video_detail.jsp?video_id='+data[i].video_id+'"><span class="span_jz">'+data[i].video_name+' </span> <span class="time fr">'+data[i].addtime+'</span></a><input type="hidden" value="'+data[i].video_id+'"/></li>');
				}
			}
		}
	});
  
  	$.ajax({
		url:'front/web/video/getVideoData',
		type:'post',
		dataType:'json',
		success:function (data) {
			
			
			
			
			
			if (data.length>0) {
				for (var i = 0; i < 5; i++) {

					$("#videotype").append('<li class="layui-nav-item li_item"><a href="frontpage/video.jsp?data_id='+data[i].data_id +'" class="fl">'+data[i].data_name+'</a><span class="span_right"></span></li>');
					
					$("#"+divid).after('<div class="main_center" id='+('div'+i)+'> <div class="fr wid_100 mar_top"><div class="fl_top bac_38 font_s16 co_ff clearfix" >'
	                     	+'<span>'+data[i].data_name+'</span><a href="frontpage/video_more.jsp?data_id='+data[i].data_id+'" class="co_ff fr">更多>></a>'
	                     	+'</div></div><div><ul class="box_ull zh_ul clearfix"   id='+('ul'+i)+'></ul></div></div>');
	                     	
	                     $.ajax({
	                    url: "front/web/video/listVideos",
	                    data: {page:1,rows:3,data_id:data[i].data_id},
	                    dataType : "json", 
	                    async:false, 
	                    type : "POST",
	                    success: function(res){        
	                    		func(res,"ul"+i);
	                     	}
	               		});   
	                     	
	                     	
	                     	divid = "div"+i;
				}
				
				$("#videotype").append('<li class="layui-nav-item li_item"><a href="javascript:;"   class="fl" id="gengduo">更多</a><dl class="dl_child layui_child layui-nav-child" style="left:-350px;" id="gengduo1"></dl></li>');
				
				if(data.length<5){
					return;
				}
				
				for (var i = 5; i < data.length; i++) {

					$("#gengduo1").append('<dd class="child_left"><a href="frontpage/video.jsp?data_id='+data[i].data_id +'" class="">'+data[i].data_name+'</a></dd>');
					
					$("#"+divid).after('<div class="main_center" id='+('div'+i)+'> <div class="fr wid_100 mar_top"><div class="fl_top bac_38 font_s16 co_ff clearfix" >'
	                     	+'<span>'+data[i].data_name+'</span><a href="frontpage/video_more.jsp?data_id='+data[i].data_id+'" class="co_ff fr">更多>></a>'
	                     	+'</div></div><div><ul class="box_ull zh_ul clearfix"   id='+('ul'+i)+'></ul></div></div>');
	                     	
	                     $.ajax({
	                    url: "front/web/video/listVideos",
	                    data: {page:1,rows:3,data_id:data[i].data_id},
	                    dataType : "json", 
	                    async:false, 
	                    type : "POST",
	                    success: function(res){        
	                    		func(res,"ul"+i);
	                     	}
	               		});   
	                     	
	                     	
	                     	divid = "div"+i;
				}
				layui.use('element', function(){
					  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
					  
					  //监听导航点击
					  element.on('nav(demo)', function(elem){
						//console.log(elem)
						layer.msg(elem.text());
					  });
					});
				
			}
		}
	});
  	
	
});



