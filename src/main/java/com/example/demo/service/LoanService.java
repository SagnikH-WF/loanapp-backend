package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modal.Loan;
import com.example.demo.repository.LoanRepo;

@Service
public class LoanService {
	
	@Autowired
	LoanRepo repo;
	public Loan saveLoanDetails(Loan l) {
		Loan obj=repo.save(l);
		return obj;
	}

}
