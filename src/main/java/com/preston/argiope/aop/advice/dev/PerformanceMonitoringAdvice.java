package com.preston.argiope.aop.advice.dev;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.preston.argiope.aop.pointcut.Pointcuts;
import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.props.dev.DevApplicationProperties;
import com.preston.argiope.app.props.sub.MethodPerformanceLoggingSettings;
import com.preston.argiope.dao.dev.MethodPerformanceMonitoringResultDao;
import com.preston.argiope.model.dev.MethodPerformanceMonitoringResult;

@Profile(AppConstants.Profiles.NOT_PROD)
@Aspect
@Component
public class PerformanceMonitoringAdvice {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private MethodPerformanceMonitoringResultDao perfResultDao;
	@Autowired private DevApplicationProperties devProps;

	private boolean recordAppLayer;
	private boolean recordServiceLayer;
	private boolean recordDaoLayer;
	private boolean recordControllerLayer;
	
	@PostConstruct
	public void init() {
		MethodPerformanceLoggingSettings settings = devProps.getMethodPerformanceLoggingSettings();
		
		recordAppLayer 			= settings.isRecordAppLayer();
		recordServiceLayer 		= settings.isRecordServiceLayer();
		recordDaoLayer 			= settings.isRecordDaoLayer();
		recordControllerLayer	= settings.isRecordControllerLayer();
	}
	
	@Around(Pointcuts.Layers.APP_LAYER)
	public Object monitorAppLayer(ProceedingJoinPoint pjp) throws Throwable {
		return monitorPerformance(pjp, recordAppLayer, "app-layer-pointcut");
	}
	
	@Around(Pointcuts.PerformanceMonitoring.SERVICE_LAYER)
	public Object monitorServiceLayer(ProceedingJoinPoint pjp) throws Throwable {
		return monitorPerformance(pjp, recordServiceLayer, "service-layer-pointcut");
	}

	@Around(Pointcuts.PerformanceMonitoring.DAO_LAYER)
	public Object monitorDaoLayer(ProceedingJoinPoint pjp) throws Throwable {
		return monitorPerformance(pjp, recordDaoLayer, "dao-layer-pointcut");
	}
	
	@Around(Pointcuts.PerformanceMonitoring.CONTROLLER_LAYER)
	public Object monitorControllerLayer(ProceedingJoinPoint pjp) throws Throwable {
		return monitorPerformance(pjp, recordControllerLayer, "controller-layer-pointcut");
	}

	// Helper Methods
	// ====================================================================================================
	private Object monitorPerformance(ProceedingJoinPoint pjp, boolean enabled, String recordedBy) throws Throwable {
		
		if(!enabled)
			return pjp.proceed();
		
		StopWatch stopWatch = new StopWatch();
		
		MethodPerformanceMonitoringResult result = new MethodPerformanceMonitoringResult();
		Signature sig = pjp.getSignature();
		result.setRecordedBy(recordedBy);
		result.setPackageName(sig.getDeclaringType().getPackage().getName());
		result.setClassName(sig.getDeclaringType().getSimpleName());
		result.setMethodName(sig.getName());
		result.setDate(LocalDateTime.now());
		
		stopWatch.start();
		try {
			return pjp.proceed();
		}
		finally {
			stopWatch.stop();
			result.setMillis(stopWatch.getLastTaskTimeMillis());
			logger.trace("Performance monitoring: {}.{}(...) - {}ms", result.getClassName(), 
					result.getMethodName(), result.getMillis());
			perfResultDao.save(result);
		}
		
	}
}
