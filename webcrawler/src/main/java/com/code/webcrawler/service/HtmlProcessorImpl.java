package com.code.webcrawler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.code.webcrawler.util.ApplicationUtil;

/**
 * This class will process html document
 * 
 * @author Vivek2.Dubey
 *
 */
public class HtmlProcessorImpl implements HtmlProcessorService {

	Document document;

	/*
	 * This method reads html elements from html document
	 * 
	 * return Elements
	 */
	@Override
	public Elements readHtmlElements(String element) {
		// TODO Auto-generated method stub
		return document.select(element);
	}

	/*
	 * This method reads html elements attribute from html document
	 * 
	 * return List<String>
	 */

	@Override
	public List<String> readHtmlElementsAttribute(String element, String attribute) {
		List<String> htmlAttributes = new ArrayList<>();

		Elements htmlElements = document.select(element);

		for (Element htmlElement : htmlElements) {
			String htmlAttribute = htmlElement.attr(attribute);
			if (!ApplicationUtil.isNullOrEmpty(htmlAttribute)) {
				htmlAttributes.add(htmlAttribute);
			}
		}
		return htmlAttributes;
	}

	/*
	 * This method reads html document
	 * 
	 * return List<String>
	 */

	@Override
	public void readDocument(String url) throws IOException {
		// TODO Auto-generated method stub
		document = Jsoup.connect(url).ignoreContentType(true).timeout(60000).get();
	}

}
