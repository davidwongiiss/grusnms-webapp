package com.device.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * ��peak��Ŀԭ SessionFactory �޸ģ�ʹ��hibernate3.0�������� getCurrentSession ��
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
			log.info("��ʼʵ����sessionfactory...");
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
					log.error("sessionFactoryʧ��!!!" + e.getMessage());
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

	// -----------------------------�����
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
				log.error("����sessionfactoryʧ��..." + e.getMessage());
			}
			throw new ExceptionInInitializerError(e);
		}
		return factory;
	}
}
