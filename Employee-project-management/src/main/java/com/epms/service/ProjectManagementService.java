package com.epms.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.epms.entity.Assignment;
import com.epms.entity.Employee;
import com.epms.entity.Project;
import com.epms.repository.AssignmentRepo;
import com.epms.repository.EmployeeRepo;
import com.epms.repository.ProjectRepo;
import com.epms.vo.AssignmentVo;
import com.epms.vo.EmployeeVo;
import com.epms.vo.ProjectVo;

@Service
public class ProjectManagementService {
	
	@Autowired
	EmployeeRepo employeeRepo; 
	@Autowired
	ProjectRepo projectRepo;
	@Autowired
	AssignmentRepo assignmentRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
//	create employee
	public String addEmployee(EmployeeVo employeeDetails) {
		Employee  addDetails=modelMapper.map(employeeDetails, Employee.class);
		employeeRepo.save(addDetails);
		return "employee added successfully";
	}
	
//	create project
	public String addProject(ProjectVo projectDetails) {
		Project addProject=modelMapper.map(projectDetails, Project.class);
		projectRepo.save(addProject);
		return "project added successfully";
	}
	
//	assign employee to project
	public String assignEmployeeToProject(AssignmentVo assignmentDetails) {
		Employee employee = employeeRepo.findById(assignmentDetails.getEmployeeId()).get();
		Project project = projectRepo.findById(assignmentDetails.getProjectId()).get();
		int sumOfAllocationPercentage=assignmentRepo.findAllocationPercentageByEmployeeId(employee.getId());
		int sumOfSalary=employeeRepo.findSalaryByEmployeeId(employee.getId());
		int budget=projectRepo.findBudgetByProjectId(employee.getId());
		if(sumOfAllocationPercentage + assignmentDetails.getAllocationPercentage() <= 100 || sumOfSalary +employee.getSalary() <=budget ) {
			Assignment assignEmployee = new Assignment();
			assignEmployee.setEmployee(employee);
			assignEmployee.setProject(project);
			assignEmployee.setRole(assignmentDetails.getRole());
			assignEmployee.setAllocation_percentage(assignmentDetails.getAllocationPercentage());
			assignmentRepo.save(assignEmployee);
			return "assign employee to project successfully";
		}
		else {
	        return "Allocation percentage exceeds limit.";
	    }

	}
	
}
