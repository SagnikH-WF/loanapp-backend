package com.example.loanappbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.UserLogin;
import com.example.loanappbackend.service.EmployeeService;

@RestController
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

//    @PutMapping("/employee/{id}")
//    public Employee updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
//        return employeeService.updateEmployeeById(id, employee);
//    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") String id) {
        return employeeService.deleteEmployeeById(id);
    }
    
    @PostMapping("/employee/login")
    public String checkLoginCredentials(@RequestBody UserLogin user) {
    	
    	return employeeService.checkLogin(user);
    }
}
