package com.example.loanappbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.loanappbackend.dto.EmployeeDto;
import com.example.loanappbackend.dto.UserLoginDto;
import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.UserLogin;
import com.example.loanappbackend.service.EmployeeService;
import com.github.fge.jsonpatch.JsonPatch;

import jakarta.validation.Valid;

@RestController
@Validated
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeController {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public EmployeeDto saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
    	System.out.println("EmployeeDtoId: "+employeeDto.getEmployeeId());
    	Employee employee=modelMapper.map(employeeDto, Employee.class);
    	System.out.println("EmployeeId: "+employee);
    	Employee savedEmployee=employeeService.saveEmployee(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }
    
    @GetMapping("/employee")
    public List<EmployeeDto> getAllEmployees() {
    	List<EmployeeDto> allEmployeesList=new ArrayList<>();
    	List<Employee> allEmployees=employeeService.getAllEmployees();
    	for(Employee e:allEmployees) {
    		allEmployeesList.add(modelMapper.map(e, EmployeeDto.class));
    	}
    	return allEmployeesList;
    }

    @GetMapping("/employee/{id}")
    public EmployeeDto getEmployeeById(@Valid @PathVariable("id") String id) {
        return modelMapper.map(employeeService.getEmployeeById(id), EmployeeDto.class);
    }

    @PatchMapping(path = "/employee/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable("id") String id,@Valid @RequestBody JsonPatch patch) {
        return employeeService.updateEmployeeById(id, patch);
    }
    
    @PutMapping(path = "/employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@Valid @PathVariable("id") String id,@Valid @RequestBody Employee employee) {
        
    	Employee updatedEmployee=employeeService.updateEmployeeById(id, employee).getBody();
 
    	return ResponseEntity.ok(modelMapper.map(updatedEmployee, EmployeeDto.class));
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@Valid @PathVariable("id") String id) {
        return employeeService.deleteEmployeeById(id);
    }
    
    @PostMapping("/employee/user/login")
    public ResponseEntity<?> checkLoginCredentials(@Valid @RequestBody UserLoginDto userDto) {    	
    	return employeeService.checkLogin(modelMapper.map(userDto, UserLogin.class));
    }
    
    @PostMapping("/employee/admin/login")
    public ResponseEntity<?> checkAdminLoginCredentials(@Valid @RequestBody UserLoginDto userDto) {    	
    	return employeeService.checkAdminLogin(modelMapper.map(userDto, UserLogin.class));
    }
}
