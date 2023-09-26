package com.example.loanappbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public void updateLoanById() {
        // Mock data
        String loanId = "L1";
        Loan loan = new Loan(loanId,  10,  "Furniture");

        // Mock the behavior of loanRepository.findById(id)
        when(loanRepository.findById(loanId)).thenReturn(java.util.Optional.of(loan));
        // Mock the behavior of loanRepository.save(loan)
        when(loanRepository.save(loan)).thenReturn(loan);

        // Call the service layer method
        ResponseEntity<Loan> response = loanService.updateLoanById(loanId, loan);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(loan, response.getBody());
    }
    
    @Test
    public void testDeleteLoanById_Success() {
        // Mock data
        String loanId = "L1";

        // Mock the behavior of loanRepository.findById(id)
        when(loanRepository.findById(loanId)).thenReturn(java.util.Optional.of(new Loan(loanId,  10,  "Furniture")));

        // Call the service layer method
        ResponseEntity<?> response = loanService.deleteLoanById(loanId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Loan deleted successfully", response.getBody());
        // Verify that loanRepository.deleteById(id) was called
        verify(loanRepository, times(1)).deleteById(loanId);
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
