package com.code.webcrawler.custom.exception;

/**
 * If property file reading fails occur this exception
 * 
 * @author Vivek2.Dubey
 *
 */
public class PropertyReadingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PropertyReadingException(String message, Throwable cause) {
		super(message, cause);
	}

}
