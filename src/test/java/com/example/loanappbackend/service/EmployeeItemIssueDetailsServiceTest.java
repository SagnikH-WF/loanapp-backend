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

import com.example.loanappbackend.model.EmployeeItemIssueDetails;
import com.example.loanappbackend.repository.EmployeeItemIssueDetailsRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeItemIssueDetailsServiceTest {
	
	@Mock
	private EmployeeItemIssueDetailsRepository employeeItemIssueDetailsRepository;
	
	@InjectMocks
	private EmployeeItemIssueDetailsServiceImpl employeeItemIssueDetailsService;
	
	@Test
	public void saveEmployeeItemIssueDetails() {
		
		EmployeeItemIssueDetails employeeItemIssueDetails = new EmployeeItemIssueDetails();
		
		employeeItemIssueDetails.setIssueId(1);
		
		when(employeeItemIssueDetailsRepository.save(employeeItemIssueDetails)).thenReturn(employeeItemIssueDetails);
		
		EmployeeItemIssueDetails savedEmployeeItemIssueDetails = employeeItemIssueDetailsService.saveEmployeeItemIssueDetails(employeeItemIssueDetails);
		
		assertThat(savedEmployeeItemIssueDetails).isNotNull();
		
		assertEquals(1,savedEmployeeItemIssueDetails.getIssueId());
		
		verify(employeeItemIssueDetailsRepository,times(1)).save(employeeItemIssueDetails);
	}
	
	
}
