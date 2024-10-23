package com.epms.vo;

import com.epms.entity.Employee;

public class AssignmentVo {
	private int Id;
	private int employeeId;
	private int projectId;
	private String role;
	private int allocationPercentage;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAllocationPercentage() {
		return allocationPercentage;
	}
	public void setAllocationPercentage(int allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}
	public Integer getId() {
		return null;
	}
	public void setId(int id) {
		Id = id;
	}
	
	
}
