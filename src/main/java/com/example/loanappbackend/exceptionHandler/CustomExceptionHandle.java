package com.example.loanappbackend.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
		public @ResponseBody ErrorResponse handleNoDataFoundException(NoDataFoundException ex) {
			return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		}
}