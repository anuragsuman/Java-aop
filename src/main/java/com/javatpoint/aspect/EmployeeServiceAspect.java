package com.javatpoint.aspect;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.javatpoint.UnauthorizeException;
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

	@Pointcut(value= "execution(* com.javatpoint.service.EmployeeService.*(..))")
	public void logDisplayingBalance() {
	}

	@Before("logDisplayingBalance()")
	public void employeeValidationToken(JoinPoint joinPoint) throws  Exception{
		if(!isTokenValid()){
			throw new UnauthorizeException();
		}
	}

	private boolean isTokenValid() throws Exception {
		String headerName = httpServletRequest.getHeader("access_token");
		try {
			if (headerName.equalsIgnoreCase("123")) {
				return true;
			}else{
				return false;
			}
		}catch (Exception e){
			throw new UnauthorizeException();
		}
	}

	@Around("logDisplayingBalance()")
	public Object handleError(ProceedingJoinPoint jp) throws Throwable{
		String headerName = httpServletRequest.getHeader("access_token");
		Employee error = new Employee();
		error.setErrorCode("INVALID_TOKEN");
		error.setErrorMsg("token is not valid..............");
		try{
			return jp.proceed();
		}catch (UnauthorizeException e) {
			if (jp.getSignature().getName().equalsIgnoreCase("createEmployee")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
			}
		}
		return  null;
	}


}
