package com.example.loanappbackend.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="loan")
public class Loan {	
	@Id
	@Column(length=6)
	private String loanId;
	
	@Column(name="duration", nullable=false)
	private int durationInYears;
	
	@Column(unique=true, length=15, nullable=false)
	private String loanType;

	@OneToMany(mappedBy="loanId", cascade=CascadeType.ALL)
	private Set<EmployeeLoanCard> employeeLoanCard;
	
	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Loan(String loanId, int durationInYears, String loanType) {
		super();
		this.loanId = loanId;
		this.durationInYears = durationInYears;
		this.loanType = loanType;
	}


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

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", durationInYears=" + durationInYears + ", loanType=" + loanType + "]";
	}
}
