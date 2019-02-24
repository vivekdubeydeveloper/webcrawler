package com.code.webcrawler.service;

import java.io.IOException;
import java.util.List;

import org.jsoup.select.Elements;

public interface HtmlProcessorService {
	public void readDocument(String url)  throws IOException;
	public Elements readHtmlElements(String element);
	public List<String> readHtmlElementsAttribute(String element,String attribute);
}
