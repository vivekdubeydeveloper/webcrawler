package com.code.webcrawler.model;

import java.util.ArrayList;
import java.util.List;

import com.code.webcrawler.util.InternalUrlUtil;

public class WebPage {

	private List<String> internalLinks = new ArrayList<>();
	private List<String> externalLinks = new ArrayList<>();
	private List<String> images = new ArrayList<>();

	public void addLink(String link) {
		if (link.contains(InternalUrlUtil.getInternalUrl())) {
			internalLinks.add(link);
		} else {
			externalLinks.add(link);
		}

	}

	public void addImage(String img) {
		images.add(img);
	}

	public List<String> getInternalLinks() {
		return internalLinks;
	}

	public void setInternalLinks(List<String> internalLinks) {
		this.internalLinks = internalLinks;
	}

	public List<String> getExternalLinks() {
		return externalLinks;
	}

	public void setExternalLinks(List<String> externalLinks) {
		this.externalLinks = externalLinks;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
