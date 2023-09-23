package com.example.loanappbackend.dto;

import java.time.LocalDate;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.Loan;

public class EmployeeLoanCardDto {

	private int employeeCardId;
	
	private LocalDate cardIssueDate;
	
	private Employee employee;
	
	private Loan loan;

	public int getEmployeeCardId() {
		return employeeCardId;
	}

	public void setEmployeeCardId(int employeeCardId) {
		this.employeeCardId = employeeCardId;
	}

	public LocalDate getCardIssueDate() {
		return cardIssueDate;
	}

	public void setCardIssueDate(LocalDate cardIssueDate) {
		this.cardIssueDate = cardIssueDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	
	
	
}
