package com.example.loanappbackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
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

import com.example.loanappbackend.dto.ItemDto;
import com.example.loanappbackend.dto.LoanDto;
import com.example.loanappbackend.exceptionHandler.DuplicateDataFoundException;
import com.example.loanappbackend.model.Item;
import com.example.loanappbackend.service.ItemService;

import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin(origins="http://localhost:3000")
public class ItemController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/item")
	public ItemDto saveItem(@Valid @RequestBody ItemDto itemDto) throws DuplicateDataFoundException {
		
		Item itemToSave=modelMapper.map(itemDto, Item.class);
		ItemDto isLoanExisted=getItemById(itemToSave.getItemId());
		if(isLoanExisted.getItemId()!=null) {
			throw new DuplicateDataFoundException("This Item Id already exists");
		}
		return modelMapper.map(itemService.saveItem(itemToSave),ItemDto.class);
		
	}
	
	@GetMapping("/item")
	@ResponseBody
	public List<String> getCategorySpecificItems(@Valid @RequestParam String category) {
		return itemService.getCategorySpecificItemMakes(category);
	}
	
	//TODO: add suitable mapping -> change in frontend with new mapping
	@GetMapping("/item/category&make")
	@ResponseBody
	public List<ItemDto> getItemsForSpecficCategoryAndMake(@Valid @RequestParam String category,@Valid @RequestParam String make) {
		
		List<ItemDto> responseList=new ArrayList<>();
		List<Item> responseFromService=itemService.getItemsForSpecficCategoryAndMake(category, make);
		for(Item i:responseFromService) {
			responseList.add(modelMapper.map(i,ItemDto.class));
		}
		return responseList;
	}
	
	@GetMapping("/item/allItems")
	public List<ItemDto> getAllItemsDetails(){
		
		List<ItemDto> responseList=new ArrayList<>();
		List<Item> responseFromService=itemService.getAllItemsDetails();
		for(Item i:responseFromService) {
			responseList.add(modelMapper.map(i,ItemDto.class));
		}
		return responseList;
	}
	
	@GetMapping("/item/{id}")
	public ItemDto getItemById(@Valid @PathVariable("id") String id) {
		return modelMapper.map(itemService.getItemById(id), ItemDto.class);
	}
	
	 @PutMapping("/item/{id}") 
	 public ResponseEntity<ItemDto> updateItem(@Valid @PathVariable("id") String id,@Valid @RequestBody ItemDto itemDto) {
		 
		 Item itemToUpdate=modelMapper.map(itemDto, Item.class);
		 Item updatedItem=itemService.updateItemById(id,itemToUpdate).getBody();
		 return ResponseEntity.ok(modelMapper.map(updatedItem, ItemDto.class));
	 }
	 
	 @DeleteMapping("/item/{id}")
	 public ResponseEntity<?> deleteItem(@Valid @PathVariable("id") String id) {
		 return itemService.deleteItemById(id);
	 }
	 
}
