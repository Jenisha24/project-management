package com.epms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epms.entity.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	
	@Query("SELECT SUM(CAST(:months AS double) * e.salary) FROM Employee e WHERE e.employeeId IN (:employeeIds)")
	Double findTotalSalaryByEmployeeIds(@Param("months") double months, @Param("employeeIds") List<Integer> employeeIds);
	
	@Query("SELECT e.salary FROM Employee e WHERE e.employeeId = :employeeId")
	Integer findSalaryByEmployeeId(@Param("employeeId") int employeeId);
	
	@Query(value = "SELECT e.employee_id, e.first_name, e.last_name ,e.email, e.department, e.salary, p.project_id, a.role, a.allocation_percentage "
			+ "FROM employee e " + "LEFT JOIN assignment a ON e.employee_id = a.employee_id "
			+ "LEFT JOIN project p ON a.project_id = p.project_id", nativeQuery = true)
	List<Object[]> getAllEmployeetDetails();

}
