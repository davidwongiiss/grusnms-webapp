define(function(require, exports) {

	/*
	 * var qam = { nodeId : '', ip : '', slots : [ // 9个slot数组(9个模块卡) { slotNo :
	 * '', qams : [ [ 1, 1, 1, 1, 1, 1, 0, 0 ], [ 1, 1, 1, 1, 1, 1, 0, 0 ] ], //
	 * 16长的数组，为0的都不显示 bitrate : [ [ 7, 7, 7, 7, 7, 7, 7, 7 ], [ 7, 7, 7, 7, 7, 7,
	 * 7, 7 ] ], numOfServices : [ [ 49, 41, 46, 49, 44, 46, 55, 45 ], [ 49, 41,
	 * 46, 49, 44, 46, 55, 45 ] ] }, { slotNo : '', qams : [ [ 1, 1, 1, 1, 1, 1,
	 * 0, 0 ], [ 1, 1, 1, 1, 1, 1, 0, 0 ] ], // 16长的数组，为0的都不显示 bitrate : [ [ 7, 7,
	 * 7, 7, 7, 7, 7, 7 ], [ 7, 7, 7, 7, 7, 7, 7, 7 ] ], numOfServices : [ [ 49,
	 * 41, 46, 49, 44, 46, 55, 45 ], [ 49, 41, 46, 49, 44, 46, 55, 45 ] ] }, {
	 * slotNo : '', qams : [ [ 1, 1, 1, 1, 1, 1, 0, 0 ], [ 0, 0, 0, 1, 0, 1, 0, 0 ] ], //
	 * 16长的数组，为0的都不显示 bitrate : [ [ 7, 7, 7, 7, 7, 7, 7, 7 ], [ 7, 7, 7, 7, 7, 7,
	 * 7, 7 ] ], numOfServices : [ [ 49, 41, 46, 49, 44, 46, 55, 45 ], [ 49, 41,
	 * 46, 49, 44, 46, 55, 45 ] ] }, { slotNo : '', qams : [ [ 1, 1, 1, 1, 1, 1,
	 * 0, 0 ], [ 0, 0, 0, 1, 0, 1, 0, 0 ] ], // 16长的数组，为0的都不显示 bitrate : [ [ 7, 7,
	 * 7, 7, 7, 7, 7, 7 ], [ 7, 7, 7, 7, 7, 7, 7, 7 ] ], numOfServices : [ [ 49,
	 * 41, 46, 49, 44, 46, 55, 45 ], [ 49, 41, 46, 49, 44, 46, 55, 45 ] ] }, {
	 * slotNo : '', qams : [ [ 1, 1, 1, 1, 1, 1, 0, 0 ], [ 1, 1, 1, 1, 1, 1, 0, 0 ] ], //
	 * 16长的数组，为0的都不显示 bitrate : [ [ 7, 7, 7, 7, 7, 7, 7, 7 ], [ 7, 7, 7, 7, 7, 7,
	 * 7, 7 ] ], numOfServices : [ [ 49, 41, 46, 49, 44, 46, 55, 45 ], [ 49, 41,
	 * 46, 49, 44, 46, 55, 45 ] ] }, { slotNo : '', qams : [ [ 1, 1, 0, 0, 0, 0,
	 * 0, 0 ], [ 1, 1, 0, 0, 0, 0, 0, 0 ] ], // 16长的数组，为0的都不显示 bitrate : [ [ 7, 7,
	 * 7, 7, 7, 7, 7, 7 ], [ 7, 7, 7, 7, 7, 7, 7, 7 ] ], numOfServices : [ [ 49,
	 * 41, 46, 49, 44, 46, 55, 45 ], [ 49, 41, 46, 49, 44, 46, 55, 45 ] ] }, {
	 * slotNo : '', qams : [ [ 1, 1, 0, 0, 0, 0, 0, 0 ], [ 1, 1, 0, 0, 0, 0, 0, 0 ] ], //
	 * 16长的数组，为0的都不显示 bitrate : [ [ 7, 7, 7, 7, 7, 7, 7, 7 ], [ 7, 7, 7, 7, 7, 7,
	 * 7, 7 ] ], numOfServices : [ [ 49, 41, 46, 49, 44, 46, 55, 45 ], [ 49, 41,
	 * 46, 49, 44, 46, 55, 45 ] ] }, { slotNo : '', qams : [ [ 1, 1, 0, 0, 0, 0,
	 * 0, 0 ], [ 1, 1, 0, 0, 0, 0, 0, 0 ] ], // 16长的数组，为0的都不显示 bitrate : [ [ 7, 7,
	 * 7, 7, 7, 7, 7, 7 ], [ 7, 7, 7, 7, 7, 7, 7, 7 ] ], numOfServices : [ [ 49,
	 * 41, 46, 49, 44, 46, 55, 45 ], [ 49, 41, 46, 49, 44, 46, 55, 45 ] ] }, {
	 * slotNo : '', qams : [ [ 1, 1, 0, 0, 0, 0, 0, 0 ], [ 1, 1, 0, 0, 0, 0, 0, 0 ] ], //
	 * 16长的数组，为0的都不显示 bitrate : [ [ 7, 7, 7, 7, 7, 7, 7, 7 ], [ 7, 7, 7, 7, 7, 7,
	 * 7, 7 ] ], numOfServices : [ [ 49, 41, 46, 49, 44, 46, 55, 45 ], [ 49, 41,
	 * 46, 49, 44, 46, 55, 45 ] ] } ] };
	 */

	// 解析slots中的数据
	// oData为slots中的每个对象
	// iData为slots下标+1
	var aSlots = function(oData, iData) {
		// 临时数组
		var arrTemp = new Array(), arrTemp1 = new Array(), arrXString = new Array(), oTemp = {};
		for (var i = 0; i < 2; i++) {
			for (var j = 0; j < 8; j++) {
				if (1 == oData.qams[i][j]) {
					arrTemp.push(oData.bitrates[i][j]);
					arrTemp1.push(oData.numOfServices[i][j]);
					arrXString.push(iData + '.' + (i + 1) + '.' + (j + 1));
				}
			}
			// 加0显示空格
			if (0 == i) {
				arrTemp.push(0);
				arrTemp1.push(0);
				arrXString.push('      ');
			}
		}
		oTemp.bitrates = arrTemp;
		oTemp.numOfServices = arrTemp1;
		// 横轴坐标值
		oTemp.xStrs = arrXString;
		return oTemp;
	};

	var chartsArr = [];
	// 初始化图表，并保存到数组中
	var refreshCharts = function(qam) {
		for (var i = 0, length = qam.slots.length; i < length; i++) {
			// 循环判断slots[i]中的数据
			// 返回一个处理后的json对象
			var oSlot = aSlots(qam.slots[i], i + 1);
			if (chartsArr.length == 9) {
				try {
					chartsArr[i].series[1].setData(oSlot.bitrates, false);
					chartsArr[i].series[0].setData(oSlot.numOfServices, false);
					chartsArr[i].redraw();
				}
				catch (e) {
				}
			}
			else {
				chartsArr[i] = new Highcharts.Chart({
					chart : {
						zoomType : 'xy',
						renderTo : 'container' + i
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
								return (this.value / 1000) + 'k';
							},
							style : {
								color : '#89A54E'
							}
						},
						title : {
							text : 'Birate',
							style : {
								color : '#89A54E'
							}
						},
						opposite : true
					}, { // Secondary yAxis
						gridLineWidth : 0,
						title : {
							text : 'Services',
							style : {
								color : '#4572A7'
							}
						},
						labels : {
							formatter : function() {
								return this.value;
							},
							style : {
								color : '#4572A7'
							}
						},
						opposite : false
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
							valueSuffix : ''
						}
					}, {
						name : 'Bitrate',
						color : '#89A54E',
						type : 'column',
						data : oSlot.bitrates,
						tooltip : {
							valueSuffix : ''
						}
					} ]
				});
			}
		}
		$(".highcharts-button").css("display", "none");
	};

	exports.host = '';
	exports.run = function(ids) {
	  var fn = function() {
	    $.ajax({
	      type: 'POST',
	      url: exports.host + '/nodes/monitor_query_queryDevicesCurrentQamBitrates.sip',
	      dataType: 'json',
	      data: {
	        ids: ids
	      }
	    }).done(function(data) {
	      if (data != null && $.isArray(data) && data.length > 0) {
	        try {
	          refreshCharts(data[0]);
	        }
	        catch (ex) {
	          console.log(ex.message);
	        }
	      }	      
	    }).always(function() {
	    	setTimeout(fn, 5000);
	    });
	  };

	  fn();
	};
});
