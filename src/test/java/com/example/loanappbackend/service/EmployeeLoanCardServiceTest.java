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

import com.example.loanappbackend.model.EmployeeLoanCard;
import com.example.loanappbackend.model.UserLoan;
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
	
	 @Test
	    public void findLoansByEmployeeId() {
	        // Define some sample data
	        String employeeId = "123";
	        List<UserLoan> expectedLoans = new ArrayList<>();
	        expectedLoans.add(new UserLoan("L1","furniture",2,LocalDate.now()));
	        expectedLoans.add(new UserLoan("L2","furniture",4,LocalDate.now()));

	        // Mock the repository's behavior
	        when(employeeLoanCardRepository.findLoansByEmployeeId(employeeId)).thenReturn(expectedLoans);

	        // Call the service method
	        List<UserLoan> actualLoans = employeeLoanCardService.findLoansByEmployeeId(employeeId);

	        // Assert that the returned list matches the expected list
	        assertEquals(expectedLoans, actualLoans);
	    }

}
