package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.Loan;
import com.example.demo.repository.LoanRepo;
import com.example.demo.service.LoanService;

@RestController
public class LoanController {
	
	@Autowired
	LoanService ls;

	
	@GetMapping("/showmessage")
	public String show() {
		return "welcome";
	}
	@PostMapping("/saveloan")
	public String saveLoan(@Valid @RequestBody Loan l) {
		
	System.out.println("Entering post method");
		String result="";
		Loan obj=ls.saveLoanDetails(l);
		if(obj!=null) {
			result="Loan details saved successfully";
		}
		else {
			result="Error in saving loan details";
		}
		System.out.println("Entering post method");
		return result;
	}

}
