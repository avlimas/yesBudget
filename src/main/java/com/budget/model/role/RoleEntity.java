package com.budget.model.role;

import java.io.Serializable;
import java.time.ZonedDateTime;
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

import com.budget.model.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "APP_ROLE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity implements Serializable {
	
	private static final long serialVersionUID = 2512229048017644512L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private long roleId;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Column(name = "CREATED_IN")
	private ZonedDateTime createdIn;
	
	@OneToMany(
	        mappedBy = "userRoles",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	@Builder.Default
	private List<UserEntity> users = new ArrayList<>();
}
