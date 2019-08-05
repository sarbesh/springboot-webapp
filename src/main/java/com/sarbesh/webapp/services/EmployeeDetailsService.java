package com.sarbesh.webapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sarbesh.webapp.model.Employee;
import com.sarbesh.webapp.model.EmployeeInfo;
import com.sarbesh.webapp.repository.EmployeeInfoRepository;
import com.sarbesh.webapp.repository.EmployeeRepository;

@Service
public class EmployeeDetailsService implements UserDetailsService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private EmployeeInfoRepository empInfoRepo;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	EmployeeInfo employee = empInfoRepo.findByEmail(arg0);
	if(employee==null)
		throw new UsernameNotFoundException("User 404");
		return null;
	}
	
	public UserDetails loadUserById(long arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	Employee employee = empRepo.findById(arg0);
	if(employee==null)
		throw new UsernameNotFoundException("Id 404");
		return new EmployeePrincipal(employee);
	}
	
//	public UserDetails loadUserByEmail(String arg0) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		EmployeeInfo employee = empInfoRepo.findByEmail(arg0);
//		if(employee==null)
//			throw new UsernameNotFoundException("User 404");
//		return null;
//	}

}
