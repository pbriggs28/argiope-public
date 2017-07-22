package com.preston.argiope.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement;
import com.preston.argiope.model.dev.IpBlockingResetRequest;
import com.preston.argiope.model.dev.IpBlockingResetResponse;
import com.preston.argiope.model.dev.IpBlockingResetResponse.IpBlockingResetResult;
import com.preston.argiope.selenium.app.DocumentReadyPredicate;
import com.preston.argiope.selenium.app.TestConfig;
import com.preston.argiope.selenium.constant.ArgiopeConstantTesting;

/*
	JavaDoc constants

	Records the current URL
	if (returnToPage == true), navigates back to the original page this method was called from.

 */

/**
 * Created by pbriggs on 9/12/2016.
 */
public class TestUtils {
	private static WebDriver driver = TestConfig.getDriver();
	private static WebDriverWait driverWait = TestConfig.getDriverWait();
	public static final DocumentReadyPredicate docReadyPred = new DocumentReadyPredicate();

	public static void waitDocReady() {
		driverWait.until(docReadyPred);
	}

	/**
	 * ~Test Safe~ <br/>
	 * Safely checks if an element with the specified class exists
	 * on the current page. Returns false if the element does not exist. Returns
	 * true if any number of the element exists unless
	 * <code>classNameUnique == true</code> in which case this will only return true if
	 * a single element exists and will {@link Assert#fail()} if more than one exists.
	 * 
	 * @param elemClass
	 * @param classNameUnique
	 * @return
	 */
	public static boolean elementExistsByClass(String elemClass, boolean classNameUnique) {
		boolean elemExists = false;
		int numOfElems = driver.findElements(By.className(elemClass)).size();
		if(numOfElems == 0) {
			return elemExists;
		} else if(numOfElems == 1) {
			elemExists = true;
		} else if(numOfElems > 1) {
			if(classNameUnique == true) {
				//classNameUnique
				Assert.fail("More than one element with class name '" + elemClass + "' exists and boolean input parameter is set to '" + classNameUnique + "'");			
			} else {
				elemExists = true;
			}
		} else {
			Assert.fail("numOfElems < 0");
			// TODO: Error: numOfElems < 0 or null
		}

		return elemExists;
	}

	/**
	 * *****Not Test Safe*****<br />
	 * <br />
	 * Records the current URL, checks if user is authenticated, if true, navigates to login page, logs in and
	 * if (returnToPage == true), navigates back to the original page this method was called from.
	 *
	 */
	public static void loginAutomationTestingUser() {
		String returnToUrl = driver.getCurrentUrl();
		if(UserUtils.isUserAuth() == false) {
			driver.get(ArgiopeConstantTesting.URL_LOGIN);
			UserUtils.typeLoginForm(true, true);
			driver.get(returnToUrl);
		}
	}

	/**
	 * *****Not Test Safe*****<br />
	 * <br />
	 * Checks if the user is authenticated and the logout button exists. If so logs 
	 * the user out by calling {@link TestUtils#clickLogout()}.
	 *
	 */
	public static void logoutAutomationTestingUser() {
		if(UserUtils.isUserAuth()) {
			if(elementExistsByClass(ArgiopeConstantTestElement.CLASS_BUTTON_LOGOUT, true)) {
				clickLogout();
				if(UserUtils.isUserAuth()) {
					Assert.fail("clickLogout() did not unauthorize user.");
				}
			} else {
				Assert.fail("User is authorized but " + ArgiopeConstantTestElement.CLASS_BUTTON_LOGOUT + " element not found.");
			}
		}

	}

	public static void createNewTestUser() {
		if(newTestUserExists() == false) {
			driver.get(ArgiopeConstantTesting.URL_CREATE_USER);
			UserUtils.typeCreateUserForm(true, true, true, true);
		}
	}

	/**
	 * *****Not Test Safe*****<br />
	 * <br />
	 * Deletes the temporary test user used for testing creation, deletion etc. of users (i.e.,
	 * {@link ArgiopeConstantTestElement#NEW_USER_USERNAME}). Not to be confused with the user
	 * that the automation testing logs in as (i.e., {@link ArgiopeConstantTestElement#AUTOMATION_TESTING_USER_USERNAME})
	 * If the user does not exist nothing happens.<br />
	 * <br />
	 * This method is <i>not</i> meant to be used for testing purposes. This is simply to delete a user from the database (e.g.,
	 * so the user can safely be created again). <br />
	 * <br />
	 * This method will record the current page, navigate to the
	 * {@link ArgiopeConstantTestElement#URL_DISPLAY_USERS} page, check if the username exists, if
	 * true, delete the corresponding user and then navigate back to the original page this method was called from.<br />
	 * <br/>
	 * For testing purposes use {@link UserUtils#deleteUserInTable}
	 * @see UserUtils#deleteUserInTable
	 *
	 */
	public static void deleteNewTestUser() {
		String returnToUrl = driver.getCurrentUrl();
		driver.get(ArgiopeConstantTesting.URL_DISPLAY_USERS);

		UserUtils.deleteUserInTable(ArgiopeConstantTesting.NEW_USER_USERNAME);

		driver.get(returnToUrl);
	}


