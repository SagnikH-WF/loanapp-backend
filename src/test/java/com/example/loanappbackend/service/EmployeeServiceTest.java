package com.example.loanappbackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.UserLogin;
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
    public void getAllEmployees() {
        // Mock data
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(new Employee("E1","anurag","manager","dti",'M',LocalDate.now(),LocalDate.now()));
        employeesList.add(new Employee("E2","nitin","manager","ccibt",'M',LocalDate.now(),LocalDate.now()));

        // Mock the behavior of the employeeRepository.findAll() method
        when(employeeRepository.findAll()).thenReturn(employeesList);

        // Call the service layer method
        List<Employee> savedEmployees = employeeService.getAllEmployees();

        // Assertions
        assertNotNull(savedEmployees);
        assertEquals(2, savedEmployees.size());
        assertEquals("anurag", savedEmployees.get(0).getName());
        assertEquals("nitin", savedEmployees.get(1).getName());
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
	    public void updateEmployeeById() {
	        // Mock data
	        String employeeId = "E1";
	        Employee updatedEmployee = new Employee("E1","nitin","manager","dti",'M',LocalDate.now(),LocalDate.now());
	        Optional<Employee> employeeFromRepository = Optional.of(new Employee("E1","anurag","manager","dti",'M',LocalDate.now(),LocalDate.now()));

	        // Mock the behavior of the employeeRepository.findById(id) method
	        when(employeeRepository.findById(employeeId)).thenReturn(employeeFromRepository);

	        // Mock the behavior of the employeeRepository.save(employee) method
	        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

	        // Call the service layer method
	        ResponseEntity<Employee> response = employeeService.updateEmployeeById(employeeId, updatedEmployee);

	        // Assertions
	        verify(employeeRepository).findById(employeeId);
	        verify(employeeRepository).save(updatedEmployee);
	        assert response.getStatusCode() == HttpStatus.OK;
	        assert response.getBody() != null;
	        assert response.getBody().getName().equals("nitin");
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
	
	 @Test
	    public void checkUserLogin() {
	        // Mock data
	        UserLogin user = new UserLogin("E1", "hi");
	        Employee employee = new Employee("E1","nitin","manager","dti",'M',LocalDate.now(),LocalDate.now());

	        // Mock the behavior of the employeeRepository.findById(id) method
	        when(employeeRepository.findById("E1")).thenReturn(Optional.of(employee));

	        // Call the service layer method
	        ResponseEntity<?> response = employeeService.checkLogin(user);

	        // Assertions
	        assert response.getBody() != null;
	        System.out.println(response.getBody());
	        
	        // Add more assertions as needed to check the response content
	    }
	
}
