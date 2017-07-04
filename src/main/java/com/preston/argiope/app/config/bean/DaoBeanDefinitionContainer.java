package com.preston.argiope.app.config.bean;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.preston.argiope.app.constant.AppConstants;

@Configuration
public class DaoBeanDefinitionContainer {
	
	@Bean
	@ConfigurationProperties(prefix=AppConstants.PropertyKeys.DataSource.PREFIX)
	public DataSource dataSourceBean() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		return dataSource;
	}
}
