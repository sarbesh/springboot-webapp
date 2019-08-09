package com.sarbesh.webapp.config;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarbesh.webapp.model.LoginViewModel;
import com.sarbesh.webapp.services.EmployeePrincipal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import rc.bootsecurity.model.LoginViewModel;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    
    @Value("${jwt.secret}")
	String signKey;
    
    @Value("${jwt.expiretime}")
    long expTime;
    
    @Value("${jwt.TOKEN_PREFIX}")
    String token_prefix;
    
    @Value("${jwt.HEADER_STRING}")
    String header_string;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /* Trigger when we issue POST request to /login
    We also need to pass in {"id":"1", "password":"abc@123$"} in the request body
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // Grab credentials and map them to login viewmodel
        LoginViewModel credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getId(),
                credentials.getPassword(),
                new ArrayList<>());

        System.out.println(credentials.getId()+", "+credentials.getPassword());
        System.out.println("token :"+authenticationToken);
        // Authenticate user
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        System.out.println("auth :"+auth);

        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // Grab principal
        EmployeePrincipal principal = (EmployeePrincipal) authResult.getPrincipal();
        System.out.println(principal);

        // Create JWT Token
        String token = JWT.create()
                .withSubject(String.valueOf(principal.getId()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expTime))
                .sign(HMAC512(signKey.getBytes()));
        System.out.println("success token :"+token);

        // Add token in response
        response.addHeader(header_string, token_prefix +  token);
    }
}
