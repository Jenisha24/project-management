package com.epms.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.epms.entity.Project;

public class ProjectDetailsVo {
	
	private int projectId;
	private String projectName;
	private LocalDate startDate;
	private LocalDate endDate;
	private int budget;
	private int employeeeID;
	private String roles;
	private int salary;
	
	public ProjectDetailsVo(int projectId, int budget, LocalDate startDate, String string,
			LocalDate localDate, int employeeeID, String roles, int salary) {
		this.projectId = projectId;
		this.projectName = string;
		this.startDate = startDate;
		this.endDate = localDate;
		this.budget = budget;
		this.employeeeID = employeeeID;
		this.roles = roles;
		this.salary = salary;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
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
	public int getEmployeeeID() {
		return employeeeID;
	}
	public void setEmployeeeID(int employeeeID) {
		this.employeeeID = employeeeID;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	
}	