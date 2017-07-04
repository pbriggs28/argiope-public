package com.preston.argiope.app.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.constant.WebConstants.RequestMappings.Static;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{

	private static final String STATIC_RESOURCE_URL_PATH = Static.DOMAIN_PATH + "**";
	private static final String STATIC_RESOURCE_DIR_PATH = AppConstants.Directory.STATIC_RESOURCES;

	/**
	 * Configure the {@link ResourceHandlerRegistry}.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		addResourceMappings(registry);
		// Add other configurations here. (Extracted to a self-documenting method name)
	}

	/**
	 * Add mappings from URL patterns (<b>localhost:8080/static/**</b>) 
	 * to physical classpath file locations (<b>src/main/webapp/WEB-INF/resources/</b>).
	 */
	public void addResourceMappings(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/static/**").addResourceLocations("/resources/");
		registry.addResourceHandler(STATIC_RESOURCE_URL_PATH).addResourceLocations(STATIC_RESOURCE_DIR_PATH);
	}
}
