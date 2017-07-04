package com.preston.argiope.selenium.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement;
import com.preston.argiope.selenium.app.TestConfig;
import com.preston.argiope.selenium.constant.ArgiopeConstantTesting;

/**
 * This class only contains methods that are test safe (i.e., they do not execute any page navigation).
 * Each method is expected to be called from a specific page and will verify via
 * {@link org.testng.Assert#assertTrue}<br />
 * <br />
 *
 * Created by pbriggs on 9/12/2016.
 */
public class UserUtils {
	private static WebDriver driver = TestConfig.getDriver();
	private static WebDriverWait driverWait = TestConfig.getDriverWait();

	/**
	 * ~Test Safe~
	 * @param usernameCorrect
	 * @param passwordCorrect
	 */
	public static void typeLoginForm(boolean usernameCorrect, boolean passwordCorrect) {
		if(usernameCorrect) {
			typeLoginUserName(AppConstants.Users.AutomationTesting.USERNAME);
		} else {
			typeLoginUserName(ArgiopeConstantTesting.INCORRECT_USERNAME);
		}

		if(passwordCorrect) {
			typeLoginPassword(AppConstants.Users.AutomationTesting.PASSWORD, true);
		} else {
			typeLoginPassword(ArgiopeConstantTesting.INCORRECT_PASSWORD, true);
		}
	}

	public static void typeLoginFormWithBlanks(boolean usernameBlank, boolean passwordBlank) {
		if(usernameBlank) {
			typeLoginUserName(ArgiopeConstantTesting.EMPTY_STRING);
		} else {
			typeLoginUserName(AppConstants.Users.AutomationTesting.USERNAME);
		}

		if(passwordBlank) {
			typeLoginPassword(ArgiopeConstantTesting.EMPTY_STRING, true);
		} else {
			typeLoginPassword(AppConstants.Users.AutomationTesting.PASSWORD, true);
		}
	}

	/**
	 * ~Test Safe~
	 * @param username
	 */
	public static void typeLoginUserName(String username) {
		String elemClass = ArgiopeConstantTestElement.CLASS_INPUT_LOGIN_FORM_USERNAME;
		Assert.assertTrue(TestUtils.elementExistsByClass(elemClass, true));
		WebElement elem = driver.findElement(By.className(elemClass));
		elem.clear();
		elem.sendKeys(username);
	}
	
	/**
	 * ~Test Safe
	 * @param password
	 * @param submit
	 */
	public static void typeLoginPassword(String password, boolean submit) {
		String elemClass = ArgiopeConstantTestElement.CLASS_INPUT_LOGIN_FORM_PASSWORD;
		Assert.assertTrue(TestUtils.elementExistsByClass(elemClass, true));
		WebElement elem = driver.findElement(By.className(elemClass));
		elem.clear();
		elem.sendKeys(password);

		if(submit == true) {
			driver.findElement(By.className(elemClass)).submit();
		}
	}

	public static void typeCreateUserForm(boolean firstNameValid, boolean lastNameValid,
										  boolean usernameValid, boolean passwordValid) {
		if(firstNameValid) {
			typeCreateUserFirstName(ArgiopeConstantTesting.NEW_USER_FIRST_NAME);
		} else {
			typeCreateUserFirstName(ArgiopeConstantTesting.EMPTY_STRING);
			// TODO: When validation is implemented update this section to send invalid text
		}
		if (lastNameValid) {
			typeCreateUserLastName(ArgiopeConstantTesting.NEW_USER_LAST_NAME);
		} else {
			typeCreateUserLastName(ArgiopeConstantTesting.EMPTY_STRING);
			// TODO: When validation is implemented update this section to send invalid text
		}

		if(usernameValid) {
			typeCreateUserUsername(ArgiopeConstantTesting.NEW_USER_USERNAME);
		} else {
			typeCreateUserUsername(ArgiopeConstantTesting.EMPTY_STRING);
			// TODO: When validation is implemented update this section to send invalid text
		}

		if(passwordValid) {
			typeCreateUserPassword(ArgiopeConstantTesting.NEW_USER_PASSWORD, true);
		} else {
			typeCreateUserPassword(ArgiopeConstantTesting.EMPTY_STRING, true);
			// TODO: When validation is implemented update this section to send invalid text
		}
	}

