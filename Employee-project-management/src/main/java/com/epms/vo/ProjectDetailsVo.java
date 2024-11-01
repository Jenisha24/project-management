package com.epms.vo;

import java.time.LocalDate;
import java.util.List;

public class ProjectDetailsVo {
	
	private int projectId;
	private String projectName;
	private LocalDate startDate;
	private LocalDate endDate;
	private int budget;
	private List<EmployeeProjectDataVo> employeeProjectDataVo;
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
	public List<EmployeeProjectDataVo> getEmployeeProjectDataVo() {
		return employeeProjectDataVo;
	}
	public void setEmployeeProjectDataVo(List<EmployeeProjectDataVo> employeeProjectDataVo) {
		this.employeeProjectDataVo = employeeProjectDataVo;
	}
	
	
	
}	