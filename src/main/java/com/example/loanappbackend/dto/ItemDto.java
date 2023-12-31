package com.example.loanappbackend.dto;

import java.util.Set;

import com.example.loanappbackend.model.EmployeeItemIssueDetails;

public class ItemDto {
	
	
	private String itemId;
	
	private String itemDescription;
	
	private char itemStatus;
	
	private String itemMake;
	
	private String itemCategory;
	
	private int itemValuation;
	
	private Set<EmployeeItemIssueDetails> employeeIssueDetails;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public char getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(char itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getItemMake() {
		return itemMake;
	}

	public void setItemMake(String itemMake) {
		this.itemMake = itemMake;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public int getItemValuation() {
		return itemValuation;
	}

	public void setItemValuation(int itemValuation) {
		this.itemValuation = itemValuation;
	}

	public Set<EmployeeItemIssueDetails> getEmployeeIssueDetails() {
		return employeeIssueDetails;
	}

	public void setEmployeeIssueDetails(Set<EmployeeItemIssueDetails> employeeIssueDetails) {
		this.employeeIssueDetails = employeeIssueDetails;
	}
	
	
}
