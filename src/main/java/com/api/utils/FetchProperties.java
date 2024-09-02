package com.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FetchProperties {

	public static Properties propEnv = new Properties();
	public static Properties propMain = new Properties();
	

	public static String fetchURI() throws IOException {
		FileInputStream fisEnv = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\env.properties");
		propEnv.load(fisEnv);
		String environment = propEnv.getProperty("env");
		String URI=null;
		try {
			if (environment.equalsIgnoreCase("dev")) {

				FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\dev.properties");
				propMain.load(fisDev);
				URI = propMain.getProperty("ServerUrl");
				
			} else if (environment.equalsIgnoreCase("qa")) {
				FileInputStream fisQA = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\qa.properties");
				propMain.load(fisQA);
				URI = propMain.getProperty("ServerUrl");
				
			} else if (environment.equalsIgnoreCase("staging")) {
				FileInputStream fisStaging = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\staging.properties");
				propMain.load(fisStaging);
				URI = propMain.getProperty("ServerUrl");
				
			} else {
				System.out.println("Invalid URI entry!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception : " + e.getMessage());
		}

		return URI;

	}
	
	

}
