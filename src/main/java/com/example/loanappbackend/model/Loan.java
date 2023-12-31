package com.example.loanappbackend.model;

import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="loan")
public class Loan {	
	@Id
	@Column(length=6)
	private String loanId;
	
	@Column(name="duration", nullable=false)
	@NotNull(message="Duration field cannot be empty")
	private int durationInYears;
	
	@Column(unique=true, length=15, nullable=false)
	@NotEmpty(message="LoanType field cannot be empty")
	private String loanType;

	@OnDelete(action=OnDeleteAction.CASCADE)
	@OneToMany(mappedBy="loan", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference(value="loan2")
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
