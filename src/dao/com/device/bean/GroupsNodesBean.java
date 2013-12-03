/*
 * Created on 2005-7-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.device.bean;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.device.util.HibernateHelper;
import com.device.util.PeakSessionFactory;


/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupsNodesBean {
	private static Log log = LogFactory.getLog(GroupsNodesBean.class);

	private static GroupsNodesBean instance;

	/**
	 * 私有构造器，杜绝通过new创建实例，保障单实例
	 *  
	 */
	private GroupsNodesBean() {
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
	public static GroupsNodesBean getInstance() {
		//double check
		if (instance == null) {
			synchronized (GroupsNodesBean.class) {
				if (instance == null) {
					instance = new GroupsNodesBean();
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
	public void batchInsert(String gid , String[] nidArray) {
		Collection c = null;
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			String delSQL = "delete from GroupsNodes gn where gn.groupId = ? ";
			HibernateHelper.delete(session, delSQL, new Object[]{gid});
			if(nidArray != null){
				String sql = "insert into groups_nodes (node_id , group_id) values (?,'"+gid+"')";
				HibernateHelper.executeBatch(session, sql, nidArray);
			}
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}
	}
	
	public List getGroupNodeIps(String groupId){
		List c = null;
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			String queryString = "select n.ip , n.id from nodes n , groups_nodes g where n.Id = g.node_id and g.group_id = ? ";
			c = HibernateHelper.querySql(session, queryString, new String[]{groupId});
			return c ;
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}
		return null;
	}
	
	public List getGroupIps(String groupId){
		List c = null;
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			String queryString = "select n.ip from nodes n , groups_nodes g where n.Id = g.node_id and g.group_id = ? ";
			c = HibernateHelper.querySql(session, queryString, new String[]{groupId});
			return c ;
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}
		return null;
	}
	
	public List getAllIps(){
		List c = null;
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			String queryString = "select n.ip from nodes n  ";
			c = HibernateHelper.querySql(session, queryString, null);
			return c ;
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}
		return null;
	}

	public List getIpsByGroupIds(String[] idArray) {
		List c = null;
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			StringBuffer queryString = new StringBuffer("select n.ip from nodes n , groups_nodes g where n.Id = g.node_id and g.group_id in (#");
				for(String id : idArray){
					queryString.append(",'"+id+"'");
				}	
				queryString.append(")");
				
			String sql = queryString.toString().replaceAll("#,", "");
			c = HibernateHelper.querySql(session, sql,null);
			return c ;
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}
		return null;
	}

}
