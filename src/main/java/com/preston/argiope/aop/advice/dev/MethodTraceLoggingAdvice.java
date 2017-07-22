package com.preston.argiope.aop.advice.dev;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.preston.argiope.aop.pointcut.PointcutContainer;
import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.props.AppProps;
import com.preston.argiope.app.props.sub.MethodTraceLoggingSettings;

@Profile(AppConstants.Profiles.NOT_PROD)
@Component
@Aspect
public class MethodTraceLoggingAdvice {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/* Static members */
	private static final String TRACE_LOG_MSG_PREPEND = "     -----tracelog:";

	/* Autowired members */
	@Autowired private AppProps appProps;
	private boolean indentTraceLogs;
	private boolean logArgTypes;
	private boolean logArgNames;
	private boolean logArgValues;
	
	private boolean traceAppLayer;
	private boolean traceServiceLayer;
	private boolean traceDaoLayer;
	private boolean traceControllerLayer;

	/* Other members */
	private AtomicInteger indent = new AtomicInteger(0);
	
	private boolean loggedInvalidConfMsg = false;

	@PostConstruct
	private void init() {
		MethodTraceLoggingSettings settings = appProps.getMethodTraceLoggingSettings();
		
		indentTraceLogs  		= settings.isIndentTraceLogs();
		logArgValues			= settings.isTraceLogArgValues();
		logArgNames				= settings.isTraceLogArgNames();
		logArgTypes 			= settings.isTraceLogArgTypes();
		
		traceAppLayer 			= settings.isTraceAppLayer();
		traceServiceLayer 		= settings.isTraceServiceLayer();
		traceDaoLayer 			= settings.isTraceDaoLayer();
		traceControllerLayer	= settings.isTraceControllerLayer();
	}

	@Around(value=PointcutContainer.APP_LAYER)
	public Object traceLogAppLayer(ProceedingJoinPoint pjp) throws Throwable {
		return traceMethod(pjp, traceAppLayer, "app-layer", logger);
	}

	@Around(value=PointcutContainer.SERVICE_LAYER)
	public Object traceLogServiceLayer(ProceedingJoinPoint pjp) throws Throwable {
		return traceMethod(pjp, traceServiceLayer, "service-layer", logger);
	}

	@Around(value=PointcutContainer.DAO_LAYER)
	public Object traceLogDaoLayer(ProceedingJoinPoint pjp) throws Throwable {
		return traceMethod(pjp, traceDaoLayer, "dao-layer", logger);
	}

	@Around(value=PointcutContainer.CONTROLLER_LAYER)
	public Object traceLogControllerLayer(ProceedingJoinPoint pjp) throws Throwable {
		return traceMethod(pjp, traceControllerLayer, "controller-layer", logger);
	}

	// Helper Methods
	// ====================================================================================================
	private Object traceMethod(ProceedingJoinPoint pjp, boolean enabled, 
			String traceName, Logger log) throws Throwable {
		
		if(!enabled)
			return pjp.proceed();
		
		if(!log.isTraceEnabled()) {
			logIncorrectConfig(traceName);
			return pjp.proceed();
		}
		
		String methodStr = buildMethodString(pjp);
		increment();
		log.debug("{}{}[Begin] {}", TRACE_LOG_MSG_PREPEND, indent(), methodStr);
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			log.debug("{}{}[Exc  ] {} Ex: {}", TRACE_LOG_MSG_PREPEND, indent(), methodStr, e.getMessage());
			throw e;
		} finally {
			log.debug("{}{}[End  ] {}", TRACE_LOG_MSG_PREPEND, indent(), methodStr);
			decrement();
		}
	}
	
	private void logIncorrectConfig(String traceName) {
		if(loggedInvalidConfMsg)
			return;
		
		logger.warn("Invalid Method-Trace-Logging configuration. The Method-Trace-Logging feature was enabled for [{}] "
				+ "but the logger [{}] is not set to trace. Logging will not be executed.", traceName, logger.getName());
		loggedInvalidConfMsg = true;
	}

	private void increment() {
		if(indentTraceLogs)
			indent.incrementAndGet();
	}
	
	private void decrement() {
		if(indentTraceLogs)
			indent.decrementAndGet();
	}
	
	private String indent() {
		StringBuilder sb = new StringBuilder("");
		if(indentTraceLogs) {
			for(int i=0; i<indent.get(); i++) {
				sb.append("\t");
			}			
		}
		return sb.toString();
	}
	
	private String buildMethodString(ProceedingJoinPoint pjp) {
		String className = pjp.getSignature().getDeclaringType().getSimpleName();
		String methodName = pjp.getSignature().getName();
		String argString = "(...)";
		
		if(logArgTypes) {
			argString = buildArgString(pjp);
		}
		
		return String.format("%s.%s%s", className, methodName, argString);
	}

	
	/**
	 * Examples:<br/>
	 * <br/>
	 * (MyClass->MyClassImpl argName = "hello world")<br/>
	 * (String username = "ricknmorty", String password = "schwifty")
	 * 
	 * @param pjp
	 * @return
	 */
	private String buildArgString(ProceedingJoinPoint pjp) {
		List<String> argStringList = new ArrayList<>();
		
		Class<?>[] argClassList = ((CodeSignature) pjp.getSignature()).getParameterTypes();
		String[] argNameList = ((CodeSignature) pjp.getSignature()).getParameterNames();
		/* Method argument names of bytecode generated classes are not available (ex: CrudRepository) */
		// TODO: Find the declaring interfaces method and get the arg names from there */
		if(argNameList == null) {
			argNameList = new String[argClassList.length];
			for (int i = 0; i < argNameList.length; i++) {
				argNameList[i] = "?";
			}
		}
		Object[] argImplList = pjp.getArgs();
		
		for (int i = 0; i < argClassList.length; i++) {
			StringBuilder sb = new StringBuilder();
			String argClassName = argClassList[i].getSimpleName();
			String argImplClassName;
			String argName = argNameList[i];
			String argImplValue;
			
			Object argClazz = argImplList[i];
			if(argClazz != null) {
				argImplClassName = argClazz.getClass().getSimpleName();
				argImplValue = argClazz.toString();
			} else {
				argImplClassName = "null";
				argImplValue = "null";
			}

			sb.append(argClassName);
			
			if(logArgValues && !argClassName.equals(argImplClassName) && !"null".equals(argImplClassName)) {
				sb.append("->");
				sb.append(argImplClassName);					
			}
			
			if(logArgNames) {
				sb.append(" ");
				sb.append(argName);					
			}
			
			if(logArgValues) {
				sb.append(" = ");
				sb.append(argImplValue);					
			}
			
			argStringList.add(sb.toString());
		}
		
		return argStringList.stream().collect(Collectors.joining(", ", "(", ")"));
	}
}
