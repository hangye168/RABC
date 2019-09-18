package com.web.service;

import com.web.model.Role;
import com.web.query.RoleQuery;

public interface RoleService extends BaseService<Role, RoleQuery>{
	
	public void updateGrantPerm(Integer roleId, String permIds);
	
	public void updateRole(Role role);
}
