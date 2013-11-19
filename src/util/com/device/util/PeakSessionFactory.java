package com.device.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * 对peak项目原 SessionFactory 修改，使用hibernate3.0的新特性 getCurrentSession 。
 * 
 * 
 */
public class PeakSessionFactory {
	
	private static Log log = LogFactory.getLog(PeakSessionFactory.class);

	private static SessionFactory[] factorys;
	private static int factoryCount;

//	private static final Logger log = new Logger();
	private static PeakSessionFactory instance = new PeakSessionFactory();

	public static int getFactoryCount() {
		return factoryCount;
	}

	public static PeakSessionFactory instance() {
		return instance;
	}

	private PeakSessionFactory() {
		if(log.isInfoEnabled())
		{
			log.info("开始实例化sessionfactory...");
		}
		this.factoryCount = ConfigManager.instance().getIntProperty("sessionfactory", "sessionfactory.count");

		factorys = new SessionFactory[factoryCount];

		for (int i = 0; i < factoryCount; i++) {
			String hibernatecfgxml = ConfigManager.instance().getProperty("sessionfactory",
					"sessionfactory." + i);
			log.info("session " + i + ":" + hibernatecfgxml);
			try
			{
				factorys[i] = getFactory(hibernatecfgxml);
			}catch(Exception e)
			{
				if(log.isErrorEnabled())
				{
					log.error("sessionFactory失败!!!" + e.getMessage());
				}
			}
		}
	}

	public void clearCurrentSession() {
		for (int i = 1; i < factoryCount; i++) {
			factorys[i].getCurrentSession().clear();
		}
	}

	public Session getCurrentSession(int module) {

		return factorys[module].getCurrentSession();
	}

	public Session getCurrentSession() {

		return factorys[0].getCurrentSession();
	}

	// -----------------------------新添加
	public Session newSession() {
		return factorys[0].openSession();
	}

	public Session newSession(int module) {
		return factorys[module].openSession();
	}

	// ---------------------------------

	private SessionFactory getFactory(String hibernateConfigFile) {
		SessionFactory factory = null;
		Configuration configuration = new Configuration();
		try {
			configuration.configure(hibernateConfigFile);
			factory = configuration.buildSessionFactory();
		} catch (Throwable e) {
			e.printStackTrace();
			if(log.isErrorEnabled())
			{
				log.error("建立sessionfactory失败..." + e.getMessage());
			}
			throw new ExceptionInInitializerError(e);
		}
		return factory;
	}
}
