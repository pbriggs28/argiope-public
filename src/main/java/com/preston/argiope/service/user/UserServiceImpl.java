package com.preston.argiope.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.preston.argiope.dao.user.UserDao;
import com.preston.argiope.exception.common.form.MissingRequiredFieldException;
import com.preston.argiope.exception.service.user.DeleteUserException;
import com.preston.argiope.exception.service.user.UserNotFoundException;
import com.preston.argiope.exception.service.user.UsernameAlreadyExistsException;
import com.preston.argiope.model.user.CreateUserForm;
import com.preston.argiope.model.user.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private UserDao userDao;
	@Autowired private UserFormService userFormService;

	@Override
	public User getUser(String username) {
		User returnedUser = userDao.findByUsername(username);
		return returnedUser;
	}

	@Override
	public User getUser(String username, boolean ignoreCase) {
		if(ignoreCase)
			return userDao.findByUsernameIgnoreCase(username);
		User returnedUser = getUser(username);
		return returnedUser;
	}

	@Override
	public Iterable<User> getAllUsers() {
		Iterable<User> returnedUserList = userDao.findAll();
		return returnedUserList;
	}

	@Override
	public Iterable<User> getAllActiveUsers() {
		throw new RuntimeException("Method is not implemented yet...");
	}

	@Override
	public Iterable<User> getEnabledUsers() {
		throw new RuntimeException("Method is not tested yet...");
//		Iterable<User> userList = userDao.findByEnabled(true);
//		return userList;
	}

	@Override
	public Iterable<User> getDisabledUsers() {
		throw new RuntimeException("Method is not tested yet...");
//		Iterable<User> userList = userDao.findByEnabled(false);
//		return userList;
	}

	@Override
	public Iterable<User> getExpiredUsers() {
		throw new RuntimeException("Method is not implemented yet...");
	}

	@Override
	public Iterable<User> getLockedUsers() {
		throw new RuntimeException("Method is not implemented yet...");
	}

	@Override
	public Iterable<User> getCredentialsExpiredUsers() {
		throw new RuntimeException("Method is not implemented yet...");
	}

	@Override
	public User createUserFromForm(CreateUserForm createUserForm) throws MissingRequiredFieldException, UsernameAlreadyExistsException {
		User user = userFormService.convertFormToUser(createUserForm);
		User newUser = createUser(user);
		
		return newUser;
	}

	@Override
	public User createUser(User newUser) throws UsernameAlreadyExistsException {
		User userExists = userDao.findByUsername(newUser.getUsername());
		if(userExists != null) {
			String msg = String.format("Cannot create user with username [%s] as one already exists with an id of [%s].",
					newUser.getUsername(), userExists.getId());
			logger.debug("UserService: {}", msg);
			throw new UsernameAlreadyExistsException(msg);
		}
		
		User returnedUser = createOrUpdateUser(newUser);
		return returnedUser;
	}

	@Override
	public User updateUser(User updatedUser) throws UserNotFoundException {
		User userExists = userDao.findByUsername(updatedUser.getUsername());
		if(userExists == null) {
			String msg = String.format("Cannot update user with username [%s] as it does not exist. ",	updatedUser.getUsername());
			logger.debug("UserService: {}", msg);
			throw new UserNotFoundException(msg);
		}
		
		User returnedUser = createOrUpdateUser(updatedUser);
		return returnedUser;
	}

	@Override
	public User createOrUpdateUser(User user) {
		User returnedUser = userDao.save(user);
		return returnedUser;
	}

	@Override
	public void deleteUser(User user) throws UserNotFoundException, DeleteUserException {
		Assert.notNull(user, "UserServiceImpl.deleteUser(...): User cannot be null.");
		
		/* Verify user actually exists */
		User userExists = getUser(user.getUsername());
		if(userExists == null) {
			String msg = String.format("Cannot delete user with a username of [%s] as it does not exist.", user.getUsername());
			logger.warn(msg);
			throw new UserNotFoundException(msg);
		}
		
		userDao.delete(user);
		
		/* Verify user was deleted successfully */
		verifyUserDeleted(user);
	}
	
	// Private Helper Methods
	// ====================================================================================================
	private void verifyUserDeleted(User user) throws DeleteUserException {
		User userCreated = getUser(user.getUsername());
		if(userCreated != null) {
			String msg = String.format("Error deleting user with a username of [%s]. User still exists after deletion.", user.getUsername());
			logger.error(msg);
			throw new DeleteUserException(msg);
		}
	}
}
