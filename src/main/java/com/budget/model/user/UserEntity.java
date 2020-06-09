package com.budget.model.user;

import java.io.Serializable;
import java.time.ZonedDateTime;
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

import com.budget.model.expense.ExpenseEntity;
import com.budget.model.role.RoleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "APP_USER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -1374591101661768179L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private long userId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Column(name = "USER_EMAIL")
	private String userEmail;

	@Column(name = "CREATED_IN")
	private ZonedDateTime createdIn;
	
    @ManyToOne(fetch = FetchType.LAZY)
	private RoleEntity userRoles;
	
	@OneToMany(
	        mappedBy = "reporter",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	@Builder.Default
	private List<ExpenseEntity> expenses = new ArrayList<>();
}
