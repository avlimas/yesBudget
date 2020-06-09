package com.budget.api.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPostRequest {

	@NotBlank(message = "Username is mandatory")
	@JsonProperty("userName")
	private String userName;

	@NotBlank(message = "Full name is mandatory")
	@JsonProperty("fullName")
	private String fullName;

	@NotBlank(message = "Email is mandatory")
	@JsonProperty("userEmail")
	private String userEmail;
	
	@JsonProperty("roleName")
	private String roleName;
}
