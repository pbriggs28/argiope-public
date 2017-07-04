package com.preston.argiope.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.dao.user.RoleDao;
import com.preston.argiope.model.user.Role;

/**
 * <p>
 * {@link Role}s are a constant in the sense they will only be updated with 
 * code changes. {@link Role}s will not be added/modified by users using the application.
 *  They could be completely removed from the data store and 
 * managed 100% in the source code however this would make it less clear when
 * querying from the data store. Instead, upon application start up the {@link Role}s
 * are updated/added to the data store and any inconsistencies are logged. After this
 * we can safely access {@link Role}s using the {@link RoleConstant}s.
 * </p>
 * <p>
 * Ex: {@link RoleService#getRole(RoleConstant)}
 * </p>
 * @author pbriggs
 *
 */
@Service
public class RoleServiceImpl implements RoleService, RoleInitializationService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private boolean initialized = false;
	
	@Autowired private RoleDao roleDao;

	// RoleService implementations
	// ====================================================================================================
	@Override
	public Role getRole(RoleConstant role) {
		return role.daoRole;
	}

	// RoleInitializationService implementations
	// ====================================================================================================
	@Override
	public void initializeRoles() {

		if(initialized == true)
			return;
		logger.debug("Initializing roles in data store...");
		
		/* Persist any roles defined in role constants that don't exist in db */
		for(RoleConstant roleConstant : RoleConstant.values()) {
			Role roleFromConstant = roleConstant.daoRole;
			Role roleFromDao = roleDao.findByName(roleFromConstant.getName());
			if(roleFromDao == null) {
				logger.info("Role not found in data store. Persisting role: [{}].", roleFromConstant);
				roleFromDao = roleDao.save(roleFromConstant);
				roleConstant.daoRole = roleFromDao;
			} else {
				roleConstant.daoRole = roleFromDao;
				if(roleFromDao.getId() != roleFromConstant.getId()) {
					logger.warn("Data Integrity Warning: Id of role found in data store [{}] does not match id "
							+ "found in {} [{}] for role [{}].", roleFromDao.getId(), RoleConstant.class.getSimpleName(), 
							roleFromConstant.getId(), roleFromDao.getName());
				}
			}
		}
		
		/* Now that all role constants have been persisted in db, verify there are no extra roles in db */
		Iterable<Role> daoRoleList = roleDao.findAll();
		for(Role daoRole : daoRoleList) {
			if(RoleConstant.getRoleByName(daoRole.getName()) == null)
				logger.warn("Data Integrity Warning: Found role in data store with a name of "
						+ "[{}] and an id of [{}] but found no matching role in {}.", 
						daoRole.getName(), daoRole.getId(), RoleConstant.class.getSimpleName());
		}
		
		initialized = true;
		logger.debug("Initialization of roles in data store complete.");
	}

	// Inner classes/enums
	// ====================================================================================================
	public enum RoleConstant {
		USER(	AppConstants.DataStore.StaticData.Role.User.KEY, 	AppConstants.DataStore.StaticData.Role.User.ID),
		ADMIN(	AppConstants.DataStore.StaticData.Role.Admin.KEY, 	AppConstants.DataStore.StaticData.Role.Admin.ID);
		
		RoleConstant(String key, Integer id) {
			Role role = new Role();
			role.setId(id);
			role.setName(key);
			this.daoRole = role;
		}

		private Role daoRole;
		
		private static Role getRoleByName(String name) {
			if(name == null || name.length() == 0)
				return null;
			for(RoleConstant role : RoleConstant.values()) {
				if(role.daoRole.getName().equals(name))
					return role.daoRole;
			}
			
			return null;
		}
	}
}
