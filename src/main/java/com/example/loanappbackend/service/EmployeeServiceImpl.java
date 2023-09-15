package com.example.loanappbackend.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.UserLogin;
import com.example.loanappbackend.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
    private EmployeeRepository employeeRepository;
	
//	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//		this.employeeRepository=employeeRepository;
//	}

    @Override
    public Employee saveEmployee(Employee employee) {
    	//TODO: stop updating existing primary key
        return employeeRepository.save(employee);
    }    

    @Override
    public Employee getEmployeeById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        return null;
    }

//    @Override
//    public Employee updateEmployeeById(String id, Employee employee) {
//        Optional<Employee> employee1 = employeeRepository.findById(id);
//
//        if (employee1.isPresent()) {
//            Employee originalEmployee = employee1.get();
//
//            if (Objects.nonNull(employee.getEmployeeName()) && !"".equalsIgnoreCase(employee.getEmployeeName())) {
//                originalEmployee.setEmployeeName(employee.getEmployeeName());
//            }
//            if (Objects.nonNull(employee.getEmployeeSalary()) && employee.getEmployeeSalary() != 0) {
//                originalEmployee.setEmployeeSalary(employee.getEmployeeSalary());
//            }
//            return employeeRepository.save(originalEmployee);
//        }
//        return null;
//    }

    @Override
    public String deleteEmployeeById(String id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully";
        }
        return "No such employee in the database";
    }
    
    @Override
    public String checkLogin(UserLogin user) {
    	String id = user.getUserId();
    	String password = user.getPassword();
    	Optional<Employee> employee1 = employeeRepository.findById(id);
    	
        if (employee1.isPresent()) {
        	Employee originalEmployee = employee1.get();
        	
        	if (Objects.equals(password, originalEmployee.getPassword()))
        		return "Successful!!";
        	else
        		return "Password mismatch";
        		
        }
        
        return "Employee with userId not present";
        
    }
}
