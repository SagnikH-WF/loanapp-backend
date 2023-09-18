package com.example.loanappbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.loanappbackend.model.EmployeeLoanCard;
import com.example.loanappbackend.repository.EmployeeLoanCardRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeLoanCardServiceTest {

	@Mock
	EmployeeLoanCardRepository employeeLoanCardRepository;
	
	@InjectMocks
	EmployeeLoanCardImpl employeeLoanCardService;
	
	@Test
	void saveEmployeeLoanCard() {
		
		EmployeeLoanCard employeeLoanCard=new EmployeeLoanCard();
		employeeLoanCard.setEmployeeCardId(1);
		
		when(employeeLoanCardRepository.save(employeeLoanCard)).thenReturn(employeeLoanCard);
		
		EmployeeLoanCard savedEmployeeLoanCard = employeeLoanCardService.saveEmployeeLoanCard(employeeLoanCard);
		
		assertThat(savedEmployeeLoanCard).isNotNull();
		
		assertEquals(1,savedEmployeeLoanCard.getEmployeeCardId());
		
		verify(employeeLoanCardRepository,times(1)).save(employeeLoanCard);
	}
}
