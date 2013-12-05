<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.*,
	com.device.common.impl.NodesListResult,
	com.device.po.Nodes"%>
<%@page import="com.device.util.StringUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>设备流量监控</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<META content="MSHTML 6.00.5730.13" name=GENERATOR>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style30.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/vendor/highcharts-3.0.7/highcharts.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/sea-debug-2.1.1.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/jsp/monitor/chart.css">

</HEAD>
<BODY class="tab">
	<div style="margin: 5px">
		<input id="qam" type="button" value="QAM" onclick="show(0)"> 
		<input id="gbe" type="button" value="GBE" onclick="show(1)">
		<!-- 
		<input id="event" type="button" value="事件" onclick="show(2)">
		 --> 
	</div>
	<div id="qam-panel" class="mainDiv">
		<center>
			<h2>QAM Service/Briate View</h2>
		</center>
		<div class="module">
			<div class="title" id="title0">
				<a href="#" target="_self">Module 1</a>
			</div>
			<div class="rf">
				<div class="container" id="container0"></div>
			</div>
		</div>
		<div class="module">
			<div class="title" id="title0">
				<a href="#" target="_self">Module 2</a>
			</div>
			<div class="rf">
				<div class="container" id="container1"></div>
			</div>
		</div>
		<div class="module">
			<div class="title" id="title0">
				<a href="#" target="_self">Module 3</a>
			</div>
			<div class="rf">
				<div class="container" id="container2"></div>
			</div>
		</div>
		<div class="module">
			<div class="title" id="title0">
				<a href="#" target="_self">Module 4</a>
			</div>
			<div class="rf">
				<div class="container" id="container3"></div>
			</div>
		</div>
		<div class="module">
			<div class="title" id="title0">
				<a href="#" target="_self">Module 5</a>
			</div>
			<div class="rf">
				<div class="container" id="container4"></div>
			</div>
		</div>
		<div class="module">
			<div class="title" id="title0">
				<a href="#" target="_self">Module 6</a>
			</div>
			<div class="rf">
				<div class="container" id="container5"></div>
			</div>
		</div>
		<div class="module">
			<div class="title" id="title0">
				<a href="#" target="_self">Module 7</a>
			</div>
			<div class="rf">
				<div class="container" id="container6"></div>
			</div>
		</div>
		<div class="module">
			<div class="title" id="title0">
				<a href="#" target="_self">Module 8</a>
			</div>
			<div class="rf">
				<div class="container" id="container7"></div>
			</div>
		</div>
		<div class="module">
			<div class="title" id="title0">
				<a href="#" target="_self">Module 9</a>
			</div>
			<div class="rf">
				<div class="container" id="container8"></div>
			</div>
		</div>
	</div>
	<div id="gbe-panel" class="mainDiv">
		<center>
			<h2>GBE Service/Briate View</h2>
		</center>
		<div class="title" id="title0">
			<a href="#" target="_self">GBE</a>
		</div>
		<div id="containergbe"></div>
	</div>
	<!-- 
	<div id="event-panel" class="mainDiv">
		<center>
			<h2>事件</h2>
		</center>
	</div>
	 -->

	<script type="text/javascript">
	function show(i) {
		if (i == 0) {
			$('#qam-panel').show();
			$('#gbe-panel').hide();
			$('#event-panel').hide();
		}
		else if (i == 1) {
			$('#qam-panel').hide();
			$('#gbe-panel').show();
			$('#event-panel').hide();
		}
		else {
			$('#qam-panel').hide();
			$('#gbe-panel').hide();
			$('#event-panel').show();
		}
	}
	
	show(0);
	
	var qamChart, gbeChart;
	seajs.use([
		'<%=request.getContextPath()%>/jsp/monitor/chart.js', 
		'<%=request.getContextPath()%>/jsp/monitor/gbe.js' ], function(qam, gbe) {
			qamChart = qam;
			gbeChart = gbe;
			
			var ip = '<%=request.getParameter("ipaddress")%>';
			qamChart.run(ip);
			gbeChart.run(ip);
		});

	</script>

</BODY>
</HTML>