	// TODO: Merge these into one method specifying the Element class name
	public static void typeCreateUserFirstName(String firstName) {
		String elemClass = ArgiopeConstantTestElement.CLASS_INPUT_CREATE_USER_FORM_FIRST_NAME;
		Assert.assertTrue(TestUtils.elementExistsByClass(elemClass, true));
		WebElement elem = driver.findElement(By.className(elemClass));
		elem.clear();
		elem.sendKeys(firstName);
	}

	public static void typeCreateUserLastName(String lastName) {
		String elemClass = ArgiopeConstantTestElement.CLASS_INPUT_CREATE_USER_FORM_LAST_NAME;
		Assert.assertTrue(TestUtils.elementExistsByClass(elemClass, true));
		WebElement elem = driver.findElement(By.className(elemClass));
		elem.clear();
		elem.sendKeys(lastName);
	}

	public static void typeCreateUserUsername(String username) {
		String elemClass = ArgiopeConstantTestElement.CLASS_INPUT_CREATE_USER_FORM_USERNAME;
		Assert.assertTrue(TestUtils.elementExistsByClass(elemClass, true));
		WebElement elem = driver.findElement(By.className(elemClass));
		elem.clear();
		elem.sendKeys(username);
	}

	public static void typeCreateUserPassword(String password, boolean submit) {
		String elemClass = ArgiopeConstantTestElement.CLASS_INPUT_CREATE_USER_FORM_PASSWORD;
		Assert.assertTrue(TestUtils.elementExistsByClass(elemClass, true));
		WebElement elem = driver.findElement(By.className(elemClass));
		elem.clear();
		elem.sendKeys(password);

		if(submit == true) {
			driver.findElement(By.className(elemClass)).submit();
		}
	}

	public static void typeCreateUserAlreadyExists() {
		typeCreateUserFirstName(AppConstants.Users.AutomationTesting.FIRST_NAME);
		typeCreateUserLastName(AppConstants.Users.AutomationTesting.LAST_NAME);
		typeCreateUserUsername(AppConstants.Users.AutomationTesting.USERNAME);
		typeCreateUserPassword(AppConstants.Users.AutomationTesting.PASSWORD, true);
	}

	/**
	 * Deletes a user given the provided username. This page must be called from the Display Users page (i.e.,
	 * {@link com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement#URL_DISPLAY_USERS}). This method will perform no
	 * navigation as to not interrupt any expected test results.
	 *
	 * @param username
	 */
	public static void deleteUserInTable(String username) {
		WebElement elemTableDisplayUsers = driver.findElement(By.className(ArgiopeConstantTestElement.CLASS_TABLE_DISPLAY_USERS));
		List<WebElement> lstTableRows = elemTableDisplayUsers.findElements(By.tagName("tr"));

		/*
		Loop through all table rows, check if the username matches the input username, if so, click the delete button
		for that table row, wait for document.ready and then stop checking anymore table rows.
		 */
		outerloop:
		for(WebElement elemTableRow : lstTableRows) {
			List<WebElement> lstColumns = elemTableRow.findElements(By.className(ArgiopeConstantTestElement.CLASS_TEXT_DISPLAY_USERS_USERNAME));
			if(lstColumns.size() == 1) {
				String rowUserName = lstColumns.get(0).getText();
				if(rowUserName.equals(username)) {
					elemTableRow.findElement(By.className(ArgiopeConstantTestElement.CLASS_BUTTON_DISPLAY_USERS_DELETE)).click();
					TestUtils.waitDocReady();
					break outerloop;
				}
			} else if(lstColumns.size() > 1) {
				//TODO: Error
			}
		}

		if(userExistsThisPage(username)) {
			// TODO: Error
		}
	}

	/**
	 * ~Test Safe~
	 * @param username
	 * @return
	 */
	public static boolean userExistsThisPage(String username) {
		List<WebElement> lstUsernames = driver.findElements(By.className(ArgiopeConstantTestElement.CLASS_TEXT_DISPLAY_USERS_USERNAME));
		boolean userExists = false;

		for(WebElement elemUsername : lstUsernames) {
			if(elemUsername.getText().equals(username)) {
				userExists = true;
			}
		}

		return userExists;

	}

	/**
	 * ~Test Safe~
	 * @return
	 */
	public static boolean isUserAuth() {
		boolean userAuthenticated;
		int results = driver.findElements(By.className(ArgiopeConstantTestElement.CLASS_STATUS_USER_AUTH_TRUE)).size();

		if(results == 0) {
			userAuthenticated = false;
		} else if(results == 1) {
			userAuthenticated = true;
		} else {
			userAuthenticated = false;
			// TODO: Error
		}

		return userAuthenticated;
	}
}
