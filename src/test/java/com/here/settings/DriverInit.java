package com.here.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class DriverInit {
	
	private  WebDriver driver;
	
	@Before
	public void initDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		Driver.setDriver(driver);
		driver.manage().window().maximize();
	}
	
	
	@After
	public void tearDown() {
		Driver.getDriver().quit();
	}

}
