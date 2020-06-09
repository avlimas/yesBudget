package com.budget.component;

import static com.budget.constant.BudgetConstant.DEFAULT_FILE_JSON_PATH;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.budget.api.request.BudCategoryPostRequest;
import com.budget.api.request.ExpCategoryPostRequest;
import com.budget.api.request.RolePostRequest;
import com.budget.api.request.UserPostRequest;
import com.budget.property.BudgetProperties;
import com.budget.service.BudCategoryService;
import com.budget.service.ExpCategoryService;
import com.budget.service.RoleService;
import com.budget.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitStartup {
	
	private final BudgetProperties budgetProperties;
	
	private final RoleService roleService;
	
	private final UserService userService;

	private final BudCategoryService budCategoryService;

	private final ExpCategoryService expCategoryService;
	
	private final ObjectMapper objMapper = new ObjectMapper();
	
	@PostConstruct
	private void initData() {
		if (budgetProperties.isInitUsersData()) {
			log.debug("Initializing users data from JSON resource folder...");
			if (loadDefaultRolesData()) 
				loadDefaultUsersData();
			log.debug("Finish initializing users data...");
		}
		if (budgetProperties.isInitCategoriesData()) {
			log.debug("Initializing categories data from JSON resource folder...");
			loadBudgetCategoryData();
			loadExpenseCategoryData();
			log.debug("Finish initializing categories data...");
		}
	}
	
	private void loadExpenseCategoryData() {
		final String expCategoryJsonFilePath = "exp_category.json";
		try {
			TypeReference<List<ExpCategoryPostRequest>> typeReference = new TypeReference<List<ExpCategoryPostRequest>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream(DEFAULT_FILE_JSON_PATH + expCategoryJsonFilePath);
			List<ExpCategoryPostRequest> expenseCategories = objMapper.readValue(inputStream, typeReference).stream()
					.filter(expCategoryPostRequest -> 
					!this.expCategoryService.isExpenseCategoryDuplicated(expCategoryPostRequest.getExpCategoryName()))
					.collect(Collectors.toList());
			expenseCategories.stream().forEach(expCategoryPostRequest -> {
				log.debug("Now importing " + expCategoryPostRequest);
				this.expCategoryService.createExpenseCategory(expCategoryPostRequest);
			});
		} catch (IOException e) {
			log.error("Error in loading " + DEFAULT_FILE_JSON_PATH + expCategoryJsonFilePath,e);
		}
	}
	
	private void loadBudgetCategoryData() {
		final String budCategoryJsonFilePath = "bud_category.json";
		try {
			TypeReference<List<BudCategoryPostRequest>> typeReference = new TypeReference<List<BudCategoryPostRequest>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream(DEFAULT_FILE_JSON_PATH + budCategoryJsonFilePath);
			List<BudCategoryPostRequest> budgetCategories = objMapper.readValue(inputStream, typeReference).stream()
					.filter(budCategoryPostRequest -> 
					!this.budCategoryService.isBudgetCategoryDuplicated(budCategoryPostRequest.getBudCategoryName()))
					.collect(Collectors.toList());
			budgetCategories.stream().forEach(budCategoryPostRequest -> {
				log.debug("Now importing " + budCategoryPostRequest);
				this.budCategoryService.createBudgetCategory(budCategoryPostRequest);
			});
		} catch (IOException e) {
			log.error("Error in loading " + DEFAULT_FILE_JSON_PATH + budCategoryJsonFilePath,e);
		}
	}
	
	private boolean loadDefaultRolesData() {
		boolean result = false;
		final String roleJsonFilePath = "app_roles.json";
		try {
			TypeReference<List<RolePostRequest>> typeReference = new TypeReference<List<RolePostRequest>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream(DEFAULT_FILE_JSON_PATH + roleJsonFilePath);
			List<RolePostRequest> roles = objMapper.readValue(inputStream, typeReference).stream()
					.filter(rolePostRequest -> !this.roleService.isRoleDuplicated(rolePostRequest.getRoleName()))
					.collect(Collectors.toList());
			roles.stream().forEach(rolePostRequest -> {
				log.debug("Now importing " + rolePostRequest.getRoleName());
				this.roleService.createRole(rolePostRequest);
			});
			result = true;
		} catch (IOException e) {
			log.error("Error in loading " + DEFAULT_FILE_JSON_PATH + roleJsonFilePath,e);
		}
		return result;
	}
	
	private void loadDefaultUsersData() {
		final String userJsonFilePath = "app_users.json";
		try {
			TypeReference<List<UserPostRequest>> typeReference = new TypeReference<List<UserPostRequest>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream(DEFAULT_FILE_JSON_PATH + userJsonFilePath);
			List<UserPostRequest> users = objMapper.readValue(inputStream, typeReference).stream()
					.filter(userPostRequest -> !this.userService.isUserDuplicated(userPostRequest.getUserName()))
					.collect(Collectors.toList());
			users.stream().forEach(userPostRequest -> {
				log.debug("Now importing " + userPostRequest);
				this.userService.createUser(userPostRequest);
			});
		} catch (IOException e) {
			log.error("Error in loading " + DEFAULT_FILE_JSON_PATH + userJsonFilePath,e);
		}
	}
}
