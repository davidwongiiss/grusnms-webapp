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

import com.device.common.impl.NodeResult;
import com.device.common.impl.NodesListEvent;
import com.device.common.impl.NodesListResult;
import com.device.po.Nodes;
import com.device.util.HibernateHelper;
import com.device.util.Pagination;
import com.device.util.PeakSessionFactory;


/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NodesBean {
	private static Log log = LogFactory.getLog(NodesBean.class);

	private static NodesBean instance;

	/**
	 * 私有构造器，杜绝通过new创建实例，保障单实例
	 *  
	 */
	private NodesBean() {
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
	public static NodesBean getInstance() {
		//double check
		if (instance == null) {
			synchronized (NodesBean.class) {
				if (instance == null) {
					instance = new NodesBean();
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
	public NodesListResult listUnallocat(NodesListEvent event) {
		NodesListResult result = new NodesListResult();
		String deviceType = event.getDeviceType();
		String name = event.getName();
		int pageNO = event.getPageNO();
		int pageCount = event.getPageCount();
		Pagination pagination = new Pagination(pageNO, pageCount);
		Collection c = null;

		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			StringBuffer hql = new StringBuffer("select n.* from Nodes n  where n.id not in (select node_id from users_nodes where user_name = ? )   ");
			c = HibernateHelper.sqlQuery(session, hql.toString(), pagination, new String[]{name}, Nodes.class);
			result.setPageNO(event.getPageNO());
			result.setPageCount(event.getPageCount());
			result.setPagination(pagination);
			result.setC(c);
			result.setDeviceType(deviceType);
			result.setParamName(name);
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}

		return result;
	}
	
	/**
	 * 权限项列表，按状态、pattern查找
	 * @param event
	 * @return
	 */
	public NodesListResult list(NodesListEvent event) {
		NodesListResult result = new NodesListResult();
		String deviceType = event.getDeviceType();
		String name = event.getName();
		int pageNO = event.getPageNO();
		int pageCount = event.getPageCount();
		Pagination pagination = new Pagination(pageNO, pageCount);
		Collection c = null;

		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			StringBuffer hql = new StringBuffer("select * from Nodes where 1=1 ");
			if (!name.equals("")) {
				hql.append(" and name like '%"+name+"%'");
			} 

			c = HibernateHelper.sqlQuery(session, hql.toString(), pagination, null, Nodes.class);
			result.setPageNO(event.getPageNO());
			result.setPageCount(event.getPageCount());
			result.setPagination(pagination);
			result.setC(c);
			result.setDeviceType(deviceType);
			result.setParamName(name);
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}

		return result;
	}
	
	public NodesListResult listAllNodes(String groupId) {
		NodesListResult result = new NodesListResult();
		Collection c = null;

		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			StringBuffer hql = new StringBuffer("select * from Nodes n left join groups_nodes gn on n.id = gn.node_id  ");
			c = HibernateHelper.queryAllNoPage(session, hql.toString() , null, NodeResult.class);
			result.setC(c);
		} catch (Exception e) {
			//to do something....
			e.printStackTrace();
		} finally {}

		return result;
	}

	public NodesListResult queryUserNodes(String userName) {
		NodesListResult result = new NodesListResult();
		Collection c = null;

		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			StringBuffer hql = new StringBuffer("select n from Nodes n , UsersNodes un  where n.id = un.nodeId and un.userName = '"+userName+"'  ");
			c = HibernateHelper.getQueryResult(session, hql.toString());
			result.setC(c);
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}

		return result;
	}

	public void batchInsert(String userName, String[] nids) {
		Session session = null;
		try{
			session = PeakSessionFactory.instance().getCurrentSession();
			String queryString = "insert into users_nodes (user_name , node_id) values ('"+userName+"',?)";
			HibernateHelper.insertBatch(session, queryString, nids);
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}

}
