package com.epms.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
import com.epms.vo.ProductDetailsVo;
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
		Integer sumOfAllocationPercentage=assignmentRepo.findAllocationPercentageByEmployeeId(employee.getId());
		if (sumOfAllocationPercentage == null) {
	        sumOfAllocationPercentage = 0; 
	    }
		List<Integer> employeeIds=assignmentRepo.findEmployeeIdByProjectId(assignmentDetails.getProjectId());
		LocalDate startDate=projectRepo.findStartDateByProjectId(assignmentDetails.getProjectId());
		LocalDate endDate=projectRepo.findendDateByProjectId(assignmentDetails.getProjectId());
        long totalMonths = ChronoUnit.MONTHS.between(startDate, endDate)+1;
        double months = totalMonths * (assignmentDetails.getAllocationPercentage() / 100.0);
        Double sumOfSalary= employeeRepo.findTotalSalaryByEmployeeIds(months, employeeIds); 
        if (sumOfSalary == null) {
            sumOfSalary = 0.0;
        }
		Integer budget=projectRepo.findBudgetByProjectId(assignmentDetails.getProjectId());
		if (budget == null) {
			budget = 0; 
	    }
		int salary=employeeRepo.findSalaryByEmployeeId(assignmentDetails.getEmployeeId());
		if(sumOfAllocationPercentage + assignmentDetails.getAllocationPercentage() > 100  ) {
	        return "Allocation percentage exceeds limit.";
		}
		else if(sumOfSalary + (months*salary) > budget){
			return "Salary exceeds budget";
		}
		else {
			Assignment assignEmployee = new Assignment();
			assignEmployee.setEmployee(employee);
			assignEmployee.setProject(project);
			assignEmployee.setRole(assignmentDetails.getRole());
			assignEmployee.setAllocation_percentage(assignmentDetails.getAllocationPercentage());
			assignmentRepo.save(assignEmployee);
			return "assign employee to project successfully";
	    }

	}
	
//  get product details with employees
	public List<Object[]> getProductDetailsWithEmployees() {
		 List<Object[]> allDetails=projectRepo.getAllProjectDetails();
		return allDetails;
	}
	
}
