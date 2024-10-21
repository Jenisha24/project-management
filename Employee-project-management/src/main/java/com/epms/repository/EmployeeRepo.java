package com.epms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epms.entity.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	
	@Query("SELECT SUM(e.salary) FROM Employee e WHERE e.employeeId IN :employeeIds")
	Integer findTotalSalaryByEmployeeIds(@Param("employeeIds") List<Integer> employeeIds);
	
	@Query("SELECT e.salary FROM Employee e WHERE e.employeeId = :employeeId")
	Integer findSalaryByEmployeeId(@Param("employeeId") int employeeId);
}
