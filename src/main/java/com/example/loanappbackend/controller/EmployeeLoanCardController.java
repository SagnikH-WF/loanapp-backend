package com.example.loanappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanappbackend.model.EmployeeLoanCard;
import com.example.loanappbackend.model.UserLoan;
import com.example.loanappbackend.service.EmployeeLoanCardService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeLoanCardController {
	
	@Autowired
	private EmployeeLoanCardService employeeLoanCardService;
	
	@PostMapping("/loanCard")
	public EmployeeLoanCard saveEmployeeLoanCard(@RequestBody EmployeeLoanCard employeeLoanCard) {
		return employeeLoanCardService.saveEmployeeLoanCard(employeeLoanCard);
	}
	
	@GetMapping("/loanCard")
	public List<UserLoan> findLoansByEmployeeId(String employeeId) {
		return employeeLoanCardService.findLoansByEmployeeId(employeeId);
	}
}
