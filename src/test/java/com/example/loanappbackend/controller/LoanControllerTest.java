package com.example.loanappbackend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.example.loanappbackend.dto.LoanDto;
import com.example.loanappbackend.model.Loan;
import com.example.loanappbackend.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;

// Import other necessary classes...

@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;
    
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper for JSON serialization/deserialization


    @Test
    public void getLoanList() throws Exception {
        // Create example loans and LoanDto objects for testing
        Loan loan1 = new Loan();
        loan1.setLoanId("1");
        
        LoanDto loanDto1 = new LoanDto();
        loanDto1.setLoanId("1");
        

        Loan loan2 = new Loan();
        loan2.setLoanId("2");
        
        LoanDto loanDto2 = new LoanDto();
        loanDto2.setLoanId("2");
        

        // Create a list of example loans
        List<Loan> allLoans = Arrays.asList(loan1, loan2);

        // Create a list of example LoanDto objects for the response
        List<LoanDto> allLoanDtos = Arrays.asList(loanDto1, loanDto2);

        // Mock the behavior of loanService to return the list of example loans
        when(loanService.getAllLoans()).thenReturn(allLoans);

        // Mock the behavior of modelMapper to map each Loan to a LoanDto
        when(modelMapper.map(any(Loan.class), eq(LoanDto.class))).thenReturn(loanDto1, loanDto2);

        // Perform the GET request to retrieve the list of loans
        mockMvc.perform(get("/loan"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                
    }


    @Test
    public void getLoanById() throws Exception {
        // Create an example loan ID for testing
        String loanId = "L1";

        // Create an example Loan and LoanDto for the response
        Loan loan = new Loan();
        loan.setLoanId(loanId);
        
        LoanDto loanDto = new LoanDto();
        loanDto.setLoanId(loanId);
        

        // Mock the behavior of loanService to return the example Loan
        when(loanService.getLoanById(eq(loanId))).thenReturn(loan);

        // Mock the behavior of modelMapper to map the Loan to LoanDto
        when(modelMapper.map(eq(loan), eq(LoanDto.class))).thenReturn(loanDto);

        // Perform the GET request to retrieve a loan by ID
        mockMvc.perform(get("/loan/{id}", loanId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                // Check a specific property in the response
    }
    

   

    @Test
    public void deleteLoan() throws Exception {
        // Create an example loan ID for testing
        String loanId = "1";

        // Mock the behavior of loanService to return a successful response
        when(loanService.deleteLoanById(eq(loanId))).thenReturn(ResponseEntity.noContent().build());

        // Perform the DELETE request to delete a loan
        mockMvc.perform(delete("/loan/{id}", loanId))
                .andExpect(status().isNoContent()); // Expect a 204 No Content response
    }
    

    @Test
    public void distinctLoanTypes() throws Exception {
        // Define a list of sample distinct loan types for the expected response
        List<String> expectedLoanTypes = Arrays.asList("Personal Loan", "Home Loan");

        // Mock the behavior of loanService.getDistinctLoanTypes
        when(loanService.getDistinctLoanTypes()).thenReturn(expectedLoanTypes);

        // Perform a GET request to /loan/distinctLoanTypes
        mockMvc.perform(get("/loan/distinctLoanTypes"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedLoanTypes)));
    }
}

