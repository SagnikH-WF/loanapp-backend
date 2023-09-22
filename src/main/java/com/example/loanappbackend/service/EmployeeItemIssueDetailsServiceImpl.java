package com.example.loanappbackend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanappbackend.model.EmployeeItemIssueDetails;
import com.example.loanappbackend.model.ItemIssue;
import com.example.loanappbackend.repository.EmployeeItemIssueDetailsRepository;

@Service
public class EmployeeItemIssueDetailsServiceImpl implements EmployeeItemIssueDetailsService{
	
	@Autowired
	private EmployeeItemIssueDetailsRepository employeeItemIssueDetailsRepository;
	
	@Override
	public EmployeeItemIssueDetails saveEmployeeItemIssueDetails(EmployeeItemIssueDetails employeeItemIssueDetails) {
		return employeeItemIssueDetailsRepository.save(employeeItemIssueDetails);
	}
	
	@Override
	public List<ItemIssue> findItemsIssuedToEmployee(String employeeId) {
		LocalDate todayDate = LocalDate.now();
		return employeeItemIssueDetailsRepository.findItemsIssuedToEmployee(employeeId, todayDate);
	}
	
}
