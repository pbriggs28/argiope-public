package com.preston.argiope.dao.dev;

import org.springframework.data.repository.RepositoryDefinition;

import com.preston.argiope.dao.BaseDao;
import com.preston.argiope.model.dev.MethodPerformanceMonitoringResult;

@RepositoryDefinition(domainClass=MethodPerformanceMonitoringResult.class, idClass=Long.class)
public interface MethodPerformanceMonitoringResultDao extends BaseDao<MethodPerformanceMonitoringResult, Long> {
}
