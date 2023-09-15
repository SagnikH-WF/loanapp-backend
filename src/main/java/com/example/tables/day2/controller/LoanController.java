package com.example.tables.day2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tables.day2.model.LoanMaster;
import com.example.tables.day2.repository.LoanRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class LoanController {
	@Autowired
	LoanRepository loanRepository;
	
	@PostMapping("/loan")
	public String createNewloanloyee( @RequestBody LoanMaster loan) {
		loanRepository.save(loan);

		return "Loan details entered in the database"; 
	
	}
	
	@GetMapping("/loan")
	public List<LoanMaster> getAllLoanDetails(){
		List<LoanMaster> loanList = new ArrayList<>();
		loanRepository.findAll().forEach(loanList::add);
		return loanList;
	}
	
	@GetMapping("/loan/{loanId}")
	public LoanMaster getLoanById(@PathVariable String loanId){
		Optional<LoanMaster> loan = loanRepository.findById(loanId);
		if(loan.isPresent()) {
			return loan.get();
		}
		System.out.println("Id not found in database");
		return new LoanMaster();
	}
	

}
