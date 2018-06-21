$(function (){
	//获取系统公告
	$.ajax({
		url:'front/web/listNotice',
		type:'post',
		data:{page:1,rows:8},
		dataType:'json',
		success:function (data) {
			if (data.length>0) {
				for (var i = 0; i < data.length; i++) {
					$("#marquee1").append('<a style="margin: 15px 15px;" href="frontpage/notice_detail.jsp?notice_id='+data[i].notice_id+'"><span>'+data[i].notice_title+'</span></a>');
				}
			}
			
		
	  
	   $('#marquee1').liMarquee({
				scrollamount:80
      });
			
		}
	});
	
	
	
	//获取推荐视频
	$.ajax({
		url:'front/web/listRecommendVideo',
		type:'post',
		data:{page:1,rows:3},
		dataType:'json',
		success:function (data) {
			if (data.length>0) {
				for (var i = 0; i < data.length; i++) {
					$("#recommendVideo").append('<li class="clearfix"> <img  src='+data[i].coverpath+' class ="fl d_inb padd_5 width_le width_le2"><div class="font_s18 fl d_inb wid_75">  <a href="frontpage/video_detail.jsp?video_id='+data[i].video_id+'"  class="co_38 box_right_text box_right_text wid_95 d_b">'+data[i].video_name+'</a>  <p class="padd_top co_80 font_s16">'+data[i].content+' </p><p class="texta_r co_80 font_s16">'+data[i].recommendtime+'</p></div><input type="hidden" value="'+data[i].video_id+'"/></li>');
				}
			}
		}
	});
	
	
	
	//获取热门图书
	$.ajax({
		url:'front/web/listHotBooks',
		type:'post',
		data:{page:1,rows:5},
		dataType:'json',
		success:function (data) {
			if (data.length>0) {
				for (var i = 0; i < data.length; i++) {
					$("#listHotBooks").append('<li style="height: 30vh;"> <a href="frontpage/books_detail.jsp?books_id='+data[i].books_id+'"><img src='+data[i].coverpath+' class="wid_274"><div><p class="co_387 font_s18 yincang" >'+data[i].books_name+' </p><p class="co_66  yincang">'+data[i].content+'</p></div></a></li>');
				}
			}
		}
	});
	
	
	//获取指南
	$.ajax({
		url:'front/web/clinic/listClinics',
		type:'post',
		data:{page:1,rows:6},
		dataType:'json',
		success:function (data) {
			if (data.length>0) {
				for (var i = 0; i < data.length; i++) {
					$("#box_right").append('<li class="clearfix"> <a class="box_right_text d_inb wid_79" href="frontpage/clinic_detail.jsp?clinic_id='+data[i].clinic_id+'" >'+data[i].clinic_name+'</a><span class="fr" >'+data[i].addtime+'</span><input type="hidden" value="'+data[i].clinic_id+'"/></li>');
				}
			}
		}
	});
	
	
	
});



function getContent(cid)
{
		
	 window.location.href='frontpage/clinic_detail.jsp?clinic_id='+cid;
}


function getBookContent(bid)
{
	 window.location.href='frontpage/books_detail.jsp?books_id='+bid;
}

function getVideoContent(bid)
{
	 window.location.href='frontpage/video_detail.jsp?video_id='+bid;
}


