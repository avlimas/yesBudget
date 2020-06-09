package com.budget.api.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.budget.api.request.UserPostRequest;
import com.budget.model.user.UserEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
	
	@Mappings({
		@Mapping(target="userId", ignore=true),
		@Mapping(target="userName", source="userPostRequest.userName"),		
		@Mapping(target="userEmail", source="userPostRequest.userEmail"),
		@Mapping(target="fullName", source="userPostRequest.fullName"),
		@Mapping(target="createdIn", ignore=true),
		@Mapping(target="userRoles", ignore=true),
		@Mapping(target="expenses", ignore=true)
	})
	UserEntity userPostRequestToUserEntity(UserPostRequest userPostRequest);
}
