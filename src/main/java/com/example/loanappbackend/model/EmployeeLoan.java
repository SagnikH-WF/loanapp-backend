package com.example.loanappbackend.model;

public class EmployeeLoan {
	private String employeeId;
	
	private String itemCategory;
	
	private String itemDescription;
	
	private int itemValuation;
	
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
