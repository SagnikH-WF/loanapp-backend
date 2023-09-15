package com.example.tables.day2.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class EmployeeCard {
	
	@Id
	@Column(length=6)
	private String card_id;
	
	@Column(length=6)
	private String empId;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id")
	private LoanMaster loan;

	@Column
	private Date issue_date;
	
	public EmployeeCard() {
		
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getempId() {
		return empId;
	}

	public void setempId(String empId) {
		this.empId = empId;
	}

	public LoanMaster getLoan() {
		return loan;
	}

	public void setLoan(LoanMaster loan) {
		this.loan = loan;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}
	

}
