package com.example.loanappbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanappbackend.dto.LoanDto;
import com.example.loanappbackend.exceptionHandler.DuplicateDataFoundException;
import com.example.loanappbackend.model.Loan;
import com.example.loanappbackend.service.LoanService;

import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin(origins="http://localhost:3000")
public class LoanController {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LoanService loanService;
	
	@PostMapping("/loan")
	public LoanDto saveLoan(@Valid @RequestBody LoanDto loanDto) throws DuplicateDataFoundException {
		Loan loan=modelMapper.map(loanDto,Loan.class);
		System.out.println("Loan id: "+loan.getLoanId());
		
		LoanDto isLoanExisted=getLoanById(loan.getLoanId());
		if(isLoanExisted.getLoanId()!=null) {
			throw new DuplicateDataFoundException("This Loan Id already exists");
		}

		return modelMapper.map(loanService.saveLoan(loan),LoanDto.class);
	}
	
	@GetMapping("/loan")
	public List<LoanDto> getLoanList() {	
		List<LoanDto> allLoansList=new ArrayList<>();
		List<Loan> allLoans=loanService.getAllLoans();
		for(Loan l:allLoans) {
			allLoansList.add(modelMapper.map(l, LoanDto.class));
		}
		return allLoansList;
	}
	
	@GetMapping("/loan/{id}")
	public LoanDto getLoanById(@Valid @PathVariable("id") String id) {
		return modelMapper.map(loanService.getLoanById(id),LoanDto.class);
	}
	
	@PutMapping("/loan/{id}")
	public ResponseEntity<LoanDto> updateLoan(@Valid @PathVariable("id") String id,@Valid @RequestBody LoanDto loanDto) {
		
		Loan loanNeedsToUpdate=modelMapper.map(loanDto, Loan.class);
		Loan updatedLoan=loanService.updateLoanById(id, loanNeedsToUpdate).getBody();
		
		return ResponseEntity.ok(modelMapper.map(updatedLoan, LoanDto.class));
	}
	
	@DeleteMapping("/loan/{id}")
	public ResponseEntity<?> deleteLoan(@Valid @PathVariable("id") String id) {
		return loanService.deleteLoanById(id);
	}
	
	@GetMapping("/loan/distinctLoanTypes")
	public List<String> getDistinctLoanTypes() {
		return loanService.getDistinctLoanTypes();
	}
}
