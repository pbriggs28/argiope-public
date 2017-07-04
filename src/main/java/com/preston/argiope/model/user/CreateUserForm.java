package com.preston.argiope.model.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.preston.argiope.service.user.RoleServiceImpl.RoleConstant;

/**
 * This is a temporary class used to create new users.
 * 
 * This object is meant to be passed to a Dao object to create the user
 * in the database and the Dao object should then return a true User
 * object.
 * 
 * @author pbriggs
 *
 */
@Component
@Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CreateUserForm {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Set<RoleConstant> roles = new HashSet<RoleConstant>();
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<RoleConstant> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleConstant> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateUserForm [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", username=");
		builder.append(username);
		builder.append(", roles=");
		builder.append(roles);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * ONLY to be used in dev mode!
	 */
	public String toStringWithPassword() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateUserForm [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", roles=");
		builder.append(roles);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
