package com.example.loanappbackend.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanappbackend.model.EmployeeIssueDetails;

import com.example.loanappbackend.repository.EmployeeIssueDetailsRepository;



@Service
public class EmployeeIssueDetailsServiceImpl implements EmployeeIssueDetailsService {

	@Autowired
	private EmployeeIssueDetailsRepository employeeIssueDetailsRepository;
	
	public List<EmployeeIssueDetails> getEmployeeItems(String employeeId)
	{
		return employeeIssueDetailsRepository.getEmployeeItems(employeeId);
	}
	
}
