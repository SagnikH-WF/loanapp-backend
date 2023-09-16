package com.example.loanappbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.loanappbackend.model.EmployeeLoanCard;
import com.example.loanappbackend.model.UserLoan;

@Repository
public interface EmployeeLoanCardRepository extends JpaRepository<EmployeeLoanCard, Integer>{

	@Query("SELECT new com.example.loanappbackend.model.UserLoan(l.loanId, l.loanType, l.durationInYears, elc.cardIssueDate)" + 
			"FROM EmployeeLoanCard elc INNER JOIN elc.loan l WHERE elc.employee.employeeId = ?1")
	public List<UserLoan> findLoansByEmployeeId(String employeeId);
}
