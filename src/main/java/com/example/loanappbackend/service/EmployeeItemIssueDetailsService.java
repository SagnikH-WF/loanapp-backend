package com.example.loanappbackend.service;

import java.util.List;

import com.example.loanappbackend.model.EmployeeItemIssueDetails;
import com.example.loanappbackend.model.ItemIssue;

public interface EmployeeItemIssueDetailsService {
	
	EmployeeItemIssueDetails saveEmployeeItemIssueDetails(EmployeeItemIssueDetails employeeItemIssueDetails);
	
	List<ItemIssue> findItemsIssuedToEmployee(String employeeId);
}
