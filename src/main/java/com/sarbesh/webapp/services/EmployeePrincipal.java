package com.sarbesh.webapp.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sarbesh.webapp.model.Employee;
import com.sarbesh.webapp.model.EmployeeInfo;

public class EmployeePrincipal implements UserDetails {
	
	private Employee employee;

	public EmployeePrincipal(Employee employee) {
		super();
		this.employee = employee;
	}
	
	private EmployeeInfo employeeInfo;

	public EmployeePrincipal(EmployeeInfo employeeInfo) {
		super();
		this.employeeInfo = employeeInfo;
//		employee = employee.getId(employeeInfo.getId());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
//		List<SimpleGrantedAuthority> authList = new ArrayList<>();
//        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
//        return authList;
//		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return String.valueOf(employee.getId());
	}
	
	public long getId() {
		// TODO Auto-generated method stub
		return employee.getId();
	}
	
	public String getEmail() {
		return employeeInfo.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
