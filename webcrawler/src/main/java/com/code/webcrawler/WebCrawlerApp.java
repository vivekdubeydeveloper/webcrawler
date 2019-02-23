package com.code.webcrawler;

import java.io.IOException;
import java.util.Scanner;

import com.code.webcrawler.model.WebCrawlerSummary;
import com.code.webcrawler.service.WebCrawlerImpl;
import com.code.webcrawler.util.InternalUrlUtil;

/**
 * @author Vivek2.Dubey
 * 
 * This class is starting point of Web Crawler Application
 * 
 * This will take input from User or it will use default URL
 *
 */
public class WebCrawlerApp {

	public static void main(String[] args) throws IOException {

		//url="http://www.prudential.co.uk/";
		System.out.println("Enter the url for crawling: ");
		Scanner scanner=new Scanner(System.in);
		String url = scanner.nextLine();
		System.out.println("Input URL for Crawling :"+url);
		scanner.close();
		InternalUrlUtil.setInternalUrl(url);
		WebCrawlerImpl webC = new WebCrawlerImpl();
		webC.extractData(url);
		WebCrawlerSummary.printSummary();
		WebCrawlerSummary.writeSummary();
		
	}

}
