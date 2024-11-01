package com.epms.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
import com.epms.vo.EmployeeAndProjectVo;
import com.epms.vo.EmployeeDetailsVo;
import com.epms.vo.EmployeeProjectDataVo;
import com.epms.vo.EmployeeVo;
import com.epms.vo.ProjectDataVo;
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
		if (projectDetails.getEndDate().isBefore(projectDetails.getStartDate())) {
			return "Error: End date cannot be before start date.";
		}
		Project addProject = modelMapper.map(projectDetails, Project.class);
		projectRepo.save(addProject);
		return "project added successfully";
	}

//	assign employee to project and update assignment
	public String assignEmployeeToProjectAndUpdate(AssignmentVo assignmentDetails) {
		Employee employee = employeeRepo.findById(assignmentDetails.getEmployeeId()).get();
		Project project = projectRepo.findById(assignmentDetails.getProjectId()).get();
		Integer sumOfAllocationPercentage = assignmentRepo
				.findAllocationPercentageByEmployeeId(employee.getEmployeeId(), assignmentDetails.getAssignmentId());
		if (sumOfAllocationPercentage == null) {
			sumOfAllocationPercentage = 0;
		}

		long totalMonths = ChronoUnit.MONTHS.between(project.getStartDate(), project.getEndDate()) + 1;
		ArrayList<Assignment> assignmentData = assignmentRepo.findByProjectId(assignmentDetails.getProjectId(),
				assignmentDetails.getAssignmentId());
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
		List<Project> projectDetails = projectRepo.findAll();

		List<ProjectDetailsVo> projectDetailWithEmployees = new ArrayList<>();

		for (Project projectDetail : projectDetails) {
			ProjectDetailsVo projectdetail = modelMapper.map(projectDetail, ProjectDetailsVo.class);
			List<Assignment> assignmentData = assignmentRepo.findAssignmentByProjectId(projectDetail.getId());
			List<EmployeeProjectDataVo> employeeProjectDataVoList = new ArrayList<>();
			for (Assignment employeeDetail : assignmentData) {
				EmployeeProjectDataVo employeeProjectDataVo = new EmployeeProjectDataVo();
				employeeProjectDataVo.setEmployeeId(employeeDetail.getEmployee().getEmployeeId());
				employeeProjectDataVo.setRoles(employeeDetail.getRole());
				employeeProjectDataVo.setSalary(employeeDetail.getEmployee().getSalary());
				employeeProjectDataVoList.add(employeeProjectDataVo);
			}
			projectdetail.setEmployeeProjectDataVo(employeeProjectDataVoList);
			projectDetailWithEmployees.add(projectdetail);
		}
		return projectDetailWithEmployees;
	}

//	get employee details with projects
	public List<EmployeeDetailsVo> getEmployeeDetailsWithProjects() {
		List<Employee> employeeDetails = employeeRepo.findAll();

		List<EmployeeDetailsVo> employeeDetailWithprojects = new ArrayList<>();

		for (Employee employeeDetail : employeeDetails) {
			EmployeeDetailsVo employeeDetailVo = modelMapper.map(employeeDetail, EmployeeDetailsVo.class);
			List<Assignment> assignmentData = assignmentRepo.findAssignmentByEmployeeId(employeeDetail.getEmployeeId());
			List<ProjectDataVo> projectDataVoList = new ArrayList<>();
			for (Assignment projectDetail : assignmentData) {
				ProjectDataVo projectDataVo = new ProjectDataVo();
				projectDataVo.setProjectId(projectDetail.getProject().getId());
				projectDataVo.setRoles(projectDetail.getRole());
				projectDataVo.setAllocationPercentage(projectDetail.getAllocationPercentage());
				projectDataVoList.add(projectDataVo);
			}
			employeeDetailVo.setProjectDataVo(projectDataVoList);
			employeeDetailWithprojects.add(employeeDetailVo);
		}
		return employeeDetailWithprojects;
	
	}

//	get assignment details
	public List<AssignmentVo> getAssignment() {
		List<Assignment> assignment = assignmentRepo.findAll();
		List<AssignmentVo> assignmentDetails = assignment.stream()
				.map(assignments -> modelMapper.map(assignments, AssignmentVo.class)).toList();
		return assignmentDetails;
	}

//	remove employee
	public String removeEmployeeFromProject(EmployeeAndProjectVo employeeAndProjectIds) {
		Assignment assignment = assignmentRepo.findAssignmentByEmployeeIdAndProjectId(
				employeeAndProjectIds.getEmployeeId(), employeeAndProjectIds.getProjectId());
		assignmentRepo.delete(assignment);
		return "successfully removed employee from the project";
	}

//	get top allocated employees
	public List<EmployeeVo> getEmployees() {
		List<Object[]> sumOfAllocationPercentOfEmployee = assignmentRepo.FindSumOfAllocationPercentage();
		List<EmployeeVo> ListOfEmployee = new ArrayList<>();
		for (Object[] employeeData : sumOfAllocationPercentOfEmployee) {
			Employee employeeDetails = employeeRepo.findById((int) employeeData[0]).get();
			EmployeeVo employee = modelMapper.map(employeeDetails, EmployeeVo.class);
			employee.setTotalAllocation((long) employeeData[1]);
			ListOfEmployee.add(employee);
		}
		return ListOfEmployee;
	}

//	project budget utilization
	public List<ProjectVo> getProjects() {
		List<Project> projectDetails = projectRepo.findAll();
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
