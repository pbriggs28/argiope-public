package com.preston.argiope.dao.user;

import org.springframework.data.repository.RepositoryDefinition;

import com.preston.argiope.dao.BaseDao;
import com.preston.argiope.model.user.User;

@RepositoryDefinition(domainClass=User.class, idClass=Long.class)
public interface UserDao extends BaseDao<User, Long> {
	
	User findByUsername(String username);
	
	Iterable<User> findByEnabled(boolean enabled);
	
}
