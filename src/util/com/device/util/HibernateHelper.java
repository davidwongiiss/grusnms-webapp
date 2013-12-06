package com.device.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.springframework.dao.DataAccessException;

import com.device.exception.HibernateHelperException;



public class HibernateHelper {
	private static Log log = LogFactory.getLog(HibernateHelper.class);

    /**
     * 创建对象
     */
    public static void create(Session session,Object o) throws HibernateException {
        try {
        	session.save(o);
        } catch (DataAccessException e) {
        	log.error(e.getMessage(), e);
        }
    }
    
    public static Object get(Session session,String id, Class c) throws HibernateException {
        try {
        	return session.get(c, id);
        } catch (DataAccessException e) {
        	log.error(e.getMessage(), e);
        }
		return null;
    }
    
    public static void update(Session session,Object o) {
        try {
        	session.update(o);
        } catch (DataAccessException e) {
        	log.error(e.getMessage(), e);
        }   	
    }
    
	
	
	/**
	 * 根据NamedQuery和参数值进行分页查询
	 * 
	 * @param namedQuery
	 *            ，NamedQuery的名字，见配置文件
	 * @param page
	 *            ，分页对象
	 * @param queryParams
	 *            ，参数值
	 * @return 分页结果集 ，如果没有结果返回一个size=0的集合
	 * @throws HibernateHelperException
	 */
	public static Collection pageQuery(Session session, Query query,
			Pagination pagination, Object[] queryParams)
			throws HibernateException {

		if (queryParams != null) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i, queryParams[i]);
			}
		}

		if (pagination != null) {

			if (pagination.getPageCount() == 0) {
				pagination.setPageCount(20);
			}

			if (pagination.getRecordSum() < 0 && pagination.isNeedRecordSum())
				pagination.setRecordSum(getRecordSum(session, query
						.getQueryString(), queryParams));
			query.setFirstResult(pagination.getStartIndex() - 1);

			if (log.isDebugEnabled()) {
				log.debug(" query.setFirstResult = "
						+ (pagination.getStartIndex() - 1));
			}

			query.setMaxResults(pagination.getPageCount());

			if (log.isDebugEnabled()) {
				log.debug(" pagination.getPageCount = "
						+ (pagination.getPageCount()));
			}
		} else {

		}

		return query.list();
	}

	/**
	 * 根据NamedQuery和参数值进行查询
	 * 
	 * @param namedQuery
	 *            ，NamedQuery的名字，见配置文件
	 * @param queryParams
	 *            ，参数值
	 * @return 分页结果集 ，如果没有结果返回一个size=0的集合
	 * @throws Exception
	 */
	public static Collection query(Session session, String namedQuery,
			Object[] queryParams) throws HibernateException {
		Query query = null;
		query = session.getNamedQuery(namedQuery);

		for (int i = 0; i < queryParams.length; i++) {
			query.setParameter(i, queryParams[i]);
		}
		return query.list();
	}

	private static int getRecordSum(Session session, String queryString,
			Object[] queryParams) {
		int getRecordSum = -1;
		try {
			Query countQuery = null;
			queryString = queryString.replace('\n', ' ').replace('\t', ' ')
					.replace('\r', ' ').replaceFirst(" +order +by +.*", "");
			// String pattern1 = " *select +([^ ]+)( +from +.*)";
			String pattern1 = " *select +(.+)( +from +.*)";
			Matcher matcher1 = Pattern.compile(pattern1).matcher(queryString);
			if (matcher1.find()) {
				queryString = "select count(*) " + matcher1.group(2);
			} else {
				queryString = "select count(*) " + queryString;
			}
			countQuery = session.createQuery(queryString);

			if (queryParams != null) {

				for (int i = 0; i < queryParams.length; i++) {
					countQuery.setParameter(i, queryParams[i]);
				}
			}

			log.debug("countQuery=" + queryString);
			// System.err.println("countQuery="+queryString);
			List list = countQuery.list();
			getRecordSum = ((Long) list.get(0)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			getRecordSum = -1;
		}
		return getRecordSum;
	}
	
	public static int getSQLRecordSum(Session session, String queryString,
			Object[] values) {
		int getRecordSum = -1;
		Connection con = session.connection();
		try {
			queryString = queryString.replace('\n', ' ').replace('\t', ' ')
					.replace('\r', ' ').replaceFirst(" +order +by +.*", "");
			// String pattern1 = " *select +([^ ]+)( +from +.*)";
			String pattern1 = " *select +(.+)( +from +.*)";
			Matcher matcher1 = Pattern.compile(pattern1).matcher(queryString);
			if (matcher1.find()) {
				queryString = "select count(*) " + matcher1.group(2);
			} else {
				queryString = "select count(*) " + queryString;
			}
			PreparedStatement pstmt=con.prepareStatement(queryString );
			
			if (values != null && values.length != 0) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i, values[i]);
				}
			}
			
			ResultSet result = pstmt.executeQuery();
			if (result.next()){
				getRecordSum = result.getInt("count(*)");
			} 
			log.debug("countQuery=" + queryString);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			getRecordSum = -1;
		}
		return getRecordSum;
	}


	public static String getCountQueryStr(String queryString) {
		try {
			queryString = queryString.replace('\n', ' ').replace('\t', ' ')
					.replace('\r', ' ').replaceFirst(" +order +by +.*", "");
			String pattern1 = " *select +(.+)( +from +.*)";
			Matcher matcher1 = Pattern.compile(pattern1).matcher(queryString);
			if (matcher1.find()) {
				queryString = "select count(*) AS count " + matcher1.group(2);
			} else {
				queryString = "select count(*) AS count " + queryString;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryString;
	}



	public static int getQuerySum(Session session, String queryString) {
		int getRecordSum = -1;
		try {
			Query countQuery = null;

			System.out.println("queryString = " + queryString);
			countQuery = session.createQuery(queryString);

			log.debug("countQuery=" + queryString);
			List list = countQuery.list();
			getRecordSum = ((Integer) list.get(0)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			getRecordSum = -1;
		}
		return getRecordSum;
	}

	public static Collection getQueryResult(Session session, String queryString)
			throws HibernateException {

		Query countQuery = null;

		// System.out.println("queryString = " + queryString);
		countQuery = session.createQuery(queryString);

		// log.debug("countQuery="+queryString);

		return countQuery.list();
	}
	/**
	public static Collection getQueryResult(Session session, String queryString)
			throws HibernateException {

		Query countQuery = null;

		// System.out.println("queryString = " + queryString);
		countQuery = session.createQuery(queryString);

		// log.debug("countQuery="+queryString);

		return countQuery.list();
	}
	**/

	/**
	 * 批量删除记录Object[]为参数，type[]为参数类型
	 * 
	 * @param queryString
	 * @return
	 * @throws HibernateException
	 */
	public static void delete(Session session, String queryString)
			throws HibernateException {
		delete(session, queryString, new Object[] {});
	}

	public static void delete(Session session, String queryString,
			Object value, Type type) throws HibernateException {

		delete(session, queryString, new Object[] { value });
	}

	public static void delete(Session session, final String queryString,
			final Object[] values) throws HibernateException {

		// session.delete(queryString, values);
		// String hqlDelete = "delete Model where id = :oldName";
		Query query = session.createQuery(queryString);

		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		int deletedEntities = query.executeUpdate();

		/**
		 * Session session = sessionFactory.openSession(); Transaction tx =
		 * session.beginTransaction(); String hqlDelete = "delete Model where id
		 * = :oldName"; int deletedEntities = session.createQuery( hqlDelete )
		 * .setString( "oldName", oldName ) .executeUpdate(); tx.commit();
		 * session.close();
		 * 
		 */
		// return deleteCount.intValue();
	}

	/**
	 * 批量删除记录Object[]为参数，type[]为参数类型
	 * 
	 * @param queryString
	 * @return
	 * @throws HibernateException
	 */
	public static void update(Session session, String queryString)
			throws HibernateException {
		update(session, queryString, new Object[] {});
	}

	public static void update(Session session, String queryString,
			Object value, Type type) throws HibernateException {

		delete(session, queryString, new Object[] { value });
	}

	public static void update(Session session, final String queryString,
			final Object[] values) throws HibernateException {

		// session.delete(queryString, values);
		// String hqlDelete = "delete Model where id = :oldName";
		Query query = session.createQuery(queryString);
		// session.
		// session.createSQLQuery(arg0);

		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		int deletedEntities = query.executeUpdate();

	}

	public static void deleteBatch(Session session, final String queryString,
			final Object[] values) throws HibernateException {

		Query query = session.createSQLQuery(queryString);

		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		@SuppressWarnings("unused")
		int deletedEntities = query.executeUpdate();

	}

	public static void insertBatch(Session session, final String queryString,
			final Object[] values) throws HibernateException {

		Query query = session.createSQLQuery(queryString);

		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		int deletedEntities = query.executeUpdate();

	}
	
	public static void executeBatch(Session session , final String queryString , final Object[] values){
		Connection con = session.connection();
		try{
			PreparedStatement pstmt=con.prepareStatement(queryString );
			if (values != null && values.length != 0) { 
				for (int i = 0; i < values.length; i++) {
					pstmt.setString(1 , (String)values[i]);
					pstmt.addBatch();//加入批处理，进行打包
				}
			}
			pstmt.executeBatch();
		}catch(Exception e)
		{
           e.printStackTrace();
		}
		
	}
	/***************************************************************************
	 * 执行SQL,无分页
	 * 
	 * @param session
	 * @param queryString
	 * @param values
	 *            参数集合
	 * @return
	 * @throws HibernateException
	 */
	public static List querySql(Session session, final String queryString,
			final Object[] values) throws HibernateException {

		Query query = session.createSQLQuery(queryString);

		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();

	}


	/***************************************************************************
	 * 执行SQL
	 * 
	 * @param session
	 * @param queryString
	 * @param values
	 *            参数集合
	 * @return
	 * @throws HibernateException
	 */
	public static int executeSql(Session session, final String queryString,
			final Object[] values) throws HibernateException {

		Query query = session.createSQLQuery(queryString);

		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list().size();

	}

	/***************************************************************************
	 * 执行SQL，但返回一定对象
	 * 
	 * @param session
	 * @param queryString
	 * @param pagination
	 * @param values
	 * @param clas
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public static Collection sqlQuery2(Session session,
			final String queryString, Pagination pagination,
			final Object[] values, Map<String,Class> clzs,String totalsql) throws HibernateException {

		
		SQLQuery query = session.createSQLQuery(queryString);
		Iterator<Map.Entry<String, Class>> iter = clzs.entrySet().iterator(); 
		while (iter.hasNext()) { 
			Map.Entry<String, Class> entry = (Map.Entry) iter.next();
			
		   query.addEntity(entry.getKey(), entry.getValue());
		} 
		

		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		if (pagination != null) {

			if (pagination.getPageCount() == 0) {
				pagination.setPageCount(20);
			}
			
			if(values.length>2)
			{
				totalsql = totalsql.replaceAll("\\*", "1");
				
				totalsql="select count(1) from ("+totalsql+") msgtotal ";
			}else
			{
				totalsql = totalsql.replace("*", "count(*)");
			}
			
			
			SQLQuery query2 = session.createSQLQuery(totalsql);
			if (values != null && values.length != 0) {
				for (int i = 0; i < values.length; i++) {
					query2.setParameter(i, values[i]);
				}
			}
			
			Object obj = query2.uniqueResult();
			
			
			
			pagination.setRecordSum(Integer.parseInt(obj.toString()));
			query.setFirstResult(pagination.getStartIndex() - 1);
			query.setMaxResults(pagination.getPageCount());
		}
		return query.list();

	}

	/***************************************************************************
	 * 执行SQL，但返回一定对象
	 * 
	 * @param session
	 * @param queryString
	 * @param pagination
	 * @param values
	 * @param clas
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public static Collection sqlQuery(Session session, String queryString,
			Pagination pagination, final Object[] values,
			Map<String, Class> clzs, String totalsql) throws HibernateException {
		// `order`
		if (queryString.contains("union")) {
			queryString = queryString.replaceAll("`order`", "");// 去除
		} else {
			queryString = queryString.replace("`order`", "order by date desc limit "
					+ (pagination.getStartIndex() - 1) + ","
					+ pagination.getPageCount() + " ");
		}

		SQLQuery query = session.createSQLQuery(queryString);
		Iterator<Map.Entry<String, Class>> iter = clzs.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Class> entry = (Map.Entry) iter.next();

			query.addEntity(entry.getKey(), entry.getValue());
		}

		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		if (pagination != null) {

			if (pagination.getPageCount() == 0) {
				pagination.setPageCount(20);
			}

			if (values.length > 2) {
				totalsql = totalsql.replaceAll("\\*", "1");

				totalsql = "select count(1) from (" + totalsql + ") msgtotal ";
			} else {
				totalsql = totalsql.replace("*", "count(*)");
			}

			SQLQuery query2 = session.createSQLQuery(totalsql);
			if (values != null && values.length != 0) {
				for (int i = 0; i < values.length; i++) {
					query2.setParameter(i, values[i]);
				}
			}

			Object obj = query2.uniqueResult();

			pagination.setRecordSum(Integer.parseInt(obj.toString()));
			if (queryString.contains("union")) {
				query.setFirstResult(pagination.getStartIndex() - 1);
				query.setMaxResults(pagination.getPageCount());
			} 
			
		}
		return query.list();

	}
	
	
	/***************************************************************************
	 * 执行SQL，但返回一定对象
	 * 
	 * @param session
	 * @param queryString
	 * @param pagination
	 * @param values
	 * @param clas
	 * @return
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public static Collection sqlQuery(Session session,
			final String queryString, Pagination pagination,
			final Object[] values, Class clas) throws HibernateException {

		Query query = session.createSQLQuery(queryString).addEntity(clas);

		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		if (pagination != null) {

			if (pagination.getPageCount() == 0) {
				pagination.setPageCount(20);
			}
			pagination.setRecordSum(query.list().size());
			query.setFirstResult(pagination.getStartIndex() - 1);
			query.setMaxResults(pagination.getPageCount());
		}
		return query.list();

	}
	/**
	 * 联合查询返回自定义的bean文件
	 * @param session
	 * @param queryString
	 * @param values
	 * @param clas
	 * @return
	 * @throws Exception
	 */
	public static Collection queryBeanList(Session session,
			String queryString,Pagination pagination, final Object[] values, Class clas )
			throws Exception {
		Connection con = session.connection();
		
		if (queryString.contains("union")) {
			queryString = queryString.replaceAll("`order`", "");// 去除
		} else if (queryString.contains("`order`")){
			queryString = queryString.replace("`order`", "order by date desc limit "
					+ (pagination.getStartIndex() - 1) + ","
					+ pagination.getPageCount() + " ");
		}else{
			queryString = queryString + " limit "
					+ (pagination.getStartIndex() - 1) + ","
					+ pagination.getPageCount() + " ";
		}
		
		PreparedStatement pstmt=con.prepareStatement(queryString );
		
		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				pstmt.setObject(i, values[i]);
			}
		}
		
		ResultSet result = pstmt.executeQuery();
		ResultSetMetaData metadata = (ResultSetMetaData) result.getMetaData();
		List list = new ArrayList();
		while (result.next()) {
			Object obj = getObject(result, clas);
			list.add(obj);
		}
		

		

		return list;

	}

	/**
	 * 联合查询返回自定义的bean文件
	 * @param session
	 * @param queryString
	 * @param values
	 * @param clas
	 * @return
	 * @throws Exception
	 */
	public static Collection queryAllNoPage(Session session,
			final String queryString, final Object[] values, Class clas )
			throws Exception {

		//SQLQuery query = session.createSQLQuery(queryString);
		
		  Connection con = session.connection();
	//	session.createSQLQuery("").getReturnAliases()
		
		  PreparedStatement pstmt=con.prepareStatement(queryString);
		

		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				pstmt.setObject(i, values[i]);
			}
		}
		ResultSet result = pstmt.executeQuery();
		
		ResultSetMetaData metadata = (ResultSetMetaData) result.getMetaData();
		//List queryList = query.list();
