package com.example.loanappbackend.service;

import java.util.List;

import com.example.loanappbackend.model.Item;

public interface ItemService {
	
	Item saveItem(Item item);
	
	List<String> getCategorySpecificItemMakes(String category);
	
	List<Item> getItemsForSpecficCategoryAndMake(String category, String make);
}
