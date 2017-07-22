package com.preston.argiope.service.security;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.preston.argiope.app.utils.WebAppUtils;

/**
 * A service that stores a {@link LoadingCache} map containing an IP (Key)
 * and the number of failed login attempts (value). Contains methods to
 * update on login failure and login success as well as a method to check if
 * the IP is currently blocked.<br/>
 * <br/>
 * Tutorial from: <a href="http://www.baeldung.com/spring-security-block-brute-force-authentication-attempts">
 * http://www.baeldung.com/spring-security-block-brute-force-authentication-attempts</a>
 * @author pbriggs
 *
 */

@Service
public class LoginAttemptService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String CLASS_NAME = "LoginAttemptService";
	private static class MethodNames {
		public static final String LOGIN_SUCCEEDED = CLASS_NAME + ".loginSucceeded(...)";
		public static final String LOGIN_FAILED = CLASS_NAME + ".loginFailed(...)";
		public static final String GET_IP = CLASS_NAME + ".getIp(...)";
		public static final String NUM_FAILED_ATTEMPTS = CLASS_NAME + ".numFailedAttempts(...)";
		public static final String IP_BLOCKED = CLASS_NAME + ".ipBlocked(...)";
	}
	
	@Autowired private WebAppUtils webAppUtils;
	
	// TODO: Extract to preferences	
	public static final int DEFAULT_MAX_ATTEMPTS = 10;
	public static final int DEFAULT_CACHE_DURATION = 1;
	public static final TimeUnit DEFAULT_CACHE_DURATION_TIME_UNIT = TimeUnit.DAYS;
	

	private static int maxAttempts = DEFAULT_MAX_ATTEMPTS;
	private static int cacheDuration = DEFAULT_CACHE_DURATION;
	private static TimeUnit cacheDurationTimeUnit = DEFAULT_CACHE_DURATION_TIME_UNIT;
	
	// TODO: Why is this static?
	private static LoadingCache<String, Integer> attemptsCache;

	public LoginAttemptService() {
		attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheDurationTimeUnit).build(new AttemptsCacheLoader());
	}

	// TODO: Extract this functionality out into an extension class that is only active in DEV mode
//	/**
//	 * Needed for automation testing where we will be logging in with
//	 * invalid credentials repetitively.
//	 * @param MAX_ATTEMPTS
//	 */
//	public static void startAutomationTestingMode() {
////		 TODO: Set a timer to return this value back to default just in case it was not called
//		MAX_ATTEMPTS = MAX_ATTEMPTS_AUTOMATION_TESTING;
//		CACHE_DURATION = CACHE_DURATION_AUTOMATION_TESTING;
//		CACHE_DURATION_TIME_UNIT = CACHE_DURATION_TIME_UNIT_AUTOMATION_TESTING;
//	}
//
//	/** See {@link startAutomationTestingMode} for details. */
//	public static void stopAutomationTestingMode() {	
//		MAX_ATTEMPTS = MAX_ATTEMPTS_DEFAULT;
//		CACHE_DURATION = CACHE_DURATION_DEFAULT;
//		CACHE_DURATION_TIME_UNIT = CACHE_DURATION_TIME_UNIT_DEFAULT;
//	}
	
	public String getIp(HttpServletRequest req) {
		assertRequest(req, MethodNames.GET_IP);
		String ip = webAppUtils.resolveIp(req);
		return ip;
	}
	
	/** Reset the number of failed login attempts for the given request's IP. */
	public void loginSucceeded(HttpServletRequest req) {
		assertRequest(req, MethodNames.LOGIN_SUCCEEDED);
		Assert.notNull(req, "Request must not be null. Failed in: " + MethodNames.LOGIN_SUCCEEDED);
		loginSucceeded(webAppUtils.resolveIp(req));
	}
	
	/** Reset the number of failed login attempts for the given IP. */
	public void loginSucceeded(String ip) {
		assertIp(ip, MethodNames.LOGIN_SUCCEEDED);
		int attempts = getCacheValue(ip);
		logger.debug("Resetting failed attempts counter for IP [{}] from [{}] to 0.", ip, attempts);
		attemptsCache.invalidate(ip);
	}
	
	/** Increment the number of failed login attempts for the given Request. */
	public void loginFailed(HttpServletRequest req) {
		assertRequest(req, MethodNames.LOGIN_FAILED);
		String ip = webAppUtils.resolveIp(req);
		loginFailed(ip);
	}

	/** Increment the number of failed login attempts for the given IP. */
	public void loginFailed(String ip) {
		assertIp(ip, MethodNames.LOGIN_FAILED);
		Assert.hasText(ip, "IP address must not be null/blank. Failed in: " + MethodNames.LOGIN_FAILED);
		int attempts = getCacheValue(ip) + 1;
		logger.debug("Incrementing failed attempts counter for IP [{}] to [{}].", ip, attempts);
		attemptsCache.put(ip, attempts);
	}
	
	public boolean ipBlocked(HttpServletRequest req) {
		assertRequest(req, MethodNames.IP_BLOCKED);
		String ip = webAppUtils.resolveIp(req);
		return ipBlocked(ip);
	}

	public boolean ipBlocked(String ip) {
		assertIp(ip, MethodNames.IP_BLOCKED);
		int attempts = getCacheValue(ip);
		return attempts >= maxAttempts;
	}
	
	public int numFailedAttempts(HttpServletRequest req) {
		assertRequest(req, MethodNames.NUM_FAILED_ATTEMPTS);
		String ip = webAppUtils.resolveIp(req);
		return numFailedAttempts(ip);
	}

	public int numFailedAttempts(String ip) {
		assertIp(ip, MethodNames.NUM_FAILED_ATTEMPTS);
		int attempts = getCacheValue(ip);
		return attempts;
	}
	

	private class AttemptsCacheLoader extends CacheLoader<String, Integer>{

		@Override
		public Integer load(String key) throws Exception {
			/* I believe this is only used in creating new keys. 
			 * We always want them to start at 0 so this is fine. */
			return 0;
		}
	}

	// Getters/Setters
	// ====================================================================================================
	public int getMaxAttempts() {
		return maxAttempts();
	}
	public static int maxAttempts() {
		return maxAttempts;
	}

	// Private Helper Methods
	// ====================================================================================================
	private int getCacheValue(String ip) {
		try {
			return attemptsCache.get(ip);
		} catch (ExecutionException e) {
			logger.warn("Exception occurred retrieving ip [{}] from cache.", ip);
			logger.warn("Exception: ", e);
			return 0;
		}
	}
	
	private void assertRequest(HttpServletRequest req, String methodName) {
		Assert.notNull(req, "Request must not be null. Failed in: " + methodName);
	}
	
	private void assertIp(String ip, String methodName) {
		Assert.hasText(ip, "IP address must not be null/blank. Failed in: " + methodName);
	}
}

