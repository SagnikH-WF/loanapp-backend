package com.example.loanappbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanappbackend.model.EmployeeItemIssueDetails;
import com.example.loanappbackend.repository.EmployeeItemIssueDetailsRepository;

@Service
public class EmployeeItemIssueDetailsServiceImpl implements EmployeeItemIssueDetailsService{
	@Autowired
	private EmployeeItemIssueDetailsRepository employeeItemIssueDetailsRepository;
	
	@Override
	public EmployeeItemIssueDetails saveEmployeeItemIssueDetails(EmployeeItemIssueDetails employeeItemIssueDetails) {
		return employeeItemIssueDetailsRepository.save(employeeItemIssueDetails);
	}
	
}
