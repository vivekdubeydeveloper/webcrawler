package com.code.webcrawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.code.webcrawler.util.InternalUrlUtil;
import com.code.webcrawler.util.URLValidatorUtil;

@RunWith(value = Parameterized.class)
public class WebCrawlerAppTest {

	private WebCrawlerApp webCrawlerApp;

	@Parameter(value = 0)
	public String internalUrl;

	@Parameter(value = 1)
	public String externalUrl;

	@Parameter(value = 2)
	public String invalidUrl;

	@Parameter(value = 3)
	public String ignoreFormatUrl;

	@Parameter(value = 4)
	public String imageUrl;

	@Parameters(name = "webcrawlerdata")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "http://www.prudential.co.uk", "http://www.mandg.com",
				"abc.prudential.co.uk", "http://www.prudential.co.uk/a.pdf",
				"https://www.prudential.co.uk/~/media/Images/P/Prudential-V2/logo/prudential-logo.png" } });
	}

	/**
	 * This method will initialize object and run the process that data will be
	 * available for each test case
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
			webCrawlerApp = new WebCrawlerApp();
			InputStream in = new ByteArrayInputStream(internalUrl.getBytes());
			System.setIn(in);
			webCrawlerApp.readUserInputUrl();
			webCrawlerApp.startProcess();
	}

	/**
	 * This method will test user input url
	 */
	@Test
	public void testReadUserInputUrl() {
		String url = webCrawlerApp.getUrl();
		assertEquals(internalUrl, url);
	}

	/**
	 * This method check url pattern is valid
	 */
	@Test
	public void testValidUrl() {
		assertTrue(URLValidatorUtil.isValidURL(internalUrl));
	}

	/**
	 * This method will check url is invalid
	 */
	@Test
	public void testInvalidUrl() {
		assertFalse(URLValidatorUtil.isValidURL(invalidUrl));
	}

	/**
	 * This method check if url is internal url 
	 * if we give any url as input it will be added into internal url by default
	 */
	@Test
	public void testInternalUrl() {
		assertTrue(InternalUrlUtil.isInternalUrl(internalUrl));
	}

	/**
	 * This method if fail test case for above method
	 */
	@Test
	public void testInternalUrlF() {
		assertFalse(InternalUrlUtil.isInternalUrl(externalUrl));
	}

	
	/**
	 * This method check url format is in igonre url format
	 * like pdf url refer property ignore.format in application.properties
	 */
	@Test
	public void testUrlFormat() {
		assertTrue(URLValidatorUtil.isIgnoreFormatURL(ignoreFormatUrl));
	}
	
	/**
	 * Fail case for above url pattern
	 */

	@Test
	public void testUrlFormatF() {
		assertFalse(URLValidatorUtil.isIgnoreFormatURL(internalUrl));
	}
	
	/*
	 * Check internal link keep internal url only not external url
	 * 
	 */
	@Test
	public void testPageInternalLinks() {
		List<String> internalLinks = webCrawlerApp.getWebCrawlerSummary().getCrawlingSummaryMap().get(internalUrl)
				.getInternalLinks();
		assertFalse(internalLinks.stream().allMatch(ele -> ele.contains(externalUrl)));
	}
	
	/*
	 * Check external link keep external url only not internal url
	 * 
	 */
	@Test
	public void testPageExternalLinks() {
		List<String> externalLinks = webCrawlerApp.getWebCrawlerSummary().getCrawlingSummaryMap().get(internalUrl)
				.getExternalLinks();
		assertFalse(externalLinks.stream().allMatch(ele -> ele.contains(internalUrl)));
	}

	/*
	 * Check page image exist in image collection
	 * 
	 */
	@Test
	public void testPageImage() {
		List<String> images = webCrawlerApp.getWebCrawlerSummary().getCrawlingSummaryMap().get(internalUrl).getImages();
		assertTrue(images.stream().anyMatch(ele -> ele.contains(imageUrl)));
	}

}
