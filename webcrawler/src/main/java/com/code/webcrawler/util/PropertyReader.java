package com.code.webcrawler.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.code.webcrawler.custom.exception.PropertyReadingException;

/**
 * This class reads property file
 * 
 * @author Vivek2.Dubey
 *
 */
public class PropertyReader {

	private final static Logger logger = Logger.getLogger(PropertyReader.class);
	private static Properties props;

	/**
	 * This returns key value
	 * 
	 * @param key
	 * @return String
	 */
	public static String getProperty(String key) {

		if (null == props) {
			try {
				readProperties();
				logger.info("Property File Loaded Successfully");
			} catch (IOException e) {
				logger.error("Exception in reading property file ", e);
				throw new PropertyReadingException("Exception in reading property file ", e);
			}
		}

		return props.getProperty(key);
	}

	/**
	 * This reads property file
	 * 
	 * @throws IOException
	 */
	private static void readProperties() throws IOException {
		props = new Properties();
		props.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(ApplicationConstant.PROPERTY_FILE_NAME));

	}

}
