package com.example.loanappbackend.model;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	private int employeeCardId;
	
	@Column(nullable=false)	
	private LocalDate cardIssueDate;
	
	@ManyToOne
	@JoinColumn(nullable=false, referencedColumnName="employeeId")
	@JsonBackReference(value="loan")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(nullable=false, referencedColumnName="loanId")
	@JsonBackReference(value="loan2")
	private Loan loan;

	public EmployeeLoanCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeLoanCard(LocalDate cardIssueDate, Employee employee, Loan loan) {
		super();		
		this.cardIssueDate = cardIssueDate;
		this.employee = employee;
		this.loan = loan;
	}

	public long getEmployeeCardId() {
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

	@Override
	public String toString() {
		return "EmployeeLoanCard [employeeCardId=" + employeeCardId + ", cardIssueDate=" + cardIssueDate
				+ ", employee=" + employee + ", loan=" + loan + "]";
	}
	
}