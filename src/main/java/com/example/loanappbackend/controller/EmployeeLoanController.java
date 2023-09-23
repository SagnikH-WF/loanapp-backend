package com.example.loanappbackend.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanappbackend.dto.EmployeeLoanDto;
import com.example.loanappbackend.model.EmployeeLoan;
import com.example.loanappbackend.service.EmployeeLoanService;

import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeLoanController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	EmployeeLoanService employeeLoanService;
	
	@PostMapping("/applyLoan")
	public String applyForLoan(@Valid @RequestBody EmployeeLoanDto employeeLoanDto) {
		
		
		EmployeeLoan employeeLoan=modelMapper.map(employeeLoanDto, EmployeeLoan.class);
		return employeeLoanService.applyForLoan(employeeLoan);
		
	}
}
