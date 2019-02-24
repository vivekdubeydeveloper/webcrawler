package com.code.webcrawler.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vivek2.Dubey
 * 
 *         This class will help in finding internal url
 *
 */
public class InternalUrlUtil {

	private static List<String> internalUrl = new ArrayList<String>();

	/**
	 * @param patterns
	 * 
	 *            This method add url patterns to check internal url
	 */
	public static void addInternalUrlPatterns(String patterns) {
		String[] pattern = patterns.split(",");
		Arrays.stream(pattern).map(url -> {
			url = url.replace("https://", "").replace("http://", "");
			int index = url.indexOf("/");
			if (index != -1) {
				url = url.substring(0, index);
			}
			return url;
		}).filter(url -> !internalUrl.contains(url)).forEach(internalUrl::add);
	}

	/**
	 * This method will return true if input url is internal url else false
	 * 
	 * @param testUrl
	 * @return
	 */
	public static boolean isInternalUrl(String testUrl) {
		return internalUrl.stream().filter(url -> testUrl.contains(url)).findAny().isPresent();
	}

}
