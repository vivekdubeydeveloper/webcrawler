package com.code.webcrawler.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WebCrawlerSummary {

	private static Map<String,WebPage> crawlingSummaryMap=new HashMap<>();

	public static void addWebPage(String url,WebPage page) {
		crawlingSummaryMap.put(url, page);
	}
	
	
	public static void printSummary() {
		Iterator<String> urls=crawlingSummaryMap.keySet().iterator();
		int counter=1;
		while(urls.hasNext()) {
			String url=urls.next();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
			WebPage webPage=crawlingSummaryMap.get(url);
			System.out.println("url ="+url);
			System.out.println("Internal== "+webPage.getInternalLinks());
			System.out.println("External=="+webPage.getExternalLinks());
			System.out.println("Images=="+webPage.getImages());
			System.out.println("counter "+counter);
			counter++;
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		}
	}
	
}
