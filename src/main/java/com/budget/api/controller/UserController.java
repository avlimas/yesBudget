package com.budget.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budget.api.request.UserPostRequest;
import com.budget.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping(value="/sign-up", consumes = "application/json")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserPostRequest userPostRequest) 
    {
    	boolean isCreated = this.userService.createUser(userPostRequest);
    	if (isCreated) {
    		HttpHeaders responseHeaders = new HttpHeaders();
    		responseHeaders.set("Location", "/api/v1/user/sign-up");
            return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).build();
		}
    	else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
}
