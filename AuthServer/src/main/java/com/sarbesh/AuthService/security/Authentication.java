package com.sarbesh.AuthService.security;

import com.sarbesh.AuthService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class Authentication {
    private static HashMap<Long,String> map= new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    private Authentication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateToken(User usr) throws RuntimeException {
        long id = usr.getId();
        String name = new Authentication(restTemplate).getFullName(id);
        String token = id + "." + name + "." + usr.getEmail();
        if (map.isEmpty()||!map.containsKey(id)) {
            map.put(id, token);
        } else {
            throw new RuntimeException("User has an session active");
        }
        return token;
    }
    public boolean removeToken(String token) throws RuntimeException {
        try {
        if (map.containsValue(token)) {
            System.out.println(token.charAt(1));
            long id = Long.parseLong(String.valueOf(token.charAt(1)));
            map.remove(id);
            return true;
        } else {
            throw new RuntimeException("Token not remove");
        } } catch (NullPointerException e){
            throw new RuntimeException(e);
        }
    }

    private String getFullName(Long id){
        try {
            String url= "http://Account-Service/profile/getName/" + id;
            ResponseEntity<String> resp = restTemplate.getForEntity( url, String.class);
            if (resp.getStatusCode()== HttpStatus.OK){
                return resp.getBody();
            } else {
                return "Default Name";
            }
        } catch (NullPointerException e){
            throw new RuntimeException("Token_Name_Error");
        }
    }

}
