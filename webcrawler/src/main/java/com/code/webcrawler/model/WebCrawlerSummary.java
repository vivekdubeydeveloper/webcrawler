package com.code.webcrawler.model;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.code.webcrawler.util.JsonUtil;
import com.code.webcrawler.util.PropertyKeyConstant;
import com.code.webcrawler.util.PropertyReader;

public class WebCrawlerSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Domain domain;
	private Map<String,WebPage> crawlingSummaryMap=new HashMap<>();

	public void addDomain(Domain domain) {
		this.domain=domain;
	}
	
	public void addWebPage(String url,WebPage page) {
		crawlingSummaryMap.put(url, page);
	}
	
	
	public  void printSummary() {
		Iterator<String> urls=crawlingSummaryMap.keySet().iterator();
		int counter=1;
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Doamin ="+domain.getBaseUrl());
		while(urls.hasNext()) {
			String url=urls.next();
			WebPage webPage=crawlingSummaryMap.get(url);
			System.out.println("url ="+url);
			System.out.println("Internal== "+webPage.getInternalLinks());
			System.out.println("External=="+webPage.getExternalLinks());
			System.out.println("Images=="+webPage.getImages());
			System.out.println("counter "+counter);
			counter++;
			
		}
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	public void writeSummary() {
		File file=new File(PropertyReader.getProperty(PropertyKeyConstant.OUTPUT_FILE_PATH));
		JsonUtil.writeObjectInFile(this, file);
		
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Map<String, WebPage> getCrawlingSummaryMap() {
		return crawlingSummaryMap;
	}

	public void setCrawlingSummaryMap(Map<String, WebPage> crawlingSummaryMap) {
		this.crawlingSummaryMap = crawlingSummaryMap;
	}
	
	
	
}
