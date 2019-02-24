package com.code.webcrawler.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author Vivek2.Dubey
 *
 *         This class will validate the URL
 *
 */
public class URLValidatorUtil {

	private final static Logger logger = Logger.getLogger(URLValidatorUtil.class);

	private static List<String> validFormats = new ArrayList<>();

	/**
	 * Check url is valid or not
	 * 
	 * @param url
	 * @return boolean
	 */
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

	public static void addValidFormats(String format) {

		String[] formats = format.split(",");
		Arrays.stream(formats).map(f -> f.toUpperCase()).forEach(validFormats::add);
	}

	public static boolean isIgnoreFormatURL(String url) {
		boolean isIgnoreFormatURL = false;

		if (PropertyReader.getProperty(PropertyKeyConstant.IGNORE_NON_HTML_FORMAT_URL).equals("y")) {
			isIgnoreFormatURL = validFormats.stream().filter(format -> url.toUpperCase().endsWith(format)).findAny()
					.isPresent();
		}

		return isIgnoreFormatURL;
	}

}
