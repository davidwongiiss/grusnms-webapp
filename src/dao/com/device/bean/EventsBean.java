/*
 * Created on 2005-7-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.device.bean;

import java.io.FileOutputStream;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.device.common.impl.EventsListEvent;
import com.device.common.impl.EventsListResult;
import com.device.common.impl.NodeEventsResult;
import com.device.po.NodeEvents;
import com.device.util.HibernateHelper;
import com.device.util.Pagination;
import com.device.util.PeakSessionFactory;
import com.grus.nms.report.action.DateUtil;

/**
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EventsBean {
	private static Log log = LogFactory.getLog(EventsBean.class);

	private static EventsBean instance;

	/**
	 * ˽�й��������ž�ͨ��new����ʵ�������ϵ�ʵ��
	 * 
	 */
	private EventsBean() {
		try {
			init();
		}
		catch (Exception e) {
			// log.error(e);
		}
	}

	/**
	 * ��ʼ��
	 */
	private void init() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * ���singletonʵ��
	 * 
	 * @return CacheManager
	 */
	public static EventsBean getInstance() {
		// double check
		if (instance == null) {
			synchronized (EventsBean.class) {
				if (instance == null) {
					instance = new EventsBean();
				}
			}
		}
		return instance;
	}

	/**
	 * Ȩ�����б���״̬��pattern����
	 * 
	 * @param event
	 * @return
	 */
	public EventsListResult list(EventsListEvent event) {
		EventsListResult result = new EventsListResult();
		// String groupTypes = event.getGroupTypes();
		Integer handle = event.getHandle();
		String ip = event.getIp();
		String groupId = event.getGroupId();
		Integer severity = event.getSeverity();
		int pageNO = event.getPageNO();
		int pageCount = event.getPageCount();
		Pagination pagination = new Pagination(pageNO, pageCount);
		Collection c = null;

		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			StringBuffer hql = new StringBuffer("select e.*,n.ip from node_events e , nodes n ");
			if(groupId != null &&!"".equals(groupId)){
				hql.append(", groups_nodes gn where e.node_id = n.id and n.id = gn.node_id and gn.group_id = '"+groupId+"'");//���������
			}else{
				hql.append(" where e.node_id = n.id "); 
			}
			
			if (severity == 0) { // ȫ��
			}
			else if (severity > 0 && severity <= 4) { // �¼�
				hql.append(" and e.severity >= 1 AND e.severity <= 4");
			}
			else { // �澯
				hql.append(" and e.severity > 4");
			}				
			if (handle != null) {
				hql.append(" and e.handled = " + handle);
			}
			if(ip != null && !"".equals(ip)){
				hql.append(" and n.ip like '"+ip+"%'");
			}
			hql.append(" order by e.create_time desc ");
			
			c = HibernateHelper.queryBeanList(session, hql.toString(), pagination, null, NodeEventsResult.class);
			int totleNumber = HibernateHelper.getSQLRecordSum(session, hql.toString(),null);
			pagination.setRecordSum(totleNumber);
			result.setPageNO(event.getPageNO());
			result.setPageCount(event.getPageCount());
			result.setPagination(pagination);
			result.setC(c);
			// result.setDeviceType(groupTypes);
			result.setParamName("");
		}
		catch (HibernateException e) {
			// to do something....
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		}

		return result;
	}

	public void batchHandle(String[] idArray) {
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			String hql = "update NodeEvents set handled = 1 where id = ? ";
			HibernateHelper.update(session, hql, idArray);
		}
		catch (HibernateException e) {
			// to do something....
			e.printStackTrace();
		}
		finally {
		}

		return;
	}

	public void handleEvent(String id) {
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			String hql = "update NodeEvents set handled = 1 where id = ? ";
			HibernateHelper.update(session, hql, new Object[] { id });
		}
		catch (HibernateException e) {
			// to do something....
			e.printStackTrace();
		}
		finally {
		}

		return;
	}
	
	public HSSFWorkbook exportEvent(List<NodeEventsResult> result){
		// ����һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		// ��webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet("��������");
		// ��sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
		HSSFRow row = sheet.createRow((int) 0);
		// ������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("IP");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("����");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("����");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("��Դ");
		cell.setCellStyle(style);
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("��Դ");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("ʱ��");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("�Ƿ���");
		cell.setCellStyle(style);

		// д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���

		for (int i = 0; result != null && i < result.size(); i++)
		{
			row = sheet.createRow((int) i + 1);
			NodeEventsResult event = (NodeEventsResult) result.get(i);
			// ��������Ԫ�񣬲�����ֵ
			row.createCell((short) 0).setCellValue((String) event.getIp());
			row.createCell((short) 1).setCellValue(event.getSeverity());
			row.createCell((short) 2).setCellValue(event.getDescription());
			row.createCell((short) 3).setCellValue(event.getEventObject());
			row.createCell((short) 3).setCellValue(event.getPhysIdx());
			row.createCell((short) 5).setCellValue(DateUtil.date2Str(event.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
			cell = row.createCell((short) 6);
			if("1".equals(event.getHandled())){
				cell.setCellValue("�Ѵ���");
			}else{
				cell.setCellValue("δ����");
			}
		}
		return wb ;
	}

	public Collection getResult(EventsListEvent event) {
		Integer handle = event.getHandle();
		String ip = event.getIp();
		String groupId = event.getGroupId();
		Integer severity = event.getSeverity();
		Collection c = null;

		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			StringBuffer hql = new StringBuffer("select e.*,n.ip from node_events e , nodes n ");
			if(groupId != null &&!"".equals(groupId)){
				hql.append(", groups_nodes gn where e.node_id = n.id and n.id = gn.node_id and gn.group_id = '"+groupId+"'");//���������
			}else{
				hql.append(" where e.node_id = n.id "); 
			}
			
			if (severity == 0) { // ȫ��
			}
			else if (severity > 0 && severity <= 4) { // �¼�
				hql.append(" and e.severity >= 1 AND e.severity <= 4");
			}
			else { // �澯
				hql.append(" and e.severity > 4");
			}				
			if (handle != null) {
				hql.append(" and e.handled = " + handle);
			}
			if(ip != null && !"".equals(ip)){
				hql.append(" and n.ip like '"+ip+"%'");
			}
			hql.append(" order by e.create_time desc ");
			
			c = HibernateHelper.queryAllNoPage(session, hql.toString() , null ,NodeEventsResult.class);
			
			
		}
		catch (HibernateException e) {
			// to do something....
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		}
		return c ;
	}

}
