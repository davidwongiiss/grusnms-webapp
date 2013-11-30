package com.grus.nms.report.action;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.device.util.JSONUtil;
import com.device.util.ParamUtil;
import com.device.util.PeakSessionFactory;
import com.device.util.Struts2Utils;

public class ReportAction {

	// http://localhost:8080/grusnms/nodes/report_querySingleIpStatistics.sip?params={"nodeId":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246","start":"1970/01/01 00:00:00","end":"2015/01/01 00:00:00","period":"0"}
	public class ip_gbe_record {
		//public String b[] = new String[8];
		//public String n[] = new String[8];
		public BigInteger b[] = new BigInteger[8];
		public BigInteger n[] = new BigInteger[8];
		public String ct = "";
	};
	
	public class ip_qam_record {
		//public String b[] = new String[9];
		//public String n[] = new String[9];
		public BigInteger b[] = new BigInteger[9];
		public BigInteger n[] = new BigInteger[9];
		public String ct = "";
	};	

	public class ip_statistics {
		public String dummy = "";
		public String id = "";
		public String ip = "";
		public List<ip_gbe_record> gbe = new ArrayList<ip_gbe_record>();
		public List<ip_qam_record> qam = new ArrayList<ip_qam_record>();
	};

	/**
	 * 返回单ip指定日期的gbe和qam分时走势结果
	 */
	public void querySingleIpStatistics() {
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String nodeId = ParamUtil.getString(request, "nodeId");
		String lhs = ParamUtil.getString(request, "start");
		String rhs = ParamUtil.getString(request, "end");
		String period = ParamUtil.getString(request, "period");
		String chartType = ParamUtil.getString(request, "chartType");
		
		Session session = null;
		session = PeakSessionFactory.instance().getCurrentSession();

		@SuppressWarnings("deprecation")
		Connection conn = session.connection();
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) conn.prepareStatement("call grusnms.ip_gbe_statistics(?,?,?,?)");
			stmt.setString(1, nodeId);
			stmt.setTimestamp(2, new Timestamp(DateUtil.str2Date(lhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setTimestamp(3, new Timestamp(DateUtil.str2Date(rhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setInt(4, Integer.parseInt(period));
			ResultSet rs = stmt.executeQuery();

			Map<String, ip_statistics> records = new HashMap<String, ip_statistics>();
			List<ip_statistics> records1 = new ArrayList<ip_statistics>();
			while (rs.next()) {
				int i = 2;

				String id = rs.getString(i);
				ip_statistics p = records.get(id);
				if (p == null) {
					p = new ip_statistics();
					p.id = id;
					records.put(id, p);
					records1.add(p);
				}

				p.ip = rs.getString(++i);
				
				ip_gbe_record r = new ip_gbe_record();
				/*
				r.b[0] = rs.getBigDecimal(++i).toString();
				r.b[1] = rs.getBigDecimal(++i).toString();
				r.b[2] = rs.getBigDecimal(++i).toString();
				r.b[3] = rs.getBigDecimal(++i).toString();
				r.b[4] = rs.getBigDecimal(++i).toString();
				r.b[5] = rs.getBigDecimal(++i).toString();
				r.b[6] = rs.getBigDecimal(++i).toString();
				r.b[7] = rs.getBigDecimal(++i).toString();
				r.n[0] = rs.getBigDecimal(++i).toString();
				r.n[1] = rs.getBigDecimal(++i).toString();
				r.n[2] = rs.getBigDecimal(++i).toString();
				r.n[3] = rs.getBigDecimal(++i).toString();
				r.n[4] = rs.getBigDecimal(++i).toString();
				r.n[5] = rs.getBigDecimal(++i).toString();
				r.n[6] = rs.getBigDecimal(++i).toString();
				r.n[7] = rs.getBigDecimal(++i).toString();
				*/
				
				r.b[0] = rs.getBigDecimal(++i).toBigInteger();
				r.b[1] = rs.getBigDecimal(++i).toBigInteger();
				r.b[2] = rs.getBigDecimal(++i).toBigInteger();
				r.b[3] = rs.getBigDecimal(++i).toBigInteger();
				r.b[4] = rs.getBigDecimal(++i).toBigInteger();
				r.b[5] = rs.getBigDecimal(++i).toBigInteger();
				r.b[6] = rs.getBigDecimal(++i).toBigInteger();
				r.b[7] = rs.getBigDecimal(++i).toBigInteger();
				r.n[0] = rs.getBigDecimal(++i).toBigInteger();
				r.n[1] = rs.getBigDecimal(++i).toBigInteger();
				r.n[2] = rs.getBigDecimal(++i).toBigInteger();
				r.n[3] = rs.getBigDecimal(++i).toBigInteger();
				r.n[4] = rs.getBigDecimal(++i).toBigInteger();
				r.n[5] = rs.getBigDecimal(++i).toBigInteger();
				r.n[6] = rs.getBigDecimal(++i).toBigInteger();
				r.n[7] = rs.getBigDecimal(++i).toBigInteger();			
				r.ct = DateUtil.date2Str(rs.getTimestamp(++i), "yyyy/MM/dd HH:mm");

				p.gbe.add(r);
			}

			stmt = (PreparedStatement) conn.prepareStatement("call grusnms.ip_qam_statistics(?,?,?,?)");
			stmt.setString(1, nodeId);
			stmt.setTimestamp(2, new Timestamp(DateUtil.str2Date(lhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setTimestamp(3, new Timestamp(DateUtil.str2Date(rhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setInt(4, Integer.parseInt(period));
			rs = stmt.executeQuery();

			while (rs.next()) {
				int i = 1;

				String id = rs.getString(i);
				ip_statistics p = records.get(id);
				if (p == null) {
					p = new ip_statistics();
					p.id = id;
					records.put(id, p);
					records1.add(p);
				}

				p.ip = rs.getString(++i);
				
				ip_qam_record r = new ip_qam_record();
				/*
				r.b[0] = rs.getBigDecimal(++i).toString();
				r.b[1] = rs.getBigDecimal(++i).toString();
				r.b[2] = rs.getBigDecimal(++i).toString();
				r.b[3] = rs.getBigDecimal(++i).toString();
				r.b[4] = rs.getBigDecimal(++i).toString();
				r.b[5] = rs.getBigDecimal(++i).toString();
				r.b[6] = rs.getBigDecimal(++i).toString();
				r.b[7] = rs.getBigDecimal(++i).toString();
				r.b[8] = rs.getBigDecimal(++i).toString();
				r.n[0] = rs.getBigDecimal(++i).toString();
				r.n[1] = rs.getBigDecimal(++i).toString();
				r.n[2] = rs.getBigDecimal(++i).toString();
				r.n[3] = rs.getBigDecimal(++i).toString();
				r.n[4] = rs.getBigDecimal(++i).toString();
				r.n[5] = rs.getBigDecimal(++i).toString();
				r.n[6] = rs.getBigDecimal(++i).toString();
				r.n[7] = rs.getBigDecimal(++i).toString();
				r.n[8] = rs.getBigDecimal(++i).toString();
				*/
				r.b[0] = rs.getBigDecimal(++i).toBigInteger();
				r.b[1] = rs.getBigDecimal(++i).toBigInteger();
				r.b[2] = rs.getBigDecimal(++i).toBigInteger();
				r.b[3] = rs.getBigDecimal(++i).toBigInteger();
				r.b[4] = rs.getBigDecimal(++i).toBigInteger();
				r.b[5] = rs.getBigDecimal(++i).toBigInteger();
				r.b[6] = rs.getBigDecimal(++i).toBigInteger();
				r.b[7] = rs.getBigDecimal(++i).toBigInteger();
				r.b[8] = rs.getBigDecimal(++i).toBigInteger();
				r.n[0] = rs.getBigDecimal(++i).toBigInteger();
				r.n[1] = rs.getBigDecimal(++i).toBigInteger();
				r.n[2] = rs.getBigDecimal(++i).toBigInteger();
				r.n[3] = rs.getBigDecimal(++i).toBigInteger();
				r.n[4] = rs.getBigDecimal(++i).toBigInteger();
				r.n[5] = rs.getBigDecimal(++i).toBigInteger();
				r.n[6] = rs.getBigDecimal(++i).toBigInteger();
				r.n[7] = rs.getBigDecimal(++i).toBigInteger();						
				r.n[8] = rs.getBigDecimal(++i).toBigInteger();
				r.ct = DateUtil.date2Str(rs.getTimestamp(++i), "yyyy/MM/dd HH:mm");

				p.qam.add(r);
			}			

			String[] a = { "dummy" };
			String s = JSONUtil.listToJson2(records1, a);
			Struts2Utils.renderJson(s);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * 返回多ip指定日期的分时走势和合计结果
	 * 
	 * http://localhost:8080/grusnms/nodes/report_queryMultiIpsStatistics.sip?params={"nodeId":"a89c0829-9d2d-4d1a-996e-07bbdcfdd246,a89c0829-9d2d-4d1a-996e-07bbdcfdd247","start":"1970/01/01 00:00:00","end":"2015/01/01 00:00:00","period":"0","chartType":"0"}
	 */
	public class ip_gbe_total_record {
		public String b = "0", n = "0";
		public String ct = "";
	};

	public class ip_qam_total_record {
		public String b = "0", n = "0";
		public String ct = "";
	};
	
	public class ip_total_statistics {
		public String dummy = "";
		public String id = "";
		public String ip = "";
		public List<ip_gbe_total_record> gbe = new ArrayList<ip_gbe_total_record>();
		public List<ip_qam_total_record> qam = new ArrayList<ip_qam_total_record>();
	};
	
	public void queryMultiIpsStatistics() {
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String params = ParamUtil.getString(request, "params");
		@SuppressWarnings("unchecked")
		Map<String, String> map = JSONUtil.fromJson(params, HashMap.class);

		String nodeId = map.get("nodeIds");
		String lhs = map.get("start");
		String rhs = map.get("end");
		String period = map.get("period");
		String chartType = map.get("chartType");

		Session session = null;
		session = PeakSessionFactory.instance().getCurrentSession();

		@SuppressWarnings("deprecation")
		Connection conn = session.connection();
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) conn.prepareStatement("call grusnms.multi_ips_gbe_statistics(?,?,?,?,?)");
			stmt.setString(1, nodeId);
			stmt.setTimestamp(2, new Timestamp(DateUtil.str2Date(lhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setTimestamp(3, new Timestamp(DateUtil.str2Date(rhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setInt(4, Integer.parseInt(period));
			stmt.setBoolean(5, Integer.parseInt(chartType) == 0 ? false : true);
			ResultSet rs = stmt.executeQuery();

			Map<String, ip_total_statistics> records = new HashMap<String, ip_total_statistics>();
			List<ip_total_statistics> records1 = new ArrayList<ip_total_statistics>();
			while (rs.next()) {
				int i = 1;

				String id = rs.getString(i);
				ip_total_statistics p = records.get(id);
				if (p == null) {
					p = new ip_total_statistics();
					p.id = id;
					records.put(id, p);
					records1.add(p);
				}

				p.ip = rs.getString(++i);
				
				ip_gbe_total_record r = new ip_gbe_total_record();
				r.b = rs.getBigDecimal(++i).toString();
				r.n = rs.getBigDecimal(++i).toString();
				r.ct = DateUtil.date2Str(rs.getTimestamp(++i), "yyyy/MM/dd HH:mm");

				p.gbe.add(r);
			}

			stmt = (PreparedStatement) conn.prepareStatement("call grusnms.multi_ips_qam_statistics(?,?,?,?,?)");
			stmt.setString(1, nodeId);
			stmt.setTimestamp(2, new Timestamp(DateUtil.str2Date(lhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setTimestamp(3, new Timestamp(DateUtil.str2Date(rhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setInt(4, Integer.parseInt(period));
			stmt.setBoolean(5, Integer.parseInt(chartType) == 0 ? false : true);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int i = 1;

				String id = rs.getString(i);
				ip_total_statistics p = records.get(id);
				if (p == null) {
					p = new ip_total_statistics();
					p.id = id;
					records.put(id, p);
					records1.add(p);
				}
				
				p.ip = rs.getString(++i);

				ip_qam_total_record r = new ip_qam_total_record();
				r.b = rs.getBigDecimal(++i).toString();
				r.n = rs.getBigDecimal(++i).toString();
				r.ct = DateUtil.date2Str(rs.getTimestamp(++i), "yyyy/MM/dd HH:mm");

				p.qam.add(r);
			}			

			String[] a = { "dummy" };
			String s = JSONUtil.listToJson2(records1, a);
			System.out.println(s);			
			Struts2Utils.renderJson(s);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回多ip指定日期的分时走势和合计结果
	 * 
	 * http://localhost:8080/grusnms/nodes/report_queryGroupsStatistics.sip?params={"groupIds":"40288b394283bac5014283bb76b60001","start":"1970/01/01 00:00:00","end":"2015/01/01 00:00:00","period":"0","chartType":"0"}
	 */
	public class group_gbe_record {
		public String b = "0", n = "0";
		public String ct = "";
	};

	public class group_qam_record {
		public String b = "0", n = "0";
		public String ct = "";
	};
	
	public class group_statistics {
		public String dummy = "";
		public String id = "";
		public String name = "";
		public List<group_gbe_record> gbe = new ArrayList<group_gbe_record>();
		public List<group_qam_record> qam = new ArrayList<group_qam_record>();
	};
	
	public void queryGroupsStatistics() {
		HttpServletRequest request = org.apache.struts2.ServletActionContext.getRequest();
		String params = ParamUtil.getString(request, "params");
		@SuppressWarnings("unchecked")
		Map<String, String> map = JSONUtil.fromJson(params, HashMap.class);

		String groupIds = map.get("groupIds");
		String lhs = map.get("start");
		String rhs = map.get("end");
		String period = map.get("period");
		String chartType = map.get("chartType");

		Session session = null;
		session = PeakSessionFactory.instance().getCurrentSession();

		@SuppressWarnings("deprecation")
		Connection conn = session.connection();
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) conn.prepareStatement("call grusnms.groups_gbe_statistics(?,?,?,?,?)");
			stmt.setString(1, groupIds);
			stmt.setTimestamp(2, new Timestamp(DateUtil.str2Date(lhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setTimestamp(3, new Timestamp(DateUtil.str2Date(rhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setInt(4, Integer.parseInt(period));
			stmt.setBoolean(5, Integer.parseInt(chartType) == 0 ? false : true);
			ResultSet rs = stmt.executeQuery();

			Map<String, group_statistics> records = new HashMap<String, group_statistics>();
			List<group_statistics> records1 = new ArrayList<group_statistics>();
			while (rs.next()) {
				int i = 0;

				String id = rs.getString(++i);
				group_statistics p = records.get(id);
				if (p == null) {
					p = new group_statistics();
					p.id = id;
					
					String name = rs.getString(++i);
					p.name = name;
					
					records.put(id, p);
					records1.add(p);
				}

				i = 2;
				group_gbe_record r = new group_gbe_record();
				r.b = rs.getBigDecimal(++i).toString();
				r.n = rs.getBigDecimal(++i).toString();
				r.ct = DateUtil.date2Str(rs.getTimestamp(++i), "yyyy/MM/dd HH:mm");

				p.gbe.add(r);
			}

			stmt = (PreparedStatement) conn.prepareStatement("call grusnms.groups_qam_statistics(?,?,?,?,?)");
			stmt.setString(1, groupIds);
			stmt.setTimestamp(2, new Timestamp(DateUtil.str2Date(lhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setTimestamp(3, new Timestamp(DateUtil.str2Date(rhs, "yyyy/MM/dd HH:mm").getTime()));
			stmt.setInt(4, Integer.parseInt(period));
			stmt.setBoolean(5, Integer.parseInt(chartType) == 0 ? false : true);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int i = 0;

				String id = rs.getString(++i);
				group_statistics p = records.get(id);
				if (p == null) {
					p = new group_statistics();
					p.id = id;
					
					String name = rs.getString(++i);
					p.name = name;
					
					records.put(id, p);
					records1.add(p);
				}

				i = 2;
				group_qam_record r = new group_qam_record();
				r.b = rs.getBigDecimal(++i).toString();
				r.n = rs.getBigDecimal(++i).toString();
				r.ct = DateUtil.date2Str(rs.getTimestamp(++i), "yyyy/MM/dd HH:mm");

				p.qam.add(r);
			}			

			String[] a = { "dummy" };
			String s = JSONUtil.listToJson2(records1, a);
			System.out.println(s);			
			Struts2Utils.renderJson(s);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
}