package com.preston.argiope.dao.user;

import org.springframework.data.repository.RepositoryDefinition;

import com.preston.argiope.dao.BaseDao;
import com.preston.argiope.model.user.Role;

@RepositoryDefinition(domainClass=Role.class, idClass=Long.class)
public interface RoleDao extends BaseDao<Role, Long>{
	
	Role findByName(String name);
	
}
