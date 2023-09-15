package com.example.tables.day2.model;

import java.sql.Date;

public class TempViewLoan {
	private String loan_id;
	private Date card_issue_date;
	private String loan_type;
	private int duration_in_years;
	public String getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(String loan_id) {
		this.loan_id = loan_id;
	}
	public Date getCard_issue_date() {
		return card_issue_date;
	}
	public void setCard_issue_date(Date card_issue_date) {
		this.card_issue_date = card_issue_date;
	}
	public String getLoan_type() {
		return loan_type;
	}
	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}
	public int getDuration_in_years() {
		return duration_in_years;
	}
	public void setDuration_in_years(int duration_in_years) {
		this.duration_in_years = duration_in_years;
	}
	
	public TempViewLoan() {
		
	}
		

}
