package com.sarbesh.AuthService.controller;

import java.util.List;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netflix.discovery.converters.Auto;
import com.sarbesh.AuthService.model.StringMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sarbesh.AuthService.model.User;
import com.sarbesh.AuthService.model.LoginViewModel;

import com.sarbesh.AuthService.repository.UserRepository;

import com.sarbesh.AuthService.services.UserService;

import static java.lang.System.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class AuthRestController {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private StringMessage stringMessage;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/profiles")
	public List<User> search(){
		return userRepo.findAll();
	}
	
	@GetMapping("/delete/{id}")
	public String delProfile(@PathVariable("id") long id) {
		userRepo.deleteById(id);
		return "Success";
	}
	
	@GetMapping("/delete")
	public String delProfiles() {
		userRepo.deleteAll();
		return "Success";
	}

	@PostMapping(value = "/login", produces = "application/json")
	public StringMessage loginUser(@RequestBody LoginViewModel loginModel){
		String s = userService.loginUser(loginModel);
		stringMessage.setMessage(s);
		return stringMessage;
	}

	@GetMapping("/logout")
	public StringMessage logout(){

		stringMessage.setMessage("Successfully logged out");
		return stringMessage;
	}
	
	@GetMapping("/user/{id}")
	public User profile(@PathVariable("id") long id) {
		return userRepo.findById(id).orElse(null);
	}
	
	@PostMapping("/register")
	public User profile(@RequestBody User newUser) {
		return userRepo.save(newUser);
	}
	
}
