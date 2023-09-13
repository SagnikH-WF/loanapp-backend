package com.example.loanappbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanappbackend.model.Item;
import com.example.loanappbackend.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}
	
	@Override
	public List<String> getCategorySpecificItemMakes(String category) {
		return itemRepository.getCategorySpecificItemMakes(category);
	}
	
	@Override
	public List<Item> getItemsForSpecficCategoryAndMake(String category, String make) {
		return itemRepository.getItemsForSpecficCategoryAndMake(category, make);
	}
}
