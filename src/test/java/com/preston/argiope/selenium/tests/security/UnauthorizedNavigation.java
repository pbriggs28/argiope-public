package com.preston.argiope.selenium.tests.security;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement;
import com.preston.argiope.selenium.app.AbstractTest;
import com.preston.argiope.selenium.constant.ArgiopeConstantTesting;
import com.preston.argiope.selenium.utils.TestUtils;

public class UnauthorizedNavigation extends AbstractTest {
	private final String loginPage = ArgiopeConstantTestElement.CLASS_PAGE_LOGIN;
	private String currentPage;
	
	/**
	 * Put the testing user in an unauthorized state before each test.
	 */
	@BeforeMethod
	public void beforeMethod() {
		TestUtils.logoutAutomationTestingUser();
		driver.get(ArgiopeConstantTesting.URL_INDEX);
	}
	/**
	 * Each test method within this class will set {@link #currentPage} 
	 * which can then be used by this method to navigate to that page and 
	 * again in the Assert message.
	 * 
	 * All pages should route to login page if unauthorized.
	 */
	// TODO: Check if an assert failure in an "after" method will show the correct test case failing
	// Also verify it does not then skip the rest of the tests for that class
	@AfterMethod
	public void afterMethod() {
		driver.get(currentPage);
		Assert.assertTrue(TestUtils.elementExistsByClass(loginPage, true), 
				"Navigated to '" + currentPage + "' as unathorized user but no element with class " 
				+ loginPage + " found.");
	}
	
	@Test
	public void testNavToLogin() {
		currentPage = ArgiopeConstantTesting.URL_LOGIN;
	}
	
	@Test
	public void testNavToLogout() {
		currentPage = ArgiopeConstantTesting.URL_LOGOUT;
	}

	@Test
	public void testNavToIndex() {
		currentPage = ArgiopeConstantTesting.URL_INDEX;
	}
	
	@Test
	public void testNavToCreateUser() {
		currentPage = ArgiopeConstantTesting.URL_CREATE_USER;
	}
	
	@Test
	public void testNavToDisplayUsers() {
		currentPage = ArgiopeConstantTesting.URL_DISPLAY_USERS;
	}
	
	@Test
	public void testNavToDeleteUser() {
		currentPage = ArgiopeConstantTesting.URL_DELETE_USER;
	}
	
	@Test
	public void testNavToAdmin() {
		currentPage = ArgiopeConstantTesting.URL_ADMIN;
	}
	
	@Test
	public void testNavTo403() {
		currentPage = ArgiopeConstantTesting.URL_403;
	}
}
