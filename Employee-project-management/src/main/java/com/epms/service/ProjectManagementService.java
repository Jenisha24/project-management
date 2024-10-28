package com.epms.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.entity.Assignment;
import com.epms.entity.Employee;
import com.epms.entity.Project;
import com.epms.repository.AssignmentRepo;
import com.epms.repository.EmployeeRepo;
import com.epms.repository.ProjectRepo;
import com.epms.vo.AssignmentVo;
import com.epms.vo.EmployeeDetailsVo;
import com.epms.vo.EmployeeVo;
import com.epms.vo.ProjectDetailsVo;
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
		Employee addDetails = modelMapper.map(employeeDetails, Employee.class);
		employeeRepo.save(addDetails);
		return "employee added successfully";
	}

//	create project
	public String addProject(ProjectVo projectDetails) {
		Project addProject = modelMapper.map(projectDetails, Project.class);
		projectRepo.save(addProject);
		return "project added successfully";
	}

//	assign employee to project and update assignment
	public String assignEmployeeToProjectAndUpdate(AssignmentVo assignmentDetails) {
		Employee employee = employeeRepo.findById(assignmentDetails.getEmployeeId()).get();
		Project project = projectRepo.findById(assignmentDetails.getProjectId()).get();
		Integer sumOfAllocationPercentage = assignmentRepo.findAllocationPercentageByEmployeeId(employee.getEmployeeId(),assignmentDetails.getAssignmentId());
		if (sumOfAllocationPercentage == null) {
			sumOfAllocationPercentage = 0;
		}
		
		long totalMonths = ChronoUnit.MONTHS.between(project.getStartDate(), project.getEndDate()) + 1;
		ArrayList<Assignment> assignmentData = assignmentRepo.findByProjectId(assignmentDetails.getProjectId(), assignmentDetails.getAssignmentId());
		double sumOfSalary = assignmentData.stream().mapToDouble(assignData -> assignData.getEmployee().getSalary()
				* ((double) totalMonths * assignData.getAllocationPercentage() / 100)).sum();
		double months = totalMonths * (assignmentDetails.getAllocationPercentage() / 100.0);
		if (sumOfAllocationPercentage + assignmentDetails.getAllocationPercentage() > 100) {
			return "Allocation percentage exceeds limit.";
		} else if (sumOfSalary + (months * employee.getSalary()) > project.getBudget()) {
			return "Salary exceeds budget";
		} else {
			if (assignmentDetails.getAssignmentId() == 0) {
				Assignment assignEmployee = new Assignment();
				assignEmployee.setEmployee(employee);
				assignEmployee.setProject(project);
				assignEmployee.setRole(assignmentDetails.getRole());
				assignEmployee.setAllocationPercentage(assignmentDetails.getAllocationPercentage());
				assignmentRepo.save(assignEmployee);
				return "assign employee to project successfully";
			} else {
				Assignment getDetails = assignmentRepo.findById(assignmentDetails.getAssignmentId()).get();
				getDetails = modelMapper.map(assignmentDetails, Assignment.class);
				assignmentRepo.save(getDetails);
				return "assigment updated successfully";
			}
		}

	}

//  get project details with employees
	public List<ProjectDetailsVo> getProjectDetailsWithEmployees() {
		List<Object[]> allDetails = projectRepo.getAllProjectDetails();
		List<ProjectDetailsVo> projectDetails = (List<ProjectDetailsVo>) allDetails.stream()
				.map(allDetail -> new com.epms.vo.ProjectDetailsVo(
						(int) allDetail[0], (int) allDetail[1],
						((Date) allDetail[2]).toLocalDate(), 
						(String) allDetail[3],
						((Date) allDetail[4]).toLocalDate(),
						(int)( allDetail[5] != null ? (int) allDetail[5] : 0), 
						(String) allDetail[6], 
						(int)( allDetail[7] != null ? (int) allDetail[7] : 0)
				)).collect(Collectors.toList());

		return projectDetails;
	}

//	get employee details with projects
	public List<EmployeeDetailsVo> getEmployeeDetailsWithProjects() {
		List<Object[]> allDetails = employeeRepo.getAllEmployeetDetails();
		List<EmployeeDetailsVo> employeeDetails = (List<EmployeeDetailsVo>) allDetails.stream()
				.map(allDetail -> new com.epms.vo.EmployeeDetailsVo(
						(int) allDetail[0],
						(String) allDetail[1],
						(String) allDetail[2],
						(String) allDetail[3],
						(String) allDetail[4], 
						(int) allDetail[5],
						(int) (allDetail[6] != null ? (int) allDetail[6] : 0), 
						(String) allDetail[7],
						(int) (allDetail[8] != null ? (int) allDetail[8] : 0)

				)).collect(Collectors.toList());

		return employeeDetails;
	}

//	get assignment details
	public AssignmentVo getAssignment(int id) {
		Assignment assignment = assignmentRepo.findById(id).get();
		AssignmentVo assignmentDetails = modelMapper.map(assignment, AssignmentVo.class);
		return assignmentDetails;

	}

//	remove employee
	public String deleteEmployee(int id) {
		assignmentRepo.deleteById(id);
		return "employee deleted successfully";
	}

//	get top allocated employees
	public List<EmployeeVo> getEmployees() {
		List<Object[]> sumOfAllocationPercentageWithEmployee = assignmentRepo.FindSumOfAllocationPercentage();
		List<EmployeeVo> ListOfEmployee = new ArrayList<>();
		for (Object[] employees : sumOfAllocationPercentageWithEmployee) {
			int employeeId = (int) employees[0];
			Employee employeeDetails = employeeRepo.findById(employeeId).get();
			EmployeeVo employee = modelMapper.map(employeeDetails, EmployeeVo.class);
			ListOfEmployee.add(employee);
		}
		return ListOfEmployee;
	}

//	project budget utilization
	public List<ProjectVo> getProjects() {
		List<Project> projectDetails = projectRepo.getProjectDetails();
		List<ProjectVo> listOfProject = new ArrayList<>();
		for (Project detail : projectDetails) {
			ProjectVo project = modelMapper.map(detail, ProjectVo.class);
			long totalMonths = ChronoUnit.MONTHS.between(project.getStartDate(), project.getEndDate()) + 1;
			List<Assignment> assignmentData = assignmentRepo.findAssignmentByProjectId(project.getProjectId());
			double totalCost = assignmentData.stream().mapToDouble(assignData -> assignData.getEmployee().getSalary()
					* ((double) totalMonths * assignData.getAllocationPercentage() / 100)).sum();
			double percentageOfBudgetUtilized = (totalCost / project.getBudget()) * 100;
			project.setTotalCost(totalCost);
			project.setPercentageOfBudgetUtilized(percentageOfBudgetUtilized);
			listOfProject.add(project);
		}
		return listOfProject;
	}

}
