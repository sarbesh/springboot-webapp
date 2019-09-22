package com.sarbesh.AuthService.security;

import com.sarbesh.AuthService.model.User;
import com.sarbesh.AuthService.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static java.lang.System.*;

public class Authentication {
    private static HashMap<Long,String> map= new HashMap<Long, String>();
    @Autowired
    private static RestTemplate restTemplate;

    public static String generateToken(User usr){
        out.println("In Token");
        long id = usr.getId();
        String name;
        ResponseEntity<String> resp = restTemplate.getForEntity("http://localhost:8091/profile/getName/"+id, String.class);
        if (resp.getStatusCode()== HttpStatus.OK){
            name = resp.getBody();
        } else {
            name = "Default Name";
        }
        String token = id + "." + name + "." + usr.getEmail();
        out.println(token);
        if (map.isEmpty()||map.get(id).equals(token)){
            map.put(id,token);
        } else {
            throw new RuntimeException("User has an session active");
        }
        return token;
    }

}
