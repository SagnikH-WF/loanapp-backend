package com.example.loanappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanappbackend.model.ItemIssue;
import com.example.loanappbackend.service.EmployeeItemIssueDetailsService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeItemIssueDetailsController {
	
	@Autowired
	EmployeeItemIssueDetailsService employeeItemIssueDetailsService;
	
	@GetMapping("/itemsIssued")
	public List<ItemIssue> gettemsIssuedToEmployee(@RequestParam String employeeId) {
		return employeeItemIssueDetailsService.findItemsIssuedToEmployee(employeeId);
	}
}
