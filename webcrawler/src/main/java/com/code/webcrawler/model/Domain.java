package com.code.webcrawler.model;

import java.io.Serializable;

public final class Domain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String baseUrl;

	public Domain(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
	
}
