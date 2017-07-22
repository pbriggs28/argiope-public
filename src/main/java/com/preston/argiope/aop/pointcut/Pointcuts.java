package com.preston.argiope.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

@SuppressWarnings("hiding")
public class Pointcuts {
	private static final String CLASS_PATH = "com.preston.argiope.aop.pointcut.";
	private static final String CLASS_NAME = "Pointcuts.";
	private static final String CLASS_FULL_PATH = CLASS_PATH + CLASS_NAME;

	private static final String AND = " && ";
	private static final String NOT = "!";

	public class Layers {
		private static final String CLASS_PATH = Pointcuts.CLASS_FULL_PATH;
		private static final String CLASS_NAME = "Layers.";
		private static final String CLASS_FULL_PATH = CLASS_PATH + CLASS_NAME;
		
		public static final String APP_LAYER = CLASS_FULL_PATH + "appLayerPointcut()";
		@Pointcut("execution(* com.preston.argiope.app..*.*(..))")
		public void appLayerPointcut() {}
		
		public static final String SERVICE_LAYER = CLASS_FULL_PATH + "serviceLayerPointcut()";
		@Pointcut("execution(* com.preston.argiope.service..*.*(..))")
		public void serviceLayerPointcut() {}
		
		public static final String DAO_LAYER = CLASS_FULL_PATH + "daoLayerPointcut()";
		@Pointcut("execution(* com.preston.argiope.dao..*.*(..))")
		public void daoLayerPointcut() {}
		
		public static final String CONTROLLER_LAYER = CLASS_FULL_PATH + "controllerLayerPointcut()";
		@Pointcut("execution(* com.preston.argiope.controller..*.*(..))")
		public void controllerLayerPointcut() {}
	}


	public class PerformanceMonitoring {
		private static final String CLASS_PATH = Pointcuts.CLASS_FULL_PATH;
		private static final String CLASS_NAME = "PerformanceMonitoring.";
		private static final String CLASS_FULL_PATH = CLASS_PATH + CLASS_NAME;

		private static final String NOT_PERFORMANCE_MONITORING_DAO = CLASS_FULL_PATH + "notPerformanceMonitoringPointcut()";
		@Pointcut("!target(com.preston.argiope.dao.dev.MethodPerformanceMonitoringResultDao)")
		public void notPerformanceMonitoringPointcut() {}
		
		public static final String APP_LAYER = CLASS_FULL_PATH + "appLayerPointcut()";
		@Pointcut(Pointcuts.Layers.APP_LAYER) // TODO: This is not working: + AND + NOT_PERFORMANCE_MONITORING_DAO)
		public void appLayerPointcut() {}

		public static final String SERVICE_LAYER = CLASS_FULL_PATH + "serviceLayerPointcut()";
		@Pointcut(Pointcuts.Layers.SERVICE_LAYER) // TODO: This is not working: + AND + NOT_PERFORMANCE_MONITORING_DAO)
		public void serviceLayerPointcut() {}

		public static final String DAO_LAYER = CLASS_FULL_PATH + "daoLayerPointcut()";
		@Pointcut(Pointcuts.Layers.DAO_LAYER + AND + NOT_PERFORMANCE_MONITORING_DAO)
		public void daoLayerPointcut() {}

		public static final String CONTROLLER_LAYER = CLASS_FULL_PATH + "controllerLayerPointcut()";
		@Pointcut(Pointcuts.Layers.CONTROLLER_LAYER) // TODO: This is not working: + AND + NOT_PERFORMANCE_MONITORING_DAO)
		public void controllerLayerPointcut() {}
	}
}
