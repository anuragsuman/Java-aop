package com.javatpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.model.Employee;
import com.javatpoint.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/add/employee", method = RequestMethod.GET)
	public ResponseEntity<Employee> addEmployee(@RequestParam("empId") String empId,
												@RequestParam("firstName") String firstName,
												@RequestParam("secondName") String secondName) throws Exception{
		ResponseEntity<Employee> employee = null;
		try{
			employee = employeeService.createEmployee(empId,firstName,secondName);
		}catch (Exception e){
			System.out.println(1111);
		}
		return employee;

	}

	@RequestMapping(value = "/remove/employee", method = RequestMethod.GET)
	public String removeEmployee( @RequestParam("empId") String empId) {

		employeeService.deleteEmployee(empId);

		return "Employee removed";
	}

}