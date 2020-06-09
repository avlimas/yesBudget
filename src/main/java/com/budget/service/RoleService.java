package com.budget.service;

import com.budget.api.request.RolePostRequest;

public interface RoleService {
	
	public boolean createRole(RolePostRequest rolePostRequest);
	
	public boolean isRoleDuplicated(String roleName);
}
