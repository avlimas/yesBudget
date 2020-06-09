package com.budget.service.impl;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.budget.api.request.BudCategoryPostRequest;
import com.budget.model.category.BudCategoryEntity;
import com.budget.repository.BudCategoryRepository;
import com.budget.service.BudCategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudCategoryServiceImpl implements BudCategoryService {
	
	private final BudCategoryRepository budCategoryRepository;
	
	public boolean createBudgetCategory(BudCategoryPostRequest budCategoryPostRequest) {
		boolean result = false;
		String budCategoryName = budCategoryPostRequest.getBudCategoryName();
		if (!StringUtils.isEmpty(budCategoryName)) {
			if (!isBudgetCategoryDuplicated(budCategoryName)) {
				this.budCategoryRepository.save(BudCategoryEntity.builder()
						.budCategoryName(budCategoryName)
						.build());
				result = true;
			}
			else {
				throw new DuplicateKeyException("Duplicate Budget Category name");
			}
		}
		else {
			throw new IllegalArgumentException("Budget category name variable is null or empty");
		}
		return result;
	}

	public boolean isBudgetCategoryDuplicated(String budCategoryName) {
		return this.budCategoryRepository.findByBudCategoryName(budCategoryName).isPresent();
	}

}
