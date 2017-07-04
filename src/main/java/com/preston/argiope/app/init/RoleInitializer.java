package com.preston.argiope.app.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.service.user.RoleInitializationService;

/**
 * @see RoleInitializationService
 * @author pbriggs
 *
 */
@Order(AppConstants.Order.InitializerOrders.ROLE_VALIDATOR)
@Component
public class RoleInitializer implements ApplicationListener<ApplicationReadyEvent>{
	@Autowired private RoleInitializationService roleInitializationService;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		roleInitializationService.initializeRoles();
	}
}