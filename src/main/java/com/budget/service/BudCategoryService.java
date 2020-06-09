package com.budget.service;

import com.budget.api.request.BudCategoryPostRequest;

public interface BudCategoryService {

	public boolean createBudgetCategory(BudCategoryPostRequest budCategoryPostRequest);
	
	public boolean isBudgetCategoryDuplicated(String budCategoryName);
}
