package com.epms.vo;

import jakarta.persistence.Column;

public class EmployeeDetailsVo {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String department;
	private int salary;
	private int projectId;
	private String roles;
	private int allocationPercentage;
	public EmployeeDetailsVo(int employeeId, String firstName, String lastName, String email, String department,
			int salary, int projectId, String roles, int allocationPercentage) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
		this.salary = salary;
		this.projectId = projectId;
		this.roles = roles;
		this.allocationPercentage = allocationPercentage;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public int getAllocationPercentage() {
		return allocationPercentage;
	}
	public void setAllocationPercentage(int allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}
	
	
	
	
	
}
