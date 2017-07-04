package com.preston.argiope.service.user;

import com.preston.argiope.model.user.Role;
import com.preston.argiope.service.user.RoleServiceImpl.RoleConstant;

public interface RoleService {

	Role getRole(RoleConstant role);

}
