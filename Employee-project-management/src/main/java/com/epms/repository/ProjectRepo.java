package com.epms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epms.entity.Project;
@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer>{
	
	@Query("SELECT p.budget FROM Project p WHERE p.id = :projectId")
	int findBudgetByProjectId(@Param("projectId") int projectId);

}
