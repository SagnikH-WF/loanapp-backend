package com.example.loanappbackend.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@Column(length = 6)
	private String itemId;
	
	@Column(length = 25, nullable=false)
	private String itemDescription;
	
	@Column(nullable = false)
	private char itemStatus;
	
	@Column(length = 25, nullable=false)
	private String itemMake;
	
	@Column(length = 20, nullable=false)
	private String itemCategory;
	
	@Column(length = 10, nullable = false)
	private int itemValuation;
	
	@OneToMany(mappedBy="item", cascade=CascadeType.ALL)
	@JsonManagedReference(value="item2")
	private Set<EmployeeItemIssueDetails> employeeIssueDetails;
	

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(String itemId, String itemDescription, char itemStatus, String itemMake, String itemCategory,
			int itemValuation) {
		super();
		this.itemId = itemId;
		this.itemDescription = itemDescription;
		this.itemStatus = itemStatus;
		this.itemMake = itemMake;
		this.itemCategory = itemCategory;
		this.itemValuation = itemValuation;
	}

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
