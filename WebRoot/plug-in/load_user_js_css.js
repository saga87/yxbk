
var args = new Array(
		//引入ligerui的js和css
		"plug-in/ligerui/ligerUI/skins/Aqua/css/ligerui-all.css",
		"plug-in/ligerui/ligerUI/skins/ligerui-icons.css",
		"plug-in/ligerui/jquery/jquery-1.9.0.min.js", 
		"plug-in/ligerui/ligerUI/js/ligerui.all.js",
		"plug-in/ligerui/jquery.cookie.js",
		//引入其他js和css
		"system/css/public.css",
		"system/icons/wkrjicon.css",
		"system/js/main/main.js",
		"system/js/main/tab_deve.js");

//导入
importDoc(args);

function importDoc(arguments) //函数可以批量引入多个js、css
{
	for (var i = 0; i < arguments.length; i++) {
		var file = arguments[i];
		if (file.match(/.*\.js$/)){
			document.write('<script type="text/javascript" src="' + file + '"></script>');
		}
		else if (file.match(/.*\.css$/)){
			document.write('<link rel="stylesheet" href="' + file + '" type="text/css" />');
		}
	}
}

function yinru(argument) //函数可以单独引入一个js或者css
{
	var file = argument;
	if (file.match(/.*\.js$/)) //以任意开头但是以.js结尾正则表达式
	{
		document.write('<script type="text/javascript" src="' + file + '"></script>');
	} else if (file.match(/.*\.css$/)) {
		document.write('<link rel="stylesheet" href="' + file + '" type="text/css" />');
	}
}