package com.preston.argiope.service.security;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.preston.argiope.app.utils.WebAppUtils;

/**
 * A service that stores a {@link LoadingCache} map containing an IP (Key)
 * and the number of failed login attempts (value). Contains methods to
 * update on login failure and login success as well as a method to check if
 * the IP is currently blocked ({@link }<br/>
 * <br/>
 * Tutorial from: <a href="http://www.baeldung.com/spring-security-block-brute-force-authentication-attempts">
 * http://www.baeldung.com/spring-security-block-brute-force-authentication-attempts</a>
 * @author pbriggs
 *
 */

@Service
public class LoginAttemptService {

	@Autowired
	private WebAppUtils webAppUtils;
	
	
	// TODO: Extract to preferences	
	public static final int DEFAULT_MAX_ATTEMPTS = 10;
	public static final int DEFAULT_CACHE_DURATION = 1;
	public static final TimeUnit DEFAULT_CACHE_DURATION_TIME_UNIT = TimeUnit.DAYS;
	

	private static int maxAttempts = DEFAULT_MAX_ATTEMPTS;
	private static int cacheDuration = DEFAULT_CACHE_DURATION;
	private static TimeUnit cacheDurationTimeUnit = DEFAULT_CACHE_DURATION_TIME_UNIT;
	
	private static LoadingCache<String, Integer> attemptsCache;

	public LoginAttemptService() {
		super();
		attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheDurationTimeUnit).build(new AttemptsCacheLoader());
	}

	/**
	 * Needed for automation testing where we will be logging in with
	 * invalid credentials repetitively.
	 * @param MAX_ATTEMPTS
	 */
	public static void startAutomationTestingMode() {
		// TODO: Set a timer to return this value back to default just in case it was not called
//		MAX_ATTEMPTS = MAX_ATTEMPTS_AUTOMATION_TESTING;
//		CACHE_DURATION = CACHE_DURATION_AUTOMATION_TESTING;
//		CACHE_DURATION_TIME_UNIT = CACHE_DURATION_TIME_UNIT_AUTOMATION_TESTING;
	}

	/** See {@link startAutomationTestingMode} for details. */
	public static void stopAutomationTestingMode() {	
//		MAX_ATTEMPTS = MAX_ATTEMPTS_DEFAULT;
//		CACHE_DURATION = CACHE_DURATION_DEFAULT;
//		CACHE_DURATION_TIME_UNIT = CACHE_DURATION_TIME_UNIT_DEFAULT;
	}
	
	public String getIp() {
		String ip = webAppUtils.getClientIp();
		return ip;
	}
	
	/**
	 * Resets the number of failed login attempts (value) for the current request's IP (key).
	 * 
	 * @param key IP address
	 */
	public void loginSucceeded() {
		String key = webAppUtils.getClientIp();
		attemptsCache.invalidate(key);
	}
	
	/**
	 * Increments the {@link LoginAttemptService#attemptsCache} value (number of
	 * failed login attempts) by one for the current request's IP (key). If
	 * {@link ExecutionException} is caught during retrieval of the cache
	 * ({@link LoadingCache#get(Object)}) then the attemptsCache value is reset
	 * to 0.
	 * 
	 * @param key
	 *            IP address
	 */
	public void loginFailed() {
		String key = webAppUtils.getClientIp();
		int attempts = 0;
		try {
			attempts = attemptsCache.get(key);
		} catch (ExecutionException e) {
			// TODO: Error
			attempts = 0;
		}
		attempts++;
		attemptsCache.put(key, attempts);
		// TODO: Log IP address and number of failed attempts.
		
	}
	
	/**
	 * Returns true if {@link LoadingCache#get(Object)} >= {@link #MAX_ATTEMPTS}.
	 * If {@link ExecutionException} is caught during retrieval of the cache
	 * ({@link LoadingCache#get(Object)}) then false is returned.
	 *  
	 * @param key IP address
	 * @return
	 */
	public boolean isBlocked() {
		String key = webAppUtils.getClientIp();
		try {
			int attempts = attemptsCache.get(key);
			boolean blocked = attempts >= maxAttempts;
			return blocked;
		} catch (ExecutionException e) {
			// TODO: Error
			return false;
		}
		// TODO: Log IP blocked and send an email.
		// Actually that should not be handled here but in service class instead
	}
	
	public int getNumFailedAttempts() {
		String key = webAppUtils.getClientIp();
		try {
			int attempts = attemptsCache.get(key);
			return attempts;
		} catch (ExecutionException e) {
			// TODO: Error
			return 0;
		}
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
}

