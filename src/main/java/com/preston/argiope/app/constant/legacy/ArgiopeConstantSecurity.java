package com.preston.argiope.app.constant.legacy;

import com.preston.argiope.app.constant.dev.DevWebConstants;

/**
 * ================================<br/>
 * THESE CONSTANTS HAVE BEEN MIGRATED<br/>
 * ================================<br/>
 * <br/>
 * The constants in this class have been migrated to
 * {@link DevWebConstants} to use the new hierarchical style
 * constants. The constants in this class are used by the JSP which cannot
 * access constants from inner classes. Until we change our view implementation
 * (Likely to Thymeleaf: DEV-46) or find a solution to reference inner class
 * constants in the JSP, the constants in this class will simply be a pointer to
 * the new style constants.
 * 
 * @author pbriggs
 *
 */
public class ArgiopeConstantSecurity {
	/*
	 * ~~~~~This comment is used in any ArgiopeConstant Class that is used in jsp pages. Be sure to update all if modifying comment~~~~~
	 * 
	 * This class is used by the jsp pages so it needs getters
	 * that follow a specific naming convention. Eclipse'S "generate 
	 * getters/setters" function will change the case if the fields are final.
	 * 
	 * When adding new constants you will need to regenerate the getters. See steps below.
	 * 
	 * Steps to generate getters
	 * 1-Delete all getter/setters (Use outline)
	 * (Ignore underscores in for the following lines, they are there so they do not get replaced with find/replace)
	 * 2-Find/Replace 
	 * 		"p_ublic s_tatic f_inal" with "p_ublic f_inal" (removes static from all fields)
	 * 3-Generate getters (No setters)
	 * 4-Find/Replace (enable case sensitive) 
	 * 		"p_ublic f_inal" with "p_ublic s_tatic f_inal" (adds static back to all fields)
	 */	
	
	// Use this in any jsp: ${argiopeConstantSecurity.RESP_ATTR_IP_BLOCKED}
	
	
	/* ~~~~~~~~~~~~~~~~~ Response Attributes ~~~~~~~~~~~~~~~~~ */
	
	public static final String RESP_ATTR_IP_BLOCKED = DevWebConstants.ResponseAttributes.IpBlocked.ATTRIBUTE; // "wOsiHdYvSPMRDFPOk6ZV";
	public static final String RESP_VALUE_IP_BLOCKED_TRUE = DevWebConstants.ResponseAttributes.IpBlocked.TRUE; // "HKnCNgNuM1yPdJnvC7tF";
	public static final String RESP_VALUE_IP_BLOCKED_FALSE = DevWebConstants.ResponseAttributes.IpBlocked.FALSE; // "Hc2HJrzixMBcqv3WV8b0";
	public static final String RESP_ATTR_NUM_FAILED_LOGIN_ATTEMPTS = DevWebConstants.ResponseAttributes.FailedLoginCount.ATTRIBUTE; // "UQhuO4qPnW08PKRSfR1L";
	public static final String RESP_ATTR_IP_BLOCK_RESET = DevWebConstants.ResponseAttributes.IpBlockReset.ATTRIBUTE; // "YcArkxP7jC5RdoU7pVmE";
	public static final String RESP_VALUE_IP_BLOCK_RESET_TRUE = DevWebConstants.ResponseAttributes.IpBlockReset.TRUE; // "sCfu6iFsH8d4BU09lWcw";
	public static final String RESP_VALUE_IP_BLOCK_RESET_FALSE = DevWebConstants.ResponseAttributes.IpBlockReset.FALSE; // "YuMutrjQt2FCh5P33Xby";
	// TODO: How should we disguise the number of login attempts? Or should we not worry about it?
	
	public String getRESP_ATTR_IP_BLOCKED() {
		return RESP_ATTR_IP_BLOCKED;
	}
	public String getRESP_VALUE_IP_BLOCKED_TRUE() {
		return RESP_VALUE_IP_BLOCKED_TRUE;
	}
	public String getRESP_VALUE_IP_BLOCKED_FALSE() {
		return RESP_VALUE_IP_BLOCKED_FALSE;
	}
	public String getRESP_ATTR_NUM_FAILED_LOGIN_ATTEMPTS() {
		return RESP_ATTR_NUM_FAILED_LOGIN_ATTEMPTS;
	}
	public String getRESP_ATTR_IP_BLOCK_RESET() {
		return RESP_ATTR_IP_BLOCK_RESET;
	}
	public String getRESP_VALUE_IP_BLOCK_RESET_TRUE() {
		return RESP_VALUE_IP_BLOCK_RESET_TRUE;
	}
	public String getRESP_VALUE_IP_BLOCK_RESET_FALSE() {
		return RESP_VALUE_IP_BLOCK_RESET_FALSE;
	}
}
