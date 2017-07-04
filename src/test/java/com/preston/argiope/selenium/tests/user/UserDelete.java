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
 * Created by pbriggs on 9/13/2016.
 */
public class UserDelete extends AbstractTest{
	@BeforeMethod
	public void beforeMethod() {
		TestUtils.loginAutomationTestingUser();
		TestUtils.createNewTestUser();
		driver.get(ArgiopeConstantTesting.URL_DISPLAY_USERS);	
	}
	
	@Test
	public void testExecDeleteUserSuccessfully() {
		UserUtils.deleteUserInTable(ArgiopeConstantTesting.NEW_USER_USERNAME);
	
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_SUCCESS_DELETE_USER, true));
	}
}
