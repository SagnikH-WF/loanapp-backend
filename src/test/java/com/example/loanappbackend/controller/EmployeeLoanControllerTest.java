package com.example.loanappbackend.controller;

import com.example.loanappbackend.model.EmployeeLoan;
import com.example.loanappbackend.service.EmployeeLoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(EmployeeLoanController.class)
public class EmployeeLoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeLoanService employeeLoanService;

    

    @Test
    public void testApplyForLoan() throws Exception {
        // Create a sample EmployeeLoan object for testing
        EmployeeLoan employeeLoan = new EmployeeLoan();
        employeeLoan.setEmployeeId("L1");
        employeeLoan.setItemValuation(1000);

        // Mock the service method to return a response
        String responseMessage = "Loan application submitted successfully";
        Mockito.when(employeeLoanService.applyForLoan(Mockito.any(EmployeeLoan.class))).thenReturn(responseMessage);

        // Convert the object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employeeLoan);

        // Perform a POST request to the /applyLoan endpoint with the JSON body
        mockMvc.perform(MockMvcRequestBuilders.post("/applyLoan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseMessage));
    }
}
