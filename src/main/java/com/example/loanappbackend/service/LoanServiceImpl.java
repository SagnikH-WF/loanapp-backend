package com.example.loanappbackend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		Loan recievedLoan=new Loan();
		recievedLoan.setLoanId(null);
		return recievedLoan;
	}
	
	@Override
	public List<Loan> getAllLoans() {
		return loanRepository.findAll();
	}
	
	@Override
	public ResponseEntity<Loan> updateLoanById(String id, Loan loan) {
		try {
    		Optional<Loan> loanFromRepository = loanRepository.findById(id);
    		loanFromRepository.orElseThrow();
    		
    		Loan updatedLoan = loanRepository.save(loan);
    		return ResponseEntity.ok(updatedLoan);    		
    	} catch (NoSuchElementException e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }  catch(Exception e) {
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@Override
	public ResponseEntity<?> deleteLoanById(String id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		if(loanRepository.findById(id).isPresent()) {
			loanRepository.deleteById(id);
			return ResponseEntity.ok("Loan deleted successfully");
		}
		map.put("message", "Loan not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}
	
	@Override	
	public List<String> getDistinctLoanTypes() {
		return loanRepository.getDistinctLoanTypes();
	}
}
