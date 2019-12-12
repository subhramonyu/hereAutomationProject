package com.here.Utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.here.settings.Driver;

public class commonUtils {

	public static void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> domLoad = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}

		};

		WebDriverWait wait = new WebDriverWait(driver, 180);
		try {
			wait.until(domLoad);
		} catch (Throwable error) {
			System.out.println("Timeout waiting for Page Load Request to complete.");
		}
	}

	public static void waitUntilAngularReady(WebDriver driver) {
		try {
			Boolean angularUnDefined = (Boolean) ((JavascriptExecutor) Driver.getDriver())
					.executeScript("return window.angular === undefined");
			if (!angularUnDefined) {
				Boolean angularInjectorUnDefined = (Boolean) ((JavascriptExecutor) Driver.getDriver())
						.executeScript("return angular.element(document).injector() === undefined");
				if (!angularInjectorUnDefined) {
					poll(20);

					waitForAngularLoad(driver);

					poll(20);
				}
			}
		} catch (WebDriverException ignored) {
		}
	}

	private static void waitForAngularLoad(WebDriver driver) {
		try {
			ExpectedCondition<Boolean> angularLoad = new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver driver) {
					return (Boolean) ((JavascriptExecutor) driver).executeScript(
							"return angular.element(document).injector().get('$http').pendingRequests.length === 0");
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, 180);

			wait.until(angularLoad);

		} catch (WebDriverException ignored) {

		}
	}

	private static void poll(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void getResponse(String url) throws IOException {

		try {
			HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
			
			connection.setRequestMethod("HEAD");

			connection.connect();

			if (connection.getResponseCode() == 200) {
				System.out.println(url + " is a valid link");
			} else {
				System.out.println(url + " is a broken link");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		}

	}
}
