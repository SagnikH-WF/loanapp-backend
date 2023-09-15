package com.example.loanappbackend.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.EmployeeLoan;
import com.example.loanappbackend.model.EmployeeLoanCard;
import com.example.loanappbackend.model.Loan;
import com.example.loanappbackend.repository.EmployeeItemIssueDetailsRepository;
import com.example.loanappbackend.repository.EmployeeLoanCardRepository;
import com.example.loanappbackend.repository.EmployeeRepository;
import com.example.loanappbackend.repository.ItemRepository;
import com.example.loanappbackend.repository.LoanRepository;

@Service
public class EmployeeLoanServiceImpl {
	
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
		
//		EmployeeCardDetails ecd=new EmployeeCardDetails();
		EmployeeLoanCard employeeLoanCard;
		
		Employee employee = employeeRepository.findById(employeeLoan.getEmployeeId()).get();
		String loanId = loanRepository.findByLoanType(employeeLoan.getItemCategory());
		Loan loan = loanRepository.findById(loanId).get();
		
		employeeLoanCard = new EmployeeLoanCard(LocalDate.now(), employee, loan);
		employeeLoanCard.setEmployee(employee);
		ecd.setCard_issue_date(LocalDate.now());
		ecd.setLoan(l);
		EmployeeCardDetails outputecd=cardRepo.save(ecd);
		if(outputecd!=null) {
			result="Card table updated";
		}
		else {
			result="Error in updating card table";
		}
		
		int loanduration=l.getLoan_duration();
		
		EmployeeIssueDetails eid=new EmployeeIssueDetails();
		eid.setEmployee(e);
		Item it=itemRepo.findById(itemRepo.findItemDetails(al.getItemCategory(), al.getItemDesc(),
				al.getItemValue(), al.getItemMake())).get();
		eid.setItem(it);
		LocalDate ld=LocalDate.parse(LocalDate.now().toString());
		eid.setIssueDate(LocalDate.now());
		eid.setReturnDate(ld.plusYears(loanduration));
		EmployeeIssueDetails outputeid=issueRepo.save(eid);
		if(outputeid!=null) {
			result+=" Issue Table updated";
		}
		else {
			result+=" Error in updating Issue table";
		}
		
	
	
		return result;
	}
}
