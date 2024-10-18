package com.epms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.epms.entity.Assignment;

public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
	
	@Query("SELECT SUM(a.allocationPercentage) FROM Assignment a WHERE a.employee.id = :employeeId")
	int findAllocationPercentageByEmployeeId(@Param("employeeId") int employeeId);

}