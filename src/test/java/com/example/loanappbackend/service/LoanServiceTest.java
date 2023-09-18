package com.example.loanappbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.loanappbackend.model.Loan;
import com.example.loanappbackend.repository.LoanRepository;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {
	
	@Mock
	LoanRepository loanRepository;
	
	@InjectMocks
	LoanServiceImpl loanService;
	
	private Loan loan;
	
	@BeforeEach
	void setUp() {
		this.loan=new Loan("L1",4,"Furniture");
	}
	
	
	//JUnit test for saveLoan method
	@Test
	void saveLoan() {
		
		when(loanRepository.save(loan)).thenReturn(loan);
		
		Loan savedLoan=loanService.saveLoan(loan);
		
		assertThat(savedLoan).isNotNull();
		
	}
	
	//JUnit test for getLoanList method
	@Test
	void getLoanList() {
		
		when(loanRepository.findAll()).thenReturn(List.of(loan));
		
		List<Loan> loanList = loanService.getAllLoans();
		
		assertThat(loanList).isNotNull();
		
		assertThat(loanList.size()).isEqualTo(1);
		
	}
	
	@Test
	void getLoanById() {
		
		when(loanRepository.findById("L1")).thenReturn(Optional.of(loan));
		
		Loan loanById=loanService.getLoanById("L1");
		
		assertThat(loanById).isNotNull();
		
	}
	
	 @Test
	    public void deleteLoanById() {
	        // Define some sample data
	        String loanId = "123";

	        // Mock the repository behavior to simulate a successful delete
	        when(loanRepository.findById(loanId)).thenReturn(Optional.of(new Loan()));

	        // Call the service method
	        String result = loanService.deleteLoanById(loanId);

	        // Assert that the result indicates success
	        assertEquals("Loan deleted successfully", result);
	    }
	
	@Test
	void getDistinctLoanType() {
		
		List<String> expectedDistinctLoanTypes = Arrays.asList("Home Loan", "Car Loan", "Personal Loan");

		when(loanRepository.getDistinctLoanTypes()).thenReturn(expectedDistinctLoanTypes);
		
		List<String> listOfLoanTypes = loanService.getDistinctLoanTypes();
		
		assertThat(listOfLoanTypes).isNotNull();
		assertEquals(expectedDistinctLoanTypes,listOfLoanTypes);
	}

}