//		
//		for(Object orig :queryList)
//		{
//			Object dest = clas.newInstance();
//			Object[] res = (Object[])orig;
//			for(int i=0;i<alias.length;i++)
//			{
//				BeanUtils.setProperty(dest, alias[i].getName(), res[i]);
//			}
//			
//			//
//			//BeanUtils.copyProperties(dest, orig);
//		}
//	
		  List list = new ArrayList();
		while (result.next()) {
		    Object obj = getObject(result, clas);
		    list.add(obj);
		   }

		return list;

	}
	

	 private  static Object getObject(ResultSet rs, Class cls) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		  Field[] fields = cls.getDeclaredFields();
		  ResultSetMetaData metaData = rs.getMetaData();
		  int columnCount = metaData.getColumnCount();
		  Object dest=cls.newInstance();
		  for (int i = 1; i <= columnCount; i++) {
		   String columnName = metaData.getColumnName(i);
		   
		  
		  
		  Object value = rs.getObject(columnName);
		  
		  String fileName =   fieldConvert(columnName); 
			   
		   BeanUtils.setProperty(dest, fileName, value);
//		   Field field = getField(fields, columnName);
//		   if (field != null) {
//		    if (object==null) {
//		     object=cls.newInstance();
//		    }
//		    field.setAccessible(true);
//		    Object value = rs.getObject(field.getName());
//		    setFieldValue(object, value, field);
//		   }
		  }
		  return dest;
		 }
		 private  Field getField(Field[] fields, String columnName) {
		  for (Field field : fields) {
		   if (columnName.toUpperCase().equals(field.getName().toUpperCase())) {
		    return field;
		   }
		  }
		  return null;
		 }
		 private  void setFieldValue(Object obj, Object value, Field field)
		   throws IllegalArgumentException, IllegalAccessException {
		  if (value == null) {
		   return;
		  }
		 }
		  
		  private static String fieldConvert(String dbFieldName){
			    if(dbFieldName==null){
			        System.out.println("dbFieldName is null");
			        return null;
			    }

			    //不包含_,将直接返回原来的name
			    if(!dbFieldName.contains("_")){
			        return dbFieldName;
			    }


			    String[] ss = dbFieldName.split("_");
			    StringBuilder sb = new StringBuilder(ss[0]);
			    for(int i=1; i< ss.length; i++){
			        sb.append(ss[i].substring(0,1).toUpperCase()).append(ss[i].substring(1));
			    }
			    return sb.toString();
			}
		  
		 // BeanUtils.setProperty(dest, alias[i].getName(), res[i]);
