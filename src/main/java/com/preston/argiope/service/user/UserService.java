package com.preston.argiope.service.user;

import com.preston.argiope.exception.common.form.MissingRequiredFieldException;
import com.preston.argiope.exception.service.user.DeleteUserException;
import com.preston.argiope.exception.service.user.UserNotFoundException;
import com.preston.argiope.exception.service.user.UsernameAlreadyExistsException;
import com.preston.argiope.model.user.CreateUserForm;
import com.preston.argiope.model.user.User;

public interface UserService {
	
	// Get user methods
	// ====================================================================================================
//	User getUser(Long id);
	User getUser(String username);

	/** Returns all users without <b>any</b> filtering. */
	Iterable<User> getAllUsers();
	
	/** Returns all users capable of logging in which is currently defined 
	 * as enabled, non-expired, non-locked and non-credentials expired. */
	Iterable<User> getAllActiveUsers();
	
	/** Resembles {@link User#isEnabled()} */
	Iterable<User> getEnabledUsers();
	
	/** Resembles !{@link User#isEnabled()} */
	Iterable<User> getDisabledUsers();
	
	/** Resembles {@link !User#isAccountNonExpired()} */
	Iterable<User> getExpiredUsers();
	
	/** Resembles {@link !User#isAccountNonLocked()} */
	Iterable<User> getLockedUsers();
	
	/** Resembles {@link !User#isCredentialsNonExpired()} */
	Iterable<User> getCredentialsExpiredUsers();

	// Create/update/delete user methods
	// ====================================================================================================

	User createUserFromForm(CreateUserForm createUserForm) throws MissingRequiredFieldException, UsernameAlreadyExistsException;

	/**
	 * Creates a user only if it does not already exist. Throws a {@link UsernameAlreadyExistsException}
	 * if it does not. Use {@link #createOrUpdateUser(User)} if checks for the user have already been made.
	 * @param user
	 * @return
	 * @throws UserNotFoundException
	 */
	User createUser(User tempUser) throws UsernameAlreadyExistsException;
	
	/**
	 * Updates a user only if it already exists. Throws a {@link UserNotFoundException}
	 * if it does not. Use {@link #createOrUpdateUser(User)} if checks for the user have already been made.
	 * @param user
	 * @return
	 * @throws UserNotFoundException
	 */
	User updateUser(User user) throws UserNotFoundException;
	
	/**
	 * Creates a user if it does not exist or saves a user if it does. For a safer way to
	 * complete this, see {@link #createUser(User)} and {@link #updateUser(User)}.
	 * 
	 * @param user
	 * @return
	 */
	User createOrUpdateUser(User user);
	
	/**
	 * If the user cannot be found before deletion, a {@link UserNotFoundException}
	 * is thrown. If the user can still be found <b>after</b> deletion a
	 * {@link DeleteUserException} is thrown. The latter generally refers to a
	 * concurrency issue.
	 * 
	 * @param user
	 * @throws UserNotFoundException
	 * @throws DeleteUserException
	 */
	void deleteUser(User user) throws UserNotFoundException, DeleteUserException;

}