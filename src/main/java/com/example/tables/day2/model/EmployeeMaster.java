package com.example.tables.day2.model;

import java.sql.Date;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
public class EmployeeMaster {
	@Id
	private String employee_id;
	
	@Column(nullable=false)
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(nullable=false)
	private String employee_name;
	
	@Column(nullable=false)
	private String designation;
	
	@Column(nullable=false)
	private String department;
	
	@Column(nullable=false)
	private String gender;
	
	@Column(nullable=false)
	private Date date_of_birth;
	
	@Column(nullable=false)
	private Date date_of_joining;

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public Date getDate_of_joining() {
		return date_of_joining;
	}

	public void setDate_of_joining(Date date_of_joining) {
		this.date_of_joining = date_of_joining;
	}

	
	
	public EmployeeMaster() {
		
	}
	
	

}
