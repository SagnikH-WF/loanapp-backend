package com.example.loanappbackend.dto;

import java.time.LocalDate;

public class UserLoanDto {

	private String loanId;
	
	private String loanType;
	
	private int duration;

	private LocalDate cardIssueDate;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LocalDate getCardIssueDate() {
		return cardIssueDate;
	}

	public void setCardIssueDate(LocalDate cardIssueDate) {
		this.cardIssueDate = cardIssueDate;
	}
	
	
}
