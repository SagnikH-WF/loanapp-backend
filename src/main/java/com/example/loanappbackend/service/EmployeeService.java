package com.example.loanappbackend.service;

import org.springframework.http.ResponseEntity;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.UserLogin;
import com.github.fge.jsonpatch.JsonPatch;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
	
	Employee getEmployeeById(String id);

    ResponseEntity<Employee> updateEmployeeById(String id, JsonPatch patch);
    
    ResponseEntity<Employee> updateEmployeeById(String id, Employee employee);

    String deleteEmployeeById(String id);
    
    ResponseEntity<?> checkLogin(UserLogin user);
    
    ResponseEntity<?> checkAdminLogin(UserLogin user);
    
}
