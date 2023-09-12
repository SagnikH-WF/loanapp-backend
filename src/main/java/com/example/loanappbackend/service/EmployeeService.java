package com.example.loanappbackend.service;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.UserLogin;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
	
	Employee getEmployeeById(String id);

//    Employee updateEmployeeById(String id, Employee employee);

    String deleteEmployeeById(String id);
    
    String checkLogin(UserLogin user);
    
}
