package com.example.loanappbackend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.loanappbackend.model.EmployeeItemIssueDetails;
import com.example.loanappbackend.model.ItemIssue;

@Repository
public interface EmployeeItemIssueDetailsRepository extends JpaRepository<EmployeeItemIssueDetails, Integer>{
	
	@Query("SELECT new com.example.loanappbackend.model.ItemIssue(eid.issueId, i.itemDescription, i.itemStatus, i.itemMake, i.itemCategory, i.itemValuation )" + 
			"FROM EmployeeItemIssueDetails eid INNER JOIN eid.item i WHERE eid.employee.employeeId = ?1")
	public List<ItemIssue> findItemsIssuedToEmployee(String employeeId, LocalDate todayDate);
}
