package com.preston.argiope.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutContainer {
	private static final String PC_CLASS = "com.preston.argiope.aop.pointcut.PointcutContainer.";

	
	public static final String APP_LAYER = PC_CLASS + "appLayerPointcut()";
	@Pointcut("execution(* com.preston.argiope.app..*.*(..))")
	public void appLayerPointcut() {}

	public static final String SERVICE_LAYER = PC_CLASS + "serviceLayerPointcut()";
	@Pointcut("execution(* com.preston.argiope.service..*.*(..))")
	public void serviceLayerPointcut() {}

	public static final String DAO_LAYER = PC_CLASS + "daoLayerPointcut()";
	@Pointcut("execution(* com.preston.argiope.dao..*.*(..))")
	public void daoLayerPointcut() {}

	public static final String CONTROLLER_LAYER = PC_CLASS + "controllerLayerPointcut()";
	@Pointcut("execution(* com.preston.argiope.controller..*.*(..))")
	public void controllerLayerPointcut() {}

}
