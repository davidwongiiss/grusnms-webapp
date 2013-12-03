<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="java.util.Enumeration"%>
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
	String gids = (String)request.getParameter("gids"); //
	String nids = (String)request.getParameter("nids");	
	String ips = (String)request.getParameter("ips");	

	String beginTime = (String)request.getParameter("beginTime"); //开始时间
	String endTime = (String)request.getParameter("endTime"); //结束时间
	
	String cycle = (String)request.getParameter("cycle"); //周期
	
	String chartType = (String)request.getParameter("chartType");	
	
	String param = "";
	Enumeration xenum = request.getParameterNames();
	while (xenum.hasMoreElements()) {
		String name = (String) xenum.nextElement();
		String value = request.getParameter(name);
		param += name + "=" + value + "&";
	}
%>
<body>
	<div id="linechart" class="container">
		<div id="gbeb" class="gbeb"><%= param %></div>
		<div id="gben" class="gben">22</div>
		<div id="qamb" class="qamb">33</div>
		<div id="qamn" class="qamn">44</div>
	</div>

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
			
      var nids = '<%=nids%>' != 'null' ? '<%=nids%>' : null;
      var ips = '<%=ips%>' != 'null' ? '<%=ips%>' : null;
      var gids = '<%=gids%>' != 'null' ? '<%=gids%>' : null;
      
      var lhs = '<%=beginTime%>';
      var rhs = '<%=endTime%>';
      var cycle = '<%=cycle%>';
      var chartType = '<%=chartType%>' == 'total' ? 'bar' : 'line';
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
      
			// '"nodeId":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246","start":"2013/11/23 14:00","end":"2013/11/23 14:35","period":"0","chartType":"0"',
			//rt.runGroup('40288b394283bac5014283bb76b60001', "", '2013/11/23 14:17', '2013/11/23 14:35', 0, 'bar');
      //rt.runIp('a89c0829-9d2d-4d1a-996e-07bbdcfdd246', "", '2013/11/23 14:17', '2013/11/23 14:35', 0, 'line');
      if (ips != null && ips != '') {
      	rt.runIp(nids, ips, lhs, rhs, period, chartType);
      }
      else if (gids != null && gids != '') {
      	rt.runGroup(gids, '', lhs, rhs, period, chartType);
      }
				
			// rt.test.run_ip_detail();
		});
	</script>
</body>
</html>
