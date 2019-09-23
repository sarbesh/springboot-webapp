package com.sarbesh.AuthService.services;

import com.sarbesh.AuthService.model.LoginViewModel;
import com.sarbesh.AuthService.model.User;
import com.sarbesh.AuthService.repository.UserRepository;
import com.sarbesh.AuthService.security.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.System.*;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepo;
    private LoginViewModel loginModel;

    @Override
    @Transactional
    public String loginUser(LoginViewModel loginModel) {
        this.loginModel = loginModel;
        out.println("In loginUser");
        String userName = loginModel.getUserName();
        String password = loginModel.getPassword();
        out.println(userName + " " + password);
        User usr;
        if (!userName.contains("@")) {
            long id = Integer.parseInt(userName);
            out.println(id);
            usr = userRepo.findById(id).orElse(null);

        } else {
            usr = userRepo.findByEmail(userName).orElse(null);
            out.println(usr.toString());
        }
        String result;
        if (usr == null) {
            throw new RuntimeException("Employee Not Found");
        } else if (password.equals(usr.getPassword())) {
            String token = Authentication.generateToken(usr);
            out.println("token: " + token);
            result = token;
        } else {
            throw new RuntimeException("Incorrect User or Password");
        }
        return result;
    }

    @Override
    public void logoutUser(String token) {

    }

    @Override
    public boolean checkLogin(String token) {
        return false;
    }
}
