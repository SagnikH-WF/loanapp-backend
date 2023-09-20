package com.example.loanappbackend.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.loanappbackend.model.Item;

public interface ItemService {
	
	Item saveItem(Item item);
	
	List<String> getCategorySpecificItemMakes(String category);
	
	List<Item> getItemsForSpecficCategoryAndMake(String category, String make);
	
	List<Item> getAllItemsDetails();
	
	Item getItemById(String id);
	
	ResponseEntity<Item> updateItemById(String id, Item item);
	
	ResponseEntity<?> deleteItemById(String id);
	
}
