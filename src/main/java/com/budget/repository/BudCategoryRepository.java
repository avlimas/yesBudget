package com.budget.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.budget.model.category.BudCategoryEntity;

@Repository
public interface BudCategoryRepository  extends JpaRepository<BudCategoryEntity, Long> {

	Optional<BudCategoryEntity> findByBudCategoryName(String budCategoryName);
}
