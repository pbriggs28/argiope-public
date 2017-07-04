package com.preston.argiope.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import com.preston.argiope.app.constant.AppConstants.DataStore.Tables;

@Entity
@Table(
	name=Tables.Role.TABLE_NAME,
	schema=Tables.Role.TABLE_SCHEMA,
	uniqueConstraints = @UniqueConstraint(columnNames = Tables.Role.Columns.ROLE_NAME)
)
public class Role implements GrantedAuthority {
	private static final long serialVersionUID = -2456755797620617272L;

	@Id
	@Column(name = Tables.Role.Columns.ROLE_ID, unique = true, nullable = false)
	private Integer id;
	
	@NaturalId
	@Column(name = Tables.Role.Columns.ROLE_NAME, unique = true, nullable = false, length = 30)
	private String name;
	
	@Column(name = Tables.Role.Columns.DESCRIPTION)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = User.ROLE_LIST_MAPPED_BY_NAME)
	private Set<User> userList = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String key) {
		this.name = key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<User> getUserList() {
		return userList;
	}
	public void setUserList(Set<User> roleList) {
		this.userList = roleList;
	}
	
	// GrantedAuthority implementations
	// ====================================================================================================
	@Override
	public String getAuthority() {
		return getName();
	}

	// Object overrides
	// ====================================================================================================
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Role))
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
