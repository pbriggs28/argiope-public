package com.preston.argiope.model.dev;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.preston.argiope.app.constant.AppConstants.DataStore.Tables;

@Entity
@Table(name = Tables.MethodPerformanceTestingResultTable.TABLE_NAME, schema = Tables.MethodPerformanceTestingResultTable.TABLE_SCHEMA)
public class MethodPerformanceMonitoringResult {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = Tables.MethodPerformanceTestingResultTable.Columns.PERFORMANCE_MONITORING_RESULT_ID, unique = true, nullable = false)
	private long id;

	@Column(name = Tables.MethodPerformanceTestingResultTable.Columns.RECORDED_BY, nullable = false)
	private String recordedBy;

	@Column(name = Tables.MethodPerformanceTestingResultTable.Columns.PACKAGE_NAME, nullable = false)
	private String packageName;

	@Column(name = Tables.MethodPerformanceTestingResultTable.Columns.CLASS_NAME, nullable = false)
	private String className;

	@Column(name = Tables.MethodPerformanceTestingResultTable.Columns.METHOD_NAME, nullable = false)
	private String methodName;
	
	@Column(name = Tables.MethodPerformanceTestingResultTable.Columns.RUNTIME_MILLIS, nullable = false)
	private long millis;
	
	@Column(name = Tables.MethodPerformanceTestingResultTable.Columns.RUNTIME_DATE, nullable = false)
	private LocalDateTime date;

	public String getRecordedBy() {
		return recordedBy;
	}
	public void setRecordedBy(String recordedBy) {
		this.recordedBy = recordedBy;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public long getMillis() {
		return millis;
	}
	public void setMillis(long millis) {
		this.millis = millis;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
