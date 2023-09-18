package com.example.loanappbackend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.UserLogin;
import com.example.loanappbackend.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;
    
    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper for JSON deserialization
    
    
    @Test
    public void testSaveEmployee() throws Exception {
        // Create a sample employee to save
        Employee employeeToSave = new Employee();
        employeeToSave.setName("John");

        // Define the expected saved employee with an ID
        Employee savedEmployee = new Employee();
        savedEmployee.setEmployeeId("1");
        savedEmployee.setName("John");

        // Mock the behavior of employeeService.saveEmployee
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(savedEmployee);

        // Perform a POST request to /employee with JSON content
        mockMvc.perform(post("/employee")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(employeeToSave)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(savedEmployee)));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        // Create a sample employee for testing
        Employee employee = new Employee();
        employee.setEmployeeId("1");
        employee.setName("John");

        // Mock the behavior of employeeService.getEmployeeById
        when(employeeService.getEmployeeById("1")).thenReturn(employee);

        // Perform a GET request to /employee/1
        MvcResult result = mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andReturn();

        // Deserialize the JSON response into an Employee object
        String content = result.getResponse().getContentAsString();
        Employee responseEmployee = objectMapper.readValue(content, Employee.class);

        // Verify the fields of the responseEmployee object
        assertEquals("1", responseEmployee.getEmployeeId());
        assertEquals("John", responseEmployee.getName());
    }
    
    

    @Test
    public void testDeleteEmployee() throws Exception {
        // Mock the behavior of employeeService.deleteEmployeeById
        when(employeeService.deleteEmployeeById("1")).thenReturn("Employee with ID 1 deleted successfully");

        // Perform a DELETE request to /employee/1
        mockMvc.perform(delete("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee with ID 1 deleted successfully"));
    }

    @Test
    public void testCheckLoginCredentials() throws Exception {
        // Mock the behavior of employeeService.checkLogin
        when(employeeService.checkLogin(any(UserLogin.class))).thenReturn("Login successful");

        // Perform a POST request to /employee/login with JSON content
        mockMvc.perform(post("/employee/login")
                .contentType("application/json")
                .content("{\"username\":\"johndoe\",\"password\":\"secret\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));
    }
}

