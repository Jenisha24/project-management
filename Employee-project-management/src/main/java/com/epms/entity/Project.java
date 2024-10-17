package com.epms.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Project {
	@Id  
	private int projectId;
	private String projectName;
	private LocalDate startDate;
	private LocalDate endDate;
	private int budget;
	public Project() {
		super();
	}
	public Project(int id, String name, LocalDate startDate, LocalDate endDate, int budget) {
		super();
		this.projectId = id;
		this.projectName = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.budget = budget;
	}
	public int getId() {
		return projectId;
	}
	public void setId(int id) {
		this.projectId = id;
	}
	public String getName() {
		return projectName;
	}
	public void setName(String name) {
		this.projectName = name;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStart_date(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	
}
