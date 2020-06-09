package com.budget.service.impl;

import static com.budget.constant.BudgetConstant.DEFAULT_USER_ROLE;

import java.time.ZonedDateTime;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.budget.api.mapper.UserMapper;
import com.budget.api.request.UserPostRequest;
import com.budget.model.user.UserEntity;
import com.budget.repository.RoleRepository;
import com.budget.repository.UserRepository;
import com.budget.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;
	
	private final UserMapper userMapper;
	
	public boolean createUser(UserPostRequest userPostRequest) {
		boolean result = false;
		UserEntity userEntity = userMapper.userPostRequestToUserEntity(userPostRequest);
		userEntity.setCreatedIn(ZonedDateTime.now());
		if (isUserDuplicated(userEntity.getUserName())) {
			throw new DuplicateKeyException("Username is already taken. Try another one");
		}
		else {
			if (this.userRepository.findByUserEmail(userEntity.getUserEmail()).isPresent()) {
				throw new DuplicateKeyException("Email is already assigned to one account. Please use another");
			}
			else {
				String roleName = "";
				if (userPostRequest.getRoleName() != null) 
					roleName = userPostRequest.getRoleName().toLowerCase();
				else 
					roleName = DEFAULT_USER_ROLE;
				this.roleRepository.findByRoleName(roleName).ifPresent(
						userRole -> userEntity.setUserRoles(userRole));
				this.userRepository.save(userEntity);
				result = true;
			}
		}
		return result;
	}
	
	public boolean isUserDuplicated(String userName) {
		return this.userRepository.findByUserName(userName).isPresent();
	}
}
