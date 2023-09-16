package com.example.loanappbackend.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.EmployeeItemIssueDetails;
import com.example.loanappbackend.model.EmployeeLoan;
import com.example.loanappbackend.model.EmployeeLoanCard;
import com.example.loanappbackend.model.Item;
import com.example.loanappbackend.model.Loan;
import com.example.loanappbackend.repository.EmployeeItemIssueDetailsRepository;
import com.example.loanappbackend.repository.EmployeeLoanCardRepository;
import com.example.loanappbackend.repository.EmployeeRepository;
import com.example.loanappbackend.repository.ItemRepository;
import com.example.loanappbackend.repository.LoanRepository;

@Service
public class EmployeeLoanServiceImpl implements EmployeeLoanService {
	
	@Autowired
	EmployeeLoanCardRepository employeeLoanCardRepository;
	
	@Autowired
	EmployeeItemIssueDetailsRepository employeeItemIssueDetailsRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Transactional
	public String applyForLoan(EmployeeLoan employeeLoan) {
		
		String result = "";
		LocalDate currentDate = LocalDate.now();						
		
		Employee employee = employeeRepository.findById(employeeLoan.getEmployeeId()).get();
		String loanId = loanRepository.findByLoanType(employeeLoan.getItemCategory());
		Loan loan = loanRepository.findById(loanId).get();
		
		EmployeeLoanCard employeeLoanCard = new EmployeeLoanCard(currentDate, employee, loan);				
		
		EmployeeLoanCard savedEmployeeLoanCard = employeeLoanCardRepository.save(employeeLoanCard);
		if(savedEmployeeLoanCard != null) {
			result="Card table updated";
		}
		else {
			result="Error in updating card table";
		}
		
		//employee item issue card 		
		int loanDuration=loan.getDurationInYears();
				
		Item item = itemRepository.findItemDetails(employeeLoan.getItemCategory(), employeeLoan.getItemDescription(), employeeLoan.getItemValuation(), employeeLoan.getItemMake());
		
		LocalDate parsedLocalDate=LocalDate.parse(currentDate.toString());
		LocalDate returnDate = parsedLocalDate.plusYears(loanDuration);
		
		EmployeeItemIssueDetails employeeItemIssueDetails = new EmployeeItemIssueDetails(currentDate, returnDate, employee, item);

		EmployeeItemIssueDetails savedEmployeeItemIssueDetails = employeeItemIssueDetailsRepository.save(employeeItemIssueDetails);
		
		
		if(savedEmployeeItemIssueDetails != null) {
			result += " Issue Table updated";
		}
		else {
			result += " Error in updating Issue table";
		}		
	
		return result;
	}
}
