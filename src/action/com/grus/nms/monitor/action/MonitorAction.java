package com.grus.nms.monitor.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.device.bean.NodesBean;
import com.device.common.impl.NodesListResult;
import com.device.po.NodeStatus;
import com.device.po.QamValue;
import com.device.util.HibernateHelper;
import com.device.util.JSONUtil;
import com.device.util.ParamUtil;
import com.device.util.PeakSessionFactory;
import com.device.util.Struts2Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonitorAction {
	private static Log logger = LogFactory.getLog(MonitorAction.class);

	private String addresses;

	@SuppressWarnings("unchecked")
	public void queryDevicesCurrentBitrates() {
		logger.debug("queryDevicesCurrentBitrates-begin");

		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String addresses = ParamUtil.getString(request, "addresses");
		if (addresses == null || addresses.isEmpty()) {
			addresses = this.addresses;
		}

		String addresses1[] = addresses.split(",");

		Collection<QamValue> c = null;
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();

			StringBuffer sb = new StringBuffer();
			sb.append("[");

			for (int i = 0; i < addresses1.length; i++) {
				String hql = "select qam from QamValue qam where qam.nodeId = (select id from Nodes node where node.ip = '"
						+ addresses1[i] + "') order by qam.blade asc";
				c = HibernateHelper.getQueryResult(session, hql);

				if (c.size() == 0)
					continue;

				logger.debug("获取c");

				sb.append("{");
				sb.append("\"nodeId\":\"\"");
				sb.append(",\"ip\":\"");
				sb.append(addresses1[i]);
				sb.append("\"");
				sb.append(",\"slots\":[");

				StringBuffer[] sbs = new StringBuffer[9];

				for (QamValue qam : c) {
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
					sb.deleteCharAt(sb.length() - 1);
					sb.append("]"); // slots
				}
				sb.append("},");
			}

			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");

			String s = sb.toString();
			Struts2Utils.renderJson(s);

			logger.debug("queryDevicesCurrentBitrates:" + s);
		}
		catch (HibernateException e) {
			// to do something....
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
		finally {
			logger.debug("queryDevicesCurrentBitrates-end");
		}
	}

	@SuppressWarnings("unchecked")
	public void queryDevicesCurrentStatus() {
		logger.debug("queryDevicesCurrentStatus-begin");

		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String addresses = ParamUtil.getString(request, "addresses");
		String addresses1[] = addresses.split(",");

		Collection<NodeStatus> c = null;
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();

			StringBuffer sb = new StringBuffer();
			sb.append("[");

			for (int i = 0; i < addresses1.length; i++) {
				String hql = "select status from NodeStatus s where s.nodeId = (select id from Nodes node where node.ip = '"
						+ addresses1[i] + "')";
				c = HibernateHelper.getQueryResult(session, hql);

				if (c.size() == 0)
					continue;

				sb.append("{");
				sb.append("\"nodeId\":\"\"");
				sb.append(",\"ip\":\"");
				sb.append(addresses1[i]);
				sb.append("\"");

				for (NodeStatus status : c) {
					sb.append(",\"gbeBitrate\":");
					sb.append(status.getGbeBitrate());
					sb.append(",\"gbeService\":");
					sb.append(status.getGbeService());
					sb.append(",\"qamBitrate\":");
					sb.append(status.getQamBitrate());
					sb.append(",\"qamService\":");
					sb.append(status.getQamService());
					sb.append(",\"eventCount\":");
					sb.append(status.getEventCount());
					sb.append(",\"alarmService\":");
					sb.append(status.getAlarmCount());

					// 防止IP有重复只处理一组
					break;
				}

				sb.append("},");
			}

			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");

			String s = sb.toString();
			Struts2Utils.renderJson(s);

			logger.debug("queryDevicesCurrentStatus:" + s);
		}
		catch (HibernateException e) {
			// to do something....
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
		finally {
			logger.debug("queryDevicesCurrentStatus-end");
		}
	}

	// 设备监控列表
	public String monitorList() {
		logger.debug("querylist-begin");
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String groupId = ParamUtil.getString(request, "groupId");
		NodesListResult result = NodesBean.getInstance().queryNodesByGroupId(groupId);
		request.setAttribute("result", result);
		return "monitorList";
	}

	/**
	 * 
	 * @return
	 */
	public String showNodeBitrate() {
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		this.addresses = ParamUtil.getString(request, "address");
		return "monitorNodeBitrate";
	}
}
