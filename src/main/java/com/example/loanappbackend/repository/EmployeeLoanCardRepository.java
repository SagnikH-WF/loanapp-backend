package com.example.loanappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loanappbackend.model.EmployeeLoanCard;

@Repository
public interface EmployeeLoanCardRepository extends JpaRepository<EmployeeLoanCard, Integer>{

}
