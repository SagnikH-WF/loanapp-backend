package com.example.loanappbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanappbackend.model.EmployeeLoanCard;
import com.example.loanappbackend.service.EmployeeLoanCardService;

@RestController
public class EmployeeLoanCardController {
	
	@Autowired
	private EmployeeLoanCardService employeeLoanCardService;
	
	@PostMapping("/applyLoan")
	public EmployeeLoanCard saveEmployeeLoanCard(@RequestBody EmployeeLoanCard employeeLoanCard) {
		return employeeLoanCardService.saveEmployeeLoanCard(employeeLoanCard);
	}
}
