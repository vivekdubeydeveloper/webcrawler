package com.code.webcrawler.service;

import java.io.IOException;

import com.code.webcrawler.model.WebCrawlerSummary;

public interface WebCrawlerService {
	public void extractData(String url,WebCrawlerSummary webCrawlerSummary) throws IOException ;
}
