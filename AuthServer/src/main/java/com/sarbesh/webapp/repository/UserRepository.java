package com.sarbesh.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarbesh.webapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
