<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.*,
				com.device.common.impl.NodesListResult,
				com.device.po.Nodes"%>
<%@page import="com.device.util.StringUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>结点列表</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<META content="MSHTML 6.00.5730.13" name=GENERATOR>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style30.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<style type="text/css">
.offline {
	background-color: #800080;
}

.alarm {
	background-color: #FFC0CB;
}
.warning {
	background-color: #FFFF00
}
.normal {
	background-color: #90EE90
}
</style>
</HEAD>
<%
	String path = request.getContextPath();
	String groupType = request.getParameter("groupType");
	if(groupType == null) groupType = "";
	String groupId = request.getParameter("groupId");
	if(groupId == null) groupId = "";
	NodesListResult result = (NodesListResult) request.getAttribute("result");
	Collection<Object[]> c = null;
	if (result != null) {
		c = result.getC();
	}
%>
<BODY class="tab">
  <div class="table_header" height="99%">
    <h2>监控台</h2>
    <div class="table_list_right"></div>
    <div class="table_header_r"></div>
  </div>
  <table width="99.6%" height="90%" border="0" cellpadding="0" cellspacing="0" class="ls_list1">
    <tr>
      <td>
        <div id="listTableDivId" style="overflow: auto; width: 100%; height: 100%; overflow-y: yes; overflow-x: yes;">
          <p>
          <table width="100%" class="ls_list" border="0" cellpadding="0" cellspacing="0" id="hollylistTable">
            <tr style="position: relative; top: expression(this.offsetParent.scrollTop); z-index: 10;">
              <th scope="col">IP</th>
              <th scope="col">名称</th>
              <th scope="col">状态</th>
              <th scope="col">告警</th>
              <th scope="col">事件</th>
              <th scope="col" style="display: none">gbe流量</th>
              <th scope="col" style="display: none">gbe服务数</th>
              <th scope="col" style="display: none">qam流量</th>
              <th scope="col"style="display: none">qam服务数</th>
            </tr>
            <script>
							var ips = new Array();
							var nodes = new Array();
						</script>
            <%
            	if (c != null && c.size() > 0) {
    						Iterator<Object[]> it = c.iterator();
    						while (it.hasNext()) {
    							Object[] item = it.next();
            %>
                <script>
    							ips.push('<%=item[1]%>');
    							nodes.push('<%=item[0]%>');
    						</script>
            <tr id="tr<%=item[0]%>">
              <td><a href="javascript:top._addTab('id2-' + '<%=item[0]%>','流量监控-' + '<%=item[1]%>','<%=path%>/jsp/monitor/monitorNodeBitrate.jsp?ids=<%=item[0]%>');void(0)"><%=item[1]%></a></td>
              <td><%=item[2]%></td>
              <td id="tr<%=item[0]%>status"></td>
              <td id="tr<%=item[0]%>alarm"><a href="javascript:top._addTab('id2','报警管理','<%=path%>/jsp/event/eventsFrame.jsp?groupType=<%=groupType%>&groupId=<%=groupId%>');void(0)"></a></td>
              <td id="tr<%=item[0]%>event"></td>

              <td style="display: none"></td>
              <td style="display: none"></td>
              <td style="display: none"></td>
              <td style="display: none"></td>
            </tr>
            <%
                }
            }
            %>
          </table>
          </p>
        </div>
      </td>
    </tr>
  </table>
  <script type="text/javascript">
  	$(document).ready(function() {
  		var _ips = ips.join(",");
  		var _nodes = nodes.join(",");
  		if (_ips && _nodes) {
  			poll();
  		}
  		
  		function poll() {
  			$.ajax({
  				type : "POST",
  				url : '<%=request.getContextPath()%>/nodes/monitor_query_updateView.sip',
  				dataType : "json",
  				timeout : 5000,
  				data : {
  					ips : _ips,
  					ids : _nodes
  				},
  				success : function(data, textStatus) {
  					handlerMessage(data);
  				},
  				error: function() {
  					setTimeout(poll, 5000);
  				}
  			});
  		}
  		
  		function handlerMessage(data) {
  			if (data == null || !$.isArray(data)) {
  				setTimeout(poll, 5000);
  				return;
  			}
  			
  			for (var i = 0; i < data.length; i++) {
  				var obj = data[i];
  				//var otd = $("#tra89c0829-9d2d-4d1a-996e-07bbdcfdd246");
  				var otd = $('#tr' + obj.id);
  				
  				var offline = (obj.status != 0);
  				var alarm = obj.alarmCount;
  				var event = obj.eventCount;
  				
  				$('#tr' + obj.id + 'status').html(offline ? '离线' : '在线');
  				$('#tr' + obj.id + 'alarm').find('a').html(alarm);
  				$('#tr' + obj.id + 'event').html(event);
  				
  				if (offline != 0) {
  					otd.css("background-color", "#800080");
  				}
  				
  				if (alarm > 0) {
  					otd.css("background-color", "#FFC0CB");
  				}
  				else if (event > 0) {
  					otd.css("background-color", "#FFFF00");
  				}
  				else {
  					//normal
  					otd.css("background-color", "#90EE90");
  				}
  				//if("#"+tid)
  			}
  			
  			setTimeout(poll, 5000);
  		}
  	});
	</script>
</BODY>
</HTML>