package com.budget.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.budget.model.category.ExpCategoryEntity;

@Repository
public interface ExpCategoryRepository extends JpaRepository<ExpCategoryEntity, Long> {

	Optional<ExpCategoryEntity> findByExpCategoryName(String expCategoryName);
}
