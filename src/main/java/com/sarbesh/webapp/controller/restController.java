package com.sarbesh.webapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sarbesh.webapp.model.Employee;
import com.sarbesh.webapp.model.EmployeeInfo;
//import com.sarbesh.webapp.model.EmployeeInfo;
import com.sarbesh.webapp.repository.EmployeeInfoRepository;
import com.sarbesh.webapp.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class restController {
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	
	
	@PostMapping(path="/register", produces = "application/json")
	public String EmployeeRegistrar(@RequestBody EmployeeContext newEmployeeContext) {
		Employee user = newEmployeeContext.getEmployee();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		 empRepo.save(user);
		 EmployeeInfo userInfo = newEmployeeContext.getEmployeeInfo();
		 empInfoRepo.save(userInfo);
		return user.toString();
	}
	
	@GetMapping(path="/employee/{id}", produces = "application/json")
	public Optional<EmployeeInfo> EmployeeDetails(@PathVariable Long id) {
		System.out.println(empInfoRepo.findById(id));
		return empInfoRepo.findById(id);
	}
	
	@GetMapping("/employees")
	public List<EmployeeInfo> EmployeesList() {
		return empInfoRepo.findAll();
	}
	
	@RequestMapping(value = "/username", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }
}
