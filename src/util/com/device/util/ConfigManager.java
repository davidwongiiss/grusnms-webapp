package com.device.util;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigManager {

	private static final Logger log = new Logger();
	private static ConfigManager configManger = new ConfigManager();

	private volatile static Map<String, Properties> proMap = new ConcurrentHashMap<String, Properties>();

	private ConfigManager() {

	}

	public static ConfigManager instance() {
		return configManger;
	}

	public String getProperty(String profileName, String key) {
		if (key == null || profileName == null) {
			throw new IllegalArgumentException("key is null");
		}
		Properties properties = proMap.get(profileName);

		if (properties == null) {
			synchronized (this) {

				if (properties == null) {

					properties = this.loadProps(profileName);

					if (properties != null) {
						proMap.put(profileName, properties);
					} else {
						return null;
					}
				}
			}
		}

		String value = properties.getProperty(key);
		return value;
	}

	public int getIntProperty(String profileName, String key) {
		if (key == null || profileName == null) {
			throw new IllegalArgumentException("key is null");
		}

		String intValue = this.getProperty(profileName, key);

		try {
			return Integer.parseInt(intValue);
		} catch (Exception e) {
			return -1;
		}
	}

	public Properties loadProps(String proFileName) {

		log.debug("Getting Config");

		Properties properties = null;

		InputStream in = null;

		try {
			properties = new Properties();

			String fileName = "/" + proFileName + ".properties";

			in = getClass().getResourceAsStream(fileName);
			properties.load(in);

			log.info("Successfully  proFileName=" + proFileName + " load Properties:" + properties);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error reading " + proFileName + " in loadProps() " + e.getMessage());
			log.error("Ensure the " + proFileName + " file is readable and in your classpath.");
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return properties;
	}
}
