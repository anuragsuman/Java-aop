package com.javatpoint.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.javatpoint.model.Employee;

@Service
public class EmployeeService {

	public ResponseEntity<Employee> createEmployee(String empId, String fname, String sname) {

		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setFirstName(fname);
		emp.setSecondName(sname);
		return ResponseEntity.ok(emp);
	}

	public void deleteEmployee(String empId) {
		
	}
}