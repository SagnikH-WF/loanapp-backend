package com.example.loanappbackend.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.loanappbackend.model.ItemIssue;
import com.example.loanappbackend.service.EmployeeItemIssueDetailsService;

@WebMvcTest(EmployeeItemIssueDetailsController.class)
public class EmployeeItemIssueDetailsControllerTest {
	
	  @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private EmployeeItemIssueDetailsService employeeItemIssueDetailsService;

	    @Test
	    public void getItemsIssuedToEmployee() throws Exception {
	        // Create a sample list of ItemIssue objects for testing
	        List<ItemIssue> itemIssues = Arrays.asList(
	                new ItemIssue(1,"chair",'Y',"wooden","furniture",30),
	                new ItemIssue(2,"table",'Y',"wooden","furniture",50)
	        );

	        // Define the expected employeeId and mock the service method
	        String expectedEmployeeId = "123";
	        when(employeeItemIssueDetailsService.findItemsIssuedToEmployee(expectedEmployeeId))
	                .thenReturn(itemIssues);

	        // Perform a GET request to the endpoint with the employeeId parameter
	        mockMvc.perform(MockMvcRequestBuilders.get("/itemsIssued")
	                .param("employeeId", expectedEmployeeId))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

	        // You can add more assertions to check the content of the JSON response if needed
	    }

}
