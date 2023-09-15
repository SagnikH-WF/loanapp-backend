package com.example.loanappbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanappbackend.model.EmployeeLoan;
import com.example.loanappbackend.service.EmployeeLoanService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeLoanController {
	
	@Autowired
	EmployeeLoanService employeeLoanService;
	
	@PostMapping("/applyLoan")
	public String applyForLoan(@RequestBody EmployeeLoan employeeLoan) {
		return employeeLoanService.applyForLoan(employeeLoan);
	}
}
