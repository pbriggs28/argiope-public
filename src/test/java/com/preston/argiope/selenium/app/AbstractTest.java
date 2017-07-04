package com.preston.argiope.selenium.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The Abstract class all test classes implement. Provides access to
 * variables/methods.
 * 
 * @author pbriggs
 *
 */
public abstract class AbstractTest {
	public static final WebDriver driver = TestConfig.getDriver();
	public static final WebDriverWait driverWait = TestConfig.getDriverWait();
}
