package com.example.loanappbackend.exceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandle {
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
	
			Map<String,String> resp=new HashMap();
			ex.getBindingResult().getAllErrors().forEach((error)->{
				String fieldname=((FieldError)error).getField();
				String message=error.getDefaultMessage();
				resp.put(fieldname, message);
			});
			return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		}
		
		
		@ExceptionHandler(value = ResourceNotFoundException.class)
		//@ResponseStatus(HttpStatus.NOT_FOUND)
		public @ResponseBody ErrorResponse handleResoureNotFoundException(ResourceNotFoundException ex) {
			return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		}
		
		@ExceptionHandler(value = NoDataFoundException.class)
		@ResponseStatus(HttpStatus.NOT_FOUND)
		public ResponseEntity<ErrorResponse> handleNoDataFoundException(NoDataFoundException ex) {
			ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(value = DuplicateDataFoundException.class)
		@ResponseStatus(HttpStatus.FOUND)
		public ResponseEntity<ErrorResponse> handleDuplicateDataFoundException(DuplicateDataFoundException ex) {
			ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
	        return new ResponseEntity<>(errorResponse, HttpStatus.FOUND);
		}
		
		@ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
		@ResponseStatus(HttpStatus.FOUND)
		public ResponseEntity<ErrorResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
			ErrorResponse errorResponse = new ErrorResponse(" already existed.");
	        return new ResponseEntity<>(errorResponse, HttpStatus.FOUND);
		} 
}
