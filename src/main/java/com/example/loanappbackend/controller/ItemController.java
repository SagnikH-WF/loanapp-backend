package com.example.loanappbackend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/admin/allItems")
	public List<Item> getAllItemsDetails(){
		return itemService.getAllItemsDetails();
	}
	@GetMapping("/item/{id}")
	public Item getItemById(@PathVariable("id") String id)
	{
		return itemService.getItemById(id);
	}
	
	@PostMapping("/addItem")
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
	
	
	 @DeleteMapping("/deleteItem")
	    public ResponseEntity<String> deleteItem(@RequestParam String itemId) {
	        try {
	            itemService.deleteItemByItemId(itemId);
	            return new ResponseEntity<>("Item deleted successfully.", HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error deleting item: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 @PutMapping("item/{id}") 
	 public String updateItem(@PathVariable("id") String id, @RequestBody Item item) {
		 return itemService.updateItemById(id,item);
	 }
	
}
