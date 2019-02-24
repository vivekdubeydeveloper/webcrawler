package com.code.webcrawler.util;

public class ApplicationUtil {
	
	public static boolean isNullOrEmpty(String input) {
		boolean isNullOrEmpty=false;
		if(null==input || input.trim().equals("")) {
			isNullOrEmpty=true;
		}
			
		return isNullOrEmpty;
	}

}
