package com.budget.model.budget;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.budget.model.category.BudCategoryEntity;
import com.budget.model.expense.ExpenseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "BUDGET")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetEntity implements Serializable {

	private static final long serialVersionUID = -6309737581570287725L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BUDGET_ID")
	private long budgetId;
	
	@Column(name = "BUDGET_NAME")
	private String budgetName;

	@Column(name = "BUDGET_AMOUNT")
	private BigDecimal budgetAmount;
	
	@Column(name = "BUDGET_CURRENCY", columnDefinition = "char(3)")
	private String budgetCurrency;
	
	@Column(name = "BUDGET_MONTH")
	private int budgetMonth;

	@ManyToOne(fetch = FetchType.LAZY)
	private BudCategoryEntity budCategory;
	
	@OneToMany(
	        mappedBy = "budget",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	@Builder.Default
	private List<ExpenseEntity> expenses = new ArrayList<>();
}
