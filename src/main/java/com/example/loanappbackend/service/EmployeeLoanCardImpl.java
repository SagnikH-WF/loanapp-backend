package com.example.loanappbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanappbackend.model.EmployeeLoanCard;
import com.example.loanappbackend.repository.EmployeeLoanCardRepository;

@Service
public class EmployeeLoanCardImpl implements EmployeeLoanCardService{
	@Autowired
	private EmployeeLoanCardRepository employeeLoanCardRepository;
	
	@Override
	public EmployeeLoanCard saveEmployeeLoanCard(EmployeeLoanCard employeeLoanCard ) {		
		return employeeLoanCardRepository.save(employeeLoanCard);
	}
}
