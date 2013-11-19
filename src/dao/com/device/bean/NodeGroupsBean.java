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

import com.device.po.NodeGroups;
import com.device.util.HibernateHelper;
import com.device.util.JSONUtil;
import com.device.util.PeakSessionFactory;


/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NodeGroupsBean {
	private static Log log = LogFactory.getLog(NodeGroupsBean.class);

	private static NodeGroupsBean instance;

	/**
	 * 私有构造器，杜绝通过new创建实例，保障单实例
	 *  
	 */
	private NodeGroupsBean() {
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
	public static NodeGroupsBean getInstance() {
		//double check
		if (instance == null) {
			synchronized (NodeGroupsBean.class) {
				if (instance == null) {
					instance = new NodeGroupsBean();
				}
			}
		}
		return instance;
	}

	
	/**
	 * 查询结点组，生成json字符串
	 * @param rootType 
	 * @return
	 */
	public String bulidGroupTree(String rootType) {
		Collection c = getGroupType();
		c.add(NodeGroups.getRoot(rootType));
		String[] treeTab = {"id" , "pId" , "name" , "description" , "groupType" , "latitude" , "longitude" , "createTime" , "updateTime" , "creator" , "updater" , "isSystem"};
		String result = JSONUtil.listToJson(c, treeTab);
		return result ;
	}
	
	
	public Collection getGroupType(){
		Collection c = null;
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			String hql = "select g from NodeGroups g";
			c = HibernateHelper.getQueryResult(session, hql);
			return c ;
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}
		return null;
	}
	
	/**
	 * 保存结点组
	 * @param nodeGroups
	 */
	public void saveGroup(NodeGroups nodeGroups){
		Session session = null;
		try{
			session = PeakSessionFactory.instance().getCurrentSession();
			HibernateHelper.create(session, nodeGroups);
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}
	/**
	 * 修改结点组
	 * @param nodeGroups
	 */
	public void editGroup(NodeGroups nodeGroups){
		
	}
	
	public void deleteGroup(String groupId){
		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			String queryString = "delete from NodeGroups g where g.id = ?";
			HibernateHelper.delete(session, queryString , new Object[]{groupId});
			return ;
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}
		return;
	}
	

}
