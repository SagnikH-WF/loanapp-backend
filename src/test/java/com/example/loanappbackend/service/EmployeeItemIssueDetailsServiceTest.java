package com.example.loanappbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.loanappbackend.model.EmployeeItemIssueDetails;
import com.example.loanappbackend.model.Item;
import com.example.loanappbackend.model.ItemIssue;
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
	

    @Test
    public void findItemsIssuedToEmployee() {
        // Define some sample data
        String employeeId = "123";
        List<ItemIssue> expectedItems = new ArrayList<>();
        expectedItems.add(new ItemIssue(1,"table",'Y',"wooden","furniture",50));
        expectedItems.add(new ItemIssue(2,"chair",'Y',"wooden","furniture",30));

        // Mock the repository's behavior
        when(employeeItemIssueDetailsRepository.findItemsIssuedToEmployee(employeeId,LocalDate.now())).thenReturn(expectedItems);

        // Call the service method
        List<ItemIssue> actualItems = employeeItemIssueDetailsService.findItemsIssuedToEmployee(employeeId);

        // Assert that the returned list matches the expected list
        assertEquals(expectedItems, actualItems);
    }
	
	
}
