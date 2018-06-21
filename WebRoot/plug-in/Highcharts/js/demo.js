//折线图
function toLine(containerline,title,data,xdata,ytitle){
	try {
		data = eval(data);
	} catch (e) {
		// TODO: handle exception
	}
	chart = new Highcharts.Chart({
		chart : {
			renderTo : containerline,//显示内容的div
			plotBackgroundColor : null,
			plotBorderWidth : 1,
			plotShadow : false
		},
		title : {
			text : title//标题
		},
		xAxis : {
			categories : xdata//x轴坐标
		},
		yAxis:{
		   title:{
			   text:ytitle
		   },
		   min: 0
		},
		tooltip : {
			shadow: false,
			percentageDecimals : 1,
			formatter: function() {
				return  '<b>'+ this.point.name + '</b>:' +  parseInt(this.percentage) +'人';
			}

		},
		exporting:{
			//url:'${ctxPath}/reportDemoController.do?export',//下载地址   //Highcharts.numberFormat(this.percentage, 1)
			filename:title//下载标题
		},  
		plotOptions : {
			line : {
				allowPointSelect : true,
				cursor : 'pointer',
				showInLegend : true,
				dataLabels : {
					enabled : true,
					color : '#000000',
					connectorColor : '#000000',
					formatter : function() {
						return '<b>' + this.point.name + '</b>: ' + parseInt(this.percentage)+"人";
					}
				}
			}
		},
		series : data
	});
}
//柱形图
function toCol(containerCol,title,data,xdata,ytitle){
	data = eval(data);
	chart = new Highcharts.Chart({
		chart : {
			renderTo : containerCol,//显示内容的div
			plotBackgroundColor : null,
			plotBorderWidth : 1,
			plotShadow : false
		},
		title : {
			text : title//标题
		},
		xAxis : {
			categories : xdata//x坐标内容
		},
		yAxis:{
		   title:{
			   text:ytitle
		   }
		},
		tooltip : {
			percentageDecimals : 1,
			formatter: function() {
				return  '<b>'+this.point.name + '</b>:' +  parseInt(this.percentage) +'人';
			}

		},
		exporting:{
			//url:'${ctxPath}/reportDemoController.do?export',//下载地址
			filename:title//下载标题
		},
		plotOptions : {
			column : {
				allowPointSelect : true,
				cursor : 'pointer',
				showInLegend : true,
				dataLabels : {
					enabled : true,
					color : '#000000',
					connectorColor : '#000000',
					formatter : function() {
						return '<b>' + this.point.name + '</b>: ' +parseInt(this.percentage)+"人";
					}
				}
			}
		},
		series : data
	});
}
//饼状图
function toPie(containerPie,title,data,xdata){
	data = eval(data);
	chart = new Highcharts.Chart({
		chart : {
			renderTo : containerPie,//显示内容的div
			plotBackgroundColor : null,
			plotBorderWidth : 1,
			plotShadow : false
		},
		title : {
			text : title//标题
		},
		xAxis : {
			categories : xdata//x坐标内容
		},
		tooltip : {
			shadow: false,
			percentageDecimals : 1,
			formatter: function() {
				return  '<b>'+this.point.name + '</b>:' +  Highcharts.numberFormat(this.percentage, 1) +'%';
			}

		},
		exporting:{
			//url:'${ctxPath}/reportDemoController.do?export',//下载地址
			filename:title//下载标题
		},  
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				showInLegend : true,
				dataLabels : {
					enabled : true,
					color : '#000000',
					connectorColor : '#000000',
					formatter : function() {
						return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 1)+"%";
					}
				}
			}
		},
		series : data
	});
}

//折线图_人大政协
function toLineRdzx(containerline,title,data,xdata,ytitle){
	try {
		data = eval(data);
	} catch (e) {
		// TODO: handle exception
	}
	chart = new Highcharts.Chart({
		chart : {
			renderTo : containerline,//显示内容的div
			plotBackgroundColor : null,
			plotBorderWidth : 1,
			plotShadow : false
		},
		title : {
			text : title//标题
		},
		xAxis : {
			categories : xdata//x轴坐标
		},
		yAxis:{
		   title:{
			   text:ytitle
		   },
		   min: 0,
           plotLines: [{
               value: 0,
               width: 1,
               color: '#808080'
           }]
		},
		tooltip : {
			shadow: false,
			percentageDecimals : 1,
			formatter: function() {
				return '<b>' + this.point.series.name + '</b>: ' + parseInt(this.point.config)+"人";
			}

		},
		exporting:{
			//url:'${ctxPath}/reportDemoController.do?export',//下载地址   //Highcharts.numberFormat(this.percentage, 1)
			filename:title//下载标题
		},  
		plotOptions : {
			line : {
				allowPointSelect : true,
				cursor : 'pointer',
				showInLegend : true,
				dataLabels : {
					enabled : true,
					color : '#000000',
					connectorColor : '#000000',
					formatter : function() {
						return parseInt(this.point.config)+"人";
					}
				}
			}
		},
		series : data
	});
}

//折线图  专业技术资格、十二类别
function toLineJobTypenos(containerline,title,data,xdata,ytitle){
	try {
		data = eval(data);
	} catch (e) {
		// TODO: handle exception
	}
	chart = new Highcharts.Chart({
		chart : {
			renderTo : containerline,//显示内容的div
			plotBackgroundColor : null,
			plotBorderWidth : 1,
			plotShadow : false
		},
		title : {
			text : title//标题
		},
		xAxis : {
			categories : xdata//x轴坐标
		},
		yAxis:{
		   title:{
			   text:ytitle
		   },
		   min: 0
		},
		tooltip : {
			shadow: false,
			percentageDecimals : 1,
			formatter: function() {
				return  '<b>'+ this.point.name + '</b>:' +  parseInt(this.percentage) +'人';
			}

		},
		exporting:{
			//url:'${ctxPath}/reportDemoController.do?export',//下载地址   //Highcharts.numberFormat(this.percentage, 1)
			filename:title//下载标题
		},  
	/*	plotOptions : {
			line : {
				allowPointSelect : true,
				cursor : 'pointer',
				showInLegend : true,
				dataLabels : {
					enabled : true,
					color : '#000000',
					connectorColor : '#000000',
					formatter : function() {
						return '<b>' + this.point.name + '</b>: ' + parseInt(this.percentage)+"人";
					}
				}
			}
		},*/
		series : data
	});
}
//柱形图  专业技术资格、十二类别
function toColJobTypenos(containerCol,title,data,xdata,ytitle){
	data = eval(data);
	chart = new Highcharts.Chart({
		chart : {
			renderTo : containerCol,//显示内容的div
			plotBackgroundColor : null,
			plotBorderWidth : 1,
			plotShadow : false
		},
		title : {
			text : title//标题
		},
		xAxis : {
			categories : xdata//x坐标内容
		},
		yAxis:{
		   title:{
			   text:ytitle
		   }
		},
		tooltip : {
			percentageDecimals : 1,
			formatter: function() {
				return  '<b>'+this.point.name + '</b>:' +  parseInt(this.percentage) +'人';
			}

		},
		exporting:{
			//url:'${ctxPath}/reportDemoController.do?export',//下载地址
			filename:title//下载标题
		},
		/*plotOptions : {
			column : {
				allowPointSelect : true,
				cursor : 'pointer',
				showInLegend : true,
				dataLabels : {
					enabled : true,
					color : '#000000',
					connectorColor : '#000000',
					formatter : function() {
						return '<b>' + this.point.name + '</b>: ' +parseInt(this.percentage)+"人";
					}
				}
			}
		},*/
		series : data
	});
}