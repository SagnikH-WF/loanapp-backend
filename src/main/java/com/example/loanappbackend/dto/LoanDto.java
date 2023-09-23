package com.example.loanappbackend.dto;

import java.util.Set;

import com.example.loanappbackend.model.EmployeeLoanCard;

public class LoanDto {

	private String loanId;
	
	private int durationInYears;
	
	private String loanType;
	
	private Set<EmployeeLoanCard> employeeLoanCard;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public int getDurationInYears() {
		return durationInYears;
	}

	public void setDurationInYears(int durationInYears) {
		this.durationInYears = durationInYears;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Set<EmployeeLoanCard> getEmployeeLoanCard() {
		return employeeLoanCard;
	}

	public void setEmployeeLoanCard(Set<EmployeeLoanCard> employeeLoanCard) {
		this.employeeLoanCard = employeeLoanCard;
	}
	
	
}
