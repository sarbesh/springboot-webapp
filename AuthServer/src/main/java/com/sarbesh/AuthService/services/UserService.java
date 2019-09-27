package com.sarbesh.AuthService.services;

import com.sarbesh.AuthService.model.LoginViewModel;

public interface UserService {
    String loginUser(LoginViewModel loginModel);
    boolean logoutUser(String token);
    boolean checkLogin(String token);
}

