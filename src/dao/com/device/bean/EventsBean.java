/*
 * Created on 2005-7-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.device.bean;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.device.common.impl.EventsListEvent;
import com.device.common.impl.EventsListResult;
import com.device.common.impl.NodeEventsResult;
import com.device.po.NodeEvents;
import com.device.util.HibernateHelper;
import com.device.util.Pagination;
import com.device.util.PeakSessionFactory;

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
		Boolean handle = event.getHandle();
		Integer severity = event.getSeverity();
		int pageNO = event.getPageNO();
		int pageCount = event.getPageCount();
		Pagination pagination = new Pagination(pageNO, pageCount);
		Collection c = null;

		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			StringBuffer hql = new StringBuffer("select e.*,n.ip from node_events e , nodes n where e.node_id = n.id ");
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
			hql.append("");
			
			c = HibernateHelper.queryBeanList(session, hql.toString(), pagination, null, NodeEventsResult.class);
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

}
