define(function(require, exports) {
	var gbe = {
		nodeId : '',
		ip : '',
		enabled : [ 1, 1, 1, 1, 1, 1, 0, 0 ],
		bitrate : [ 7, 7, 7, 7, 7, 7, 7, 7 ],
		numOfServices : [ 49, 41, 46, 49, 44, 46, 55, 45 ]
	};
	// ����slots�е�����
	// oDataΪslots�е�ÿ������
	// iDataΪslots�±�+1
	var aGbes = function(oData) {
		// ��ʱ����
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
		// ��������ֵ
		oTemp.xStrs = arrXString;
		return oTemp;
	};
	var chart;
	// ��ʼ��ͼ�������浽������
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
	// ��̬ˢ��ͼ��������
	function do_query() {
		var data = gbe;
		refreshCharts(data);
		// ��֤ˢ����ͼ���3s���ٴε���
	//	setInterval(function() {
	//		do_query();
	//	}, 3000);
	//	return data;
	}
	//do_query();
	// ajax��������
	/*
	 * $.getJSON("URL",null, function(data){
	 * 
	 * });
	 */
	
	exports.run = do_query;
});
