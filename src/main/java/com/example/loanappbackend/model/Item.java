package com.example.loanappbackend.model;

import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@Column(length = 6)
	@NotEmpty(message="Item id cannot be null. Please enter some Id.")
	private String itemId;
	
	@Column(length = 25, nullable=false)
	@NotEmpty(message="Item Description cannot be null. Please enter some Description.")
	private String itemDescription;
	
	@Column(nullable = false)
	private char itemStatus;
	
	@Column(length = 25, nullable=false)
	@NotEmpty(message="Item make cannot be null. Please enter some make value.")
	private String itemMake;
	
	@Column(length = 20, nullable=false)
	@NotEmpty(message="Item category cannot be null. Please enter some category.")
	private String itemCategory;
	
	@Column(length = 10, nullable = false)
	@NotNull(message="Item valuation cannot be null. Please enter some value.")
	private int itemValuation;
	
	@OnDelete(action=OnDeleteAction.CASCADE)
	@OneToMany(mappedBy="item", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
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