	public static boolean newTestUserExists() {
		boolean newTestUserExists = userExists(ArgiopeConstantTesting.NEW_USER_USERNAME);

		return newTestUserExists;
	}

	/**
	 * *****Not Test Safe*****<br />
	 * @param username
	 * @return
	 */
	public static boolean userExists(String username) {
		driver.get(ArgiopeConstantTesting.URL_DISPLAY_USERS);
		boolean userExists = UserUtils.userExistsThisPage(username);

		return userExists;
	}

	/**
	 * ~Test Safe~
	 */
	public static void clickLogout() {
		if(elementExistsByClass(ArgiopeConstantTestElement.CLASS_BUTTON_LOGOUT, true)) {
			driver.findElement(By.className(ArgiopeConstantTestElement.CLASS_BUTTON_LOGOUT)).click();
		} else {
			// TODO: Error
		}
	}
	
	public static void resetIpBlocking() {
		RestTemplate rt = new RestTemplate();
		
		for(String ip : ArgiopeConstantTesting.LOCALHOST_IP_LIST) {
			IpBlockingResetRequest req = new IpBlockingResetRequest();
			req.setUsername(AppConstants.Users.AutomationTesting.USERNAME);
			req.setPassword(AppConstants.Users.AutomationTesting.PASSWORD);
			req.setIp(ip);
			
			IpBlockingResetResponse resp = rt.postForObject(ArgiopeConstantTesting.URL_RESET_IP_BLOCKING_ENDPOINT, 
					req, IpBlockingResetResponse.class);

			Assert.assertNotEquals(resp.getResult(), IpBlockingResetResult.FAIL);
			Assert.assertNotEquals(resp.getResult(), IpBlockingResetResult.INVALID_CREDENTIALS);
		}
		
	}
	
	/**
	 * Returns true if an element with the specified elemClassName exists and the elements innertext equals <code>textToAssert</code>.
	 * This will execute an {@link Assert#fail()} if more than one element with the specified elemClassName exists.
	 * @param elemClassName
	 * @param textToAssert
	 * @param classNameUnique
	 */
	public static boolean elementClassTextEquals(String elemClassName, String textToAssert) {
		Assert.assertTrue(elementExistsByClass(elemClassName, true), "Element with class name '" + elemClassName + "'does not exist");
		WebElement elem = driver.findElement(By.className(elemClassName));
		String elemInnerText = elem.getText();
		
		if(elemInnerText == null) {
			return false;
		} else if (elemInnerText.equals(textToAssert)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the inner text of an element with the given class name. If 0 or
	 * more than 1 element with the given class name exists, an {@link Assert}
	 * will be thrown.
	 * 
	 * @param elemClassName
	 * @return
	 */
	public static String getElementInnerTextByClass(String elemClassName) {
		Assert.assertTrue(elementExistsByClass(elemClassName, true), "Element with class name '" + elemClassName + "'does not exist");
		WebElement elem = driver.findElement(By.className(elemClassName));
		String elemInnerText = elem.getText();
		
		return elemInnerText;
	}
	
	
	public static void assertElementTextEqualsByClassName(String elemClassName, String expectedInnerText) {
		final String elemText = getElementInnerTextByClass(elemClassName);
		
		if(elemText != null && expectedInnerText != null) {
			final String assertMessage = "The innerText of the element with class name '" + elemClassName 
					+ "' was expected to equal " + expectedInnerText + " but instead returned " + elemText + ".";
			Assert.assertTrue(elemText.equals(expectedInnerText), assertMessage);
		} else {
			Assert.fail("Expected element text and actual element text must not be null. Expected element text: " + expectedInnerText
					+ ". Actual element text: " + elemText + ".");
		}
	}
	
	public static void synchronizeTestingState() {
		loginAutomationTestingUser();
		logoutAutomationTestingUser();
		resetIpBlocking();
	}


}
