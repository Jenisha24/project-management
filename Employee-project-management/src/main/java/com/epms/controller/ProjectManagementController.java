package com.epms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epms.service.ProjectManagementService;
import com.epms.vo.AssignmentVo;
import com.epms.vo.EmployeeDetailsVo;
import com.epms.vo.EmployeeVo;
import com.epms.vo.ProjectDetailsVo;
import com.epms.vo.ProjectVo;

@RestController
@RequestMapping("/epms")
public class ProjectManagementController {
	
	@Autowired
	ProjectManagementService<ProjectDetailsVo, ?> projectManagementService;
	
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
	
	@GetMapping("/project")
	public List<ProjectDetailsVo> getProjectDetailsWithEmployees(){
		return projectManagementService.getProjectDetailsWithEmployees();
	}

	@GetMapping("/employee")
	public List<EmployeeDetailsVo> getEmployeeDetailsWithProjects(){
		return projectManagementService.getEmployeeDetailsWithProjects();
	}
	
	@PutMapping("assignment")
	public String updateAssignment(@RequestBody AssignmentVo assignmentDetails) {
		return projectManagementService.updateAssignment(assignmentDetails);

	}
	
	
}
