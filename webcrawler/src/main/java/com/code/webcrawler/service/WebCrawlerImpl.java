package com.code.webcrawler.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.code.webcrawler.model.WebCrawlerSummary;
import com.code.webcrawler.model.WebPage;
import com.code.webcrawler.util.ApplicationUtil;
import com.code.webcrawler.util.InternalUrlUtil;
import com.code.webcrawler.util.PropertyKeyConstant;
import com.code.webcrawler.util.PropertyReader;
import com.code.webcrawler.util.URLValidatorUtil;

/**
 * This class will extract data from html document and add record into report
 * bean
 * 
 * @author Vivek2.Dubey
 *
 */
public class WebCrawlerImpl implements WebCrawlerService {

	private final static Logger logger = Logger.getLogger(WebCrawlerImpl.class);

	private static int counter = 1;
	Set<String> urlsSet = new HashSet<>();
	HtmlProcessorService htmlProcessorService;
	private static int maxPageToScan;

	public WebCrawlerImpl(HtmlProcessorService htmlProcessorService) {
		this.htmlProcessorService = htmlProcessorService;
		maxPageToScan = Integer.parseInt(PropertyReader.getProperty(PropertyKeyConstant.MAX_PAGE_TO_SCAN));
	}

	/**
	 * This method will extract data from url and add into summary bean
	 * 
	 * @param url
	 * @param webCrawlerSummary
	 * @throws IOException
	 */
	public void extractData(String url, WebCrawlerSummary webCrawlerSummary) {

		if (!ApplicationUtil.isNullOrEmpty(url) && !urlsSet.contains(url) && InternalUrlUtil.isInternalUrl(url)
				&& !URLValidatorUtil.isIgnoreFormatURL(url)) {
			System.out.println("Processing url :" + url);
			urlsSet.add(url);

			counter++;
			// create a container to keep data of this page
			WebPage webPage = new WebPage();

			// read html document
			try {
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
					if (maxPageToScan != 0 && counter > maxPageToScan) {
						break;
					}

					extractData(link, webCrawlerSummary);

				}
			} catch (Throwable e) {

				System.out.println("Error in url :" + url);
				webCrawlerSummary.addError(url);
				logger.info("Error  in url :" + url);
				logger.error("Error  in url :", e);
			}

		} else {
			webCrawlerSummary.addError(url);
		}

	}

}
