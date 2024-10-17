package com.epms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Assignment {
	@Id
	private int id;
	private int employeeId;
	private int projectId;
	private String role;
	private String allocationPercentage;
	public Assignment(int id, int employeeId, int projectId, String role, String allocationPercentage) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.projectId = projectId;
		this.role = role;
		this.allocationPercentage = allocationPercentage;
	}
	public Assignment() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getAllocation_percentage() {
		return allocationPercentage;
	}
	public void setAllocation_percentage(String allocation_percentage) {
		this.allocationPercentage = allocation_percentage;
	}
	
	
	
	
}
