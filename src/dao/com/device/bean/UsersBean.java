/*
 * Created on 2005-7-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.device.bean;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.device.common.impl.UsersEvent;
import com.device.common.impl.UsersListResult;
import com.device.po.Users;
import com.device.util.HibernateHelper;
import com.device.util.Pagination;
import com.device.util.PeakSessionFactory;


/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UsersBean {
	private static Log log = LogFactory.getLog(UsersBean.class);

	private static UsersBean instance;

	/**
	 * 私有构造器，杜绝通过new创建实例，保障单实例
	 *  
	 */
	private UsersBean() {
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
	public static UsersBean getInstance() {
		//double check
		if (instance == null) {
			synchronized (UsersBean.class) {
				if (instance == null) {
					instance = new UsersBean();
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
	public UsersListResult list(UsersEvent event) {
		UsersListResult result = new UsersListResult();
		String userName = event.getUserName();
		String moblieNo = event.getMobileNo();
		Integer isAdmin = event.getIsAdmin();
		Date beginTime = event.getBeginTime();
		Date endTime =event.getEndTime();
		int pageNO = event.getPageNO();
		int pageCount = event.getPageCount();
		Pagination pagination = new Pagination(pageNO, pageCount);
		Collection c = null;

		Session session = null;
		try {
			session = PeakSessionFactory.instance().getCurrentSession();
			StringBuffer hql = new StringBuffer("select * from users where is_delete=0 ");
			if(!"".equals(userName)){
				hql.append(" and name = '"+userName+"' ");
			}
			if(!"".equals(moblieNo)){
				hql.append(" and moblie_no = '"+moblieNo+"' ");
			}
			if(isAdmin != -1){
				hql.append(" and is_admin = '"+isAdmin+"' ");
			}
			if((beginTime!=null &&!"".equals(beginTime))&&(endTime != null && !"".equals(endTime))){
				hql.append(" and create_time between '"+beginTime+"' and '"+endTime+"'");
			}
			c = HibernateHelper.sqlQuery(session, hql.toString(), pagination, null, Users.class);
			result.setPageNO(event.getPageNO());
			result.setPageCount(event.getPageCount());
			result.setPagination(pagination);
			result.setC(c);
		} catch (HibernateException e) {
			//to do something....
			e.printStackTrace();
		} finally {}

		return result;
	}

	public void saveUser(Users user) {
		Session session = null;
		try{
			session = PeakSessionFactory.instance().getCurrentSession();
			HibernateHelper.create(session, user);
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}

	public void updateUser(Users user) {
		Session session = null;
		try{
			session = PeakSessionFactory.instance().getCurrentSession();
			HibernateHelper.update(session, user);
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}

	public void deleteUser(String userName) {
		Session session = null;
		try{
			session = PeakSessionFactory.instance().getCurrentSession();
			String queryString = "update Users set isDelete = 1 where name = ? ";
			HibernateHelper.delete(session, queryString, new String[]{userName});
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}

	public Users queryUserBean(String userName) {
		Session session = null;
		try{
			session = PeakSessionFactory.instance().getCurrentSession();
			Users user = (Users)HibernateHelper.get(session, userName, Users.class);
			return user;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return null;
	}

}