//		  if (field.getType() == Long.class) {
//		   field.set(obj, StringUtil.toLong(value));
//		  } else if (field.getType() == Double.class) {
//		   field.set(obj, StringUtil.toDouble(value));
//		  } else if (field.getType() == Integer.class) {
//		   field.set(obj, StringUtil.toInteger(value));
//		  } else if (field.getType() == Date.class) {
//		   field.set(obj, new Date());
//		  } else {
//		   field.set(obj, StringUtil.toString(value));
//		  }
		 

	@SuppressWarnings("unchecked")
	public static Collection sqllQuery(Session session, final Query query,
			final Object[] values) throws HibernateException {
		// Query query =null;
		try {
			// query = session.createSQLQuery(queryString).;
			if (values != null && values.length != 0) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public static Collection sqlTransQuery(Session session,
			final String queryString, Pagination pagination,
			final Object[] values, Class clas) throws HibernateException {

		Query query = session.createSQLQuery(queryString).addEntity("m", clas)
				.addJoin("p", "m.model");
		// query.setResultTransformer(Transformers.aliasToBean(clas));
		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		if (pagination != null) {

			if (pagination.getPageCount() == 0) {
				pagination.setPageCount(20);
			}

			pagination.setRecordSum(query.list().size());
			query.setFirstResult(pagination.getStartIndex() - 1);
			query.setMaxResults(pagination.getPageCount());
		}
		return query.list();
	}

	public static Object getUniqueResult(Session session, String queryString,
			Object[] params) throws HibernateException {

		Query countQuery = null;

		countQuery = session.createQuery(queryString).setMaxResults(1);

		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				countQuery.setParameter(i, params[i]);
			}
		}
		return countQuery.uniqueResult();
	}
}
