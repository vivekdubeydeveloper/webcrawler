package com.code.webcrawler.util;

/**
 * 
 * This will keep utility methods related to application
 * 
 * @author Vivek2.Dubey
 *
 */
public class ApplicationUtil {

	/**
	 * This method will return true if string is empty or null
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String input) {
		boolean isNullOrEmpty = false;
		if (null == input || input.trim().equals("")) {
			isNullOrEmpty = true;
		}

		return isNullOrEmpty;
	}

}
