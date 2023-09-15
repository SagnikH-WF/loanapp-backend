package com.example.tables.day2.repository;

import java.util.*;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tables.day2.model.EmployeeCard;
import com.example.tables.day2.model.LoanMaster;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCard,String> {
	@Query("SELECT e FROM EmployeeCard e INNER JOIN e.loan WHERE e.empld=?1")
	public List<EmployeeCard> findByEmpId(String empId);
}
