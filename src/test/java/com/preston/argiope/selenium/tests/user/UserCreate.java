package com.preston.argiope.selenium.tests.user;

import org.testng.Assert;
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
public class UserCreate extends AbstractTest{
	@BeforeMethod
	public void beforeMethod() {
		TestUtils.loginAutomationTestingUser();
		driver.get(ArgiopeConstantTesting.URL_CREATE_USER);
	}

	@Test
	public void testExecCreateUserAlreadyExists() {
		UserUtils.typeCreateUserAlreadyExists();

		/* User created successfully message displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_ERROR_CREATE_USER_FORM, true));
	}

	@Test
	public void testExecCreateUserEmptyFirstName() {
		UserUtils.typeCreateUserForm(false, true, true, true);

		/* User created successfully message displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_ERROR_CREATE_USER_FORM, true));
	}

	@Test
	public void testExecCreateUserEmptyLastName() {
		UserUtils.typeCreateUserForm(true, false, true, true);

		/* User created successfully message displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_ERROR_CREATE_USER_FORM, true));
	}

	@Test
	public void testExecCreateUserEmptyUsername() {
		UserUtils.typeCreateUserForm(true, true, false, true);

		/* User created successfully message displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_ERROR_CREATE_USER_FORM, true));
	}

	@Test
	public void testExecCreateUserEmptyPassword() {
		UserUtils.typeCreateUserForm(true, true, true, false);

		/* User created successfully message displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_ERROR_CREATE_USER_FORM, true));
	}

	@Test
	public void testExecCreateUserSuccessfully() {
		TestUtils.deleteNewTestUser();
		UserUtils.typeCreateUserForm(true, true, true, true);

		/* User created successfully message displayed */
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_SUCCESS_CREATE_USER, true));
	}
}
