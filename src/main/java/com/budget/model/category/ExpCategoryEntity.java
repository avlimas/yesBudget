package com.budget.model.category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.budget.model.expense.ExpenseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "EXP_CATEGORY")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpCategoryEntity implements Serializable {
	
	private static final long serialVersionUID = 8752181481018153103L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXP_CATEGORY_ID")
	private long expCategoryId;

	@Column(name = "EXP_CATEGORY_NAME")
	private String expCategoryName;
	
	@OneToMany(
	        mappedBy = "expCategory",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	@Builder.Default
	private List<ExpenseEntity> expenses = new ArrayList<>();
}
