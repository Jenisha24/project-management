package com.epms.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epms.entity.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {

	@Query("SELECT p.budget FROM Project p WHERE p.projectId = :projectId")
	Integer findBudgetByProjectId(@Param("projectId") int projectId);

	@Query(value = "SELECT p.project_id, p.budget, p.end_date, p.project_name, p.start_date, a.employee_id, a.role,e.salary "
			+ "FROM project p " + "LEFT JOIN assignment a ON p.project_id = a.project_id "
			+ "LEFT JOIN employee e ON a.employee_id = e.employee_id", nativeQuery = true)
	List<Object[]> getAllProjectDetails();

	@Query("SELECT p.startDate FROM Project p WHERE p.id = :projectId")
	LocalDate findStartDateByProjectId(@Param("projectId") int projectId);

	@Query("SELECT p.endDate FROM Project p WHERE p.projectId = :projectId ")
	LocalDate findendDateByProjectId(@Param("projectId") int projectId);

}
