package com.example.loanappbackend.controller;

import com.example.loanappbackend.model.EmployeeLoanCard;
import com.example.loanappbackend.model.UserLoan;
import com.example.loanappbackend.service.EmployeeLoanCardService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

@WebMvcTest(EmployeeLoanCardController.class)
public class EmployeeLoanCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeLoanCardService employeeLoanCardService;

    @BeforeEach
    public void setUp() {
        // Initialize any necessary mock behavior here
    }

    @Test
    public void saveEmployeeLoanCard() throws Exception {
        // Create a sample EmployeeLoanCard object for testing
        EmployeeLoanCard employeeLoanCard = new EmployeeLoanCard();
        employeeLoanCard.setEmployeeCardId(1);
        
        
        EmployeeLoanCard savedEmployeeLoanCard = new EmployeeLoanCard();
        savedEmployeeLoanCard.setEmployeeCardId(1);

        // Mock the service method to return the same object
        when(employeeLoanCardService.saveEmployeeLoanCard(any(EmployeeLoanCard.class)))
                .thenReturn(savedEmployeeLoanCard);

        // Convert the object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        

        // Perform a POST request to the /loanCard endpoint with the JSON body
        mockMvc.perform(post("/loanCard")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(employeeLoanCard)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(savedEmployeeLoanCard)));
    }
    
    @Test
    public void findLoansByEmployeeId() throws Exception {
        // Create a sample list of UserLoan objects for testing
        List<UserLoan> userLoans = Arrays.asList(
                new UserLoan("L1","furniture",3,LocalDate.now()),
                new UserLoan("L2","furniture",2,LocalDate.now())
        );

        // Define the expected employeeId and mock the service method
        String expectedEmployeeId = "E1";
        when(employeeLoanCardService.findLoansByEmployeeId(expectedEmployeeId))
                .thenReturn(userLoans);

        // Perform a GET request to the endpoint with the employeeId parameter
        mockMvc.perform(MockMvcRequestBuilders.get("/loanCard")
                .param("employeeId", expectedEmployeeId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        
    }

}



