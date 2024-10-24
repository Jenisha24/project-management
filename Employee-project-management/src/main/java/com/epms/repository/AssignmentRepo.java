package com.epms.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.epms.entity.Assignment;
import com.epms.vo.AssignmentVo;

public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
	
	@Query("SELECT SUM(a.allocationPercentage) FROM Assignment a WHERE a.employee.id = :employeeId AND a.id != :id")
	Integer findAllocationPercentageByEmployeeId(@Param("employeeId") int employeeId, @Param("id") int id);
	
	@Query("SELECT a.employee.employeeId FROM Assignment a WHERE a.project.projectId = :projectId AND a.id != :id")
	List<Integer> findEmployeeIdByProjectId(@Param("projectId") int projectId, @Param("id") int id);
	
	@Query("SELECT a.allocationPercentage FROM Assignment a WHERE a.employee.id = :employeeIds")
	long findAllocationPercentageById(@Param("employeeIds") List<Integer> employeeIds);

	@Query("SELECT a FROM Assignment a WHERE a.project.projectId = :projectId AND a.id != :id")
	ArrayList<Assignment> findByProjectId(@Param("projectId") int projectId, @Param("id") int id);
	
	
}