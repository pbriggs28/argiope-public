package com.preston.argiope.selenium.tests.security;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement;
import com.preston.argiope.selenium.app.AbstractTest;
import com.preston.argiope.selenium.constant.ArgiopeConstantTesting;
import com.preston.argiope.selenium.utils.TestUtils;
import com.preston.argiope.selenium.utils.UserUtils;
import com.preston.argiope.service.security.LoginAttemptService;

public class IpBlocking extends AbstractTest{
	
	private final String failedLoginAttempts = ArgiopeConstantTestElement.CLASS_STATUS_NUM_FAILED_LOGIN_ATTEMPTS;
	
	@BeforeClass
	public void beforeClass() {
		LoginAttemptService.startAutomationTestingMode();
	}
	
	/**Put the testing user in an unauthorized state before each test.*/
	@BeforeMethod
	public void beforeMethod() {
		TestUtils.synchronizeTestingState();
		driver.get(ArgiopeConstantTesting.URL_INDEX);
	}
	
	@AfterClass
	public void afterClass() {
		TestUtils.resetIpBlocking();
		LoginAttemptService.stopAutomationTestingMode();
	}

	/**
	 * Attempt login with correct username and incorrect password (MAX_ATTEMPTS
	 * + 2) and verify the counter increases appropriately including past
	 * MAX_ATTEMPTS.
	 */
	@Test
	public void testFailedLoginAttemptsCounter() {
		for(int i=0; i < LoginAttemptService.maxAttempts() + 2; i++) {
			final String expectedInnerText = String.valueOf(i+1);
			UserUtils.typeLoginForm(true, false);
			TestUtils.assertElementTextEqualsByClassName(failedLoginAttempts, expectedInnerText);
		}
	}
	
	/**
	 * Verify the failed login attempts counter is being reset properly with a successful login.
	 * <br/>
	 * <br/>Steps executed:
	 * <ul>
	 * 	<li>Login unsuccessfully one less time than what is allowed ({@link LoginAttemptService#getMAX_ATTEMPTS()} - 1)</li>
	 * 	<li>Login successfully to reset the counter</li>
	 * 	<li>Logout</li>
	 * 	<li>Attempt login with incorrect password one time (this will lock user if counter was not reset)</li>
	 * 	<li>Assert IP not blocked</li>
	 * 	<li>Login successfully</li>
	 * 	<li>Assert login successful</li>
	 * </ul>
	 */
	@Test
	public void testFailedLoginAttemptsCounterReset() {
		int expectedCounterValue = 0;
		/*Login unsuccessfully one less time than what is allowed*/
		// TODO: This should be a while loop...
		for(   ; expectedCounterValue<LoginAttemptService.maxAttempts() - 1; expectedCounterValue++) {
			UserUtils.typeLoginForm(true, false);
		}
		/*Login successfully to reset the counter*/
		UserUtils.typeLoginForm(true, true);
		
		/*Logout*/
		TestUtils.clickLogout();

		/*Verify counter reset to 0*/
		TestUtils.assertElementTextEqualsByClassName(failedLoginAttempts, "0");
		
		/*Attempt login with incorrect password one time (this will lock user if counter was not reset)*/ 
		// TODO: This should be a while loop...
		for(   ; expectedCounterValue<LoginAttemptService.maxAttempts() + 1; expectedCounterValue++) {
			UserUtils.typeLoginForm(true, false);
		}
		
		/*Assert IP not blocked*/
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_PAGE_LOGIN, true));

		/*Login successfully*/
		UserUtils.typeLoginForm(true, true);
		
		/*Assert login successful*/
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_PAGE_INDEX, true));
	}
	
	
	@Test
	public void testIpBlockedErrorMsg() {
		for(int i=0; i<LoginAttemptService.maxAttempts() + 1; i++) {
			UserUtils.typeLoginForm(true, false);
		}
		Assert.assertTrue(TestUtils.elementExistsByClass(ArgiopeConstantTestElement.CLASS_ERROR_LOGIN_FORM_IP_BLOCKED, true));
	}
	
	/**
	 * Verify once the IP Block expires, a user can log back in
	 */
	@Test
	public void testIpBlockResetAfterCacheExpiration() {
		// TODO: Cannot complete this test until we figure out how to change the 
		// LoginAttemptService variables from the testing app (to reduce the cache time period)
	}
}
