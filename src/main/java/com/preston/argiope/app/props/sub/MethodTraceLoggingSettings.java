package com.preston.argiope.app.props.sub;

import com.preston.argiope.aop.advice.dev.MethodTraceLoggingAdvice;

/**
 * Settings to be used for logging all method execution by layer, etc.
 * 
 * @see {@link MethodTraceLoggingAdvice}
 * @author pbriggs
 */
public class MethodTraceLoggingSettings {
	/**
	 * When one of the method trace logs are enabled, indent the trace logs
	 * based on how "deep" in the stack trace the call is. This makes it easy to
	 * see nested method calls. The indent is increased on each method start and
	 * decreased at the end.
	 * 
	 * Defaults to true.
	 */
	private boolean indentTraceLogs = true;
	/**
	 * (Assuming method trace logging is enabled)  Include the method's argument types.
	 * Ex when enabled: -----tracelog:	[Begin] UserController.loginPageGet(ModelMap model, String logoutSuccess, String loginError, HttpServletRequest req)
	 * Ex when disabled: -----tracelog:	[Begin] UserController.loginPageGet(...)
	 */
	private boolean traceLogArgTypes = true;
	/**
	 * (Assuming method trace logging is enabled) Include the method's argument names.
	 * Ex when enabled: -----tracelog:	[Begin] UserController.loginPageGet(ModelMap model, String logoutSuccess, String loginError, HttpServletRequest req)
	 * Ex when disabled: -----tracelog:	[Begin] UserController.loginPageGet(ModelMap, String, String, HttpServletRequest)
	 */
	private boolean traceLogArgNames = true;
	/**
	 * (Assuming method trace logging is enabled) Include the method's argument values.
	 * Ex when enabled: -----tracelog:	[End  ] AdminController.deleteUsersPage(String username = AutomationTesting01)
	 * Ex when disabled: -----tracelog:	[End  ] AdminController.deleteUsersPage(String username)
	 */
	private boolean traceLogArgValues = false;
	
	/**
	 * Trace all method calls in the app package layer. (Requires MethodTraceLoggingAdvice logger to be set to TRACE)
	 */
	private boolean traceAppLayer = false;

	/**
	 * Trace all method calls in the service package layer. (Requires MethodTraceLoggingAdvice logger to be set to TRACE)
	 */
	private boolean traceServiceLayer = false;

	/**
	 * Trace all method calls in the dao package layer. (Requires MethodTraceLoggingAdvice logger to be set to TRACE)
	 */
	private boolean traceDaoLayer = false;

	/**
	 * Trace all method calls in the controller package layer. (Requires MethodTraceLoggingAdvice logger to be set to TRACE)
	 */
	private boolean traceControllerLayer = false;
	
	public boolean isIndentTraceLogs() {
		return indentTraceLogs;
	}
	public void setIndentTraceLogs(boolean indentTraceLogs) {
		this.indentTraceLogs = indentTraceLogs;
	}
	public boolean isTraceLogArgTypes() {
		return traceLogArgTypes;
	}
	public void setTraceLogArgTypes(boolean logArgTypes) {
		this.traceLogArgTypes = logArgTypes;
	}
	public boolean isTraceLogArgNames() {
		return traceLogArgNames;
	}
	public void setTraceLogArgNames(boolean logArgTypesAndNames) {
		this.traceLogArgNames = logArgTypesAndNames;
	}
	public boolean isTraceLogArgValues() {
		return traceLogArgValues;
	}
	public void setTraceLogArgValues(boolean logArgTypesAndValues) {
		this.traceLogArgValues = logArgTypesAndValues;
	}
	
	
	
	public boolean isTraceAppLayer() {
		return traceAppLayer;
	}
	public void setTraceAppLayer(boolean traceApp) {
		this.traceAppLayer = traceApp;
	}
	public boolean isTraceServiceLayer() {
		return traceServiceLayer;
	}
	public void setTraceServiceLayer(boolean traceService) {
		this.traceServiceLayer = traceService;
	}
	public boolean isTraceDaoLayer() {
		return traceDaoLayer;
	}
	public void setTraceDaoLayer(boolean traceDao) {
		this.traceDaoLayer = traceDao;
	}
	public boolean isTraceControllerLayer() {
		return traceControllerLayer;
	}
	public void setTraceControllerLayer(boolean traceController) {
		this.traceControllerLayer = traceController;
	}
}