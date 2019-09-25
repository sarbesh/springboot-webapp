package com.sarbesh.AuthService.controller;

import java.util.List;

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

	@PostMapping("/login")
	public String loginUser(@RequestBody LoginViewModel loginModel){
		out.println(loginModel.getUserName()+" "+loginModel.getPassword());
		String s = userService.loginUser(loginModel);
		out.println(s);
		return s;
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