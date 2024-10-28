package com.epms.vo;

import java.time.LocalDate;

public class ProjectVo {
	private int projectId;
	private String projectName;
	private LocalDate startDate;
	private LocalDate endDate;
	private int budget;
	private double totalCost;
	private double percentageOfBudgetUtilized;
	
	
	
	
	public ProjectVo(int projectId,int budget,LocalDate endDate,String projectName,LocalDate startDate,double totalCost, double percentageOfBudgetUtilized) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.budget = budget;
		this.totalCost = totalCost;
		this.percentageOfBudgetUtilized = percentageOfBudgetUtilized;
	}
	public ProjectVo() {
		super();
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
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getPercentageOfBudgetUtilized() {
		return percentageOfBudgetUtilized;
	}
	public void setPercentageOfBudgetUtilized(double percentageOfBudgetUtilized) {
		this.percentageOfBudgetUtilized = percentageOfBudgetUtilized;
	}
	
	
}	