package com.code.webcrawler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.code.webcrawler.util.ApplicationUtil;

public class HtmlProcessorImpl implements HtmlProcessorService {

	Document document;
	
	public HtmlProcessorImpl(){
		
	}

	@Override
	public Elements readHtmlElements(String element) {
		// TODO Auto-generated method stub
		return document.select(element);
	}

	@Override
	public List<String> readHtmlElementsAttribute(String element, String attribute) {
		List<String> htmlAttributes=new ArrayList<>();
		
		Elements htmlElements=document.select(element);
		
		for (Element htmlElement : htmlElements) {
			String htmlAttribute=htmlElement.attr(attribute);
			if(!ApplicationUtil.isNullOrEmpty(htmlAttribute)) {
				htmlAttributes.add(htmlAttribute);
			}
		}
		return htmlAttributes;
	}

	@Override
	public void readDocument(String url) throws IOException {
		// TODO Auto-generated method stub
		document = Jsoup.connect(url).get();
	}
	
}
