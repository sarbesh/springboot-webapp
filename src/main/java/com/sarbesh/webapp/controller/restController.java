package com.sarbesh.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarbesh.webapp.model.Employee;
import com.sarbesh.webapp.model.EmployeeInfo;
//import com.sarbesh.webapp.model.EmployeeInfo;
import com.sarbesh.webapp.repository.EmployeeInfoRepository;
import com.sarbesh.webapp.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class restController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private EmployeeInfoRepository empInfoRepo;
	
	@Value("${spring.application.name}")
	String appName;
	
	@GetMapping("/index")
	public String indexPage(Model model) {
//		model.addAttribute("appName", appName);
		return appName;
	}
	
	@PostMapping("/login")
	public String loginPage(@RequestBody Employee empLogin) {
		Long id = empLogin.getId();
//		if (empLogin.getPassword().equals(empRepo.findById(id).get)
//		empRepo.save(empLogin);
		return "true";
	}
	
	@PostMapping(path="/register")
	public EmployeeInfo EmployeeRegistrar(@RequestBody EmployeeContext newEmployeeContext) {
		Employee user = newEmployeeContext.getEmployee();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		 empRepo.save(user);
		return empInfoRepo.save(newEmployeeContext.getEmployeeInfo());
	}
}
