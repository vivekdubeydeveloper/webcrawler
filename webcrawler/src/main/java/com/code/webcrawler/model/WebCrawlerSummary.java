package com.code.webcrawler.model;


import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.code.webcrawler.util.JsonUtil;
import com.code.webcrawler.util.PropertyKeyConstant;
import com.code.webcrawler.util.PropertyReader;

public class WebCrawlerSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	private Domain domain;
	private Map<String, WebPage> crawlingSummaryMap = new HashMap<>();
	private Set<String> errorUrl = new HashSet<>();

	public void addDomain(Domain domain) {
		this.domain = domain;
	}

	public void addWebPage(String url, WebPage page) {
		crawlingSummaryMap.put(url, page);
	}

	public void addError(String url) {
		errorUrl.add(url);
	}

	public void writeSummary() {
		File file = new File(PropertyReader.getProperty(PropertyKeyConstant.OUTPUT_FILE_PATH));
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

	public Set<String> getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(Set<String> errorUrl) {
		this.errorUrl = errorUrl;
	}

}
