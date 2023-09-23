package com.example.loanappbackend.dto;

import java.time.LocalDate;
import java.util.Set;

import com.example.loanappbackend.model.EmployeeItemIssueDetails;
import com.example.loanappbackend.model.EmployeeLoanCard;


public class EmployeeDto {
	
	private String employeeId;	
	
	private String name;
	
	private String password;
	
	private String designation;
	
	private String department;
	
	private Character gender;
	
	private LocalDate dateOfBirth;
	
	private LocalDate dateOfJoining;
	
	private Set<EmployeeLoanCard> employeeLoanCard;
	
	private Set<EmployeeItemIssueDetails> employeeItemIssueDetails;
	
	private String isAdmin;
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Set<EmployeeLoanCard> getEmployeeLoanCard() {
		return employeeLoanCard;
	}

	public void setEmployeeLoanCard(Set<EmployeeLoanCard> employeeLoanCard) {
		this.employeeLoanCard = employeeLoanCard;
	}

	public Set<EmployeeItemIssueDetails> getEmployeeItemIssueDetails() {
		return employeeItemIssueDetails;
	}

	public void setEmployeeItemIssueDetails(Set<EmployeeItemIssueDetails> employeeItemIssueDetails) {
		this.employeeItemIssueDetails = employeeItemIssueDetails;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	
	
	

}
