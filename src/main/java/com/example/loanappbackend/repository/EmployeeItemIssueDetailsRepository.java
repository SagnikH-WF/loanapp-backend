package com.example.loanappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loanappbackend.model.EmployeeItemIssueDetails;

@Repository
public interface EmployeeItemIssueDetailsRepository extends JpaRepository<EmployeeItemIssueDetails, String>{

}
