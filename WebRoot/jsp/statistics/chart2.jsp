<%@ page contentType="text/html; charset=GB2312"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>���������</title>
	</head>
<%
	String gids = (String) request.getAttribute("gids"); //��id
	System.out.println(gids);
	String cycle = (String) request.getAttribute("cycle"); //����
	System.out.println(cycle);
	String beginTime = (String) request.getAttribute("beginTime"); //��ʼʱ��
	System.out.println(beginTime);
	String endTime = (String) request.getAttribute("endTime"); //����ʱ��
	System.out.println(endTime);

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
		<div id="gbeb" class="gbeb">11</div>
		<div id="gben" class="gben">22</div>
		<div id="qamb" class="qamb">33</div>
		<div id="qamn" class="qamn">44</div>
	</div>

	<script type="text/javascript" src="<%=request.getContextPath()%>/sea-debug-2.1.1.js"></script>
	<!-- ʹ��highcharts֮ǰ����������jquery! -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.6.2.js"></script>
	<!-- 
	<script type="text/javascript" src="<%=request.getContextPath()%>/vendor/highcharts-3.0.7/highcharts.src.js"></script>
	-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/vendor/highcharts-3.0.7/highcharts.js"></script>

	<script type="text/javascript">
		seajs.use('<%=request.getContextPath()%>/jsp/report/report.js', function(rt) {
			debugger;
			rt.host = '<%=request.getContextPath()%>
		';
			// '"nodeId":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246","start":"2013/11/23 14:00","end":"2013/11/23 14:35","period":"0","chartType":"0"',
			rt.runGroup('40288b394283bac5014283bb76b60001', "", '2013/11/23 14:17', '2013/11/23 14:35', 0, 'line');
			// rt.test.run_ip_detail();
		});
	</script>
</body>
</html>
