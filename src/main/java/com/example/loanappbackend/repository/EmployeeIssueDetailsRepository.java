package com.example.loanappbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.loanappbackend.model.EmployeeIssueDetails;

@Repository
//public interface EmployeeIssueDetailsRepository extends JpaRepository<EmployeeIssueDetails,String> {
//	@Query("SELECT e.item FROM EmployeeIssueDetails e  WHERE e.employee.employeeId=?1")
//	//@Query("select item from Item item inner join item.employeeissue e where ")
//	public Set<Item>  getEmployeeItems(String employeeId);
//}
public interface EmployeeIssueDetailsRepository extends JpaRepository<EmployeeIssueDetails,String> {
	@Query("SELECT e FROM EmployeeIssueDetails e  WHERE e.employee.employeeId=?1")
	//@Query("select item from Item item inner join item.employeeissue e where ")
	public List<EmployeeIssueDetails>  getEmployeeItems(String employeeId);
}