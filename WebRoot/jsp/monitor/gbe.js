define(function(require, exports) {
	var gbe = {
		nodeId : '',
		ip : '',
		enabled : [ 1, 1, 1, 1, 1, 1, 0, 0 ],
		bitrate : [ 7, 7, 7, 7, 7, 7, 7, 7 ],
		numOfServices : [ 49, 41, 46, 49, 44, 46, 55, 45 ]
	};
	// 解析slots中的数据
	// oData为slots中的每个对象
	// iData为slots下标+1
	var aGbes = function(oData) {
		// 临时数组
		var arrTemp = new Array(), arrTemp1 = new Array(), arrXString = new Array(), oTemp = {};
		for (var i = 0; i < 8; i++) {
			if (1 == oData.enabled[i]) {
				arrTemp.push(oData.bitrate[i]);
				arrTemp1.push(oData.numOfServices[i]);
				arrXString.push('1.1' + '.' + (i + 1));
			}
		}
		oTemp.bitrate = arrTemp;
		oTemp.numOfServices = arrTemp1;
		// 横轴坐标值
		oTemp.xStrs = arrXString;
		return oTemp;
	};

	var chart = null;
	
	// 初始化图表，并保存到数组中
	var refreshCharts = function(gbe) {
		var oSlot = aGbes(gbe);
		if (chart) {
			chart.series[0].setData(oSlot.numOfServices);
			chart.series[1].setData(oSlot.bitrate);
		}
		else {
			chart = new Highcharts.Chart({
				chart : {
					zoomType : 'xy',
					renderTo : 'containergbe'
				},
				plotOptions : {
					series : {
						animation : false
					}
				},
				title : {
					text : ''
				},
				subtitle : {
					text : ''
				},
				xAxis : [ {
					categories : oSlot.xStrs
				} ],
				yAxis : [ { // Primary yAxis
					labels : {
						formatter : function() {
							return this.value + 'k';
						},
						style : {
							color : '#89A54E'
						}
					},
					title : {
						text : 'Bitrate',
						style : {
							color : '#89A54E',
							fontSize : '20px'
						}
					},
					opposite : true
				}, { // Secondary yAxis
					gridLineWidth : 0,
					title : {
						text : 'Service',
						style : {
							color : '#4572A7',
							fontSize : '20px'
						}
					},
					labels : {
						formatter : function() {
							return this.value + 'g';
						},
						style : {
							color : '#4572A7'
						}
					}
				} ],
				tooltip : {
					shared : false
				},
				legend : {
					align : 'left',
					x : 240,
					borderWidth : 0,
					lineHeight : 12,
					margin : 0,
					padding : -2,
					y : 10,
					floating : true,
					backgroundColor : '#ffffff'
				},
				series : [ {
					name : 'Service',
					color : '#4572A7',
					type : 'column',
					yAxis : 1,
					data : oSlot.numOfServices,
					tooltip : {
						valueSuffix : 'g'
					}

				}, {
					name : 'Bitrate',
					color : '#89A54E',
					type : 'column',
					data : oSlot.bitrate,
					tooltip : {
						valueSuffix : 'k'
					}
				} ]
			});
		}
		$(".highcharts-button").css("display", "none");
		$(".highcharts-legend").css("display", "none");
	};
	
	refreshCharts(gbe);
	
	// 动态刷新图标中数据
	function do_query(ip) {
		var fn = function() {
			return $jq.ajax({
	      type: 'POST',
	      url: exports.host + 'monitor_query_queryDevicesCurrentBitrates.sip',
	      dataType: 'json',
	      //data: '"nodeIds":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246,a89c0829-9d2d-4d1a-996e-07bbdcfdd247","start":"2013/11/23 14:00","end":"2013/11/23 14:35","period":"0","chartType":"1"',
	      data: {
	        ipaddress: ip
	      }
	    });
		}
		
		fn().done(function(data) {
      if (data != null) {
      	refreshCharts(data);
      }
    }).always(function() {
    	setTimeout(fn, 5000);
    });
	}
	
	exports.host = '';
	exports.run = function(ip) {
		do_query(ip);
	}
});
