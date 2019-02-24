package com.code.webcrawler.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.code.webcrawler.model.WebCrawlerSummary;
import com.code.webcrawler.model.WebPage;
import com.code.webcrawler.util.PropertyKeyConstant;
import com.code.webcrawler.util.PropertyReader;

public class WebCrawlerImpl implements WebCrawlerService {

	private static int counter = 1;
	Set<String> urlsSet = new HashSet<>();
	HtmlProcessorService htmlProcessorService;
	private static int maxPageToScan;
	
	public WebCrawlerImpl(HtmlProcessorService htmlProcessorService) {
		this.htmlProcessorService = htmlProcessorService;
		maxPageToScan=Integer.parseInt(PropertyReader.getProperty(PropertyKeyConstant.MAX_PAGE_TO_SCAN));
	}



	public void extractData(String url, WebCrawlerSummary webCrawlerSummary) throws IOException {

		if (!urlsSet.contains(url)) {
			urlsSet.add(url);
			
			counter++;
			// create a container to keep data of this page
			WebPage webPage = new WebPage();

			//read html document
			htmlProcessorService.readDocument(url);
			
			// find All the links on the page and add in web page
			List<String> linkAttributes = htmlProcessorService.readHtmlElementsAttribute("a[href]", "abs:href");
			linkAttributes.forEach(webPage::addLink);

			// find All the Images on the page and in web page
			List<String> imgAttributes = htmlProcessorService.readHtmlElementsAttribute("img", "abs:src");
			imgAttributes.forEach(webPage::addImage);

			// add Web page into summary
			webCrawlerSummary.addWebPage(url, webPage);

			for (String link : linkAttributes) {
				extractData(link, webCrawlerSummary);
				if (maxPageToScan!=0 && counter > maxPageToScan)
					break;
			}
		}

	}

	
}
