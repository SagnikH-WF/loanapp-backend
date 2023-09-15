package com.example.loanappbackend.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="employee_card_details")
public class EmployeeLoanCard {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long employeeCardId;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private java.time.LocaDate cardIssueDate;
	
	@ManyToOne
	@JoinColumn(nullable=false, referencedColumnName="employeeId")
	private Employee employeeId;
	
	@ManyToOne
	@JoinColumn(nullable=false, referencedColumnName="loanId")
	private Loan loanId;

	public EmployeeLoanCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeLoanCard(LocalDate cardIssueDate, Employee employeeId, Loan loanId) {
		super();		
		this.cardIssueDate = cardIssueDate;
		this.employeeId = employeeId;
		this.loanId = loanId;
	}

	public long getEmployeeCardId() {
		return employeeCardId;
	}

	public void setEmployeeCardId(long employeeCardId) {
		this.employeeCardId = employeeCardId;
	}

	public java.util.Date getCardIssueDate() {
		return cardIssueDate;
	}

	public void setCardIssueDate(java.util.Date cardIssueDate) {
		this.cardIssueDate = cardIssueDate;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public Loan getLoanId() {
		return loanId;
	}

	public void setLoanId(Loan loanId) {
		this.loanId = loanId;
	}

	@Override
	public String toString() {
		return "EmployeeLoanCard [employeeCardId=" + employeeCardId + ", cardIssueDate=" + cardIssueDate
				+ ", employeeId=" + employeeId + ", loanId=" + loanId + "]";
	}
	
}