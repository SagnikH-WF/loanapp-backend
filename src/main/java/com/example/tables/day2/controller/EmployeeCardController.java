package com.example.tables.day2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tables.day2.model.EmployeeCard;
import com.example.tables.day2.model.LoanMaster;
import com.example.tables.day2.model.TempViewLoan;
import com.example.tables.day2.repository.EmployeeCardRepository;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class EmployeeCardController {
	@Autowired
	EmployeeCardRepository employeeCardRepository;
	
	@PostMapping("/employeeCard")
	public String createNewEmployee( @RequestBody EmployeeCard employeeCard) {
		employeeCardRepository.save(employeeCard);

		return "Employee card details entered in the database"; 
	
	}
	
	@GetMapping("/employeeCard")
	public ResponseEntity<List<EmployeeCard>> getAllEmployee(){
		List<EmployeeCard> empList = new ArrayList<>();
		employeeCardRepository.findAll().forEach(empList::add);
		return new ResponseEntity<List<EmployeeCard>>(empList,HttpStatus.OK);
	}
	
	@GetMapping("/employeeCard/{empId}")
	public List<EmployeeCard> getEmployeeById(@PathVariable String empId){
		List<EmployeeCard> emp = employeeCardRepository.findByEmpId(empId);
//		System.out.println(emp.getLoan());
		return emp;
	}
	
	@GetMapping("/viewLoan/{empId}")
	public List<TempViewLoan> viewLoanByEmpId(@PathVariable String empId){
		List<TempViewLoan> temp = new ArrayList<>();
		List<EmployeeCard> emp = employeeCardRepository.findByEmpId(empId);
		for(EmployeeCard x:emp) {
			TempViewLoan y = new TempViewLoan();
			y.setLoan_id(x.getLoan().getLoan_id());
			y.setCard_issue_date(x.getIssue_date());
			y.setDuration_in_years(x.getLoan().getDuration_in_years());
			y.setLoan_type(x.getLoan().getLoan_type());
			temp.add(y);
		}
		return temp;
	}
}
