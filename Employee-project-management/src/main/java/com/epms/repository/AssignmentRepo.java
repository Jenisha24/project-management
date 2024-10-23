package com.epms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.epms.entity.Assignment;
import com.epms.vo.AssignmentVo;

public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
	
	@Query("SELECT SUM(a.allocationPercentage) FROM Assignment a WHERE a.employee.id = :employeeId")
	Integer findAllocationPercentageByEmployeeId(@Param("employeeId") int employeeId);
	
	@Query("SELECT a.employee.employeeId FROM Assignment a WHERE a.project.projectId = :projectId")
	List<Integer> findEmployeeIdByProjectId(@Param("projectId") int projectId);
	
	
}