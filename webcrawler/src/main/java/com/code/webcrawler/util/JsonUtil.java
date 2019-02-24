package com.code.webcrawler.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class will write object into file in json format
 * 
 * @author Vivek2.Dubey
 *
 */
public class JsonUtil {

	/**
	 * This method will write object into file in json format
	 * 
	 * @param object
	 * @param file
	 */
	public static void writeObjectInFile(Object object, File file) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
