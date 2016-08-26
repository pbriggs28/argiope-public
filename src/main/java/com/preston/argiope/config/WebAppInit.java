package com.preston.argiope.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInit implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
		webAppContext.register(WebAppConfig.class);
		
		// This replaces the <servlet> tag in web.xml
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webAppContext);
		
		// This replaces the <init-param> <param-name>contextConfigLocation in web.xml.
		ServletRegistration.Dynamic servlet = servletContext.addServlet("argiope", dispatcherServlet);
		
		// This replaces the <servlet-mapping> in web.xml
		servlet.addMapping("/");
		servlet.setAsyncSupported(true);
		// This replaces the <servlet> <load-on-startup> tag in web.xml
		servlet.setLoadOnStartup(1);
		
	}

}
