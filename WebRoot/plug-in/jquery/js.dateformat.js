//重写toString方法，将时间转换为Y-m-d H:i:s格式
Date.prototype.toString = function(){
	return this.getFullYear() + "-" + (this.getMonth()+1) + "-" + this.getDate() + " " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();	
}
//格式化时间字符串
Date.prototype.toFormatString = function(format){
	if(format == ""){
		return this.toString();	
	}
	var str = '';
	str = format.replace(/Y|y/,this.getFullYear())
				.replace(/M|m/,this.getMonth() + 1)
				.replace(/D|d/,this.getDate())
				.replace(/H|h/,this.getHours())
				.replace(/I|i/,this.getMinutes())
				.replace(/S|s/,this.getSeconds());
	return str;				
}
//在当前时间上添加年数
Date.prototype.addYear = function(years){
	var cyear = this.getFullYear();
	cyear += years;
	this.setYear(cyear);
	return this;	
}
//在当前时间上添加天数
Date.prototype.addDay = function(days){
	var cd = this.getDate();
	cd += days;
	this.setDate(cd);
	return this;
}
//在当前时间上添加月数
Date.prototype.addMonth = function(months){
	var cm = this.getMonth();
	cm += months;
	this.setMonth(cm);	
	return this;
}
//将php时间格式(Y-m-d H:i:s)转化为js日期对象
function phpDateToJsDate(phpDate){
	if(phpDate == ""){
		return new Date();	
	}
	return new Date(Date.parse(phpDate.replace(/-/g,"/")));
}
