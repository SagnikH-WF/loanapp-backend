package com.example.loanappbackend.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	private String issueId;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private java.util.Date issueDate;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private java.util.Date returnDate;
	
	@ManyToOne
	@JoinColumn(nullable=false, referencedColumnName="employeeId")
	private Employee employeeId;
	
	@ManyToOne
	@JoinColumn(nullable=false, referencedColumnName="itemId")
	private Item itemId;
	
	public EmployeeItemIssueDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeItemIssueDetails(String issueId, Date issueDate, Date returnDate, Employee employeeId, Item itemId) {
		super();
		this.issueId = issueId;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.employeeId = employeeId;
		this.itemId = itemId;
	}
	
	public String getIssueId() {
		return issueId;
	}
	
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	
	public java.util.Date getIssueDate() {
		return issueDate;
	}
	
	public void setIssueDate(java.util.Date issueDate) {
		this.issueDate = issueDate;
	}
	
	public java.util.Date getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(java.util.Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public Employee getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}
	
	public Item getItemId() {
		return itemId;
	}
	
	public void setItemId(Item itemId) {
		this.itemId = itemId;
	}
}
