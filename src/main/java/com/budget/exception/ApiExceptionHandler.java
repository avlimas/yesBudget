package com.budget.exception;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateKeyException(DuplicateKeyException e){
        Map<String, Object > errorMap = new HashMap<>();

        errorMap.put("Timestamp", ZonedDateTime.now());
        errorMap.put("Error Message", e.getLocalizedMessage());
        errorMap.put("Status", HttpStatus.BAD_REQUEST.value());
        
        log.error(e.getLocalizedMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
	
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e){
        Map<String, Object > errorMap = new HashMap<>();

        errorMap.put("Timestamp", ZonedDateTime.now());
        errorMap.put("Error Message", e.getLocalizedMessage());
        errorMap.put("Status", HttpStatus.BAD_REQUEST.value());

        log.error(e.getLocalizedMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
	
	@ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, Object>> handleIOException(IOException e){
        Map<String, Object > errorMap = new HashMap<>();

        errorMap.put("Timestamp", ZonedDateTime.now());
        errorMap.put("Error Message", e.getLocalizedMessage());
        errorMap.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.error(e.getLocalizedMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
    }
}
