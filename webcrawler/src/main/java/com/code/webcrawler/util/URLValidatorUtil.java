package com.code.webcrawler.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * @author Vivek2.Dubey
 *
 *This class will validate the URL 
 *
 */
public class URLValidatorUtil {

	private final static Logger logger = Logger.getLogger(URLValidatorUtil.class);
	
	public static boolean isValidURL(String url) {
		boolean isValid = true;

		try {
			new URL(url).toURI();
		} catch (MalformedURLException e) {
			logger.error("Malformed URL: ", e);
			isValid = false;
		} catch (URISyntaxException e) {
			logger.error("Incorrect URL Syntax : ", e);
			isValid = false;
		}

		return isValid;
	}

}
