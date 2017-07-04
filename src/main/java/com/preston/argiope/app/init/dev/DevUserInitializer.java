package com.preston.argiope.app.init.dev;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.config.bean.dev.DevUserBeanDefinitionContainer;
import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.constant.AppConstants.Order.InitializerOrders;
import com.preston.argiope.exception.common.form.MissingRequiredFieldException;
import com.preston.argiope.exception.service.user.DeleteUserException;
import com.preston.argiope.exception.service.user.UserNotFoundException;
import com.preston.argiope.exception.service.user.UsernameAlreadyExistsException;
import com.preston.argiope.model.user.CreateUserForm;
import com.preston.argiope.model.user.User;
import com.preston.argiope.model.user.dev.DevUser;
import com.preston.argiope.service.user.UserService;

/**
 * Responsible for creating or updating the users required for development on
 * application startup. To wire up a user for this class to persist, define a
 * new @Bean definition returning a {@link DevUser} type. The preferred
 * location for these types is in the {@link DevUserBeanDefinitionContainer}
 * class with their information (username, etc.) in {@link AppConstants.Users}.
 * 
 * @author pbriggs
 */
@Component
@Order(InitializerOrders.DEV_USER_INITIALIZER)
@Profile(AppConstants.Profiles.DEV)
public class DevUserInitializer implements ApplicationListener<ApplicationReadyEvent>{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private UserService userService;
	@Autowired private List<DevUser> createUserOnStartupList;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		for(CreateUserForm user : createUserOnStartupList) {
			createUser(user);			
		}
	}
	
	private void createUser(CreateUserForm createUserForm) {
		logger.info("Creating dev user on startup: {}", createUserForm.toStringWithPassword());
		
		/* Delete existing user if exists */
		User existingUser = userService.getUser(createUserForm.getUsername());
		if(existingUser != null) {
			try {
				userService.deleteUser(existingUser);
			} catch (UserNotFoundException e) {
				logger.warn("Error deleting existing user [{}] on startup. "
						+ "Existing user was found but then [{}] threw [{}] when "
						+ "attempting delete. Continuing on to attempt creating user. User [{}]. Existing User: [{}]. "
						+ "Nested exception: {}",
						createUserForm.getUsername(),
						userService.getClass().getSimpleName(), 
						e.getClass().getSimpleName(), 
						createUserForm.toStringWithPassword(),
						existingUser,
						e
				);
				/* Continue on to attempt creating user */
			} catch (DeleteUserException e) {
				logger.warn("Error deleting existing user [{}] on startup. "
						+ "[{}] threw [{}] on delete. User [{}]. Existing User: [{}]."
						+ "Nested exception: {}",
						createUserForm.getUsername(),
						userService.getClass().getSimpleName(), 
						e.getClass().getSimpleName(), 
						createUserForm.toStringWithPassword(),
						existingUser,
						e
				);
				/* Don't attempt to create user */
				return;
			}
		}
		
		/* Create new user */
		try {
			userService.createUserFromForm(createUserForm);
		} catch (MissingRequiredFieldException e) {
			logger.warn("Error creating user [{}] on startup due to missing field(s). "
					+ "Have the required fields for [{}] changed? User: [{}]. "
					+ "Nested exception: {}",
					createUserForm.getUsername(),
					createUserForm.getClass().getSimpleName(), 
					createUserForm.toStringWithPassword(),
					e
			);
		} catch (UsernameAlreadyExistsException e) {
			logger.warn("Error creating user [{}] on startup. [{}] threw [{}]. "
					+ "Was there a failure in the deletion process? "
					+ "User: [{}]. Nested exception: {}",
					createUserForm.getUsername(),
					userService.getClass().getSimpleName(),
					e.getClass().getSimpleName(),
					createUserForm.toStringWithPassword(),
					e
			);
		}
	}

}
