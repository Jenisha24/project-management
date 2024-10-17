package com.epms.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.epms.entity.Employee;
import com.epms.repository.EmployeeRepo;
import com.epms.vo.EmployeeVo;

@Service
public class ProjectManagementService {
	
	@Autowired
	EmployeeRepo employeeRepo; 
	
	@Autowired
	ModelMapper modelMapper;
	
	public String addEmployee(EmployeeVo employeeDetails) {
		Employee  addDetails=new Employee();
		addDetails=modelMapper.map(employeeDetails, Employee.class);
		employeeRepo.save(addDetails);
		return "employee added successfully";
	}
}
