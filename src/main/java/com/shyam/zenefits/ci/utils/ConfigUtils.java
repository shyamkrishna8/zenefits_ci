package com.shyam.zenefits.ci.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ConfigUtils {

	public final static ConfigUtils INSTANCE = new ConfigUtils();

	public Properties properties = new Properties();

	private ConfigUtils() {
		loadCommonProperties();
		loadEnvProperties();
		System.out.println("Config Utils started");
	}

	private void loadCommonProperties() {
		// String confDir = "classpath:" + applicationPropertiesFilePath;

		try {
			Resource resource = new ClassPathResource("/application.properties");
			this.properties = PropertiesLoaderUtils.loadProperties(resource);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	private void loadEnvProperties() {
		InputStream is = null;
		Properties properties = new Properties();
		String confDir = "ENV-" + this.properties.getProperty("environment");
		try {
			final String applicationPropertiesFilePath = confDir + java.io.File.separator + "application.properties";
			is = ConfigUtils.class.getClassLoader().getResourceAsStream(applicationPropertiesFilePath);
			properties.load(is);
			for (Entry<Object, Object> entry : properties.entrySet()) {
				this.properties.put(entry.getKey(), entry.getValue());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {

				}
			}
			properties.clear();
		}

	}

	public String getStringValue(String key) {
		String value = this.properties.getProperty(key);
		return value == null ? value : value.trim();
	}

	public String getEnvironmentSlug() {
		return properties.getProperty("environment").toLowerCase();
	}
}