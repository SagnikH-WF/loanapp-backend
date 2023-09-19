package com.example.loanappbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.loanappbackend.model.Loan;
import com.example.loanappbackend.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Override
	public Loan saveLoan(Loan loan) {
		return loanRepository.save(loan);
	}
	
	@Override
	public Loan getLoanById(String id) {
		Optional<Loan> loan = loanRepository.findById(id);
		if(loan.isPresent()) {
			return loan.get();
		}
		return null;
	}
	
	@Override
	public List<Loan> getAllLoans() {
		List<Loan> loans = loanRepository.findAll();		
		
		return loans;
	}
	
	@Override
	public String deleteLoanById(String id) {
		if(loanRepository.findById(id).isPresent()) {
			loanRepository.deleteById(id);
			return "Loan deleted successfully";
		}
		return "No such loan in the database";
	}
	
	@Override	
	public List<String> getDistinctLoanTypes() {
		return loanRepository.getDistinctLoanTypes();
	}
	
	@Override
	public String updateLoanById(String loanId,Loan loan) {
		String res = "";
		Loan l;
		Optional<Loan> opt = loanRepository.findById(loanId);
		if(opt.isPresent()) {
			l = opt.get();
			l.setDurationInYears(loan.getDurationInYears());
			l.setLoanType(loan.getLoanType());
			loanRepository.save(l);
			res = "Loan updated successfully";
		}
		else res = "Loan not found";
		return res;
	}
}
