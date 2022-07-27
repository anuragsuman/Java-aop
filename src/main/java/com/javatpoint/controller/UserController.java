package com.javatpoint.controller;

import com.javatpoint.model.Employee;
import com.javatpoint.model.User;
import com.javatpoint.repository.UserRepository;
import com.javatpoint.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/getuser", method = RequestMethod.GET)
	public User getUser(){
		Optional<User> user = null;
		try{
			user = userRepository.findById(1);
		}catch (Exception e){
			System.out.println(1111);
		}
		return user.get();

	}

}