package com.example.loanappbackend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@Override
	public List<Item> getAllItemsDetails() {
		return itemRepository.findAll();
	}
	
	@Override
	public Item getItemById(String id) {
		Optional<Item> item = itemRepository.findById(id);
		if (item.isPresent()) {
            return item.get();
        }
		Item recievedItem=new Item();
		recievedItem.setItemId(null);
        return recievedItem;
	}
	
	@Override
	public ResponseEntity<Item> updateItemById(String id, Item item) {
		try {
    		Optional<Item> itemFromRepository = itemRepository.findById(id);
    		itemFromRepository.orElseThrow();
    		
    		Item updatedItem = itemRepository.save(item);
    		return ResponseEntity.ok(updatedItem);    		    		
    	} catch (NoSuchElementException e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }  catch(Exception e) {
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@Override
	public ResponseEntity<?> deleteItemById(String id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		if (itemRepository.findById(id).isPresent()) {
            itemRepository.deleteById(id);
            return ResponseEntity.ok("Item deleted successfully");
        }
		map.put("message", "Item not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}
}
