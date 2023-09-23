package com.example.loanappbackend.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@DynamicInsert
@Entity
@Table(name="employee")
public class Employee {	
	
	@Id
	@Column(length=6)
	@NotEmpty(message="Employee Id cannot be null. Please enter some Id.")
	private String employeeId;	

	@Column(nullable=false, length=20)
	@NotEmpty(message="Employee name cannot be null. Please enter some name.")
	private String name;
	
	@Column(nullable=false)
	@NotEmpty(message="Password cannot be null. Please enter some Password.")
	private String password;
	
	@Column(nullable=false, length=25)
	@NotEmpty(message="Employee designation cannot be null. Please enter some designation.")
	private String designation;
	
	@Column(nullable=false, length=25)
	@NotEmpty(message="Employee department cannot be null. Please enter some department.")
	private String department;
	
	@Column(nullable=false, length=1)
	private Character gender;
	
	@Column
	private LocalDate dateOfBirth;
	
	@Column
	private LocalDate dateOfJoining;
	
	@OnDelete(action=OnDeleteAction.CASCADE)
	@OneToMany(mappedBy="employee", cascade=CascadeType.ALL)
	@JsonManagedReference(value="loan")
	private Set<EmployeeLoanCard> employeeLoanCard;
	
	@OnDelete(action=OnDeleteAction.CASCADE)
	@OneToMany(mappedBy="employee", cascade=CascadeType.ALL)
	@JsonManagedReference(value="item")
	private Set<EmployeeItemIssueDetails> employeeItemIssueDetails;
	
	
	@Column(columnDefinition = "varchar(255) default 'no'")
	private String isAdmin;
	
	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String employeeId, String name, String designation, String department, Character gender,
			LocalDate dateOfBirth, LocalDate dateOfJoining) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.designation = designation;
		this.department = department;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
	}
	
	@Override
	public String toString() {
		return "Employee [dateOfBirth=" + dateOfBirth + ", dateOfJoining=" + dateOfJoining + ", department="
				+ department + ", designation=" + designation + ", employeeId=" + employeeId + ", gender=" + gender
				+ ", name=" + name + "]";
	}

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

	public void setDateOfBirth(LocalDate LocalDateOfBirth) {
		this.dateOfBirth = LocalDateOfBirth;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate LocalDateOfJoining) {
		this.dateOfJoining = LocalDateOfJoining;
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
}
