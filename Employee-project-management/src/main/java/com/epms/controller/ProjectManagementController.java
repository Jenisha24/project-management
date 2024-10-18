package com.epms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epms.service.ProjectManagementService;
import com.epms.vo.AssignmentVo;
import com.epms.vo.EmployeeVo;
import com.epms.vo.ProjectVo;

@RestController
@RequestMapping("/epms")
public class ProjectManagementController {
	
	@Autowired
	ProjectManagementService projectManagementService;
	
	@PostMapping("employee")
	public String addEmployee(@RequestBody EmployeeVo employeeDetails) {
		return projectManagementService.addEmployee(employeeDetails);
	}
	
	@PostMapping("project") 
	public String addProject(@RequestBody ProjectVo projectDetails) {
		return projectManagementService.addProject(projectDetails);
	}
	
	@PostMapping("assignment")
	public String assignEmployeeToProject(@RequestBody AssignmentVo assignmentDetails) {
		return projectManagementService.assignEmployeeToProject(assignmentDetails);
	}
}
