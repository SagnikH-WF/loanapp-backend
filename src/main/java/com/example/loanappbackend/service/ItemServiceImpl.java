package com.example.loanappbackend.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanappbackend.model.Item;
import com.example.loanappbackend.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}
	
	@Override
	public Item getItemById(String itemId) {
		return itemRepository.findItemByItemId(itemId);
	}
	@Override
	public List<String> getCategorySpecificItemMakes(String category) {
		return itemRepository.getCategorySpecificItemMakes(category);
	}
	
	@Override
	public List<Item> getItemsForSpecficCategoryAndMake(String category, String make) {
		return itemRepository.getItemsForSpecficCategoryAndMake(category, make);
	}
	
	@Override
	public List<Item> getAllItemsDetails()
	{
		return itemRepository.findAll();
	}
	@Transactional
    public void deleteItemByItemId(String itemId) {
        itemRepository.deleteByItemId(itemId);
    }
	
	@Override
	public String updateItemById(String itemId,  Item item) {
		String res = "";
		Optional<Item> opt = itemRepository.findById(itemId);
		if(opt.isPresent()) {
			Item l = opt.get();
			l.setItemCategory(item.getItemCategory());
			l.setItemDescription(item.getItemDescription());
			l.setItemMake(item.getItemMake());
			l.setItemStatus(item.getItemStatus());
			l.setItemValuation(item.getItemValuation());
			itemRepository.save(l);
			res = "Item Updated successfully";
		}
		else res ="Item not present";
		return res;
		
	}
}
