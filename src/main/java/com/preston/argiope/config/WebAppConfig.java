package com.preston.argiope.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// This defines this class as the equivalent of being the file [servlet name]-servlet.xml
@Configuration
// This is the equivalent of <component-scan> in [servlet name]-servlet.xml
@ComponentScan("com.preston.argiope")
// This enables web annotations such as @RequestMapping etc (I think)
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter{
	public static final String VIEW_PREFIX = "WEB-INF/views/";
	public static final String VIEW_SUFFIX = ".jsp";
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix(VIEW_PREFIX);
		viewResolver.setSuffix(VIEW_SUFFIX);
		
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// This replaces <mvc:respource> tag in [servlet name]-servlet.xml
		// addResourceHandler() defines the url paths covered (follows ant path rules)
		// addResourceLocations() defines the actual file location of the files relative
		// to the src/main/webapp path
		registry.addResourceHandler("/static/**").addResourceLocations("/resources/");
	}
	
	
}
