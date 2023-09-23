package com.example.loanappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
    
    @GetMapping("/employee")
    public List<Employee> getAllEmployees() {
    	return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@Valid @PathVariable("id") String id) {
        return employeeService.getEmployeeById(id);
    }

    @PatchMapping(path = "/employee/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable("id") String id,@Valid @RequestBody JsonPatch patch) {
        return employeeService.updateEmployeeById(id, patch);
    }
    
    @PutMapping(path = "/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable("id") String id,@Valid @RequestBody Employee employee) {
        return employeeService.updateEmployeeById(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@Valid @PathVariable("id") String id) {
        return employeeService.deleteEmployeeById(id);
    }
    
    @PostMapping("/employee/user/login")
    public ResponseEntity<?> checkLoginCredentials(@Valid @RequestBody UserLogin user) {    	
    	return employeeService.checkLogin(user);
    }
    
    @PostMapping("/employee/admin/login")
    public ResponseEntity<?> checkAdminLoginCredentials(@Valid @RequestBody UserLogin user) {    	
    	return employeeService.checkAdminLogin(user);
    }
}
