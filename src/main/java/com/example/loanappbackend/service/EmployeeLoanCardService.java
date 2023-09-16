package com.example.loanappbackend.service;

import java.util.List;

import com.example.loanappbackend.model.EmployeeLoanCard;
import com.example.loanappbackend.model.UserLoan;

public interface EmployeeLoanCardService {
	
	EmployeeLoanCard saveEmployeeLoanCard(EmployeeLoanCard employeeLoanCard);	
	
	List<UserLoan> findLoansByEmployeeId(String employeeId);
}
