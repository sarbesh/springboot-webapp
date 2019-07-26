package com.sarbesh.webapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restController {
	@Value("${spring.application.name}")
	String appName;
	
	@GetMapping("/api/index")
	public String indexPage(Model model) {
		model.addAttribute("appName", appName);
		return appName;
	}
	
	@GetMapping("/api/login")
	public String loginPage() {
		
		return "login";
	}

}
