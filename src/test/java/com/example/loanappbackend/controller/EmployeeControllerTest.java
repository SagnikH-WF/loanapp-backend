package com.example.loanappbackend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.loanappbackend.dto.EmployeeDto;
import com.example.loanappbackend.dto.UserLoginDto;
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
    
    @MockBean
    private ModelMapper modelMapper;
    
    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper for JSON deserialization
    
    

    @Test
    public void getAllEmployees() throws Exception {
        // Create a list of example Employee objects
        List<Employee> allEmployees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmployeeId("1");
        
        allEmployees.add(employee1);

        // Mock the behavior of the employeeService to return the list of Employee objects
        when(employeeService.getAllEmployees()).thenReturn(allEmployees);

        // Mock the behavior of the modelMapper for mapping each Employee to EmployeeDto
        when(modelMapper.map(any(Employee.class), eq(EmployeeDto.class)))
                .thenAnswer(invocation -> {
                    Employee source = invocation.getArgument(0);
                    EmployeeDto target = new EmployeeDto();
                    target.setEmployeeId(source.getEmployeeId());
                    // Map other properties as needed
                    return target;
                });

        // Perform the GET request to retrieve all employees
        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(allEmployees.size())); // Verify the number of employees returned
    }


    @Test
    public void getEmployeeById() throws Exception {
        // Create an example EmployeeDto to be returned by the service
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId("1");
       
        Employee employee=new Employee();

        // Mock the behavior of the employeeService to return the example EmployeeDto
        when(employeeService.getEmployeeById(eq("1"))).thenReturn(employee);

        // Mock the behavior of the modelMapper
        when(modelMapper.map(eq(employee), eq(EmployeeDto.class))).thenReturn(employeeDto);

        // Perform the GET request with a valid employee ID
        MvcResult result = mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Extract and parse the response content
        String responseContent = result.getResponse().getContentAsString();
        EmployeeDto responseDto = objectMapper.readValue(responseContent, EmployeeDto.class);

        // Assert the properties of the response DTO
        assertEquals("1", responseDto.getEmployeeId());
        
    }
    

    @Test
    public void updateEmployee() throws Exception {
        // Create an example Employee object for the request body
        Employee updatedEmployee = new Employee();
        updatedEmployee.setEmployeeId("1");
        

        // Mock the behavior of the employeeService to return the updated Employee object
        when(employeeService.updateEmployeeById(eq("1"), any(Employee.class)))
                .thenReturn(ResponseEntity.ok(updatedEmployee));

        // Mock the behavior of the modelMapper for mapping the updated Employee to EmployeeDto
        when(modelMapper.map(eq(updatedEmployee), eq(EmployeeDto.class)))
                .thenReturn(new EmployeeDto());

        // Perform the PUT request to update an employee
        mockMvc.perform(put("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    

    @Test
    public void deleteEmployee() throws Exception {
        // Mock the behavior of employeeService.deleteEmployeeById
        when(employeeService.deleteEmployeeById("1")).thenReturn("Employee with ID 1 deleted successfully");

        // Perform a DELETE request to /employee/1
        mockMvc.perform(delete("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee with ID 1 deleted successfully"));
    }
    




}

