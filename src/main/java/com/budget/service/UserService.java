package com.budget.service;

import com.budget.api.request.UserPostRequest;

public interface UserService {
	
	public boolean createUser(UserPostRequest userPostRequest);
	
	public boolean isUserDuplicated(String userName);
}
