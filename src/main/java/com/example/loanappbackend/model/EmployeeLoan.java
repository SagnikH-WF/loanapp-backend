package com.example.loanappbackend.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmployeeLoan {
	
	@NotEmpty(message="Employee Id cannot be empty. Please enter some Id.")
	private String employeeId;
	
	@NotEmpty(message="Item Category cannot be empty. Please enter some Catgory.")
	private String itemCategory;
	
	@NotEmpty(message="Item Description cannot be empty. Please enter some Description.")
	private String itemDescription;
	
	@NotNull(message="Item Valuation cannot be null. Please enter some value.")
	private int itemValuation;
	
	@NotEmpty(message="Item Make cannot be empty. Please enter some make.")
	private String itemMake;

	public EmployeeLoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeLoan(String employeeId, String itemCategory, String itemDescription, int itemValuation, String itemMake) {
		super();
		this.employeeId= employeeId;
		this.itemCategory = itemCategory;
		this.itemDescription = itemDescription;
		this.itemValuation = itemValuation;
		this.itemMake = itemMake;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public int getItemValuation() {
		return itemValuation;
	}

	public void setItemValuation(int itemValuation) {
		this.itemValuation = itemValuation;
	}

	public String getItemMake() {
		return itemMake;
	}

	public void setItemMake(String itemMake) {
		this.itemMake = itemMake;
	}
}
