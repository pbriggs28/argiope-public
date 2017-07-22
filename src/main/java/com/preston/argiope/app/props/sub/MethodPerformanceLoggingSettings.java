package com.preston.argiope.app.props.sub;

import com.preston.argiope.aop.advice.dev.PerformanceMonitoringAdvice;

/**
 * @see {@link PerformanceMonitoringAdvice}
 * @author pbriggs
 */
public class MethodPerformanceLoggingSettings {
	
	private boolean recordAppLayer = false;
	private boolean recordServiceLayer = false;
	private boolean recordDaoLayer = false;
	private boolean recordControllerLayer = false;
	
	public boolean isRecordAppLayer() {
		return recordAppLayer;
	}
	public void setRecordAppLayer(boolean traceApp) {
		this.recordAppLayer = traceApp;
	}
	public boolean isRecordServiceLayer() {
		return recordServiceLayer;
	}
	public void setRecordServiceLayer(boolean traceService) {
		this.recordServiceLayer = traceService;
	}
	public boolean isRecordDaoLayer() {
		return recordDaoLayer;
	}
	public void setRecordDaoLayer(boolean traceDao) {
		this.recordDaoLayer = traceDao;
	}
	public boolean isRecordControllerLayer() {
		return recordControllerLayer;
	}
	public void setRecordControllerLayer(boolean traceController) {
		this.recordControllerLayer = traceController;
	}
}