package com.example.loanappbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.UserLogin;
import com.example.loanappbackend.service.EmployeeService;
import com.github.fge.jsonpatch.JsonPatch;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable("id") String id) {
        return employeeService.getEmployeeById(id);
    }

    @PatchMapping(path = "/employee/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id, @RequestBody JsonPatch patch) {
        return employeeService.updateEmployeeById(id, patch);
    }
    
    @PutMapping(path = "/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
        return employeeService.updateEmployeeById(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") String id) {
        return employeeService.deleteEmployeeById(id);
    }
    
    @PostMapping("/employee/login")
    public ResponseEntity<?> checkLoginCredentials(@RequestBody UserLogin user) {
    	
    	return employeeService.checkLogin(user);
    }
}
