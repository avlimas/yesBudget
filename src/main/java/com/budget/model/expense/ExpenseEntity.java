package com.budget.model.expense;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.budget.model.budget.BudgetEntity;
import com.budget.model.category.ExpCategoryEntity;
import com.budget.model.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "EXPENSE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseEntity implements Serializable {

	private static final long serialVersionUID = -1830355516170853577L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXPENSE_ID")
	private long expenseId;
	
	@Column(name = "EXPENSE_NAME")
	private String expenseName;
	
	@Column(name = "EXPENSE_AMOUNT")
	private BigDecimal expenseAmount;
	
	@Column(name = "EXPENSE_DATE")
	private ZonedDateTime expenseDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private BudgetEntity budget;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity reporter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ExpCategoryEntity expCategory;
}
