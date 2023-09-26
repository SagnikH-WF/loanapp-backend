package com.example.loanappbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanappbackend.dto.ItemIssueDto;
import com.example.loanappbackend.exceptionHandler.NoDataFoundException;
import com.example.loanappbackend.model.ItemIssue;
import com.example.loanappbackend.service.EmployeeItemIssueDetailsService;

import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeItemIssueDetailsController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	EmployeeItemIssueDetailsService employeeItemIssueDetailsService;
	
	@GetMapping("/itemsIssued")
	public List<ItemIssueDto> getItemsIssuedToEmployee(@Valid @RequestParam String employeeId) throws NoDataFoundException   {
		//send current date to the repo to check if the current date is greater than the return date
		
		List<ItemIssueDto> responseListOfIssuedItemsToEmployee=new ArrayList<>();
		
		List<ItemIssue> listOfIssuedItems=employeeItemIssueDetailsService.findItemsIssuedToEmployee(employeeId);
		
		for(ItemIssue issuedItem:listOfIssuedItems) {
			responseListOfIssuedItemsToEmployee.add(modelMapper.map(issuedItem, ItemIssueDto.class));
		}
		
		if(responseListOfIssuedItemsToEmployee.isEmpty()) {
			throw new NoDataFoundException("No Items are purchased by the Employee");
		}
		
		return responseListOfIssuedItemsToEmployee;
	}
}
