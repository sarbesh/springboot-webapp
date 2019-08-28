package com.sarbesh.AuthService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarbesh.AuthService.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
