$(function(){
    
    //关闭所有
    $("#m-closeall").click(function(){
        $(".tabs li").each(function(i, n){
            var title = $(n).text();
            if(title!='欢迎界面'){
           	 $('#mainTabs_dev').tabs('close',title);
            }    
        });
    });
    
    //除当前之外关闭所有
    $("#m-closeother").click(function(){
        var currTab = $('#mainTabs_dev').tabs('getSelected');
        currTitle = currTab.panel('options').title;    
        
        $(".tabs li").each(function(i, n){
            var title = $(n).text();
            
            if(currTitle != title && title!='欢迎界面'){
                $('#mainTabs_dev').tabs('close',title);            
            }
        });
    });
    
    //关闭当前
    $("#m-close").click(function(){
        var currTab = $('#mainTabs_dev').tabs('getSelected');
        currTitle = currTab.panel('options').title;  
        if(currTitle!='欢迎界面'){
        	$('#mainTabs_dev').tabs('close', currTitle);
        }
    });
});