package com.example.loanappbackend.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.loanappbackend.model.Loan;

public interface LoanService {
	
	Loan saveLoan(Loan loan);
	
	Loan getLoanById(String id);
	
	List<Loan> getAllLoans();
	
	ResponseEntity<Loan> updateLoanById(String id, Loan loan);
	
	ResponseEntity<?> deleteLoanById(String id);
	
	List<String> getDistinctLoanTypes();
		
}
