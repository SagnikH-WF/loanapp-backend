package com.example.loanappbackend.service;

import java.util.List;

import com.example.loanappbackend.model.EmployeeIssueDetails;


public interface EmployeeIssueDetailsService {
	List<EmployeeIssueDetails> getEmployeeItems(String employeeId);

}


