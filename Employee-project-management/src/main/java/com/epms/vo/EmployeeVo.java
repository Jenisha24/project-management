package com.epms.vo;

import jakarta.persistence.Column;

public class EmployeeVo {
	private int employeeId;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String department;
	private int salary;
	private long totalAllocation;
	
	
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
	public long getTotalAllocation() {
		return totalAllocation;
	}
	public void setTotalAllocation(long totalAllocation) {
		this.totalAllocation = totalAllocation;
	}
	
	
	
}
