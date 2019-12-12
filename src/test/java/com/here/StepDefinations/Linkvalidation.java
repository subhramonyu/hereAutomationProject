package com.here.StepDefinations;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.here.Utils.commonUtils;
import com.here.settings.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Linkvalidation {

	List<String> linkList = new ArrayList<String>();

	@Given("^Open the Chrome and launch the url$")
	public void launchURL() throws Throwable {
		Driver.getDriver().get("https://developer.here.com/documentation");
		commonUtils.waitForPageLoad(Driver.getDriver());
		commonUtils.waitUntilAngularReady(Driver.getDriver());
	}

	@When("^Get All the links of the page$")
	public void getAllLinks() throws Throwable {
		//Incorporated NitinComments,Using Xpath for fetching links
		List<WebElement> allLinks = Driver.getDriver().findElements(By.xpath("//a"));
		for (WebElement link : allLinks) {
			linkList.add(link.getAttribute("href"));
			
		}

	}

	@Then("^Navigate all the link and wait till it loads and AngularJS is initialized$")
	public void navigateToAllLink() throws Throwable {
		System.out.println(linkList);
		for (String link : linkList) {
			if (link.contains("https")) {
				Driver.getDriver().navigate().to(link);
				commonUtils.waitForPageLoad(Driver.getDriver());
				commonUtils.waitUntilAngularReady(Driver.getDriver());
				////Incorporated NitinComments,checking for 200 response
				commonUtils.getResponse(link);
				
			}
		}
	}

}
