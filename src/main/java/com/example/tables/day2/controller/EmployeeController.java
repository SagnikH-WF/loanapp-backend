package com.example.tables.day2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tables.day2.model.EmployeeMaster;
import com.example.tables.day2.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping("/employee")
	public String createNewEmployee( @RequestBody EmployeeMaster employee) {
		employeeRepository.save(employee);

		return "Employee details entered in the database"; 
	
	}
	
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeMaster>> getAllEmployee(){
		List<EmployeeMaster> empList = new ArrayList<>();
		employeeRepository.findAll().forEach(empList::add);
		return new ResponseEntity<List<EmployeeMaster>>(empList,HttpStatus.OK);
	}
	
	@GetMapping("/employee/{empId}")
	public ResponseEntity<EmployeeMaster> getEmployeeById(@PathVariable String empId){
		Optional<EmployeeMaster> emp = employeeRepository.findById(empId);
		if(emp.isPresent()) {
			return new ResponseEntity<EmployeeMaster>(emp.get(),HttpStatus.FOUND);
		}
		return new ResponseEntity<EmployeeMaster>(HttpStatus.NOT_FOUND);
	}
	
//	@PutMapping("/employee/{empId}")
//	public String updateEmployeeById(@PathVariable String empId, EmployeeMaster employee) {
//		Optional<EmployeeMaster> emp = employeeRepository.findById(empId);
//		if(emp.isPresent()) {
//			EmployeeMaster existEmp = emp.get();
//			existEmp.setDate_of_birth(employee.getDate_of_birth());
//			existEmp.setDate_of_joining(emp.ge)
//		}
//	}
	
	@DeleteMapping("/employees")
	public String deleteAllEmployee() {
		employeeRepository.deleteAll();
		return "All details are cleared";
	}
	
	@DeleteMapping("/employees/{empId}")
	public String deleteEmployeeById(@PathVariable String empId) {
		Optional<EmployeeMaster> emp = employeeRepository.findById(empId);
		if(emp.isPresent()) {
			employeeRepository.deleteById(empId);
			return "Employee deleted successfully";
		}
		return "Employee not found";
	}
	
}
