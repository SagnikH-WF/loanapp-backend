package com.example.tables.day2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tables.day2.model.LoanMaster;

public interface LoanRepository extends JpaRepository<LoanMaster,String> {

	
}
