package com.epms.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.epms.entity.Assignment;

public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
	
	@Query("SELECT SUM(a.allocationPercentage) FROM Assignment a WHERE a.employee.id = :employeeId AND a.assignmentId != :assignmentId")
	Integer findAllocationPercentageByEmployeeId(@Param("employeeId") int employeeId, @Param("assignmentId") int assignmentId);
	
	@Query("SELECT a.employee.employeeId FROM Assignment a WHERE a.project.projectId = :projectId AND a.assignmentId != :assignmentId")
	List<Integer> findEmployeeIdByProjectId(@Param("projectId") int projectId, @Param("assignmentId") int assignmentId);
	
	@Query("SELECT a.allocationPercentage FROM Assignment a WHERE a.employee.id = :employeeIds")
	long findAllocationPercentageById(@Param("employeeIds") List<Integer> employeeIds);

	@Query("SELECT a FROM Assignment a WHERE a.project.projectId = :projectId AND a.assignmentId != :assignmentId")
	ArrayList<Assignment> findByProjectId(@Param("projectId") int projectId, @Param("assignmentId") int assignmentId);

	@Query("SELECT a.employee.employeeId, SUM(a.allocationPercentage) as totalAllocation " +
		       "FROM Assignment a " +
		       "GROUP BY a.employee.employeeId " +
		       "ORDER BY totalAllocation DESC")
	List<Object[]> FindSumOfAllocationPercentage();
	
	@Query("SELECT a FROM Assignment a WHERE a.project.projectId = :projectId ")
	List<Assignment> findAssignmentByProjectId(@Param("projectId") int projectId);

	@Query("SELECT a FROM Assignment a WHERE a.employee.id = :employeeId AND a.project.id = :projectId")
    Assignment findAssignmentByEmployeeIdAndProjectId(@Param("employeeId") int employeeId, @Param("projectId") int projectId);
	
	@Query("SELECT a FROM Assignment a WHERE a.employee.employeeId = :employeeId ")
	List<Assignment> findAssignmentByEmployeeId(@Param("employeeId") int employeeId);
	
	

	
	
}