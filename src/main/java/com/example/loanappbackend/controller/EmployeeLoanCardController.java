package com.example.loanappbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanappbackend.dto.EmployeeLoanCardDto;
import com.example.loanappbackend.dto.UserLoanDto;
import com.example.loanappbackend.exceptionHandler.NoDataFoundException;
import com.example.loanappbackend.model.EmployeeLoanCard;
import com.example.loanappbackend.model.UserLoan;
import com.example.loanappbackend.service.EmployeeLoanCardService;

import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeLoanCardController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeLoanCardService employeeLoanCardService;
	
	@PostMapping("/loanCard")
	public EmployeeLoanCardDto saveEmployeeLoanCard(@Valid @RequestBody EmployeeLoanCardDto employeeLoanCardDto) {
		
		EmployeeLoanCard employeeLoanCard=modelMapper.map(employeeLoanCardDto, EmployeeLoanCard.class);
		return modelMapper.map(employeeLoanCardService.saveEmployeeLoanCard(employeeLoanCard), EmployeeLoanCardDto.class);
	}
	
	@GetMapping("/loanCard")
	public List<UserLoanDto> findLoansByEmployeeId(@Valid @RequestParam String employeeId) throws NoDataFoundException {
		
		
		List<UserLoanDto> responseListByEmployeeId=new ArrayList<>();
		List<UserLoan> listByEmployeeList=employeeLoanCardService.findLoansByEmployeeId(employeeId);
		for(UserLoan userLoan:listByEmployeeList) {
			responseListByEmployeeId.add(modelMapper.map(userLoan, UserLoanDto.class));
		}
		
		if(responseListByEmployeeId.isEmpty()) {
			throw new NoDataFoundException("No Items are purchased by the Employee");
		}
		return responseListByEmployeeId;
	}
}
