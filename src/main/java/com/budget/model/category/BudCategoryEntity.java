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

import com.budget.model.budget.BudgetEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "BUD_CATEGORY")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudCategoryEntity implements Serializable {

	private static final long serialVersionUID = 9158921418753215962L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BUD_CATEGORY_ID")
	private long budCategoryId;

	@Column(name = "BUD_CATEGORY_NAME")
	private String budCategoryName;
	
	@OneToMany(
	        mappedBy = "budCategory",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	@Builder.Default
	private List<BudgetEntity> budgets = new ArrayList<>();
}
