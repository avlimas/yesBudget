package com.budget.service;

import com.budget.api.request.ExpCategoryPostRequest;

public interface ExpCategoryService {

	public boolean createExpenseCategory(ExpCategoryPostRequest expCategoryPostRequest);
	
	public boolean isExpenseCategoryDuplicated(String expCategoryName);
}
