package com.example.tables.day2.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class LoanMaster {
	@Id
	@Column(length=6,nullable=false)
	private String loan_id;
	
	@Column(length=15)
	private String loan_type;
	
	@Column(nullable=false)
	private int duration_in_years;
	
	@OneToMany(mappedBy = "loan", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	private List<EmployeeCard> emp_cards;

	public List<EmployeeCard> getEmp_cards() {
		return emp_cards;
	}

	public void setEmp_cards(List<EmployeeCard> emp_cards) {
		this.emp_cards = emp_cards;
	}

	public String getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(String loan_id) {
		this.loan_id = loan_id;
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
	
	public LoanMaster() {
		
	}
	
}
