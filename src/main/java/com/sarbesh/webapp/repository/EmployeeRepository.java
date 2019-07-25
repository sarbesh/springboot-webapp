package com.sarbesh.webapp.repository;

import com.sarbesh.webapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
//import org.springframework.stereotype.Repository;

@RepositoryRestController
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
