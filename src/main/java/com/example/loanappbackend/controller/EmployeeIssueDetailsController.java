package com.example.loanappbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanappbackend.model.EmployeeIssueDetails;

import com.example.loanappbackend.service.EmployeeIssueDetailsService;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeIssueDetailsController {
	 @Autowired
	 private EmployeeIssueDetailsService employeeIssueDetailsService;
	
	@GetMapping("/getEmployeeItems/{employeeId}")
	public List<EmployeeIssueDetails> getEmployeePurchasedItems(@PathVariable("employeeId") String employeeId){
		List<EmployeeIssueDetails> empItemViewSet = employeeIssueDetailsService.getEmployeeItems(employeeId);
		System.out.println(empItemViewSet);
		return empItemViewSet;

	}}
