package com.example.loanappbackend.service;

import java.util.ArrayList;
import java.util.List;

import com.example.loanappbackend.model.Loan;

public interface LoanService {
	
	Loan saveLoan(Loan loan);
	
	Loan getLoanById(String id);
	
	List<Loan> getAllLoans();
	
//	Loan updateLoanById(String id, Loan loan);
	
	String deleteLoanById(String id);
	
	String updateLoanById(String loanId,Loan loan);
	
	List<String> getDistinctLoanTypes();
		
}
