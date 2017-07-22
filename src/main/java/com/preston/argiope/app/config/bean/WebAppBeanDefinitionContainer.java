package com.preston.argiope.app.config.bean;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.props.ServletProps;

@Configuration
public class WebAppBeanDefinitionContainer {
	public static final String VIEW_PREFIX = "/WEB-INF/views/";
	public static final String VIEW_SUFFIX = ".jsp";
	
	@Autowired private ServletProps servletProps;

	@Bean
	public ViewResolver viewResolverBean() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix(VIEW_PREFIX);
		viewResolver.setSuffix(VIEW_SUFFIX);
		
		return viewResolver;
	}
	
	/* Required for accessing the request object outside of the controllers (IP Blocking) */
	@Bean
	public RequestContextListener requestContextListenerBean(){
	    return new RequestContextListener();
	}

	// TODO: Configure both HTTP and HTTPS in the java config so they are next to each other.
	
	/* Enable HTTP (Since we overrode HTTP by setting up HTTPS in the application.properties)
	 * https://drissamri.be/blog/java/enable-https-in-spring-boot/ */
	@Profile(AppConstants.Profiles.DEV_EMBEDDED_SERVER)
	@Bean
	public EmbeddedServletContainerFactory servletContainerBean() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory(servletProps.getHttpPort());
		/* Extracted 'require HTTPS' and port redirection to be managed by Spring security */
		tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
		return tomcat;
	}

	private Connector initiateHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(servletProps.getHttpPort());
		connector.setSecure(false);
		/* Extracted 'require HTTPS' and port redirection to be managed by Spring security */

		return connector;
	}
}
