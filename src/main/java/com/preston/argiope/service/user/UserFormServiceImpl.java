package com.preston.argiope.service.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.preston.argiope.exception.common.form.MissingRequiredFieldException;
import com.preston.argiope.model.user.CreateUserForm;
import com.preston.argiope.model.user.Role;
import com.preston.argiope.model.user.User;

@Service
public class UserFormServiceImpl implements UserFormService {
	@Autowired private PasswordEncoder pwEncoder;
	@Autowired private RoleService roleService;
	
	@Override
	public User convertFormToUser(CreateUserForm form) throws MissingRequiredFieldException {
		Assert.notNull(form, "UserFormServiceImpl.convertFormToUser(...): CreateUserForm cannot be null during conversion to User");

		User user = new User();
		validate(form);
		user.setUsername(form.getUsername());
		// TODO: Extract password encoding to AOP? 
		user.setPassword(pwEncoder.encode(form.getPassword()));
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		Set<Role> roleList = new HashSet<>();
		form.getRoles().forEach(rc -> roleList.add(roleService.getRole(rc)));
		user.setRoleList(roleList);
		
		return user;
	}
	
	// TODO: Handle with Spring validation
	private void validate(CreateUserForm createUserForm) throws MissingRequiredFieldException {
		validateField(createUserForm.getUsername(), "username");
		validateField(createUserForm.getPassword(), "password");
		validateField(createUserForm.getFirstName(), "first name");
		validateField(createUserForm.getLastName(), "last name");
	}
	
	private void validateField(String field, String fieldName) throws MissingRequiredFieldException {
		if (field == null || field.length() < 1)
			throw new MissingRequiredFieldException("CreateUserForm is missing the " + fieldName + " field.");
	}

}
