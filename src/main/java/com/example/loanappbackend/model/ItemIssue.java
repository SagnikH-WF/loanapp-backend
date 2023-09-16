package com.example.loanappbackend.model;

public class ItemIssue {
	
	private int issueId;
	
	private String itemDescription;
	
	private char itemStatus;
	
	private String itemMake;
	
	private String itemCategory;
	
	private int itemValuation;
	
	public ItemIssue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemIssue(int issueId, String itemDescription, char itemStatus, String itemMake, String itemCategory,
			int itemValuation) {
		super();
		this.issueId = issueId;
		this.itemDescription = itemDescription;
		this.itemStatus = itemStatus;
		this.itemMake = itemMake;
		this.itemCategory = itemCategory;
		this.itemValuation = itemValuation;
	}
	
	public int getIssueId() {
		return issueId;
	}
	
	public void setIssueId(int issue_id) {
		this.issueId = issue_id;
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
}