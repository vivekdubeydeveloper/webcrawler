package com.code.webcrawler;

import java.io.IOException;
import java.time.LocalDateTime;
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
	private WebCrawlerSummary webCrawlerSummary = null;
	private String url = null;

	public static void main(String[] args) {
		WebCrawlerApp webCrawlerApp = new WebCrawlerApp();

		// take url from user input
		webCrawlerApp.readUserInputUrl();

		webCrawlerApp.startProcess();
	}

	/**
	 * This method will start the application
	 */
	public void startProcess() {
		logger.info("Entry");
		long startTime = System.currentTimeMillis();
		System.out.println("+++Process start+++++++++++++++++++++++++++++++++++++");

		try {

			// initialize the process
			init();

			if (!url.equalsIgnoreCase("exit")) {
				System.out.println("Crawling :" + url);
				System.out.println("Please wait while process is running............." + LocalDateTime.now());

				// Add base url in internal url list
				InternalUrlUtil.addInternalUrlPatterns(url);

				// set Domain Base Url and set Domain object into summary
				createBeanForCrawlerData();

				// run the crawler and extract data
				WebCrawlerService webcs = new WebCrawlerImpl(new HtmlProcessorImpl());
				webcs.extractData(url, webCrawlerSummary);

			} else {
				System.out.println("You have choosen to exit from application.");
			}

		} catch (PropertyReadingException e) {
			System.out.println("Exception in Process.See the log.");
			logger.error("Error in property file reading : ", e);
		} catch (IOException e) {
			System.out.println("Exception in Process.See the log.");
			logger.error("Error in process: ", e);
		} catch (Throwable e) {
			System.out.println("Exception in Process.See the log.");
			logger.error("Error in process: ", e);
		} finally {

			// write data into json file
			if (null != webCrawlerSummary) {
				webCrawlerSummary.writeSummary();
			}
		}

		// check log for summary
		System.out.println(
				"check file " + PropertyReader.getProperty(PropertyKeyConstant.OUTPUT_FILE_PATH) + " for output");
		System.out.println("Process Completed............." + LocalDateTime.now());
		long endTime = System.currentTimeMillis();
		System.out.println("Total Time taken by process==" + (endTime - startTime));
		System.out.println("check log for details ");
		System.out.println("+++Process End+++++++++++++++++++++++++++++++++++++");
		logger.info("Total Time taken by process==" + (endTime - startTime));
		logger.info("Exit");
	}

	private static void init() {
		InternalUrlUtil.addInternalUrlPatterns(PropertyReader.getProperty(PropertyKeyConstant.INTERNAL_URLS));
		URLValidatorUtil.addValidFormats(PropertyReader.getProperty(PropertyKeyConstant.IGNORE_FORMATS));
	}

	/**
	 * Take Url input from user
	 * 
	 * @return url
	 */
	public void readUserInputUrl() {
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
	}

	/**
	 * create object for storing crawling result
	 * 
	 * @param url
	 */
	private void createBeanForCrawlerData() {
		webCrawlerSummary = new WebCrawlerSummary();
		Domain domain = new Domain(url);
		webCrawlerSummary.addDomain(domain);
	}

	public WebCrawlerSummary getWebCrawlerSummary() {
		return webCrawlerSummary;
	}

	public String getUrl() {
		return url;
	}

}
