package com.epms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epms.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
