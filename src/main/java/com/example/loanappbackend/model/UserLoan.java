package com.example.loanappbackend.model;

import java.time.LocalDate;

public class UserLoan {
	
	private String loanId;
	
	private String loanType;
	
	private int duration;

	private LocalDate cardIssueDate;
	
	public UserLoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLoan(String loanId, String loanType, int duration, LocalDate cardIssueDate) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
		this.duration = duration;
		this.cardIssueDate = cardIssueDate;
	}
	
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

	public void setCard_issue_date(LocalDate cardIssueDate) {
		this.cardIssueDate = cardIssueDate;
	}
	
}
