package com.epms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int assignmentId;
	@ManyToOne
    @JoinColumn(name = "employee_id")
	private Employee employee;
	@ManyToOne
    @JoinColumn(name = "project_id")
	private Project project;
	private String role;
	private int allocationPercentage;
	public Assignment(int assignmentId, Employee employee, Project project, String role, int allocationPercentage) {
		super();
		this.assignmentId = assignmentId;
		this.employee = employee;
		this.project = project;
		this.role = role;
		this.allocationPercentage = allocationPercentage;
	}
	public Assignment() {
		super();
	}
	public int getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(int id) {
		this.assignmentId = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
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
	
	
	
	
}
