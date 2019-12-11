package com.here.settings;

import org.openqa.selenium.WebDriver;

public class Driver {
private static WebDriver driver;



public static WebDriver getDriver() {
	return driver;
}

public static void setDriver(WebDriver driverObject) {
	driver = driverObject;
}

	
}
