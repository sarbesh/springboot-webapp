package com.sarbesh.webapp.repository;

import com.sarbesh.webapp.model.EmployeeInfo;
import com.sarbesh.webapp.model.Employee;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long> {
	List<EmployeeInfo> findByFirstName(String firstName);
	
	List<EmployeeInfo> findByLastName(String lastName);
	
//	List<EmployeeInfo> findByEmail(String email);
	
	EmployeeInfo findByEmail(String email);
	
	List<EmployeeInfo> findByDesignation(String designation);
}
