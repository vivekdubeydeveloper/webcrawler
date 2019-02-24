package com.code.webcrawler;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.code.webcrawler.custom.exception.PropertyReadingException;
import com.code.webcrawler.model.Domain;
import com.code.webcrawler.model.WebCrawlerSummary;
import com.code.webcrawler.service.HtmlProcessorImpl;
import com.code.webcrawler.service.WebCrawlerImpl;
import com.code.webcrawler.service.WebCrawlerService;
import com.code.webcrawler.util.InternalUrlUtil;
import com.code.webcrawler.util.PropertyKeyConstant;
import com.code.webcrawler.util.PropertyReader;
import com.code.webcrawler.util.URLValidatorUtil;

/**
 * @author Vivek2.Dubey
 * 
 *         This class is starting point of Web Crawler Application
 * 
 *         This will take input from User and use the url for crawling
 *
 */
public class WebCrawlerApp {

	private final static Logger logger = Logger.getLogger(WebCrawlerApp.class);

	public static void main(String[] args) {
		logger.info("Entry");
		System.out.println("+++Process start+++++++++++++++++++++++++++++++++++++");
		try {

			// initialize the process
			init();

			//take url from user input
			String url = readUserInputUrl();

			if (!url.equalsIgnoreCase("exit")) {
				System.out.println("Input URL for Crawling :" + url);
				System.out.println("Please wait while process is running.............");

				// Add base url in internal url list
				InternalUrlUtil.addInternalUrlPatterns(url);

				// set Domain Base Url and set Domain object into summary
				WebCrawlerSummary webCrawlerSummary = getBeanForCrawlerData(url);

				// run the crawler and extract data
				WebCrawlerService webcs = new WebCrawlerImpl(new HtmlProcessorImpl());
				webcs.extractData(url, webCrawlerSummary);

				// write data into json file
				webCrawlerSummary.printSummary();
				webCrawlerSummary.writeSummary();

				// check log for summary
			} else {
				System.out.println("You have choosen to exit from application.");
			}

		} catch (PropertyReadingException e) {
			logger.error("Error in property file reading : ", e);
		} catch (IOException e) {
			logger.error("Error in process: ", e);
		}
		System.out.println("+++Process End+++++++++++++++++++++++++++++++++++++");
		logger.info("Exit");

	}

	private static void init() {
		InternalUrlUtil.addInternalUrlPatterns(PropertyReader.getProperty(PropertyKeyConstant.INTERNAL_URLS));
	}

	private static String readUserInputUrl() {
		String url = "";
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Enter the url for crawling or Enter exit for Exit From Application: ");
			url = scanner.nextLine();
			// validate Url
			if (url.equalsIgnoreCase("exit") || URLValidatorUtil.isValidURL(url)) {
				break;
			} else {
				System.out.println("You Have Entered Wrong Url");
			}
		}

		scanner.close();

		return url;
	}

	private static WebCrawlerSummary getBeanForCrawlerData(String url) {
		WebCrawlerSummary webCrawlerSummary = new WebCrawlerSummary();
		Domain domain = new Domain(url);
		webCrawlerSummary.addDomain(domain);
		return webCrawlerSummary;
	}
}
