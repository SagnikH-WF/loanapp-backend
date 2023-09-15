package com.example.loanappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanappbackend.model.Item;
import com.example.loanappbackend.service.ItemService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/item")
	public Item saveItem(@RequestBody Item item) {
		return itemService.saveItem(item);
	}
	
	@GetMapping("/item")
	@ResponseBody
	public List<String> getCategorySpecificItems(@RequestParam String category) {
		return itemService.getCategorySpecificItemMakes(category);
	}
	
	//TODO: add suitable mapping
	@GetMapping("/item/allItems")
	@ResponseBody
	public List<Item> getItemsForSpecficCategoryAndMake(@RequestParam String category, @RequestParam String make) {
		return itemService.getItemsForSpecficCategoryAndMake(category, make);
	}
	 @GetMapping("/itemDetails")
	 public List<Item> getAllItems() {
	        // Call the service to get the list of items
	        List<Item> items = itemService.getAllItems();
	        // You can perform additional operations on the list if needed       
	        return items;
	    }
		
}
