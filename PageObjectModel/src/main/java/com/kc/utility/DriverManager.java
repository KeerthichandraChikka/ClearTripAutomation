package com.kc.utility;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	//private static WebDriver driver ;//= new RemoteWebDriver();

	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();


	public static void setDriver(WebDriver driver) {
		System.out.println("Driver is set with memory: "+driver);
		dr.set(driver);	
	}

	public static WebDriver getDriver() {
		return dr.get();
	}


}
