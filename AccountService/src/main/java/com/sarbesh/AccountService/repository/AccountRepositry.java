package com.sarbesh.AccountService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sarbesh.AccountService.model.Profile;

@RepositoryRestResource
public interface AccountRepositry extends JpaRepository<Profile, Long>{
	
	List<Profile> findByFirstName(String firstName);
	
	List<Profile> findByLastName(String lastName);
	
//	List<Profile> findByEmail(String email);
	
	Profile findByEmail(String email);

}
