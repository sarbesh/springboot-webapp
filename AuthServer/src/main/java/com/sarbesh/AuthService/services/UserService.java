package com.sarbesh.AuthService.services;

import com.sarbesh.AuthService.model.LoginViewModel;
import com.sarbesh.AuthService.model.User;

public interface UserService {
    String loginUser(LoginViewModel loginModel);
    void logoutUser(String token);
    boolean checkLogin(String token);
}

