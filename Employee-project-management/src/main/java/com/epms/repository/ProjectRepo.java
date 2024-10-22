package com.epms.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epms.entity.Project;
import com.epms.vo.ProductDetailsVo;
@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer>{
	
	@Query("SELECT p.budget FROM Project p WHERE p.projectId = :projectId")
	Integer findBudgetByProjectId(@Param("projectId") int projectId);
	
	@Query(value = "SELECT p.*, a.employee_Id, a.role, e.salary " +
            "FROM Project p " +
            "LEFT JOIN Assignment a ON p.project_Id = a.project_Id " +
            "LEFT JOIN Employee e ON a.employee_Id = e.employee_Id", nativeQuery = true)
	List<Object[]> getAllProjectDetails();
	
	
	@Query("SELECT p.startDate FROM Project p WHERE p.id = :projectId")
	LocalDate findStartDateByProjectId(@Param("projectId") int projectId);
	
	@Query("SELECT p.endDate FROM Project p WHERE p.projectId = :projectId ")
	LocalDate findendDateByProjectId(@Param("projectId") int projectId);

}
