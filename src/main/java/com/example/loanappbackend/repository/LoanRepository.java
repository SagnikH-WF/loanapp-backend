package com.example.loanappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loanappbackend.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String>{

}
