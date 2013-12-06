package com.grus.nms.monitor.action;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.device.bean.NodesBean;
import com.device.common.impl.NodesListResult;
import com.device.po.GbeCurValue;
import com.device.po.QamCurValue;
import com.device.util.HibernateHelper;
import com.device.util.JSONUtil;
import com.device.util.ParamUtil;
import com.device.util.PeakSessionFactory;
import com.device.util.Struts2Utils;

public class MonitorAction {
	private static Log logger = LogFactory.getLog(MonitorAction.class);
	
	private String ids;
	private String ips;
	
	@SuppressWarnings("unchecked")
	public void queryDevicesCurrentQamBitrates() {
		logger.debug("queryDevicesCurrentBitrates-begin");
		
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		if (ids == null || ids.isEmpty()) {
			return;
		}
		
		String ids1[] = ids.split(",");
		
		Collection<QamCurValue> c = null;
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			
			for (int i = 0; i < ids1.length; i++) {
				String hql = "select qam from QamCurValue qam where qam.nodeId = '" + ids1[i] + "') order by qam.blade asc";
				c = HibernateHelper.getQueryResult(session, hql);
				
				if (c.size() == 0)
					continue;
				
				StringBuffer[] sbs = new StringBuffer[9];
				
				boolean first = true;
				for (QamCurValue qam : c) {
					if (first) {
						first = false;
						sb.append("{");
						sb.append("\"nodeId\":\"");
						sb.append(qam.getNodeId());
						sb.append("\",\"ip\":\"");
						sb.append(qam.getIp());
						sb.append("\",\"slots\":[");
					}
					
					int n = qam.getBlade() - 1;
					if (sbs[n] == null) {
						sbs[n] = new StringBuffer();
						sbs[n].append("{\"slotNo\":");
						sbs[n].append(qam.getBlade());						
					}
					
					sbs[n].append(",\"qams\":[[");
					sbs[n].append(qam.getQam1() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam2() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam3() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam4() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam5() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam6() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam7() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam8() ? 1 : 0);
					sbs[n].append("],[");
					sbs[n].append(qam.getQam9() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam10() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam11() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam12() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam13() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam14() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam15() ? 1 : 0);
					sbs[n].append(",");
					sbs[n].append(qam.getQam16() ? 1 : 0);
					sbs[n].append("]]");
					
					sbs[n].append(",\"bitrates\":[[");
					sbs[n].append(qam.getBitrate1());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate2());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate3());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate4());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate5());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate6());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate7());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate8());
					sbs[n].append("],[");
					sbs[n].append(qam.getBitrate9());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate10());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate11());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate12());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate13());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate14());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate15());
					sbs[n].append(",");
					sbs[n].append(qam.getBitrate16());					
					sbs[n].append("]]");

					sbs[n].append(",\"numOfServices\":[[");
					sbs[n].append(qam.getNumOfServices1());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices2());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices3());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices4());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices5());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices6());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices7());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices8());
					sbs[n].append("],[");
					sbs[n].append(qam.getNumOfServices9());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices10());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices11());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices12());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices13());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices14());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices15());
					sbs[n].append(",");
					sbs[n].append(qam.getNumOfServices16());					
					sbs[n].append("]]");
					sbs[n].append("},");
				}
				
				// 组装slots
				for (int j = 0; j < 9; j++) {
					if (sbs[j] == null)
						continue;
					
					sb.append(sbs[j].toString());
					if (sb.charAt(sb.length() - 1) == ',')
						sb.deleteCharAt(sb.length() - 1);
					sb.append("]"); // slots
				}
				sb.append("},");
			}
			
			if (sb.charAt(sb.length() - 1) == ',')
				sb.deleteCharAt(sb.length() - 1);
			
			sb.append("]");
			
			String s = sb.toString();
			Struts2Utils.renderJson(s);
			
			logger.debug("queryDevicesCurrentBitrates:" + s);
		} 
		catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
			logger.debug(e.getMessage());
		} 
		finally {
			logger.debug("queryDevicesCurrentBitrates-end");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void queryDevicesCurrentGbeBitrates() {
		logger.debug("queryDevicesCurrentGbeBitrates-begin");
		
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		if (ids == null || ids.isEmpty()) {
			return;
		}
		
		String ids1[] = ids.split(",");
		
		Collection<GbeCurValue> c = null;
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			
			for (int i = 0; i < ids1.length; i++) {
				String hql = "select gbe from GbeCurValue gbe where gbe.nodeId = '" + ids1[i] + "') order by qam.blade asc";
				c = HibernateHelper.getQueryResult(session, hql);
				if (c.size() == 0)
					continue;
				
				for (GbeCurValue gbe : c) {
					sb.append("{");
					sb.append("\"nodeId\":\"\"");
					sb.append(",\"ip\":\"");
					sb.append(gbe.getIp());
					sb.append("\",\"enabled\":[1,1,1,1,1,1,1,1]");					
					sb.append(",\"bitrates\":[");
					sb.append(gbe.getMulticastBitrate1());
					sb.append(",");
					sb.append(gbe.getMulticastBitrate2());
					sb.append(",");
					sb.append(gbe.getMulticastBitrate3());
					sb.append(",");
					sb.append(gbe.getMulticastBitrate4());
					sb.append(",");
					sb.append(gbe.getMulticastBitrate5());
					sb.append(",");
					sb.append(gbe.getMulticastBitrate6());
					sb.append(",");
					sb.append(gbe.getMulticastBitrate7());
					sb.append(",");
					sb.append(gbe.getMulticastBitrate8());
					sb.append("]");
					sb.append(",\"numOfServices\":[");
					sb.append(gbe.getNumberOfServices1());
					sb.append(",");
					sb.append(gbe.getNumberOfServices2());
					sb.append(",");
					sb.append(gbe.getNumberOfServices3());
					sb.append(",");
					sb.append(gbe.getNumberOfServices4());
					sb.append(",");
					sb.append(gbe.getNumberOfServices5());
					sb.append(",");
					sb.append(gbe.getNumberOfServices6());
					sb.append(",");
					sb.append(gbe.getNumberOfServices7());
					sb.append(",");
					sb.append(gbe.getNumberOfServices8());					
					sb.append("]},");
				}
			}
			
			if (sb.charAt(sb.length() - 1) == ',')
				sb.deleteCharAt(sb.length() - 1);
			
			sb.append("]");
			
			String s = sb.toString();
			Struts2Utils.renderJson(s);
			
			logger.debug("queryDevicesCurrentGbeBitrates:" + s);
		} 
		catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
			logger.debug(e.getMessage());
		} 
		finally {
			logger.debug("queryDevicesCurrentGbeBitrates-end");
		}
	}	
	
	//设备监控列表
	public String monitorList(){
		logger.debug("querylist-begin");
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String groupId = ParamUtil.getString(request, "groupId");
		NodesListResult result = NodesBean.getInstance().queryNodesByGroupId(groupId);
		request.setAttribute("result", result);
		logger.debug("querylist-end");
		return "monitorList";
	}
	
	public void updateView() {
		queryNodeStatus();
	}
	
	public class NodeStatus {
		public String dummy;
		public String id;
		public String name;
		public String ip;
		public int status = 0;
		public int alarmCount = 0;
		public int eventCount = 0;
		public BigInteger gbeb = BigInteger.ZERO;
		public BigInteger gben = BigInteger.ZERO;
		public BigInteger qamb = BigInteger.ZERO;
		public BigInteger qamn = BigInteger.ZERO;
	};
	
	public class GroupStatus {
		public String dummy;		
		public String id;
		public String name;
		public String x, y;
		public int offlineCount;
		public int alarmCount;
		public int eventCount;
	};
	
	public void queryGroupsStatus() {
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String ids = ParamUtil.getString(request, "ids");		

		Session session = null;
		session = PeakSessionFactory.instance().getCurrentSession();

		@SuppressWarnings("deprecation")
		Connection conn = session.connection();
		PreparedStatement stmt;
		try {
			String sql = "select gs.group_id, gs.name, gs.x, gs.y, gs.offline_count, gs.alarm_count, gs.event_count from group_status gs where 1=1";
			if (ids != null && !ids.isEmpty()) {
				sql += " and gs.group_id in(";

				String[] ss = ids.split(",");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < ss.length; ++i) {
					sb.append("'").append(ss[i]).append("'").append(",");
				}
				
				if (sb.length() > 0)
					sb.deleteCharAt(sb.length() - 1);
				
				sql += sb.toString();
				sql += ")";
				stmt = (PreparedStatement) conn.prepareStatement(sql);
			}
			else {
				stmt = (PreparedStatement) conn.prepareStatement(sql);
			}
			
			ResultSet rs = stmt.executeQuery();

			List<GroupStatus> nss = new ArrayList<GroupStatus>();
			while (rs.next()) {
				int i = 0;
				GroupStatus r = new GroupStatus();
				r.id = rs.getString(++i);
				r.name = rs.getString(++i);
				r.x = rs.getString(++i);
				r.y = rs.getString(++i);
				r.offlineCount = rs.getInt(++i);
				r.alarmCount = rs.getInt(++i);
				r.eventCount = rs.getInt(++i);

				nss.add(r);
			}
			
			String[] a = { "dummy" };
			String s = JSONUtil.listToJson2(nss, a);
			Struts2Utils.renderJson(s);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void queryNodeStatus() {
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String ids = ParamUtil.getString(request, "ids");		

		Session session = null;
		session = PeakSessionFactory.instance().getCurrentSession();

		@SuppressWarnings("deprecation")
		Connection conn = session.connection();
		PreparedStatement stmt;
		try {
			String sql = "select ns.node_id, ns.ip, ns.name, ns.status, ns.alarm_count, ns.event_count from node_status ns where 1=1";
			if (ids != null && !ids.isEmpty()) {
				sql += " and ns.node_id in(";

				String[] ss = ids.split(",");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < ss.length; ++i) {
					sb.append("'").append(ss[i]).append("'").append(",");
				}
				
				if (sb.length() > 0)
					sb.deleteCharAt(sb.length() - 1);
				
				sql += sb.toString();
				sql += ")";
				stmt = (PreparedStatement) conn.prepareStatement(sql);
			}
			else {
				stmt = (PreparedStatement) conn.prepareStatement(sql);
			}
			
			ResultSet rs = stmt.executeQuery();

			List<NodeStatus> nss = new ArrayList<NodeStatus>();
			while (rs.next()) {
				int i = 0;
				NodeStatus r = new NodeStatus();
				r.id = rs.getString(++i);
				r.ip = rs.getString(++i);
				r.name = rs.getString(++i);
				r.status = rs.getInt(++i);
				r.alarmCount = rs.getInt(++i);
				r.eventCount = rs.getInt(++i);

				nss.add(r);
			}
			
			String[] a = { "dummy" };
			String s = JSONUtil.listToJson2(nss, a);
			Struts2Utils.renderJson(s);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}
}
