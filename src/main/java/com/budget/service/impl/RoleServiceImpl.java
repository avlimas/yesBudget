package com.budget.service.impl;

import java.time.ZonedDateTime;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.budget.api.request.RolePostRequest;
import com.budget.model.role.RoleEntity;
import com.budget.repository.RoleRepository;
import com.budget.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;
	
	public boolean createRole(RolePostRequest rolePostRequest) {
		boolean result = false;
		String roleName = rolePostRequest.getRoleName();
		if (!StringUtils.isEmpty(roleName)) {
			if (!isRoleDuplicated(roleName)) {
				this.roleRepository.save(RoleEntity.builder()
						.roleName(roleName)
						.createdIn(ZonedDateTime.now())
						.build());
				result = true;
			}
			else {
				throw new DuplicateKeyException("Duplicate Role name");
			}
		}
		else {
			throw new IllegalArgumentException("Role name variable is null or empty");
		}
		return result;
	}
	
	public boolean isRoleDuplicated(String roleName) {
		return this.roleRepository.findByRoleName(roleName).isPresent();
	}
}
