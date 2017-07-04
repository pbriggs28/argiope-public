package com.preston.argiope.app.init.dev;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.props.dev.DevApplicationProperties;

@Order(AppConstants.Order.InitializerOrders.PROPERTY_VALUE_LOGGER)
@Profile(AppConstants.Profiles.DEV)
@Component
public class PropertyLogger implements ApplicationListener<ApplicationStartingEvent> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private Environment env;
	@Autowired private DevApplicationProperties devAppProps;
	
	@Override
	public void onApplicationEvent(ApplicationStartingEvent event) {
		
		if( !(devAppProps.isLogPropertySources() || devAppProps.isLogSpringResolvedProperties()) )
			return;
		
		ConfigurableEnvironment cfEnv = (ConfigurableEnvironment) env;
		MutablePropertySources props = cfEnv.getPropertySources();
		logger.debug("========================= Logging environment properties =========================");
		int counter = 1;
		for(Iterator<PropertySource<?>> it = props.iterator(); it.hasNext();) {
			PropertySource<?> unknownPs = it.next();
			logger.debug("==========Property source {} [{}]", counter, unknownPs.getClass().getSimpleName());
			if (unknownPs instanceof EnumerablePropertySource) {
				EnumerablePropertySource<?> enumPs = (EnumerablePropertySource<?>) unknownPs;
				for(String propKey : enumPs.getPropertyNames()) {
					/*
					 * Log the following to the console:
					 * -Property key.
					 *    -Ex: [key]:server.port
					 * -Property value (if enabled)
					 *    -Ex: [val]:8443
					 * -The value Spring actually resolved the property to (if enabled)
					 *    -Ex: [spring]:9000
					 * 
					 * Examples:
					 * [key]:server.port [val]:8443 [spring]: 9000
					 * [key]:server.port [val]:8443
					 * [key]:server.port [spring]: 9000
					 */
					Object propSourceValue = enumPs.getProperty(propKey);
					String springResolvedValue = env.getProperty(propKey);
					String logMsg = "[key]:" + propKey;
					if(devAppProps.isLogPropertySources())
						logMsg = logMsg + " [val]:" + propSourceValue;
					if(devAppProps.isLogSpringResolvedProperties())
						logMsg = logMsg + " [spring]:" + springResolvedValue;
					logger.debug(logMsg);
				}
			} else {
				logger.debug("\tCannot log property source of type [{}]...", unknownPs.getClass().getSimpleName());
			}
			logger.debug("");
			counter++;
		}
		logger.debug("========================= Logging environment properties complete =========================");
	}
}
