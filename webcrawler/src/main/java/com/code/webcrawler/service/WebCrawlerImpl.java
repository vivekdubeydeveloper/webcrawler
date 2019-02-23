package com.code.webcrawler.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.code.webcrawler.model.WebCrawlerSummary;
import com.code.webcrawler.model.WebPage;


public class WebCrawlerImpl {

	private static int  counter=1;
	Set<String> urlsSet=new HashSet<>();
	
	public void extractData(String url) throws IOException {
		
		if(!urlsSet.contains(url)) {
			urlsSet.add(url);
			counter++;
			//read html document
			Document document = Jsoup.connect(url).get();
			//find All the links on the page
			Elements linksOnPage = document.select("a[href]");
			WebPage webPage=new WebPage();
			
			//Iterate over Each link
			for (Element page : linksOnPage) {
				//System.out.println("tag name "+page.tagName());
				String link=page.attr("abs:href");
				if(!link.equals("")) {
					webPage.addLink(link);
				}
				
				//getPageLinks(page.attr("abs:href"));
			}
			
			//Elements images = document.select("img[src]");
			Elements images = document.select("img");
			for (Element page : images) {
				//System.out.println("tag name "+page.tagName());
				String img=page.attr("abs:src");
				if(!img.equals("")) {
					webPage.addImage(img);
				}
				
				//getPageLinks(page.attr("abs:href"));
			}
			
			
			WebCrawlerSummary.addWebPage(url, webPage);
			
			linksOnPage = document.select("a[href]");
			for (Element page : linksOnPage) {
				String link=page.attr("abs:href");
				if(!link.equals("")) {
					extractData(link);
					if(counter>10)
						break;
				}
				
				
				
			}
		}
		
		
	}
}
