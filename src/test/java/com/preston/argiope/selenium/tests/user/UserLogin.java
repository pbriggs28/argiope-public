package com.preston.argiope.selenium.tests.user;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement;
import com.preston.argiope.selenium.app.AbstractTest;
import com.preston.argiope.selenium.constant.ArgiopeConstantTesting;
import com.preston.argiope.selenium.utils.TestUtils;
import com.preston.argiope.selenium.utils.UserUtils;

/**
 * Created by pbriggs on 9/12/2016.
 */
public class UserLogin extends AbstractTest {
	
	@BeforeClass
	public void beforeClass() {
		// TODO: Find a way to make this automatically used by all tests
		driver.get(ArgiopeConstantTesting.URL_INDEX);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		TestUtils.logoutAutomationTestingUser();
		driver.get(ArgiopeConstantTesting.URL_INDEX);
	}

	@Test
	public void testNavToLoginPageUnauth() {

		/* Login page displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_PAGE_LOGIN, true));
	}

	@Test
	public void testNavToLoginPageAuth() {
		UserUtils.typeLoginForm(true, true);
		driver.get(ArgiopeConstantTesting.URL_LOGIN);

		/* Rerouted to Home page */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_PAGE_INDEX, true));
	}

	@Test
	public void testExecLoginInvalidCredentials() {
		UserUtils.typeLoginForm(false, false);

		/* Login error displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_ERROR_LOGIN_FORM, true));
	}

	@Test
	public void testExecLoginEmptyUsername() {
		UserUtils.typeLoginFormWithBlanks(true, false);

		/* Login error displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_ERROR_LOGIN_FORM, true));
	}

	@Test
	public void testExecLoginEmptyPassword() {
		UserUtils.typeLoginFormWithBlanks(false, true);

		/* Login error displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_ERROR_LOGIN_FORM, true));
	}

	@Test
	public void testExecLoginEmptyUsernameEmptyPassword() {
		UserUtils.typeLoginFormWithBlanks(true, true);

		/* Login error displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_ERROR_LOGIN_FORM, true));
	}

	@Test
	public void testExecLoginValidCredentials() {
		UserUtils.typeLoginForm(true, true);

		/* Login success page displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_PAGE_INDEX, true));
	}

	@Test
	public void testLogout() {
		UserUtils.typeLoginForm(true, true);
		TestUtils.clickLogout();

		/* Login page displayed with Logout Success message */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_SUCCESS_LOGOUT, true));
	}

}
