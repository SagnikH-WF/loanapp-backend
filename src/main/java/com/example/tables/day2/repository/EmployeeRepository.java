package com.example.tables.day2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tables.day2.model.EmployeeMaster;

public interface EmployeeRepository extends JpaRepository<EmployeeMaster,String> {

}
