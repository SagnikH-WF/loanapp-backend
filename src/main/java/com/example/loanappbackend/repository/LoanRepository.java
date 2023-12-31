package com.example.loanappbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.loanappbackend.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String>{
	
	@Query("SELECT l.loanType FROM Loan l")
	public List<String> getDistinctLoanTypes();

	@Query("SELECT l.loanId FROM Loan l WHERE l.loanType=?1")
	public String findByLoanType(String itemCategory);
}
