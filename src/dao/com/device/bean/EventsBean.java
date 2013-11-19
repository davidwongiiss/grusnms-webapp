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
import com.device.po.NodeEvents;
import com.device.util.HibernateHelper;
import com.device.util.Pagination;
import com.device.util.PeakSessionFactory;


/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EventsBean {
	private static Log log = LogFactory.getLog(EventsBean.class);

	private static EventsBean instance;

	/**
	 * 私有构造器，杜绝通过new创建实例，保障单实例
	 *  
	 */
	private EventsBean() {
		try {
			init();
		} catch (Exception e) {
			//log.error(e);
		}
	}

	/**
	 * 初始化
	 */
	private void init() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 获得singleton实例
	 * 
	 * @return CacheManager
	 */
	public static EventsBean getInstance() {
		//double check
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
	 * 权限项列表，按状态、pattern查找
	 * @param event
	 * @return
	 */
	public EventsListResult list(EventsListEvent event) {
		EventsListResult result = new EventsListResult();
//		String groupTypes = event.getGroupTypes();
		Integer handle = event.getHandle();
		String severity = event.getSeverity();
		int pageNO = event.getPageNO();
		int pageCount = event.getPageCount();
		Pagination pagination = new Pagination(pageNO, pageCount);
		Collection c = null;

		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			StringBuffer hql = new StringBuffer("select e.* from node_events e where 1 = 1");
			if (!severity.equals("")) {
				hql.append(" and e.severity = '"+severity+"'");
			} 
			/**
			if(!groupTypes.equals("")){
				hql.append(" and groupTypes = '"+groupTypes+"'");
			}
			**/
			if(handle != -1){
				hql.append(" and e.handled = "+ handle);
			}
			c = HibernateHelper.sqlQuery(session, hql.toString(), pagination, null, NodeEvents.class);
			result.setPageNO(event.getPageNO());
			result.setPageCount(event.getPageCount());
			result.setPagination(pagination);
			result.setC(c);
//			result.setDeviceType(groupTypes);
			result.setParamName(severity);
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}

		return result;
	}

	public void batchHandle(String[] idArray) {
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			String hql = "update NodeEvents set handled = 1 where id = ? ";
			HibernateHelper.update(session, hql , idArray);
		} catch (HibernateException e) {
			// to do something....
			e.printStackTrace();
		} finally {
		}

		return;
	}

	public void handleEvent(String id) {
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			String hql = "update NodeEvents set handled = 1 where id = ? ";
			HibernateHelper.update(session, hql , new Object[]{id});
		} catch (HibernateException e) {
			// to do something....
			e.printStackTrace();
		} finally {
		}

		return;
	}

}
