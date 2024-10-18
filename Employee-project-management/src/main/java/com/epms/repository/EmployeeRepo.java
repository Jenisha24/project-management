package com.epms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epms.entity.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	
	@Query("SELECT SUM(e.salary) FROM Employee e WHERE e.id = :employeeId")
	int findSalaryByEmployeeId(@Param("employeeId") int employeeId);

}
