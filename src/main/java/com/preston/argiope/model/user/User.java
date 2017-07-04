package com.preston.argiope.model.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.preston.argiope.app.constant.AppConstants.DataStore.Tables;

@Entity
@Table(
	name=Tables.User.TABLE_NAME,
	schema=Tables.User.TABLE_SCHEMA,
	uniqueConstraints = @UniqueConstraint(columnNames = Tables.User.Columns.USERNAME)
)
public class User implements UserDetails, Serializable {
	private static final long serialVersionUID = -6946266951013640278L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	// Not working
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=Tables.User.SEQUENCE_GENERATOR_NAME)
//	@SequenceGenerator(
//			name=Tables.User.SEQUENCE_GENERATOR_NAME, 
//			allocationSize=Tables.User.SEQUENCE_ALLOCATION_SIZE,
//			sequenceName=Tables.User.TABLE_SEQUENCE
//	)
	
	// Not working
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator=Tables.User.SEQUENCE_GENERATOR_NAME)
//	@GenericGenerator(
//		name=Tables.User.SEQUENCE_GENERATOR_NAME,
//		strategy="sequence",
//		parameters= {
//				@Parameter(name=SequenceStyleGenerator.SEQUENCE_PARAM, value=Tables.User.TABLE_SEQUENCE),
//				@Parameter(name=SequenceStyleGenerator.INCREMENT_PARAM, value=Tables.User.SEQUENCE_ALLOCATION_SIZE)
//		}
//	)
	@Column(name=Tables.User.Columns.USER_ID, unique = true, nullable = false)
	private Long id;

	@NaturalId
	@Column(name=Tables.User.Columns.USERNAME, unique = true, nullable = false, length = 30)
	private String username;

	@Column(name=Tables.User.Columns.PASSWORD, nullable = false, length = 60)
	private String password;

	@Column(name=Tables.User.Columns.FIRST_NAME, length = 30)
	private String firstName;

	@Column(name=Tables.User.Columns.LAST_NAME, length = 30)
	private String lastName;

	@Column(name=Tables.User.Columns.NOT_EXPIRED, nullable = false)
	private boolean accountNonExpired = true;

	@Column(name=Tables.User.Columns.NOT_LOCKED, nullable = false)
	private boolean accountNonLocked = true;

	@Column(name=Tables.User.Columns.CREDENTIALS_NOT_EXPIRED, nullable = false)
	private boolean credentialsNonExpired = true;

	@Column(name=Tables.User.Columns.ENABLED, nullable = false)
	private boolean enabled = true;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = Tables.UserRoleMap.TABLE_NAME, 
		schema=Tables.UserRoleMap.TABLE_SCHEMA,
		joinColumns = {			@JoinColumn(name = Tables.UserRoleMap.Columns.USER_ID, nullable = false, updatable = true, insertable = true) },
		inverseJoinColumns = {	@JoinColumn(name = Tables.UserRoleMap.Columns.ROLE_ID, nullable = false, updatable = true, insertable = true) }
	)
	private Set<Role> roleList = new HashSet<Role>();
	/** If changing the name of {@link User#roleList} change this constant as well.
	 * Hibernate accesses this in a non-type-safe way in the 'mappedBy' attribute. */
	public static final String ROLE_LIST_MAPPED_BY_NAME = "roleList";
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Set<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(Set<Role> roles) {
		this.roleList = roles;
	}

	// UserDetails implementations
	// ====================================================================================================
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoleList();
	}

	// Object overrides
	// ====================================================================================================
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", accountNonExpired=");
		builder.append(accountNonExpired);
		builder.append(", accountNonLocked=");
		builder.append(accountNonLocked);
		builder.append(", credentialsNonExpired=");
		builder.append(credentialsNonExpired);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", roleList=");
		builder.append(roleList);
		builder.append("]");
		return builder.toString();
	}
}
