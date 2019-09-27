package com.sarbesh.AuthService.controller;

import com.sarbesh.AuthService.model.LoginViewModel;
import com.sarbesh.AuthService.model.StringMessage;
import com.sarbesh.AuthService.model.User;
import com.sarbesh.AuthService.repository.UserRepository;
import com.sarbesh.AuthService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
	public StringMessage logout(HttpServletRequest req) throws RuntimeException {
		try {
			String authToken = req.getHeader("Token");
			if (authToken!=null){
			userService.logoutUser(authToken);
			stringMessage.setMessage("Successfully logged out");
			} else {
				throw  new RuntimeException("No token found in header");
			}
		} catch (NullPointerException e) {
			System.out.println("Token : Null Exception");
		}

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
