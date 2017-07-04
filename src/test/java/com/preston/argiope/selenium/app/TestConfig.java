package com.preston.argiope.selenium.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.preston.argiope.selenium.constant.ArgiopeConstantTesting;

/**
 * Created by pbriggs on 9/12/2016.
 */
public class TestConfig {
	private static WebDriver driver = new ChromeDriver();
	private static WebDriverWait driverWait = new WebDriverWait(driver, ArgiopeConstantTesting.DRIVER_TIMEOUT);

	public static WebDriver getDriver() {
		return driver;
	}

	public static WebDriverWait getDriverWait() {
		return driverWait;
	}

	@BeforeSuite
	private static void init(ITestContext testContext) {
		// TODO: Set the initialization parameters (browser, url etc.)
		// TODO: Verify test users are created and users to be created are deleted
		driver.get(ArgiopeConstantTesting.URL_INDEX);
	}

	@AfterSuite
	private static void close() {
		driver.quit();
	}
}
