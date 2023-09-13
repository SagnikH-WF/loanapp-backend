package com.example.loanappbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanappbackend.model.Loan;
import com.example.loanappbackend.service.LoanService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@PostMapping("/loan")
	public Loan saveLoan(@RequestBody Loan loan) {
		return loanService.saveLoan(loan);
	}
	
	@GetMapping("/loan")
	public List<Loan> getLoanList() {		
		return loanService.getAllLoans();
	}
	
	@GetMapping("/loan/{id}")
	public Loan getLoanById(@PathVariable("id") String id) {
		return loanService.getLoanById(id);
	}
	
	@DeleteMapping("/loan/{id}")
	public String deleteLoan(@PathVariable("id") String id) {
		return loanService.deleteLoanById(id);
	}
	
	@GetMapping("/loan/distinctLoanTypes")
	public List<String> getDistinctLoanTypes() {
		return loanService.getDistinctLoanTypes();
	}
}
