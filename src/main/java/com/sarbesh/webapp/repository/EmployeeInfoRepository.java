package com.sarbesh.webapp.repository;

import com.sarbesh.webapp.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long> {

}
