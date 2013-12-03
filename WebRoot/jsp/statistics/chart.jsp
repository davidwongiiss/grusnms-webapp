<%@page import="com.device.util.StringUtil"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>统计结果</title>
		<style type="text/css">
			div{border:1px green solid;};
			.container{width: 100%;border: 1px red solid;}
			.gbeb{float:left;width:49.5%;}
			.gben{float:left;;width:49.5%}
			.qamb{clear:left;width:49.5%;float:left;}
			.qamn{float:left;width:49.5%;}
		</style>
	</head>
<%
	String nids = (String)request.getParameter("nids");	
	String ips = (String)request.getParameter("ips");	
	String beginTime = (String)request.getParameter("beginTime");
	String endTime = (String)request.getParameter("endTime");
	String cycle = (String)request.getParameter("cycle");
	String chartType = (String)request.getParameter("chartType");
	
	String[] ipArray = ips.split(",");
	//gids=area	//组类型 区域，部门，自定义
	//nids=a89c0829-9d2d-4d1a-996e-07bbdcfdd246 //结点id 用，号分割
	//beginTime=17:42:00
	//cycle=hour	//周期
	//endTime=17:43:00 //结束时间
	//chartType=chart //统计类型，走势图 chart 合计 total 
	//ips=192.168.11.45  IP 逗号分割
	String param = "";
 	Enumeration xenum=request.getParameterNames();
 	while(xenum.hasMoreElements()){
   	String name=(String)xenum.nextElement();
   	String value=request.getParameter( name);
   	param+=name+"="+value+"&";
 	}
%>
<body>
<%
	for (String ip : ipArray) {
%>
	<div id="linechart<%=ip%>" class="container">
		<div><%=ip%></div>
		<div>
			<div id="gbeb<%=ip%>" class="gbeb">11</div>
			<div id="gben<%=ip%>" class="gben">22</div>
			<div id="qamb<%=ip%>" class="qamb">33</div>
			<div id="qamn<%=ip%>" class="qamn">44</div>
		</div>
	</div>
<%
	}
%>

	<script type="text/javascript" src="<%=request.getContextPath()%>/sea-debug-2.1.1.js"></script>
	<!-- 使用highcharts之前，必须引入jquery! -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.6.2.js"></script>
	<!-- 
	<script type="text/javascript" src="<%=request.getContextPath()%>/vendor/highcharts-3.0.7/highcharts.src.js"></script>
	-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/vendor/highcharts-3.0.7/highcharts.js"></script>
	
	<script type="text/javascript">
		seajs.use('<%=request.getContextPath()%>/jsp/report/report.js', function(rt) {
			rt.host = '<%=request.getContextPath()%>';
      // '"nodeId":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246","start":"2013/11/23 14:00","end":"2013/11/23 14:35","period":"0","chartType":"0"',
      
      var nids = '<%=nids%>' != 'null' ? '<%=nids%>' : null;
      var ips = '<%=ips%>' != 'null' ? '<%=ips%>' : null;
      var lhs = '<%=beginTime%>';
      var rhs = '<%=endTime%>';
      var cycle = '<%=cycle%>';
      var chartType = '<%=chartType%>' == 'total' ? 'device-detail-bar' : 'device-detail-line';
      var period = 0;
      
      switch (cycle.toLowerCase()) {
      default:
      case '1minute':
      	period = 0;
      	break;
      case '5minute':
      	period = 1;
      	break;
      case '30minute':
      	period = 2;
      	break;
      case 'hour':
      	period = 3;
      	break;
      case 'day':
      	period = 4;
      	break;
      case 'week':
      	period = 5;
      	break;
      case 'month':
      	period = 6;
      	break;
      case 'year':
      	period = 7;
      	break;
      }
      
			//rt.runIp('a89c0829-9d2d-4d1a-996e-07bbdcfdd246', '<%=ips%>', '2013/11/23 14:17', '2013/11/23 14:35', 0, 'line-detail');
			rt.runIp(nids, ips, lhs, rhs, period, chartType);
			// rt.test.run_ip_detail();
		});
	</script>
</body>
</html>
