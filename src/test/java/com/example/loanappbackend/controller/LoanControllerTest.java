package com.example.loanappbackend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper for JSON serialization/deserialization

    @Test
    public void testSaveLoan() throws Exception {
        // Create a sample loan to save
        Loan loanToSave = new Loan();
        loanToSave.setLoanType("Personal Loan");

        // Define the expected saved loan with an ID
        Loan savedLoan = new Loan();
        savedLoan.setLoanId("1");
        savedLoan.setLoanType("Personal Loan");

        // Mock the behavior of loanService.saveLoan
        when(loanService.saveLoan(any(Loan.class))).thenReturn(savedLoan);

        // Perform a POST request to /loan with JSON content
        mockMvc.perform(post("/loan")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loanToSave)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(savedLoan)));
    }

    @Test
    public void testGetLoanList() throws Exception {
        // Define a list of sample loans for the expected response
        List<Loan> expectedLoans = Arrays.asList(
            new Loan("1", 10, "Personal Loan"),
            new Loan("2", 15, "Home Loan")
        );

        // Mock the behavior of loanService.getAllLoans
        when(loanService.getAllLoans()).thenReturn(expectedLoans);

        // Perform a GET request to /loan
        mockMvc.perform(get("/loan"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedLoans)));
    }

    @Test
    public void testGetLoanById() throws Exception {
        // Define a sample loan ID
        String loanId = "1";

        // Define the expected loan for the response
        Loan expectedLoan = new Loan("1", 10, "Personal Loan");

        // Mock the behavior of loanService.getLoanById
        when(loanService.getLoanById(loanId)).thenReturn(expectedLoan);

        // Perform a GET request to /loan/{id}
        mockMvc.perform(get("/loan/{id}", loanId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedLoan)));
    }

    @Test
    public void testDeleteLoan() throws Exception {
        // Define a sample loan ID to delete
        String loanId = "1";

        // Define the expected delete response message
        String expectedResponse = "Loan with ID 1 deleted successfully";

        // Mock the behavior of loanService.deleteLoanById
        when(loanService.deleteLoanById(loanId)).thenReturn(expectedResponse);

        // Perform a DELETE request to /loan/{id}
        mockMvc.perform(delete("/loan/{id}", loanId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testGetDistinctLoanTypes() throws Exception {
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

