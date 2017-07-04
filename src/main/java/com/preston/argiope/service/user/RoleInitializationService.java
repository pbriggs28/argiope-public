package com.preston.argiope.service.user;

import com.preston.argiope.model.user.Role;

/**
 * Responsible for initializing and/or validating the static {@link Role}
 * instances in the data store. Since roles are not meant to be dynamic
 * (added/removed by the users) they are managed by the application. Additions
 * and/or modifications of the roles would likely require a code change anyways.
 * 
 * @author pbriggs
 *
 */
public interface RoleInitializationService {

	void initializeRoles();
	
}
