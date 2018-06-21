$(function(){

//    //刷新
//    $("#m-refresh").click(function(){
//        var currTab = $('#mainTabs').tabs('getSelected');    //获取选中的标签项
//        var url = $(currTab.panel('options').content).attr('src');    //获取该选项卡中内容标签（iframe）的 src 属性
//        /* 重新设置该标签 */
//        $('#mainTabs').tabs('update',{
//            tab:currTab,
//            options:{
//                content: createTabContent(url)
//            }
//        })
//    });
    
    //关闭所有
    $("#m-closeall").click(function(){
        $(".tabs li").each(function(i, n){
            var title = $(n).text();
            if(title!='欢迎界面'){
           	 $('#mainTabs').tabs('close',title);
            }    
        });
    });
    
    //除当前之外关闭所有
    $("#m-closeother").click(function(){
        var currTab = $('#mainTabs').tabs('getSelected');
        currTitle = currTab.panel('options').title;    
        
        $(".tabs li").each(function(i, n){
            var title = $(n).text();
            
            if(currTitle != title && title!='欢迎界面'){
                $('#mainTabs').tabs('close',title);            
            }
        });
    });
    
    //关闭当前
    $("#m-close").click(function(){
        var currTab = $('#mainTabs').tabs('getSelected');
        currTitle = currTab.panel('options').title;  
        if(currTitle!='欢迎界面'){
        	$('#mainTabs').tabs('close', currTitle);
        }
    });
});