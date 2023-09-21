package com.example.loanappbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	private Employee employee;
	
	@BeforeEach
	void setUp() {
		this.employee=new Employee("E1","anurag","manager","dti",'M',LocalDate.now(),LocalDate.now());
	}
	
	
	//JUnit test for save employee method
	@Test
	void saveEmployee() {
		
		
		//
		when(employeeRepository.save(employee)).thenReturn(employee);
		System.out.println(employeeRepository);
		System.out.println(employeeService);
		
		Employee savedEmployee=employeeService.saveEmployee(employee);
		
		System.out.println(savedEmployee);
		assertThat(savedEmployee).isNotNull();
	}
	
	@Test
	void findEmployeeById() {
		
		when(employeeRepository.findById("E1")).thenReturn(Optional.of(employee));
		
		System.out.println(employeeRepository);
		System.out.println(employeeService);
		
		Employee fetchedEmployee=employeeService.getEmployeeById("E1");
		System.out.println(fetchedEmployee);
		assertThat(fetchedEmployee).isNotNull();
	}

	@Test
    public void deleteEmployeeById() {
        // Define some sample data
        String employeeId = "123";

        // Mock the repository behavior to simulate a successful delete
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(new Employee()));

        // Call the service method
        String result = employeeService.deleteEmployeeById(employeeId);

        // Assert that the result indicates success
        assertEquals("Employee deleted successfully", result);
    }
	
}
