package com.example.loanappbackend.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.EmployeeLoan;
import com.example.loanappbackend.model.Item;
import com.example.loanappbackend.model.Loan;
import com.example.loanappbackend.repository.EmployeeItemIssueDetailsRepository;
import com.example.loanappbackend.repository.EmployeeLoanCardRepository;
import com.example.loanappbackend.repository.EmployeeRepository;
import com.example.loanappbackend.repository.ItemRepository;
import com.example.loanappbackend.repository.LoanRepository;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeLoanServiceTest {

	@InjectMocks
    private EmployeeLoanService employeeLoanService;
	
	@Mock
    private EmployeeRepository employeeRepository;
    
	@Mock
	private LoanRepository loanRepository;
    
	@Mock
	private EmployeeLoanCardRepository employeeLoanCardRepository;
    
	@Mock
	private EmployeeItemIssueDetailsRepository employeeItemIssueDetailsRepository;
    
	@Mock
	private ItemRepository itemRepository;

    @Before
    public void setUp() {
//        employeeRepository = Mockito.mock(EmployeeRepository.class);
//        loanRepository = Mockito.mock(LoanRepository.class);
//        employeeLoanCardRepository = Mockito.mock(EmployeeLoanCardRepository.class);
//        employeeItemIssueDetailsRepository = Mockito.mock(EmployeeItemIssueDetailsRepository.class);
//        itemRepository = Mockito.mock(ItemRepository.class);
//
//        employeeLoanService = new EmployeeLoanService(
//            employeeRepository,
//            loanRepository,
//            employeeLoanCardRepository,
//            employeeItemIssueDetailsRepository,
//            itemRepository
//        );
    }

    @Test
    public void applyForLoan() {
        // Define some sample data
        EmployeeLoan employeeLoan = new EmployeeLoan("123", "furniture","chair",30,"wooden");
        Employee employee = new Employee("123","anurag","manager","dti",'M',LocalDate.now(),LocalDate.now());
        Loan loan = new Loan("L1",3,"furniture");
        Item item = new Item("I1","chair",'Y',"wooden","furniture",30);

        // Mock repository behaviors
        when(employeeRepository.findById(employeeLoan.getEmployeeId())).thenReturn(Optional.of(employee));
        when(loanRepository.findByLoanType(employeeLoan.getItemCategory())).thenReturn("loanId");
        when(loanRepository.findById("loanId")).thenReturn(Optional.of(loan));
        when(itemRepository.findItemDetails("furniture","chair",30,"wooden")).thenReturn(item);

        // Call the service method
        String result = employeeLoanService.applyForLoan(employeeLoan);

        // Assert that the result is as expected
        assertEquals("Card table updated Issue Table updated", result);
    }

    
}

