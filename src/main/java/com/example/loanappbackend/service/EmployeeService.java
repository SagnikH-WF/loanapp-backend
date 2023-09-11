package com.example.loanappbackend.service;

import com.example.loanappbackend.model.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
	
	Employee getEmployeeById(String id);

//    Employee updateEmployeeById(String id, Employee employee);

    String deleteEmployeeById(String id);
}
