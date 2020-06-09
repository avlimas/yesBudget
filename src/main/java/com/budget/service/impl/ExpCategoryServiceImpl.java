package com.budget.service.impl;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.budget.api.request.ExpCategoryPostRequest;
import com.budget.model.category.ExpCategoryEntity;
import com.budget.repository.ExpCategoryRepository;
import com.budget.service.ExpCategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpCategoryServiceImpl implements ExpCategoryService {
	
	private final ExpCategoryRepository expCategoryRepository;
	
	public boolean createExpenseCategory(ExpCategoryPostRequest expCategoryPostRequest) {
		boolean result = false;
		String expCategoryName = expCategoryPostRequest.getExpCategoryName();
		if (!StringUtils.isEmpty(expCategoryName)) {
			if (!isExpenseCategoryDuplicated(expCategoryName)) {
				this.expCategoryRepository.save(ExpCategoryEntity.builder()
						.expCategoryName(expCategoryName)
						.build());
				result = true;
			}
			else {
				throw new DuplicateKeyException("Duplicate Expense Category name");
			}
		}
		else {
			throw new IllegalArgumentException("Expense category name variable is null or empty");
		}
		return result;
	}

	public boolean isExpenseCategoryDuplicated(String expCategoryName) {
		return this.expCategoryRepository.findByExpCategoryName(expCategoryName).isPresent();
	}

}
