package com.preston.argiope.model.user.dev;

import org.springframework.web.context.WebApplicationContext;

import com.preston.argiope.app.config.bean.dev.DevUserBeanDefinitionContainer;
import com.preston.argiope.app.init.dev.DevUserInitializer;
import com.preston.argiope.model.user.CreateUserForm;

/**
 * Used to denote a user to be created on application startup by
 * {@link DevUserInitializer}. This is needed because {@link CreateUserForm} is
 * defined as {@link WebApplicationContext#SCOPE_REQUEST} and therefore if we
 * attempt to @Autowire it, it will try to insert the REQUEST scoped version
 * instead of our @Bean definitions.<br />
 * <br />
 * See {@link DevUserBeanDefinitionContainer} for {@link DevUser} definition
 * examples.
 * 
 * @author pbriggs
 *
 */
public class DevUser extends CreateUserForm {

}
