package com.example.loanappbackend.model;

import java.time.LocalDate;
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
@Table(name="employee_issue_details")
public class EmployeeItemIssueDetails {

	@Id
	@Column(length = 6)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int issueId;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private LocalDate issueDate;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private LocalDate returnDate;
	
	@ManyToOne
	@JoinColumn(nullable=false, referencedColumnName="employeeId")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(nullable=false, referencedColumnName="itemId")
	private Item item;
	
	public EmployeeItemIssueDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeItemIssueDetails(LocalDate issueDate, LocalDate returnDate, Employee employee, Item item) {
		super();
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.employee = employee;
		this.item = item;
	}
	
	public int getIssueId() {
		return issueId;
	}
	
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}
	
	public LocalDate getIssueDate() {
		return issueDate;
	}
	
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	
	public LocalDate getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
}
