package com.javatpoint.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Employee {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String empId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String firstName;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String secondName;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String errorMsg;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String errorCode;

	public Employee() {
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
