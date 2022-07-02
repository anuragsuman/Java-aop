package com.javatpoint.aspect;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.javatpoint.model.Employee;
import com.javatpoint.model.Error;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class EmployeeServiceAspect {

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Pointcut(value= "execution(* com.javatpoint.service.EmployeeService.*(..)) and args(empId, fname, sname)")
	private void logDisplayingBalance() throws Exception {
	}

	@Around("logDisplayingBalance()")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		String headerName = httpServletRequest.getHeader("access_token");
		if(!headerName.equalsIgnoreCase("123")){
			System.out.println("1232");
			System.out.println(jp.proceed().getClass().getCanonicalName());
			Error error = new Error();
			//commit
			error.setErrorDesc("token is not valid");
			return error;
		}
		return  null;
	}


}
